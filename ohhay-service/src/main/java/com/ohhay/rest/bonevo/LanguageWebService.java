package com.ohhay.rest.bonevo;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.LanguageService;
import com.ohhay.web.core.entities.mongo.web.C400MG;

/**
 * @author ThoaiNH
 *
 */
@Path("languageWebService")
public class LanguageWebService {
	private static Logger log = Logger.getLogger(LanguageWebService.class);
	/*
	 * add language
	 */
	@POST
	@Path("addLanguage")
	@Produces("application/json")
	public String addLanguage(String postString) {
		try {
			LanguageService languageMGService = (LanguageService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
			JSONObject jsonObject = new JSONObject(postString);
			String languageCode = jsonObject.get("languageCode").toString();
			String languageText = jsonObject.get("languageText").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			LanguageMG languageMG = new LanguageMG();
			languageMG.setCode(languageCode);
			languageMG.setText(languageText);
			log.info("---addLanguage:"+languageMG.getCode()+","+languageMG.getText()+","+fo100);
			int kq = languageMGService.addLanguage(languageMG, fo100, null, true);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/*
	 * remove language
	 */
	@POST
	@Path("removeLanguage")
	@Produces("application/json")
	public String removeLanguage(String postString) {
		try {
			LanguageService languageMGService = (LanguageService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
			JSONObject jsonObject = new JSONObject(postString);
			String languageCode = jsonObject.get("languageCode").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			LanguageMG languageMG = new LanguageMG();
			languageMG.setCode(languageCode);
			log.info("---removeLanguage:"+languageMG.getCode()+","+fo100);
			int kq = languageMGService.removeLanguage(languageMG, fo100);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/*
	 * get list languages
	 */
	@GET
	@Path("getListC500")
	@Produces("application/json")
	public String getListC500(@QueryParam("fo100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			QbCriteria criteria = new QbCriteria(QbMongoFiledsName.FO100, fo100);
			C400MG c400mg = templateService
					.findDocument(fo100, C400MG.class, criteria);
			if(c400mg != null)
				return QbRestUtils.convertToJsonStringForProc(c400mg.getListC500mg());	
			else
				return QbRestUtils.getErrorStatus();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
