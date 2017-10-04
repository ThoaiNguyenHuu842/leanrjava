package com.ohhay.bean.other;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.SessionConstant;
import com.ohhay.other.mysql.service.O100Service;
import com.ohhay.service.CreateAccountService;

/**
 * @author ThoaiNH
 * create Dec 16, 2015
 */
@Controller
@Scope("prototype")
public class PublicServiceBean {
	private static Logger log = Logger.getLogger(PublicServiceBean.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	TemplateService templateMGService;
	/**
	 * map link confirm email
	 */
	@RequestMapping(value = {"publicservice/emailconfirm"}, method = RequestMethod.GET)
	public String emailconfirm(@CookieValue(value = SessionConstant.USER_LOGIN, required= false) String userLoginCookieInfo,HttpServletRequest request, ModelMap model, HttpServletResponse httpResponse,
			@RequestParam(required = true, value = "fo100") int fo100,
			@RequestParam(required = true, value = "email") String email){
		String returnEmail = null;
		int kq = 0;
		try{
			log.info("---updateTabO100Confirm:"+email+","+fo100+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
			O100Service o100Service = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
			returnEmail = o100Service.updateTabO100Confirm(email.trim(), fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("---returnEmail:"+returnEmail);
			/*
			 * 1) save to mongo new email
			 */
			if(returnEmail !=null && returnEmail.length() > 0){
				M900MG m900mg = templateMGService.findDocumentById(fo100, fo100, M900MG.class);
				if(m900mg != null){
					/*
					 * 2) update to db user
					 */
					m900mg.setMn909(1);
					m900mg.setMn919(1);//on flag confirmed email
					m900mg.setMv903(AESUtils.encrypt(returnEmail));
					m900mg.setMv921("");
					kq = templateMGService.saveDocument(fo100, m900mg, QbMongoCollectionsName.M900);
					/*
					 * 3) update piepme key
					 *  update ThoaiNH, 20/07/2016 
					 */
					if(m900mg.getMv925() != null)
					{
						String piepmeKey = m900mg.getMv925().substring(0, (int)m900mg.getMv925().length()*2/3);
						o100Service.updateTabO100Key(m900mg.getMv925(), piepmeKey, fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
					}
					/*
					 * 4) update center db
					 */
					M900MG m900mgCenter = templateMGService.findDocumentById(0, fo100, M900MG.class);
					m900mgCenter.setMn909(1);
					m900mgCenter.setMn919(1);//on flag confirmed email
					m900mgCenter.setMv903(AESUtils.encrypt(returnEmail));
					m900mgCenter.setMv921("");
					kq = templateMGService.saveDocument(0, m900mgCenter, QbMongoCollectionsName.M900);
					/*
					 * 5) Send thank you email
					 */
					try{
						CreateAccountService createOhhayAccountService = (CreateAccountService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
						createOhhayAccountService.sendMailRegisSuccess(m900mg);
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		log.info("---kq:"+kq);
		log.info("--returnEmail:"+returnEmail);
		if(kq > 0)
		{
			model.addAttribute("userNameRegis", returnEmail);
			return OhhayPagesName.OHHAY_PAGE_LOGIN;
		}
		else
			return OhhayPagesName.OHHAY_PAGE_404;
	}
	/**
	 * ThoaiNH 22/07/2016
	 * map link confirm register piepme with exists BONEVO account
	 */
	@RequestMapping(value = {"publicservice/emailconfirmUsingPiepme"}, method = RequestMethod.GET)
	public String emailconfirmUsingPiepme(@CookieValue(value = SessionConstant.USER_LOGIN, required= false) String userLoginCookieInfo,HttpServletRequest request, ModelMap model, HttpServletResponse httpResponse,
			@RequestParam(required = true, value = "fo100") int fo100,
			@RequestParam(required = true, value = "email") String email){
		String returnEmail = null;
		int kq = 0;
		try{
			CreateAccountService createAccountService = (CreateAccountService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
			kq = 1; // createAccountService.createAccountPiepmeWithExistsingBonevo(fo100);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		log.info("---kq:"+kq);
		log.info("--returnEmail:"+returnEmail);
		if(kq > 0)
		{
			model.addAttribute("userNameRegis", returnEmail);
			return OhhayPagesName.OHHAY_PAGE_LOGIN;
		}
		else
			return OhhayPagesName.OHHAY_PAGE_404;
	}
}
