package com.ohhay.rest.bonevo;

import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.M350Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mysql.service.O050Service;

/**
 * @author ThoaiNH
 *
 */
@Path("o050WebService")
public class O050WebService {
	private static Logger log = Logger.getLogger(O050WebService.class);

	/*
	 * validate ma so dang ky SMS
	 */
	@GET
	@Path("validateRegisterCode")
	@Produces("application/json")
	public String validateRegisterCode(@QueryParam("code") String code, @QueryParam("phone") String phoneNumber, @QueryParam("ov061") String ov061) {
		try {
			if (code.trim().equalsIgnoreCase("10000001"))
				return QbRestUtils.convertToJsonStringForFunc(1);
			String processedPhone = ApplicationHelper
					.processDummiesPhones(phoneNumber, ov061);
			O050Service service = (O050Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_O050);
			int kq = service.ohayConfirmTabO050(processedPhone, code, ov061, ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("--ohayConfirmTaBo050:" + processedPhone + "," + code + ","+ ov061 + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * ohayInsertTabO050
	 */
	@POST
	@Path("ohayInsertTabO050")
	@Produces("application/json")
	public String ohayInsertTabO050(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String ov051 = jsonObject.get("ov051").toString();
			String ov061 = jsonObject.get("ov061").toString();
			O050Service service = (O050Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_O050);
			String kq = service
					.ohayInsertTabO050(ov051, ov061, ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("--ohayInsertTabO050:" + ov051 + "," + ov061 + ","
					+ ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/*
	 * sendMessage
	 */
	@POST
	@Path("sendSMSCodeRegister")
	@Produces("application/json")
	public String sendSMSCodeRegister(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String to = jsonObject.get("to").toString();
			String ov061 = jsonObject.get("ov061").toString();
			// String text = jsonObject.get("text").toString();
			// Q950Service q950Service = (Q950Service)
			// ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q950);
			// String key = "keyDemo";q950Service.org_tools_getiparam("OHAY",
			// "SMS","KEY", "0903387368");
			// Q950Service q950Service1 = (Q950Service)
			// ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q950);
			// String secret =
			// "secretDemo";q950Service1.org_tools_getiparam("OHAY",
			// "SMS","SECRET", "0903387368");
			// get code sms
			O050Service o050Service = (O050Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_O050);
			int i = o050Service.sendSMSCodeRegister(to, ov061);
			log.info("----------" + i);
			if(i == 0)
				return QbRestUtils.convertToJsonStringForFunc(ApplicationHelper.processDummiesPhones(to, ov061));
			else
				return QbRestUtils.convertToJsonStringForFunc(i);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/*
	 * ThoaiNH 23/07/2016
	 * send piepme OTP to email
	 */
	@POST
	@Path("sendPiepmeOTP")
	@Produces("application/json")
	public String sendPiepmeOTP(String postString) {
		try {
			log.info("--postString:"+postString);
			JSONObject jsonObject = new JSONObject(postString);
			String emailAES = jsonObject.get("emailAES").toString();
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocument(0, M900MG.class, new QbCriteria(QbMongoFiledsName.MV903, emailAES));
			if(m900mg != null){
				O050Service o050Service = (O050Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O050);
				String registerCode = o050Service.ohayInsertTabO050(AESUtils.decrypt(emailAES), "84", ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * send OTP to email
				 */
				String mailContent = "Your OTP code is "+registerCode;
				String emailSubject = "PiepMe - OTP code to signin";
				M350Service m350Service = (M350Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350);
				int kq = m350Service.sendMailTabM350Confirm(m900mg.getId(), AESUtils.decrypt(emailAES), null,  ApplicationHelper.decodeTopicString(emailSubject), mailContent, ApplicationConstant.PVLOGIN_ANONYMOUS);
				return QbRestUtils.convertToJsonStringForFunc(kq);
			}
			return QbRestUtils.convertToJsonStringForFunc(-1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/*
	 * validate ma so dang ky SMS
	 */
	@GET
	@Path("validatePiepmeCode")
	@Produces("application/json")
	public String validatePiepmeCode(@QueryParam("code") String code, @QueryParam("emailAES") String emailAES) {
		try {
			O050Service service = (O050Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_O050);
			log.info("---ohayConfirmTabO050:"+emailAES+","+code+","+"84"+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
			int kq = service.ohayConfirmTabO050(emailAES, code, "84", ApplicationConstant.PVLOGIN_ANONYMOUS);
			if(kq > 0)
			{
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				M900MG m900mg = templateService.findDocument(0, M900MG.class, new QbCriteria(QbMongoFiledsName.MV903, emailAES));
				if(m900mg != null && m900mg.getMv924() != null)
					kq = m900mg.getId();
				else
					kq = 0;
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
