package com.ohhay.rest.bonevo;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mongo.service.KeywordService;

/**
 * @author ThoaiNH
 *
 */
@Path("keyWordWebService")
public class KeywordWebService {
	private static Logger log = Logger.getLogger(KeywordWebService.class);
	/*
	 * insert key word
	 */
	@POST
	@Path("addNewKeyWord")
	@Produces("application/json")
	public String addNewKeyWord(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String keyword = jsonObject.get("keyword").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			KeywordService keywordService = (KeywordService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_KEYWORD);
			log.info("---addNewKeyWord:"+keyword+","+fo100);
			int kq = keywordService.addNewKeyWord(keyword, fo100);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	
	
	/*
	 * remove keyword
	 */
	@POST
	@Path("removeKeyWord")
	@Produces("application/json")
	public String removeKeyWord(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String keyword = jsonObject.get("keyword").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			KeywordService keywordService = (KeywordService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_KEYWORD);
			log.info("---removeKeyWord:"+keyword+","+fo100);
			int kq = keywordService.removeKeyWord(keyword, fo100);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/*
	 * get list keyword
	 */
	@GET
	@Path("getListKeyWord")
	@Produces("application/json")
	public String getListKeyWord(@QueryParam("fo100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
			if(m900mg != null)
				return QbRestUtils.convertToJsonStringForProc(m900mg.getListKeyWord());	
			else
				return QbRestUtils.getErrorStatus();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
