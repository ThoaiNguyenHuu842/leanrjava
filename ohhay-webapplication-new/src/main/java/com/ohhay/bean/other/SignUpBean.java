package com.ohhay.bean.other;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.AccountEVORegisCriteria;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.User;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.other.mysql.service.D000Service;
import com.ohhay.service.CreateAccountService;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThoaiNH
 * create Dec 14, 2015
 */
@Controller
@Scope("prototype")
public class SignUpBean {
	private static Logger log = Logger.getLogger(SignUpBean.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	private WebLegoService webLegoService;
	/**
	 * load list job select when register
	 * update: 17/07/2015 bo sung load danh sach theo ngon ngu nguoi dung - ThoaiNH 
	 */
	@RequestMapping(value = "/signUpBean.loadListD000Regis", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse loadListD000Regis() {
		JacksonResponse jsonResponse = new JacksonResponse();
		D000Service d000Service = (D000Service) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_D000);
		log.info("---combTabD000Regis:"+MVCResourceBundleUtil.getCurrentLocale().getLanguage()+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
		List<ComtabItem> listComtabItems = d000Service.combTabD000Regis(MVCResourceBundleUtil.getCurrentLocale().getLanguage(), ApplicationConstant.PVLOGIN_ANONYMOUS);
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(listComtabItems);
		return jsonResponse;
	}
	/**
	 * @param accountRegisCriteria
	 * @param request
	 * @param response
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/signUpBean.registerAccountWithKub", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse registerAccountWithKub(@ModelAttribute(value = "account") AccountEVORegisCriteria accountRegisCriteria, HttpServletRequest request, HttpServletResponse response, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		if (!result.hasErrors()) {
			/*
			 * 1) auto login using singup info
			 */
			Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			String realEmail = new String(accountRegisCriteria.getEmail());
			User user = new User();
			user.setPhone(realEmail);
			user.setPassword(accountRegisCriteria.getPassWord());
			user.setRemember(1);
			user.setSrc(accountRegisCriteria.getRegisTypel());
			log.info("---qbonCheckTabQ100:"+user.getPhone()+","+ApplicationHelper.convertToMD5(user.getPassword())+","+user.getSrc()+","+ user.getSrc() + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
			Q100 q100 = null;
			try {
				q100 = q100Service.qbonCheckTabQ100(user.getPhone(), ApplicationHelper.convertToMD5(user.getPassword()), user.getSrc(), ApplicationConstant.PVLOGIN_ANONYMOUS);
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				log.info(e.getMessage());
			}
			/*
			 * 2) create landing page if login success
			 */
			if(q100 != null){
				AuthenticationHelper.onLoginSuccess(q100, user, response, true);
				jsonResponse.setResult(webLegoService.createKubLandingPage(q100.getPo100(), (int)accountRegisCriteria.getTemplateId(), accountRegisCriteria.getKubvideo()));
				jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				return jsonResponse;
			}
			else {
				/*
				 * 3) validate singup info
				 */
				accountRegisCriteria.setRegisTypel(AccountEVORegisCriteria.REGISTYPE_KUB);
				String mess = "";
				int kq = 0;
				if(accountRegisCriteria.getCountryCode().charAt(0) == '+')
					accountRegisCriteria.setCountryCode(accountRegisCriteria.getCountryCode().substring(1,accountRegisCriteria.getCountryCode().length()));
				log.info(accountRegisCriteria);
				CreateAccountService createOhhayAccountService = (CreateAccountService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
				kq = createOhhayAccountService.validateEVOAccount(accountRegisCriteria);
				if(kq == -1)
					mess  = MVCResourceBundleUtil.getResourceBundle("setting.changemail.error2");
				else if(kq == -2)
					mess =  MVCResourceBundleUtil.getResourceBundle("signup.error8");
				else if(kq == -3)
					mess = MVCResourceBundleUtil.getResourceBundle("setting.changepassword.message_error7");
				else if(kq == -4)
					mess = MVCResourceBundleUtil.getResourceBundle("setting.changepassword.message_error6");
				else if(kq == -5)
					mess = MVCResourceBundleUtil.getResourceBundle("setting.email.emailavailabel");
				else {
					if(kq == 1)
					{
						/*
						 * 4) register account
						 */
						if(accountRegisCriteria.getCountryCode().charAt(0) == '+')
							accountRegisCriteria.setCountryCode(accountRegisCriteria.getCountryCode().substring(1,accountRegisCriteria.getCountryCode().length()));
						log.info(accountRegisCriteria);
						//convert password to md5 before insert
						accountRegisCriteria.setPassWord(ApplicationHelper.convertToMD5(accountRegisCriteria.getPassWord()));
						accountRegisCriteria.setRePassWord(ApplicationHelper.convertToMD5(accountRegisCriteria.getRePassWord()));
						//convert email to AES before insert
						accountRegisCriteria.setEmail(AESUtils.encrypt(accountRegisCriteria.getEmail()));
						kq = createOhhayAccountService.createAccountWebEVO(accountRegisCriteria, response, request, LocaleContextHolder.getLocale().getLanguage());
						/*
						 * 5) auto confirm email and auto login
						 */
						if(kq > 0)
						{
							M900MG m900mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, M900MG.class, new QbCriteria(QbMongoFiledsName.MV903, accountRegisCriteria.getEmail()));
							if(m900mg != null){
								/*
								 * 5.1) update to index
								 */
								m900mg.setMn909(1);
								m900mg.setMn919(1);//on flag confirmed email
								m900mg.setMv903(accountRegisCriteria.getEmail());
								m900mg.setMv921("");
								kq = templateService.saveDocument(ApplicationConstant.FO100_SUPER_ADMIN, m900mg, QbMongoCollectionsName.M900);
								/*
								 * 5.2) update center db
								 */
								M900MG m900mgCenter = templateService.findDocumentById(0, m900mg.getId(), M900MG.class);
								m900mgCenter.setMn909(1);
								m900mgCenter.setMn919(1);//on flag confirmed email
								m900mgCenter.setMv903(accountRegisCriteria.getEmail());
								m900mgCenter.setMv921("");
								kq = templateService.saveDocument(0, m900mgCenter, QbMongoCollectionsName.M900);
								/*
								 * 5.3) Send thank you email
								 */
								try{
									createOhhayAccountService.sendMailRegisSuccess(m900mg);
								}
								catch(Exception ex){
									ex.printStackTrace();
								}
								/*
								 * 5.4) auto login
								 */
								log.info("--qbonCheckTabQ100:"+user.getPhone()+","+ApplicationHelper.convertToMD5(user.getPassword())+","+user.getSrc()+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
								q100 = q100Service.qbonCheckTabQ100(user.getPhone(), ApplicationHelper.convertToMD5(user.getPassword()), user.getSrc(), ApplicationConstant.PVLOGIN_ANONYMOUS);
								if(q100 != null){
									/*
									 * 5.5) create landing page
									 */
									AuthenticationHelper.onLoginSuccess(q100, user, response, true);
									jsonResponse.setResult(webLegoService.createKubLandingPage(q100.getPo100(), (int)accountRegisCriteria.getTemplateId(), accountRegisCriteria.getKubvideo()));
									jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
									return jsonResponse;
								}
							}
						}
						jsonResponse.setResult(kq);
						jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
						return jsonResponse;
					}
				}
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
				List<ObjectError> list = new ArrayList<ObjectError>();
				list.add(new ObjectError("Account",mess));
				jsonResponse.setResult(list);
				return jsonResponse;
			}
		
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		log.info("--error:" + result.getAllErrors().get(0).getDefaultMessage());
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}
	/**
	 * @param accountRegisCriteria
	 * @param request
	 * @param response
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/signUpBean.registerAccount", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse registerAccount(@ModelAttribute(value = "account") AccountEVORegisCriteria accountRegisCriteria, HttpServletRequest request, HttpServletResponse response, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		if (!result.hasErrors()) {
			accountRegisCriteria.setRegisTypel(AccountEVORegisCriteria.REGISTYPE_EVO);
			String mess = "";
			int kq = 0;
			if(accountRegisCriteria.getCountryCode().charAt(0) == '+')
				accountRegisCriteria.setCountryCode(accountRegisCriteria.getCountryCode().substring(1,accountRegisCriteria.getCountryCode().length()));
			log.info(accountRegisCriteria);
			CreateAccountService createOhhayAccountService = (CreateAccountService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
			kq = createOhhayAccountService.validateEVOAccount(accountRegisCriteria);
			if(kq == -1)
				mess  = MVCResourceBundleUtil.getResourceBundle("setting.changemail.error2");
			else if(kq == -2)
				mess =  MVCResourceBundleUtil.getResourceBundle("signup.error8");
			else if(kq == -3)
				mess = MVCResourceBundleUtil.getResourceBundle("setting.changepassword.message_error7");
			else if(kq == -4)
				mess = MVCResourceBundleUtil.getResourceBundle("setting.changepassword.message_error6");
			else if(kq == -5)
				mess = MVCResourceBundleUtil.getResourceBundle("setting.email.emailavailabel");
			else {
				if(kq == 1)
				{
					if(accountRegisCriteria.getCountryCode().charAt(0) == '+')
						accountRegisCriteria.setCountryCode(accountRegisCriteria.getCountryCode().substring(1,accountRegisCriteria.getCountryCode().length()));
					log.info(accountRegisCriteria);
					//convert password to md5 before insert
					accountRegisCriteria.setPassWord(ApplicationHelper.convertToMD5(accountRegisCriteria.getPassWord()));
					accountRegisCriteria.setRePassWord(ApplicationHelper.convertToMD5(accountRegisCriteria.getRePassWord()));
					//convert email to md5 before insert
					accountRegisCriteria.setEmail(AESUtils.encrypt(accountRegisCriteria.getEmail()));
					kq = createOhhayAccountService.createAccountWebEVO(accountRegisCriteria, response, request, LocaleContextHolder.getLocale().getLanguage());
					jsonResponse.setResult(kq);
					jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
					return jsonResponse;
				}
				jsonResponse.setResult(kq);
				jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				return jsonResponse;
			}
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			List<ObjectError> list = new ArrayList<ObjectError>();
			list.add(new ObjectError("Account",mess));
			jsonResponse.setResult(list);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		log.info("--error:" + result.getAllErrors().get(0).getDefaultMessage());
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}
}
