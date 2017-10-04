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
import com.ohhay.core.oracle.service.V100ORService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * mobile.bonevo.net/service/v100PWebService/
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-service
 */
@Path("v100PWebService")
public class V100PWebService {

	private Logger log = Logger.getLogger(V100PWebService.class);
	/**
	 * @param VN103 int
	 * @param VN104 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("createTabV100O")
	@Produces("application/json")
	public String createTabV100O(String postString) {
		try {
			log.info("Post : createTabV100O --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			int vn103 = jsonObject.getInt("VN103"); 
			int vn104 = jsonObject.getInt("VN104"); 
			String pvLogin = jsonObject.getString("PVLOGIN");
			V100ORService v100orService = (V100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V100OR);
			return QbRestUtils.convertToJsonStringForFunc(v100orService.createTabV100O(vn103, vn104, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param VN103 int
	 * @param VN104 int
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabV100")
	@Produces("application/json")
	public String listOfTabV100(@QueryParam("VN103") int vn103, @QueryParam("VN104") int vn104, @QueryParam("FO100") int fo100,@QueryParam("OFFSET") int offset,@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin){
		try {
			V100ORService v100orService = (V100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V100OR);
			return QbRestUtils.convertToJsonStringForProc(v100orService.listOfTabV100(vn103, vn104, fo100,offset,limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
}
