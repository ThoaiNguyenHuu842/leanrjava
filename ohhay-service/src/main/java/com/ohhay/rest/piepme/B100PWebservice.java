package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.service.B100PPMGService;

/**
 * mobile.bonevo.net/service/b100PWebservice/
 * report user function (public)
 * @author TuNt
 * create date May 12, 2017
 * ohhay-service
 */
@Path("b100PWebservice")
public class B100PWebservice {
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	@GET
	@Path("getListB100P")
	@Produces("application/json")
	public String getListB100P(@QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			B100PPMGService b100ppmgService = (B100PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100P);
			return QbRestUtils.convertToJsonStringForProc(b100ppmgService.getListB100P(fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/*@POST
	@Path("insertTabB100P")
	@Produces("application/json")
	public String insertTabB100(String postString){
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100p = Integer.parseInt(jsonObject.get("FO100P").toString());
			B100PPMGService b100ppmgService = (B100PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100P);
			return QbRestUtils.convertToJsonStringForFunc(b100ppmgService.insertTabB100(fo100, fo100p));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}*/
	/**
	 * @param FO100
	 * @param FO100P
	 * @return
	 */
	@POST
	@Path("removeB100P")
	@Produces("application/json")
	public String removeB100P(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100p = Integer.parseInt(jsonObject.get("FO100P").toString());
			B100PPMGService b100ppmgService = (B100PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100P);
			return QbRestUtils.convertToJsonStringForFunc(b100ppmgService.storNoTabB100(fo100, fo100p));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
