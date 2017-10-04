package com.ohhay.bean.setting;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.other.mysql.service.F100Service;
import com.ohhay.other.mysql.service.G100Service;

/**
 * @author ThoaiNH
 * create Apr 1, 2016
 * management all account: fb, google, evo
 */
@Controller
@Scope("prototype")
public class SettingAccountBean {
	private static Logger log = Logger.getLogger(SettingAccountBean.class);
	@Autowired
	private G100Service g100Service;
	@Autowired
	private F100Service f100Service;
	/*
	 * add google account
	 */
	@RequestMapping(value = "/setting.addGoogleAccount", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse addGoogleAccount(@RequestParam(required=true, value="gv101") String gv101,@RequestParam(required=true, value="gv102") String gv102) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null){
			log.info("---gbonInsertTabG100:"+q100.getPq100()+","+gv101+","+ApplicationHelper.convertToMD5(gv102)+","+q100.getQv101());
			jsonResponse.setResult(g100Service.gbonInsertTabG100(q100.getPq100(), gv101, ApplicationHelper.convertToMD5(gv102), q100.getQv101()));
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;
	}
	/*
	 * remove google account
	 */
	@RequestMapping(value = "/setting.removeGoogleAccount", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse removeGoogleAccount() {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null){
			log.info("---gbonStornoTabG100:"+q100.getPo100()+","+q100.getPq100()+","+q100.getQv101());
			jsonResponse.setResult(g100Service.gbonStornoTabG100(q100.getPo100(), q100.getPq100(), q100.getQv101()));
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;
	}
	/*
	 * add FACEBOOK account
	 */
	@RequestMapping(value = "/setting.addFacebookAccount", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse addFacebookAccount(@RequestParam(required=true, value="fv101") String fv101,@RequestParam(required=true, value="fv102") String fv102) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null){
			log.info("---gbonInsertTabG100:"+q100.getPq100()+","+fv101+","+ApplicationHelper.convertToMD5(fv102)+","+q100.getQv101());
			jsonResponse.setResult(f100Service.fbonInsertTabF100(q100.getPq100(), fv101, ApplicationHelper.convertToMD5(fv102), q100.getQv101()));
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;
	}
	/*
	 * remove FACEBOOK account
	 */
	@RequestMapping(value = "/setting.removeFacebookAccount", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse removeFacebookAccount() {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null){
			log.info("---fbonStornoTabF100:"+q100.getPo100()+","+q100.getPq100()+","+q100.getQv101());
			jsonResponse.setResult(f100Service.fbonStornoTabF100(q100.getPo100(), q100.getPq100(), q100.getQv101()));
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;
	}
}
