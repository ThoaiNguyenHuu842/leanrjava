package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.N700MGService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * mobile.bonevo.net/service/n700PWebService/
 * storage notification info
 * @author TuNt 
 * create date Dec 9, 2016 
 */
@Path("n700PWebService")
public class N700PWebService {
	Logger log = Logger.getLogger(N700PWebService.class);

	/**
	 * reset total notify to zero
	 * @param FO100 int
	 * @param OBJECT_NAME String
	 * @return
	 */
	@POST
	@Path("updateTabN700")
	@Produces("application/json")
	public String updateTabN700(String postString) {
		try {
			log.info("postString :" + postString);
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			String objectName = json.getString("OBJECT_NAME");
			N700MGService n700mgService = (N700MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N700MG);
			return QbRestUtils.convertToJsonStringForFunc(
					n700mgService.updateTabN700(ApplicationConstant.DB_TYPE_PIEPME, fo100, objectName));
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * get number of nwe notification by object name
	 * @param FO100 int
	 * @param OBJECT_NAME String
	 * @return
	 */
	@POST
	@Path("getValueOfKey")
	@Produces("application/json")
	public String getValueOfKey(String postString) {
		try {
			log.info("postString :" + postString);
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			String objectName = json.getString("OBJECT_NAME");
			N700MGService n700mgService = (N700MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N700MG);
			return QbRestUtils.convertToJsonStringForFunc(
					n700mgService.getValueOfKey(ApplicationConstant.DB_TYPE_PIEPME, fo100, objectName));
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get all notify
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("listOfTabN700")
	@Produces("application/json")
	public String listOfTabN700(@QueryParam("FO100") int fo100) {
		try {
			N700MGService n700mgService = (N700MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N700MG);
			return QbRestUtils.convertToJsonStringForProc(n700mgService.listOfTabN700(ApplicationConstant.DB_TYPE_PIEPME, fo100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}	
	}
}
