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
import com.ohhay.piepme.mongo.service.T120PMGService;

/**
 * mobile.bonevo.net/service/t120PWebService
 * @author ThoaiNH
 * create Aug 3, 2017
 */
@Path("t120PWebService")
public class T120PWebService {
	/**
	 * danh sach user yeu cau tham gia COM2
	 * @param FO100 int
	 * @param FT110 int 
	 * @param TV129 String 
	 * @param OFFSET int 
	 * @param LIMIT int 
	 * @return
	 */
	@GET
	@Path("getListT120")
	@Produces("application/json")
	public String getListT110(@QueryParam("FO100") int fo100, @QueryParam("FT110") int ft110, @QueryParam("TV129") String tv129, @QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			T120PMGService t120pmgService = (T120PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T120P);
			return QbRestUtils.convertToJsonStringForProc(t120pmgService.listOfTabT120Users(fo100, ft110, tv129, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param PT120	int
	 * @return
	 */
	@POST
	@Path("comfirmT120")
	@Produces("application/json")
	public String comfirmT120(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt120 = Integer.parseInt(jsonObject.get("PT120").toString());
			T120PMGService t120pmgService = (T120PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T120P);
			return QbRestUtils.convertToJsonStringForFunc(t120pmgService.comfirmT120(fo100, pt120));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param PT120	int
	 * @return
	 */
	@POST
	@Path("rejectT120")
	@Produces("application/json")
	public String rejectT120(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt120 = Integer.parseInt(jsonObject.get("PT120").toString());
			T120PMGService t120pmgService = (T120PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T120P);
			return QbRestUtils.convertToJsonStringForFunc(t120pmgService.rejectT120(fo100, pt120));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
