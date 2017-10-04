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
import com.ohhay.piepme.mongo.service.B100BPMGService;

/**
 * mobile.bonevo.net/service/b100BWebservice/
 * report user function (commercial)
 * @author TuNt
 * create date May 12, 2017
 * ohhay-service
 */
@Path("b100BWebservice")
public class B100BWebservice {
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	@GET
	@Path("getListB100B")
	@Produces("application/json")
	public String getListB100B(@QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			B100BPMGService b100bpmgService = (B100BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100B);
			return QbRestUtils.convertToJsonStringForProc(b100bpmgService.getListB100B(fo100, offset, limit));
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
	@Path("insertTabB100B")
	@Produces("application/json")
	public String insertTabB100(String postString){
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100p = Integer.parseInt(jsonObject.get("FO100P").toString());
			B100BPMGService b100bpmgService = (B100BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100B);
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
	@Path("removeB100B")
	@Produces("application/json")
	public String removeB100B(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100p = Integer.parseInt(jsonObject.get("FO100P").toString());
			B100BPMGService b100bpmgService = (B100BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100B);
			return QbRestUtils.convertToJsonStringForFunc(b100bpmgService.storNoTabB100(fo100, fo100p));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
