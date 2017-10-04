package com.ohhay.bean.other;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.RegexConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.AccountEVORegisCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.User;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.M350Service;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.core.utils.SessionManager;
import com.ohhay.service.CreateAccountService;
import com.ohhay.web.lego.entities.mongo.web.E400MG;

/**
 * @author ThoaiNH date create 05/08/2014
 */
@Controller
@Scope("prototype")
public class LoginBean {
	private static Logger log = Logger.getLogger(LoginBean.class);
	@Autowired
	private M900MGService m900mgService;
	@Autowired
	private M350Service m350Service;
	@Autowired
	private TemplateService templateService;
	/**
	 * service check user login for shop online date create 18/05/2016
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginBean.checkSessionExisted", method = RequestMethod.GET)
	public @ResponseBody String checkSessionExisted() {
		try {
			log.info("---loginBean.checkSessionExisted");
			Q100 q100 = AuthenticationHelper.getUserLogin();
			String result = "";
			if (q100 != null)
				result = AESUtils.encrypt(q100.getQv101()) + "##qb##" + AESUtils.encrypt(q100.getQv102()) + "##qb##" + AESUtils.encrypt(q100.getOv108());
			return QbRestUtils.convertToJsonStringForProc(result);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * update 28/07/2015 - ThoaiNH
	 * 
	 * @param user
	 * @param result
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/loginBean.login", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse login(@ModelAttribute("user") User user, BindingResult result, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		JacksonResponse jsonResponse = new JacksonResponse();
		if (result.hasErrors()) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(result.getAllErrors());
		} else {
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			log.info("---qbonCheckTabQ100:" + user.getPhone() + "," + ApplicationHelper.convertToMD5(user.getPassword()) + "," + user.getSrc() + "," + user.getSrc() + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
			Q100 q100 = q100Service.qbonCheckTabQ100(user.getPhone(), ApplicationHelper.convertToMD5(user.getPassword()), user.getSrc(), ApplicationConstant.PVLOGIN_ANONYMOUS);
			if (q100 != null) {
				// check email confirm before
				if (q100.getM900mg().getMv903() != null && q100.getM900mg().getMn919() == 1) {
					log.info(q100);
					log.info("--nv120:" + q100.getNv120());
					log.info("---user:" + user);
					AuthenticationHelper.onLoginSuccess(q100, user, response, true);

					jsonResponse.setResult(q100.getPo100());
					// ANPH: data token cross login - 16:40 - 7/6/2016
					String dataEnScrypted = AESUtils.encrypt(AESUtils.encrypt(q100.getQv101()) + "##qb##" + AESUtils.encrypt(user.getPassword()) + "##qb##" + AESUtils.encrypt("be"));
					jsonResponse.setResultString(dataEnScrypted);
					// ANPH: END
					jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				} else {
					jsonResponse.setResult(-1);
					jsonResponse.setResultString(MVCResourceBundleUtil.getResourceBundle("login.mess_mail_not_confirm"));
					jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				}
			} else {
				log.info("---login error");
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);

			}
		}
		return jsonResponse;
	}

	/**
	 * @param servletResponse
	 * @param httpServletRequest
	 * @return
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/loginBean.logoutWeb", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse logoutWeb(HttpServletResponse servletResponse, HttpServletRequest httpServletRequest) throws InterruptedException {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		try {
			log.info("---loginBean logout");
			// remove current user login map
			SessionManager.remove(ApplicationHelper.getHttpSession().getId());
			ApplicationHelper.getHttpSession().invalidate();
			// remove cookie
			Cookie[] cookies = httpServletRequest.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					cookies[i].setMaxAge(0);
					cookies[i].setDomain(".bonevo.net");
					servletResponse.addCookie(cookies[i]);
				}
			}
			// sleep while clear session and cookie
			Thread.sleep(1000);
			// lam tam hard code
			jsonResponse.setResult(1);
			// return redirect;
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			jsonResponse.setResult(0);
		}
		return jsonResponse;
	}
	/**
	 * login by social account
	 */
	@RequestMapping(value = "/loginBean.loginBySocial", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse loginBySocial(@ModelAttribute(value = "account") AccountEVORegisCriteria accountRegisCriteria, HttpServletResponse response, HttpServletRequest request, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		log.info(accountRegisCriteria);
		if (!result.hasErrors()) {
			/*
			 * 1) login
			 */
			Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			String realEmail = new String(accountRegisCriteria.getEmail());
			User user = new User();
			user.setPhone(realEmail);
			user.setPassword(accountRegisCriteria.getSocialId());
			user.setRemember(1);
			user.setSrc(accountRegisCriteria.getRegisTypel());
			log.info("---qbonCheckTabQ100:" + user.getPhone() + "," + ApplicationHelper.convertToMD5(user.getPassword()) + "," + user.getSrc() + "," + user.getSrc() + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
			Q100 q100 = null;
			try {
				q100 = q100Service.qbonCheckTabQ100(user.getPhone(), ApplicationHelper.convertToMD5(user.getPassword()), user.getSrc(), ApplicationConstant.PVLOGIN_ANONYMOUS);
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				log.info(e.getMessage());
			}
			if (q100 != null) {
				log.info(q100);
				AuthenticationHelper.onLoginSuccess(q100, user, response, true);
				jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				return jsonResponse;
			}
			/*
			 * 2/ auto register
			 */
			else {
				String mess = "";
				int kq = 0;
				if (accountRegisCriteria.getCountryCode().charAt(0) == '+')
					accountRegisCriteria.setCountryCode(accountRegisCriteria.getCountryCode().substring(1, accountRegisCriteria.getCountryCode().length()));
				log.info(accountRegisCriteria);
				CreateAccountService createOhhayAccountService = (CreateAccountService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
				kq = 1;
				/*
				 * 2.1/ Validate account
				 */
				if (ApplicationHelper.validateRegex(accountRegisCriteria.getEmail(), RegexConstant.EMAIL_PATTERN) == false) {
					kq = -1;
					mess = MVCResourceBundleUtil.getResourceBundle("setting.changemail.error2");
				} else if (m900mgService.emailIsExists(accountRegisCriteria.getEmail()) == true) {
					kq = -5;
					mess = MVCResourceBundleUtil.getResourceBundle("setting.email.emailavailabel");
				} else {
					/*
					 * 2.2) create account
					 */
					if (accountRegisCriteria.getCountryCode().charAt(0) == '+')
						accountRegisCriteria.setCountryCode(accountRegisCriteria.getCountryCode().substring(1, accountRegisCriteria.getCountryCode().length()));
					log.info(accountRegisCriteria);
					// convert password to md5 before insert
					accountRegisCriteria.setPassWord(ApplicationHelper.convertToMD5(accountRegisCriteria.getPassWord()));
					accountRegisCriteria.setRePassWord(ApplicationHelper.convertToMD5(accountRegisCriteria.getRePassWord()));
					// convert email to md5 before insert
					accountRegisCriteria.setEmail(AESUtils.encrypt(accountRegisCriteria.getEmail()));
					kq = createOhhayAccountService.createAccountWebEVO(accountRegisCriteria, response, request, LocaleContextHolder.getLocale().getLanguage());
					/*
					 * 2.3) auto login
					 */
					if (kq == 1) {
						log.info("---qbonCheckTabQ100:" + user.getPhone() + "," + ApplicationHelper.convertToMD5(user.getPassword()) + "," + user.getSrc() + "," + user.getSrc() + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
						q100 = q100Service.qbonCheckTabQ100(user.getPhone(), ApplicationHelper.convertToMD5(user.getPassword()), user.getSrc(), ApplicationConstant.PVLOGIN_ANONYMOUS);
						if (q100 != null) {
							/*
							 * 2.4) Send thank you email
							 */
							try {
								createOhhayAccountService.sendMailRegisSuccess(q100.getM900mg());
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							log.info(q100);
							AuthenticationHelper.onLoginSuccess(q100, user, response, true);
						}
					}
					jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
					return jsonResponse;
				}
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
				List<ObjectError> list = new ArrayList<ObjectError>();
				list.add(new ObjectError("Account", mess));
				jsonResponse.setResult(list);
				jsonResponse.setResult2(kq);
				return jsonResponse;
			}
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		log.info("--error:" + result.getAllErrors().get(0).getDefaultMessage());
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}
	/**
	 * login by social account trial
	 */
	@RequestMapping(value = "/loginBean.loginBySocialTrial", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse loginBySocialTrial(@ModelAttribute(value = "account") AccountEVORegisCriteria accountRegisCriteria, HttpServletResponse response, HttpServletRequest request, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		log.info(accountRegisCriteria);
		if (!result.hasErrors()) {
			/*
			 * 1) login
			 */
			Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			String realEmail = new String(accountRegisCriteria.getEmail());
			User user = new User();
			user.setPhone(realEmail);
			user.setPassword(accountRegisCriteria.getSocialId());
			user.setRemember(1);
			user.setSrc(accountRegisCriteria.getRegisTypel());
			log.info("---qbonCheckTabQ100:" + user.getPhone() + "," + ApplicationHelper.convertToMD5(user.getPassword()) + "," + user.getSrc() + "," + user.getSrc() + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
			Q100 q100 = null;
			try {
				q100 = q100Service.qbonCheckTabQ100(user.getPhone(), ApplicationHelper.convertToMD5(user.getPassword()), user.getSrc(), ApplicationConstant.PVLOGIN_ANONYMOUS);
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				log.info(e.getMessage());
			}
			if (q100 != null) {
				log.info(q100);
				//AuthenticationHelper.onLoginSuccess(q100, user, response, true);
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
				return jsonResponse;
			}
			/*
			 * 2/ auto register
			 */
			else {
				String mess = "";
				int kq = 0;
				if (accountRegisCriteria.getCountryCode().charAt(0) == '+')
					accountRegisCriteria.setCountryCode(accountRegisCriteria.getCountryCode().substring(1, accountRegisCriteria.getCountryCode().length()));
				log.info(accountRegisCriteria);
				CreateAccountService createOhhayAccountService = (CreateAccountService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
				kq = 1;
				/*
				 * 2.1/ Validate account
				 */
				if (ApplicationHelper.validateRegex(accountRegisCriteria.getEmail(), RegexConstant.EMAIL_PATTERN) == false) {
					kq = -1;
					mess = MVCResourceBundleUtil.getResourceBundle("setting.changemail.error2");
				} else if (m900mgService.emailIsExists(accountRegisCriteria.getEmail()) == true) {
					kq = -5;
					mess = MVCResourceBundleUtil.getResourceBundle("setting.email.emailavailabel");
				} else {
					/*
					 * 2.2) create account
					 */
					if (accountRegisCriteria.getCountryCode().charAt(0) == '+')
						accountRegisCriteria.setCountryCode(accountRegisCriteria.getCountryCode().substring(1, accountRegisCriteria.getCountryCode().length()));
					log.info(accountRegisCriteria);
					// convert password to md5 before insert
					accountRegisCriteria.setPassWord(ApplicationHelper.convertToMD5(accountRegisCriteria.getPassWord()));
					accountRegisCriteria.setRePassWord(ApplicationHelper.convertToMD5(accountRegisCriteria.getRePassWord()));
					// convert email to md5 before insert
					accountRegisCriteria.setEmail(AESUtils.encrypt(accountRegisCriteria.getEmail()));
					kq = createOhhayAccountService.createAccountWebEVO(accountRegisCriteria, response, request, LocaleContextHolder.getLocale().getLanguage());
					/*
					 * 2.3) auto login
					 */
					if (kq == 1) {
						log.info("---qbonCheckTabQ100:" + user.getPhone() + "," + ApplicationHelper.convertToMD5(user.getPassword()) + "," + user.getSrc() + "," + user.getSrc() + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
						q100 = q100Service.qbonCheckTabQ100(user.getPhone(), ApplicationHelper.convertToMD5(user.getPassword()), user.getSrc(), ApplicationConstant.PVLOGIN_ANONYMOUS);
						if (q100 != null) {
							/*
							 * 2.4) Send thank you email
							 */
							try {
								createOhhayAccountService.sendMailRegisSuccess(q100.getM900mg());
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							log.info(q100);
							AuthenticationHelper.onLoginSuccess(q100, user, response, true);
							/*
							 * ThoaiNH
							 * update 27/02/2017 new flow when registering successfully from trial site
							 * 2.5) get fo100, webid, web url data
							 */
							E400MG e400mg = templateService.findDocument(q100.getPo100(), E400MG.class, new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()));
							jsonResponse.setResult(q100.getPo100());
							if(e400mg != null){
								jsonResponse.setResult2(e400mg.getId());
								jsonResponse.setResultString(e400mg.getEv405());
							}
						}
					}
					jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
					return jsonResponse;
				}
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
				List<ObjectError> list = new ArrayList<ObjectError>();
				list.add(new ObjectError("Account", mess));
				jsonResponse.setResult(list);
				jsonResponse.setResult2(kq);
				return jsonResponse;
			}
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		log.info("--error:" + result.getAllErrors().get(0).getDefaultMessage());
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}

	/*
	 * Test Send email copy from ohhay 17/02/2016 ThoaiVt
	 */

	@RequestMapping(value = "/loginBean.sendEmailResetPass", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse sendEmailResetPass(@ModelAttribute("email") String email) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);

		try {
			if (ApplicationHelper.validateRegex(email.trim(), RegexConstant.EMAIL_PATTERN) == false)
				jsonResponse.setResult(-2);
			else {
				Q100Service q100service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
				String val = q100service.qhayToolsUpdatetabq100reset(email, ApplicationConstant.PVLOGIN_ANONYMOUS);

				if (val.equalsIgnoreCase("emailfalse"))
					jsonResponse.setResult(-1);
				else {
					jsonResponse.setResult(1);
					jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				}
			}
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/loginBean.saveResetPass", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse saveResetPass(@ModelAttribute("password") String password, @ModelAttribute("email") String email, @ModelAttribute("key") String key) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		try {

			if (ApplicationHelper.validateRegex(password, RegexConstant.PASSWORD_PATTERN) == false)
				jsonResponse.setResult(-1);
			else {
				M900MG m900mg = templateService.findDocument(0, M900MG.class, new QbCriteria(QbMongoFiledsName.MV903, AESUtils.encrypt(email)));
				Q100Service q100service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
				int val = q100service.qhayToolsUpdatetabq100repwd(email, ApplicationHelper.convertToMD5(password), m900mg.getMv920(), key, m900mg.getMv903Decrypt());
				if (val == 1) {
					jsonResponse.setResult(val);
					jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				} else
					jsonResponse.setResult(0);
			}
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * create 30/3/2016
	 * 
	 * @param email
	 * @param result
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/loginBean.resendConfirmEmail", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse resendConfirmEmail(@ModelAttribute("email") String email, BindingResult result, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		JacksonResponse jsonResponse = new JacksonResponse();
		if (result.hasErrors()) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(result.getAllErrors());
		} else {
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			log.info("---sendMailTabM350Check:" + email + "," + email);
			int kq = m350Service.sendMailTabM350Check(email, email);
			jsonResponse.setResult(kq);
		}
		return jsonResponse;
	}
}
