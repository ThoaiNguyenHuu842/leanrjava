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
import com.ohhay.core.oracle.service.V130ORService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * mobile.bonevo.net/service/v130PWebService/
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-service
 */
@Path("v130PWebService")
public class V130PWebService {

	private Logger log = Logger.getLogger(V130PWebService.class);
	/**
	 * @param FP100 int
	 * @param FO100 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("createTabV130")
	@Produces("application/json")
	public String createTabV130(String postString) {
		try {
			log.info("Post : createTabV130 --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fp100 = jsonObject.getInt("FP100"); 
			int fo100 = jsonObject.getInt("FO100"); 
			String pvLogin = jsonObject.getString("PVLOGIN");
			V130ORService v130orService = (V130ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V130OR);
			return QbRestUtils.convertToJsonStringForFunc(v130orService.createTabV130(fp100, fo100, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param VN133 int
	 * @param FP100 int
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabV130")
	@Produces("application/json")
	public String listOfTabV130(@QueryParam("VN133") int vn133, @QueryParam("FP100") int fp100, @QueryParam("FO100") int fo100,@QueryParam("OFFSET") int offset,@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin){
		try {
			V130ORService v130orService = (V130ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V130OR);
			return QbRestUtils.convertToJsonStringForProc(v130orService.listOfTabV130(vn133, fp100, fo100,offset,limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
}
