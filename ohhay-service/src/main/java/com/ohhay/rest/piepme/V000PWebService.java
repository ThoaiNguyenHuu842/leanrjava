package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.oracle.service.V000ORService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * mobile.bonevo.net/service/v000PWebService/
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-service
 */
@Path("v000PWebService")
public class V000PWebService {

	private Logger log = Logger.getLogger(V000PWebService.class);
	/**
	 * @param FV000 int
	 * @param VN001 int
	 * @param VN002 int
	 * @param VN003 int
	 * @param VV004 String
	 * @param VV005 String
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("insertTabV000")
	@Produces("application/json")
	public String insertTabV000(String postString) {
		try {
			log.info("Post : insertTabV000 --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fv000 = jsonObject.getInt("FV000"); 
			int vn001 = jsonObject.getInt("VN001");
			int vn002 = jsonObject.getInt("VN002"); 
			int vn003 = jsonObject.getInt("VN003"); 
			String vv004 = jsonObject.getString("VV004"); 
			String vv005 = jsonObject.getString("VV005"); 
			String pvLogin = jsonObject.getString("PVLOGIN");
			V000ORService v000orService = (V000ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V000OR);
			return QbRestUtils.convertToJsonStringForFunc(v000orService.insertTabV000O(fv000, vn001, vn002, vn003, vv004, vv005, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FV000 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("confirmTabV000")
	@Produces("application/json")
	public String confirmTabV000(String postString) {
		try {
			log.info("Post : confirmTabV000 --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fv000 = jsonObject.getInt("FV000"); 
			String pvLogin = jsonObject.getString("PVLOGIN");
			V000ORService v000orService = (V000ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V000OR);
			return QbRestUtils.convertToJsonStringForFunc(v000orService.confirmTabV000(fv000, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabV000")
	@Produces("application/json")
	public String listOfTabV000(@QueryParam("PVLOGIN") String pvLogin){
		try {
			V000ORService v000orService = (V000ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V000OR);
			return QbRestUtils.convertToJsonStringForProc(v000orService.listOfTabV000(pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
}
