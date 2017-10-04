package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.entities.other.M930PMG;
import com.ohhay.piepme.mongo.service.M930PMGService;

/**
 * mobile.bonevo.net/service/m930PWebService/
 * @author ThoaiNH
 * create Jan 16, 2017
 */
@Path("m930PWebService")
public class M930PWebService {
	/**
	 * update user history key
	 * @param FO100 int
	 * @param KEY String
	 * @return
	 */
	@POST
	@Path("insertTabM930")
	@Produces("application/json")
	public String insertTabM930(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String key = jsonObject.getString("KEY");
			M930PMGService m930pmgService = (M930PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M930P);
			return QbRestUtils.convertToJsonStringForFunc(m930pmgService.insertTabM930(fo100, key));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * list user history key
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("listOfTabM930")
	@Produces("application/json")
	public String listOfTabM930(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForProc(templateService.findDocumentById(fo100, fo100, M930PMG.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
