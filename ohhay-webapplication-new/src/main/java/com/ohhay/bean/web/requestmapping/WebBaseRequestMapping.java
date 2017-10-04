package com.ohhay.bean.web.requestmapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.OhhayViewer;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.M920MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.core.utils.SessionConstant;
import com.ohhay.other.entities.ItemTabN750;
import com.ohhay.other.mysql.service.N750Service;

/**
 * @author ThoaiNH
 * create Oct 7, 2015
 */
public class WebBaseRequestMapping {
	private static Logger log = Logger.getLogger(WebBaseRequestMapping.class);
	@Autowired
	TemplateService templateMGService;
	@Autowired
	M900MGService m900mgService;
	@Autowired
	M920MGService m920mgService;

	/**
	 * set up caching role for HttpServletResponse
	 */
	protected void setUpHttpRespone(HttpServletResponse httpResponse) {
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
		httpResponse.setDateHeader("Expires", 0); // Proxies
	}

	/**
	 * load profile into M900 return null if profile private
	 */
	protected M900MG loadM900(String phoneRequest, String userLoginCookieInfo) {
		M900MGService m900mgService = (M900MGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
		boolean privateCase = false;// load web private if md5 phone
		// find normal phone
		M900MG m900mg = m900mgService.loadUserProfile(ApplicationHelper
				.convertToMD5(phoneRequest));
		// find md5 phone
		if (m900mg == null) {
			privateCase = true;
			m900mg = m900mgService.loadUserProfile(phoneRequest);
		}
		// md5 phone private
		if (m900mg != null) {
			// clear private information
			m900mg.getListKeyWord().clear();
			// load history
			m900mgService.loadHistory(m900mg);
			// private
			if (m900mg.getMn906() == 0) {
				// neu chinh chu ma private thi van hien thi sdt va private
				// nhung co link md5 thi van dc vao
				Q100 q100 = AuthenticationHelper.getUserLogin();
				if ((q100 != null && q100.getOv101().equals(m900mg.getMv907()))
						|| privateCase == true)
					return m900mg;
				else
					return null;
			}
			else
				return m900mg;
		}
		else {
			ApplicationHelper.setSession(SessionConstant.CURRENT_M900, null);
		}
		return null;
	}

	/**
	 * create server link of web request
	 */
	protected void prepareUserLinkAW(M900MG m900mg) {
		ApplicationHelper.setSession(SessionConstant.CURRENT_M900, m900mg);
		TemplateRule.OHHAY_SERVER_LINK_VALUE = m900mg.getImgLinkCloudFront();
	}
	/**
	 * prepare model
	 */
	protected void prepareModel(ModelMap model, M900MG m900mg, Q100 q100, String languageCodeRequest) {
		model.addAttribute("m900mg", m900mg);
		model.addAttribute("userLogin", q100);
		model.addAttribute("currentWebLanguage", MVCResourceBundleUtil.getCurrentLocale().toString());
	}
	/**
	 * create o!hay viewer infor by ip location and store to session
	 * @param languageCode
	 * @param request
	 * @return
	 */
	protected String renderLanguageByIp(String languageCode, HttpServletRequest request) {
		try {
			if (languageCode == null) {
				//set language by language user choose before
				if(ApplicationHelper.getSession(SessionConstant.USER_SYS_LANGUAGE) != null)
				{
					String userCurrentLang = ApplicationHelper.getSession(SessionConstant.USER_SYS_LANGUAGE).toString();
					return ApplicationHelper.getRedirectString("?language="+userCurrentLang);
				}
				//set language by ip location
				else {
					OhhayViewer ohhayViewer = getOhhayViewer(request);
					if (ohhayViewer != null && ohhayViewer.getOhhayLangCode() != null)
						return ApplicationHelper.getRedirectString("?language="+ ohhayViewer.getOhhayLangCode());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * @param request
	 * @return
	 */
	protected OhhayViewer getOhhayViewer(HttpServletRequest request) {
		if (ApplicationHelper.getSession(SessionConstant.OHHAY_VIEWER) == null) {
			/*
			 * get viewer location info
			 */
			OhhayViewer ohhayViewer = ApplicationHelper.getInfoFromRequest(request);
			String ipAddress = ApplicationHelper.getIpAddress(request);
			log.info("--ip:" + ipAddress);
			N750Service n750Service = (N750Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N750);
			List<ItemTabN750> listItemTabN750 = n750Service.listOfTabN750(ipAddress);
			if(listItemTabN750 != null && listItemTabN750.size() > 0){
				ItemTabN750 itemTabN750 = listItemTabN750.get(0);
				ohhayViewer.setCountryCallingCode(itemTabN750.getLabel());
				ohhayViewer.setCountryCode(itemTabN750.getLabel2());
				ohhayViewer.setCountryName(itemTabN750.getLabel1());
				ohhayViewer.setLanguageCode(itemTabN750.getLabel4());
				ohhayViewer.setLanguageName(itemTabN750.getLabel3());
				ohhayViewer.setRegionCode(itemTabN750.getLabel5());
				log.info("--ohay viewer:"+ohhayViewer);
			}
			ohhayViewer.setIp(ipAddress);
			ApplicationHelper.setSession(SessionConstant.OHHAY_VIEWER, ohhayViewer);
			return ohhayViewer;
		}
		else
			return (OhhayViewer) ApplicationHelper.getSession(SessionConstant.OHHAY_VIEWER);
	}
}
