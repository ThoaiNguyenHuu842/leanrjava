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
import com.ohhay.core.oracle.service.V050ORService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * mobile.bonevo.net/service/v050PWebService/
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-service
 */
@Path("v050PWebService")
public class V050PWebService {

	private Logger log = Logger.getLogger(V050PWebService.class);
	/**
	 * @param FV050 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("confirmTabV050")
	@Produces("application/json")
	public String confirmTabV050(String postString) {
		try {
			log.info("Post : confirmTabV050 --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fv050 = jsonObject.getInt("FV050"); 
			String pvLogin = jsonObject.getString("PVLOGIN");
			V050ORService v050orService = (V050ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V050OR);
			return QbRestUtils.convertToJsonStringForFunc(v050orService.confirmTabV050(fv050, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FV000 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabV050")
	@Produces("application/json")
	public String listOfTabV050(@QueryParam("FV000") int fv000,@QueryParam("OFFSET") int offset,@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin){
		try {
			V050ORService v050orService = (V050ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V050OR);
			return QbRestUtils.convertToJsonStringForProc(v050orService.listOfTabV050(fv000,offset,limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FV050 String
	 * @param VN051 int
	 * @param VN052 int
	 * @param VN053 int
	 * @param VV054 String
	 * @param VV055 String
	 * @param FV000 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("insertTabV050O")
	@Produces("application/json")
	public String insertTabV050O(String postString) {
		try {
			log.info("Post : confirmTabV050 --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			//int fv050, int vn051, int vn052, int vn053, String vv054, String vv055, int fv000, String pvLogin) {
			int fv050 = jsonObject.getInt("FV050");
			int vn051 = jsonObject.getInt("VN051");
			int vn052 = jsonObject.getInt("VN052");
			int vn053 = jsonObject.getInt("VN053");
			String vv054 = jsonObject.getString("VV054");
			String vv055 = jsonObject.getString("VV055");
			int fv000 = jsonObject.getInt("FV000");
			String pvLogin = jsonObject.getString("PVLOGIN");
			V050ORService v050orService = (V050ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V050OR);
			return QbRestUtils.convertToJsonStringForFunc(v050orService.insertTabV050O(fv050, vn051, vn052, vn053, vv054, vv055, fv000, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	
}
