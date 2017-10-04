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
import com.ohhay.piepme.mongo.service.B100CPMGService;

/**
 * mobile.bonevo.net/service/b100PWebservice/
 * report user function (public)
 * @author TuNt
 * create date May 12, 2017
 * ohhay-service
 */
@Path("b100CWebservice")
public class B100CWebservice {
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	@GET
	@Path("getListB100C")
	@Produces("application/json")
	public String getListB100P(@QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			B100CPMGService b100cpmgService = (B100CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100P);
			return QbRestUtils.convertToJsonStringForProc(b100cpmgService.getListB100C(fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100
	 * @param FO100P
	 * @return
	 */
	/*@POST
	@Path("insertTabB100C")
	@Produces("application/json")
	public String insertTabB100(String postString){
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100p = Integer.parseInt(jsonObject.get("FO100P").toString());
			B100CPMGService b100bpmgService = (B100CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100P);
			return QbRestUtils.convertToJsonStringForFunc(b100bpmgService.insertTabB100(fo100, fo100p));
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
	@Path("removeB100C")
	@Produces("application/json")
	public String removeB100P(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100p = Integer.parseInt(jsonObject.get("FO100P").toString());
			B100CPMGService b100cpmgService = (B100CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100P);
			return QbRestUtils.convertToJsonStringForFunc(b100cpmgService.storNoTabB100(fo100, fo100p));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
