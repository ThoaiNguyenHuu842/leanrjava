package com.ohhay.bean.setting;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
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
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.RegexConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.ChangePassCriteria;
import com.ohhay.core.criteria.EmailSMTPCriteria;
import com.ohhay.core.criteria.M900MGCriteria;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.entities.M300;
import com.ohhay.core.entities.N100;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.core.utils.SessionConstant;
import com.ohhay.other.api.service.M350OrelService;
import com.ohhay.other.mysql.service.D000Service;
import com.ohhay.other.mysql.service.M300Service;
import com.ohhay.other.mysql.service.N100Service;

/**
 * @author ThoaiNH setting profile all funciton
 */
@Controller
@Scope("prototype")
public class SettingProfileBean {
	private static Logger log = Logger.getLogger(SettingProfileBean.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	TemplateService templateMGService;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_M900MG)
	M900MGService m900mgService;

	/**
	 * load list job select update: 17/07/2015 bo sung load danh sach theo ngon
	 * ngu nguoi dung - ThoaiNH
	 */
	@RequestMapping(value = "/setting.loadListD000", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListD000() {
		JacksonResponse jsonResponse = new JacksonResponse();
		D000Service d000Service = (D000Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_D000);
		log.info("---combTabD000:" + MVCResourceBundleUtil.getCurrentLocale().getLanguage() + ","
				+ ApplicationConstant.PVLOGIN_ANONYMOUS);
		List<ComtabItem> listComtabItems = d000Service.combTabD000(
				MVCResourceBundleUtil.getCurrentLocale().getLanguage(), ApplicationConstant.PVLOGIN_ANONYMOUS);
		// set selected job
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			for (ComtabItem comtabItem : listComtabItems) {
				if (q100.getM900mg().getM920MG().getVal() == comtabItem.getVal()) {
					comtabItem.setVal2(1);// dung tam thuoc tinh val2
					break;
				}
			}
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(listComtabItems);
		return jsonResponse;
	}

	/**
	 * change avarta
	 */
	@SuppressWarnings("restriction")
	@RequestMapping(value = "/setting.saveAvarta", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse saveAvarta(@ModelAttribute("avartaBase64") String avartaBase64,
			BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (!result.hasErrors() && q100 != null) {
			int kq = m900mgService.saveAvarta(avartaBase64, q100.getM900mg());
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}

	/**
	 * change password
	 */
	@RequestMapping(value = "/setting.changePassword", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse changePassword(
			@ModelAttribute("ChangePassCiteria") @Valid ChangePassCriteria changePassCiteria, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (!result.hasErrors() && q100 != null) {
			int kq = m900mgService.changePassWord(changePassCiteria, q100.getQv102(), q100.getQv101());
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}

	/**
	 * save account
	 */
	@RequestMapping(value = "/setting.saveAccount", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse saveAccount(
			@ModelAttribute("m900mgCriteria") @Valid M900MGCriteria m900mgCriteria, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (!result.hasErrors() && q100 != null) {
			int kq = m900mgService.saveAccount(m900mgCriteria, q100);
			if (kq == -3) {
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
				List<ObjectError> list = new ArrayList<ObjectError>();
				list.add(new ObjectError("MD904", "Vui lòng nhập đúng định dạng ngày (mm/dd/yyyy)"));
				jsonResponse.setResult(list);
				return jsonResponse;
			}
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}

	/**
	 * change email
	 */
	@RequestMapping(value = "/setting.changeEmail", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse changeEmail(@ModelAttribute("newEmail") String newEmail,
			@ModelAttribute("passwordConfirm") String passwordConfirm, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (!result.hasErrors() && q100 != null) {
			log.info("---changeEmail:" + q100.getPo100() + "," + q100.getQv101() + "," + q100.getQv102() + ","
					+ passwordConfirm + "," + newEmail);
			int kq = m900mgService.changeEmail(q100.getPo100(), q100.getQv101(), q100.getQv102(), passwordConfirm,
					newEmail);
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}

	/**
	 * send email again
	 */
	@RequestMapping(value = "/setting.sendEmailAgain", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse sendEmailAgain(@ModelAttribute("passwordConfirm") String passwordConfirm,
			BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (!result.hasErrors() && q100 != null) {
			String mail = "";
			if (q100.getM900mg().getMv921() != null) {
				mail = q100.getM900mg().getMv921();
			} else {
				mail = q100.getM900mg().getMv903();
			}
			log.info("---sendmailagain:" + q100.getPo100() + "," + q100.getQv101() + "," + q100.getQv102() + ","
					+ q100.getQv102() + "," + mail);
			int kq = m900mgService.sendEmailAgain(q100.getPo100(), q100.getQv101(), q100.getQv102(), q100.getQv102(),
					mail);
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;

		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}

	/**
	 * change email
	 */
	public @ResponseBody JacksonResponse changeEmail(@ModelAttribute("mn951") int mn951,
			@ModelAttribute("mn952") int mn952, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (!result.hasErrors() && q100 != null) {
			int kq = 0;
			kq = templateMGService.updateOneField(q100.getPo100(), M900MG.class, q100.getPo100(), "ROLE_TOPIC.MN951", mn951,
					QbMongoFiledsName.ML948);
			kq = templateMGService.updateOneField(q100.getPo100(), M900MG.class, q100.getPo100(), "ROLE_TOPIC.MN952", mn952,
					QbMongoFiledsName.ML948);
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}

	/**
	 * save account one field date update: 14/04/2015
	 */
	@RequestMapping(value = "/setting.saveAccountOneField", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse saveAccountOneField(@ModelAttribute("fieldName") String fieldName,
			@ModelAttribute("value") String value, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (!result.hasErrors() && q100 != null) {
			int kq = 0;
			log.info("---FIELD NAME:" + fieldName + "; VALUE:" + value);
			if (fieldName.equals(QbMongoFiledsName.MV903))
				kq = m900mgService.saveAccountOneField(fieldName, AESUtils.encrypt(value), q100);
			else
				kq = m900mgService.saveAccountOneField(fieldName, value, q100);
			log.info("---kq:" + kq);
			if (kq == -3) {
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
				List<ObjectError> list = new ArrayList<ObjectError>();
				list.add(new ObjectError("MD904", "Vui lòng nhập đúng định dạng ngày (mm/dd/yyyy)"));
				jsonResponse.setResult(list);
				return jsonResponse;
			}
			else if (kq == -4) {
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
				List<ObjectError> list = new ArrayList<ObjectError>();
				list.add(new ObjectError("ERROR_1", MVCResourceBundleUtil.getResourceBundle("aci.title14")));
				jsonResponse.setResult(list);
				return jsonResponse;
			}
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}

	/**
	 * change smtp email
	 */
	@RequestMapping(value = "/setting.changeSMTPEmail", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse changesmtpemail(
			@ModelAttribute("saveSMTPEmail") @Valid EmailSMTPCriteria emailsmtpcriteria, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (!result.hasErrors() && q100 != null) {
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
			log.info("---nhayUpdateTabN100Smtp:" + emailsmtpcriteria.getNv119() + "," + emailsmtpcriteria.getNv120()
					+ "," + q100.getPo100() + "," + q100.getQv101());
			M350OrelService m350OrelService = (M350OrelService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_M350OREL);
			int kq1 = 0;
			try {
				kq1 = m350OrelService.checkEmail(emailsmtpcriteria.getNv120(),
						URLEncoder.encode(AESUtils.encrypt(emailsmtpcriteria.getNv119()), "UTF-8"),
						emailsmtpcriteria.getNv117(), emailsmtpcriteria.getNv116(), emailsmtpcriteria.getNv118());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean gmailIs = false;

			if (emailsmtpcriteria.getNv120().trim().indexOf("@gmail") > 0)
				gmailIs = true;
			log.info("---check gmail : " + gmailIs);
			log.info(kq1);
			if (kq1 > 0) {
				int kq = n100Service.nhayUpdateTabN100Smtp(emailsmtpcriteria.getNv116(), emailsmtpcriteria.getNv117(),
						emailsmtpcriteria.getNv118(), emailsmtpcriteria.getNv119(), emailsmtpcriteria.getNv120(),
						q100.getPo100(), q100.getQv101());
				if (kq > 0) {
					q100.setNv116(emailsmtpcriteria.getNv116());
					q100.setNv117(emailsmtpcriteria.getNv117());
					q100.setNv118(emailsmtpcriteria.getNv118());
					q100.setNv119(emailsmtpcriteria.getNv119());
					q100.setNv120(emailsmtpcriteria.getNv120());
					ApplicationHelper.setSession(SessionConstant.USER_LOGIN, q100);
				}
				jsonResponse.setResult(kq);
			} else {
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
				List<ObjectError> liObjectErrors = new ArrayList<ObjectError>();
				if (gmailIs)
					liObjectErrors.add(new ObjectError("email invaild",
							MVCResourceBundleUtil.getResourceBundle("setting.error_smtp_save")));
				else
					liObjectErrors.add(new ObjectError("email invaild",
							MVCResourceBundleUtil.getResourceBundle("setting.local.smtp")));
				jsonResponse.setResult(liObjectErrors);
			}
			return jsonResponse;

		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}

	/**
	 * auto fill author : Phongdt create : 31/7/2015
	 */
	@RequestMapping(value = "/setting.autoFillProfileEmail", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse autoFillProfileEmail(@ModelAttribute("email") String email) {
		JacksonResponse jsonResponse = new JacksonResponse();
		M300Service m300Service = (M300Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M300);
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<M300> list = m300Service.autoFillProfile(q100.getQv101(), email);
			if (list.size() > 0) {
				jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				jsonResponse.setResult(list);
				return jsonResponse;
			}
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;
	}

	/**
	 * load SMTP info
	 * @return
	 */
	@RequestMapping(value = "/setting.loadSMTP", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse loadSMTP() {
		JacksonResponse jsonResponse = new JacksonResponse();
		N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		List<N100> listN100 = n100Service.nhayListOfTabN100Smtp(q100.getPo100(), q100.getQv101());
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(listN100);
		return jsonResponse;
	}
	
	/**
	 * create 04/04/2016
	 * set password before using account info
	 */
	@RequestMapping(value = "/setting.setPasswordFirst", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse setPasswordFirst(
			@ModelAttribute("qv102New") String qv102New, @ModelAttribute("qv102ReNew") String qv102ReNew, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			int kq = 0;
			// confirm 2 new password
			if (!qv102New.trim().equals(qv102ReNew.trim()))
				kq = -1;
			else if(ApplicationHelper.validateRegex(qv102New,RegexConstant.PASSWORD_PATTERN) == false)
				kq = -3;
			else {
				Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
				kq = q100Service.qhayToolsUpdateTabQ100pwd(q100.getQv101(),ApplicationHelper.convertToMD5(qv102New), q100.getQv101());
				if(kq > 0){
					q100.setQv102(ApplicationHelper.convertToMD5(qv102New));
					ApplicationHelper.removeSession(SessionConstant.USER_LOGIN);
					ApplicationHelper.setSession(SessionConstant.USER_LOGIN, q100);
				}
			}
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}
}
