package com.ohhay.rest.bonevo;
/*package com.ohhay.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.AccountRegisCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.service.CreateAccountService;

*//**
 * @author ThoaiNH
 * create: 12/08/2015
 * create  account service
 *//*
@Path("createAccountWebService")
public class CreateAccountWebService {
	private static Logger log = Logger.getLogger(M900WebService.class);
	@POST
	@Path("createAccountA900")
	@Produces("application/json")
	public String createAccountA900(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String fName = jsonObject.get("fName").toString();
			String lName = jsonObject.get("lName").toString();
			String email = jsonObject.get("email").toString();//email da ma hoa AES
			String phone = jsonObject.get("phone").toString();
			String passWord = jsonObject.get("passWord").toString();//password da ma hoa md5
			String rePassWord = jsonObject.get("passWord").toString();
			String countryCode = jsonObject.get("countryCode").toString();//ma vung 84,...
			String languageCode = jsonObject.get("languageCode").toString();
			String languageName = jsonObject.get("languageName").toString();
			String region = jsonObject.get("region").toString();
			int templateId = Integer.parseInt(jsonObject.get("templateId").toString());
			int fd000 = Integer.parseInt(jsonObject.get("fd000").toString());
			String jobName = jsonObject.get("jobName").toString();
			String gender = jsonObject.get("gender").toString();
			AccountRegisCriteria accountRegisCriteria = new AccountRegisCriteria(fName, lName, email, phone, passWord, rePassWord, countryCode, languageCode, languageName, region, templateId, fd000, jobName, gender);
			log.info("--acount register:"+accountRegisCriteria);
			CreateAccountService createAccountService = (CreateAccountService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
			M900MG m900mg = createAccountService.createAccountA900(accountRegisCriteria);
			// auto login
			if (m900mg != null) {
				Q100Service q100Service = (Q100Service) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_Q100);
				log.info("--qhayCheckTabQ100Code:" + phone + "," + countryCode +","
						+ passWord +","
						+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				Q100 q100 = q100Service.qhayCheckTabQ100Code(phone, passWord, countryCode, phone);
				q100.setM900mg(m900mg);
				List<Q100> list = new ArrayList<Q100>();
				list.add(q100);
				return QbRestUtils.convertToJsonStringForProc(list);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
		return QbRestUtils.getErrorStatus();
	}
	
}
*/