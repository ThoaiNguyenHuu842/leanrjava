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
import com.ohhay.core.oracle.service.V080ORService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * mobile.bonevo.net/service/v080PWebService/
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-service
 */
@Path("v080PWebService")
public class V080PWebService {

	private Logger log = Logger.getLogger(V080PWebService.class);
	/**
	 * @param VN081 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("createTabV080O")
	@Produces("application/json")
	public String createTabV080O(String postString) {
		try {
			log.info("Post : createTabV080O --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			int vn081 = jsonObject.getInt("VN081"); 
			String pvLogin = jsonObject.getString("PVLOGIN");
			V080ORService v080orService = (V080ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V080OR);
			return QbRestUtils.convertToJsonStringForFunc(v080orService.confirmTabV080O(vn081, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param VN081 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("confirmTabV080O")
	@Produces("application/json")
	public String confirmTabV080O(String postString) {
		try {
			log.info("Post : confirmTabV150 --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			int vn081 = jsonObject.getInt("VN081"); 			
			String pvLogin = jsonObject.getString("PVLOGIN");
			V080ORService v080orService = (V080ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V080OR);
			return QbRestUtils.convertToJsonStringForFunc(v080orService.confirmTabV080O(vn081, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param VN081 int
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabV080")
	@Produces("application/json")
	public String listOfTabV080(@QueryParam("VN081") int vn081, @QueryParam("FO100") int fo100,@QueryParam("OFFSET") int offset,@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin){
		try {
			V080ORService v080orService = (V080ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V080OR);
			return QbRestUtils.convertToJsonStringForProc(v080orService.listOfTabV080(vn081, fo100,offset,limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
