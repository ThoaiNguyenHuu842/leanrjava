package com.ohhay.rest.bonevo;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.mysql.service.C920Service;

/**
 * @author thoai nguyen
 * 
 */
@Path("l900WebService")
public class L900WebService {
	private static Logger log = Logger.getLogger(L900WebService.class);
	/*
	 * tao landing page
	 */
	@POST
	@Path("chayInsertL900")
	@Produces("application/json")
	public String chayInsertL900(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String ov101 = jsonObject.get("ov101").toString();
			int fc400 = Integer.parseInt(jsonObject.get("fc400").toString());
			String pvLogin = jsonObject.get("pvLogin").toString();
			// get elem String
			int kq = 1;
			C920Service c920Service = (C920Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_C920);
			log.info("--chayGetelemTabC920Lp:" + ov101 + "," + fc400
					+ "," + pvLogin);
			String elemString = c920Service.chayGetelemTabC920Lp(ov101, fc400,
					pvLogin);
			// insert imto mongo
			TemplateService service = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			//must add fo100
			service.insertWebStructure(0, "{" + elemString + "}", QbMongoCollectionsName.L900);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/*
	 * get html of landing page
	 */
	@GET
	@Path("getOhayHtmlL900")
	@Produces("application/json")
	public String getOhayHtmlL900(@QueryParam("hv101") String hv101) {
		try {
			return QbRestUtils.convertToJsonStringForProc(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
