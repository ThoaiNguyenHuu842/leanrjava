package com.ohhay.rest.bonevo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.mongo.service.C900MGService;

/**
 * @author thoai nguyen
 *  create: 18/03/2015
 *  web edit function: change color, change template...
 */
@Path("webToolWebService")
public class WebToolWebService {
	private static Logger log = Logger.getLogger(WebToolWebService.class);
	/*
	 * update webcolor by url
	 */
	@POST
	@Path("updateWebColorByUrl")
	@Produces("application/json")
	public String updateWebColorByUrl(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String currentUrl = jsonObject.get("url").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			String color = jsonObject.get("color").toString();
			log.info("---changeTemplateC400:"+fo100+","+currentUrl+","+color);
			C900MGService c900mgService = (C900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C900MG);
			int kq = c900mgService.updateWebColorByUrl(currentUrl, fo100, color);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/*
	 * get web color
	 */
	@POST
	@Path("getWebColorByUrl")
	@Produces("application/json")
	public String getWebColorByUrl(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String currentUrl = jsonObject.get("url").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			C900MGService c900mgService = (C900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C900MG);
			String color = null;
			OhhayWebBase ohhayWebBase = c900mgService.getWebByUrl(currentUrl, fo100);
			if(ohhayWebBase != null)
				color = ohhayWebBase.getCv807();
			return QbRestUtils.convertToJsonStringForFunc(color);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/*
	 * create: 17/08/2015
	 * get web profile
	 */
	@POST
	@Path("getWebProfile")
	@Produces("application/json")
	public String getWebProfile(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String currentUrl = jsonObject.get("url").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			C900MGService c900mgService = (C900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C900MG);
			OhhayWebBase ohhayWebBase = c900mgService.getWebByUrl(currentUrl, fo100);
			if(ohhayWebBase != null)
			{
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
				if(ohhayWebBase.getN950mg() != null && m900mg != null)
				{
					//set full url friendly
					ohhayWebBase.getN950mg().setNv966(ohhayWebBase.getFriendlyUrl());
					//set full feature img
					ohhayWebBase.getN950mg().setNv965(m900mg.getImgLinkCloudFront()+ohhayWebBase.getN950mg().getNv965());
				}
				return QbRestUtils.convertToJsonStringForProc(ohhayWebBase.getN950mg());
			}
			return QbRestUtils.convertToJsonStringForFunc(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
}
