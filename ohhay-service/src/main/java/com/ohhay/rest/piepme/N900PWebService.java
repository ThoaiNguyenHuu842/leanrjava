package com.ohhay.rest.piepme;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.entities.notification.N900PMG;
import com.ohhay.piepme.mongo.service.N900PMGService;
/**
 * mobile.bonevo.net/service/n900PWebService/
 * storage notification info
 * @author ThoaiNH
 * create Jun 20, 2017
 */
@Path("n900PWebService")
public class N900PWebService {
	Logger log = Logger.getLogger(N900PWebService.class);

	/**
	 * @param FO100 int
	 * @param OBJECT_NAME String
	 * @param STT int
	 * @return
	 */
	@POST
	@Path("updateNotification")
	@Produces("application/json")
	public String updateNotification(String postString) {
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			String objectName = json.getString("OBJECT_NAME");
			int status = json.getInt("STT");
			N900PMGService n900mgService = (N900PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N900P);
			return QbRestUtils.convertToJsonStringForFunc(n900mgService.updateNotification(fo100, objectName, status));
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * get all notify
	 * @param FO100 int
	 * @param OBJECT_NAME String
	 * @return
	 */
	@GET
	@Path("getNotification")
	@Produces("application/json")
	public String getNotification(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForProc(templateService.findDocumentById(fo100, fo100, N900PMG.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}	
	}
	
	/**
	 * get all notify
	 * @param FO100 int
	 * @param OBJECT_NAME String
	 * @return 
	 */
	@GET
	@Path("getValueByObjectName")
	@Produces("application/json")
	public String getValueByObjectName(@QueryParam("FO100") int fo100, @QueryParam("OBJECT_NAME") String objName) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			if(templateService.findDocumentById(fo100, fo100, N900PMG.class).getMapNotification().get(objName) != null)
				return QbRestUtils.convertToJsonStringForProc(templateService.findDocumentById(fo100, fo100, N900PMG.class).getMapNotification().get(objName));
			return QbRestUtils.convertToJsonStringForProc(0);
		} catch (Exception ex) {
			return QbRestUtils.convertToJsonStringForProc(0);
		}	
	}
	
	/**
	 * @param FO100 int
	 * @param OBJECTS JSON array [{OBJECT_NAME:"",STT:1}]
	 * @return
	 */
	@POST
	@Path("updateNotificationMulti")
	@Produces("application/json")
	public String updateNotificationMulti(String postString) {
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			JSONArray objects = json.getJSONArray("OBJECTS");
			Map<String, Integer> mapNotification = new HashMap<String, Integer>();
			for(int i = 0; i < objects.length(); i++){
				mapNotification.put(objects.getJSONObject(i).getString("OBJECT_NAME"), Integer.parseInt(objects.getJSONObject(i).getString("STT")));
			}
			N900PMGService n900mgService = (N900PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N900P);
			return QbRestUtils.convertToJsonStringForProc(n900mgService.updateNotificationMulti(fo100, mapNotification));
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param FO100F int
	 * @param OBJECT_NAME String
	 * @param STT int
	 * @return
	 */
	@POST
	@Path("updateNotificationV2")
	@Produces("application/json")
	public String updateNotificationV2(String postString) {
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			int fo100f = json.getInt("FO100F");
			String objectName = json.getString("OBJECT_NAME");
			int status = json.getInt("STT");
			N900PMGService n900mgService = (N900PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N900P);
			return QbRestUtils.convertToJsonStringForFunc(n900mgService.updateNotificationV2(fo100, fo100f, objectName, status));
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
