package com.ohhay.bean.web.requestmapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.entities.BonEvoAccount;
import com.ohhay.core.entities.OhhayViewer;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.AdminService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.SessionConstant;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
/**
 * @author ThoaiNH
 * create Dec 31, 2015
 * web setting and account info request mapping
 */
@Controller
@Scope("prototype")
public class SettingBean extends WebBaseRequestMapping {
	private static Logger log = Logger.getLogger(SettingBean.class);
	@Autowired
	private AdminService adminService;
	/**
	 * @param request
	 * @param model
	 * @param httpResponse
	 * @return
	 */
	@RequestMapping(value = { "account"}, headers="Host="+ApplicationConstant.HOST, method = RequestMethod.GET)
	public String mapAccoungSetting(HttpServletRequest request, ModelMap model, HttpServletResponse httpResponse) {
		// log.info("--settting page:"+pathOne);
		/*
		 * 1) setup HttpServletResponse
		 */
		setUpHttpRespone(httpResponse);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			model.addAttribute("m900mg", q100.getM900mg());
			model.addAttribute("userLogin", q100);
			OhhayViewer ohhayViewer = (OhhayViewer) ApplicationHelper.getSession(SessionConstant.OHHAY_VIEWER);
			if (ohhayViewer != null)
				model.addAttribute("ohhayViewer", ohhayViewer);
			model.addAttribute("ohhayViewer", ohhayViewer);
			/*
			 * 2) load all account social
			 */
			log.info("---listBonevoAccount:"+q100.getPo100()+","+q100.getQv101()+","+q100.getQv101());
			List<BonEvoAccount> listBonevoAccount = adminService.listOfTabAccounts(q100.getPo100(), q100.getQv101(), q100.getQv101());
			for(BonEvoAccount bonEvoAccount: listBonevoAccount){
				if(bonEvoAccount.getKonto().equalsIgnoreCase("fb"))
					model.addAttribute("fbAccount", bonEvoAccount);
				else if(bonEvoAccount.getKonto().equalsIgnoreCase("gg"))
					model.addAttribute("ggAccount", bonEvoAccount);
			}
			/*
			 * 3) check account create by social, first time using account page -> must be set BONEVO password
			 */
			int socialFirstVisit = 0;
			if(listBonevoAccount != null && listBonevoAccount.size() == 1){
				BonEvoAccount bonEvoAccount = listBonevoAccount.get(0);
				if(bonEvoAccount.getEmail().equals(q100.getQv101()) && bonEvoAccount.getPass().equals(q100.getQv102())){
					socialFirstVisit = 1;
				}
			}
			log.info("--socialFirstVisit:"+socialFirstVisit);
			model.addAttribute("socialFirstVisit", socialFirstVisit);
			return OhhayPagesName.EVO_PAGE_ACCOUNT_INFO;
		}
		return ApplicationHelper.getRedirectString("/login");
	}
	
	/**
	 * @param request
	 * @param pathOne
	 * @param model
	 * @param httpResponse
	 * @return
	 */
	@RequestMapping(value = {"websetting/{pathOne:.*}" }, headers="Host="+ApplicationConstant.HOST, method = RequestMethod.GET)
	public String mapWebSetting(HttpServletRequest request, @PathVariable(value = "pathOne") String pathOne, ModelMap model, HttpServletResponse httpResponse) {
		/*
		 * 1) setup HttpServletResponse
		 */
		setUpHttpRespone(httpResponse);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			try {
				int webId = Integer.parseInt(pathOne);
				E400MG e400mg = templateMGService.findDocument(q100.getPo100(), E400MG.class, new QbCriteria(QbMongoFiledsName.ID, webId), new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()));
				if(e400mg != null){
					model.addAttribute("m900mg", q100.getM900mg());
					model.addAttribute("userLogin", q100);
					model.addAttribute("e400mg", e400mg);
					OhhayViewer ohhayViewer = (OhhayViewer) ApplicationHelper.getSession(SessionConstant.OHHAY_VIEWER);
					if (ohhayViewer != null)
						model.addAttribute("ohhayViewer", ohhayViewer);
					model.addAttribute("draftPageThumb", null);
					return OhhayPagesName.EVO_PAGE_WEBSETTING;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return ApplicationHelper.getRedirectString("/login");
	}
}
