package com.ohhay.rest.bonevo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.DraftWebService;

/**
 * @author ThoaiNH
 * create: 121/12/2014
 * web draft servcie for mobile
 */
@Path("settingWebDraftWebService")
public class SettingWebDraftWebService {
	private static Logger log = Logger.getLogger(SettingWebDraftWebService.class);
	/**
	 *  apply draft to home 
	 */
	@POST
	@Path("applyNewTemplate")
	@Produces("application/json")
	public String applyNewTemplate(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String qv101 = jsonObject.get("qv101").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			DraftWebService draftWebMGService = (DraftWebService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_DRAFTWEBSERVICE);
			log.info("---applyNewTemplate:"+fo100+","+ qv101);
			int kq = draftWebMGService.applyNewTemplate(fo100, qv101);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 *  change template new 
	 *  create: 12/08/2015
	 *  change template new version using A900 template
	 */
	@POST
	@Path("changeTemplateWebA900")
	@Produces("application/json")
	public String changeTemplateWebA900(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String qv101 = jsonObject.get("qv101").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int fa900 = Integer.parseInt(jsonObject.get("fa900").toString());
			DraftWebService draftWebMGService = (DraftWebService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_DRAFTWEBSERVICE);
			log.info("---changeTemplate:" + fo100 + ","
					+ fa900 +"," + qv101);
			int kq = draftWebMGService.changeTemplateA900(fo100, fa900, qv101);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 *  apply draft to home 
	 *  create: 12/08/2015
	 */
	@POST
	@Path("applyNewTemplateA900")
	@Produces("application/json")
	public String applyNewTemplateA900(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String qv101 = jsonObject.get("qv101").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			DraftWebService draftWebMGService = (DraftWebService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_DRAFTWEBSERVICE);
			log.info("---applyNewTemplateA900:"+fo100+","+ qv101);
			int kq = draftWebMGService.applyNewTemplateA900(fo100, qv101);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
