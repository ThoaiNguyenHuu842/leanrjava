package com.ohhay.rest.bonevo;

import java.util.ArrayList;
import java.util.List;

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
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.AccountRegisCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.utils.AESUtilsPiepme;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.entities.O100;
import com.ohhay.other.mysql.service.O100Service;
import com.ohhay.service.CreateAccountService;

/**
 * @author ThoaiNH
 * create: 08/08/2015
 * register webservice
 */
@Path("o100WebService")
public class O100WebService {
	private static Logger log = Logger.getLogger(O100WebService.class);
	/*
	 * validate phone number khi dang ky tai khoan
	 */
	@GET
	@Path("validatePhoneNumber")
	@Produces("application/json")
	public String validatePhoneNumber(@QueryParam("phone") String phoneNumber) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			QbCriteria criteria = new QbCriteria("MV907", phoneNumber);
			M900MG m900mg = templateMGService.findDocument(0, M900MG.class, criteria);
			if (m900mg != null)
				return QbRestUtils.convertToJsonStringForFunc(0);
			else
				return QbRestUtils.convertToJsonStringForFunc(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * dang ky tai khoan
	 */
	@POST
	@Path("ohayInserttabO100")
	@Produces("application/json")
	public String ohayInserttabO100(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			AccountRegisCriteria accountRegisCriteria = new AccountRegisCriteria();
			accountRegisCriteria.setfName("first name");
			accountRegisCriteria.setlName("last name");
			String ov101 = jsonObject.get("ov101").toString();
			accountRegisCriteria.setPhone(ov101);
			String ov103 = jsonObject.get("ov103").toString();
			accountRegisCriteria.setCountryCode(ov103);
			String qv102 = jsonObject.get("qv102").toString();
			accountRegisCriteria.setPassWord(qv102);			
			int fc800 = Integer.parseInt(jsonObject.get("fc800").toString());
			accountRegisCriteria.setTemplateId(fc800);
			int fd000 = Integer.parseInt(jsonObject.get("fd000").toString());
			accountRegisCriteria.setFd000(fd000);
			String languageCode = jsonObject.get("languagecode").toString();//label 4
			accountRegisCriteria.setLanguageCode(languageCode);
			String languageName = jsonObject.get("languagename").toString();//label 3
			accountRegisCriteria.setLanguageName(languageName);
			String region = jsonObject.get("region").toString();//label 1
			accountRegisCriteria.setRegion(region);
			String mv903AES = jsonObject.get("mv903").toString();
			String mv903= AESUtils.decrypt(mv903AES);
			accountRegisCriteria.setEmail(mv903);
			log.info("---mv903:"+mv903);
			M900MGService m900mgService = (M900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
			if(m900mgService.emailIsExists(mv903) == true)
				return QbRestUtils.convertToJsonStringForFunc(-5);
			CreateAccountService createOhhayAccountService = (CreateAccountService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
			M900MG m900mg = createOhhayAccountService.createAccount(accountRegisCriteria);
			//save email
			m900mg.setMv903(mv903AES);
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.saveDocument(0, m900mg, QbMongoCollectionsName.M900);
			// auto login
			if (m900mg != null) {
				Q100Service q100Service = (Q100Service) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_Q100);
				log.info("--qhayCheckTabQ100:" + ov101 + ","
						+ ApplicationHelper.convertToMD5(qv102) +","
						+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				Q100 q100 = q100Service
						.qhayCheckTabQ100(ov101,ApplicationHelper.convertToMD5(qv102), ApplicationConstant.PVLOGIN_ANONYMOUS);
				q100.setM900mg(m900mg);
				List<Q100> list = new ArrayList<Q100>();
				list.add(q100);
				return QbRestUtils.convertToJsonStringForProc(list);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return QbRestUtils.getErrorStatus();
	}

	/*
	 * 
	 */
	@GET
	@Path("ohayListOfTabO100se")
	@Produces("application/json")
	public String ohayListOfTabO100se(@QueryParam("pvSearch") String pvSearch, @QueryParam("ov103") String ov103) {
		try {
			O100Service o100Servcie = (O100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_O100);
			List<O100> list = new ArrayList<O100>();
			log.info("----qhayListTabQ100:" + ov103 + "," + pvSearch
					+ "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
			list = o100Servcie
					.qhayListTabQ100(ov103, pvSearch, ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("---list size:" + list.size());
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("updateTabO100Key")
	@Produces("application/json")
	public String updateTabO100Key(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			String uuid = jsonObject.get("uiid").toString();
			String nickName = jsonObject.get("nickName").toString();
			String securityNumber = jsonObject.get("securityNumber").toString();
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocument(fo100, M900MG.class, new QbCriteria("MV924", nickName+securityNumber));
			/*
			 * check nickname + security number is unique
			 */
			if(m900mg != null)
				return QbRestUtils.convertToJsonStringForFunc(0);
			else
			{
				/*
				 * update key in MYSQL
				 */
				O100Service o100Servcie = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
				String piepmeKeyLogin = AESUtilsPiepme.createPiepmeKey(uuid, fo100);
				String piepmeKey = piepmeKeyLogin.substring(0, (int)piepmeKeyLogin.length()*2/3);
				o100Servcie.updateTabO100Key(piepmeKeyLogin, piepmeKey, fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * update key in mongo
				 */
				templateService.updateOneField(fo100, M900MG.class, fo100, "MV924", nickName + securityNumber, null);
				templateService.updateOneField(fo100, M900MG.class, fo100, "MV925", piepmeKey, null);
				return QbRestUtils.convertToJsonStringForFunc(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return QbRestUtils.getErrorStatus();
	}
}
