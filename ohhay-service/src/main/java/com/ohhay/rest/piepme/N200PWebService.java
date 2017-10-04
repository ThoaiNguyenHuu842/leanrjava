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
import com.ohhay.core.oracle.service.N200ORService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * mobile.bonevo.net/service/n200PWebService/
 * @author ThoaiNH
 * create Mar 30, 2017
 */
@Path("n200PWebService")
public class N200PWebService {
	private static Logger log = Logger.getLogger(N100PWebService.class);

	
	/**
	 * @param NV201 String piepme id
	 * @param NV204 String description 
	 * @param NV207 String account name
	 * @param NV208 String bank name
	 * @param NV209 String account id number
	 * @param NV212 String chi nhanh
	 * @param FO100 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("insertTabN200VND")
	@Produces("application/json")
	public String insertTabN200VND(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			String pvNV201 = jsonObject.get("NV201").toString();
			String pvNV204 = jsonObject.get("NV204").toString();
			String pvNV207 = jsonObject.get("NV207").toString();
			String pvNV208 = jsonObject.get("NV208").toString();
			String pvNV209 = jsonObject.get("NV209").toString();
			String pvNV212 = jsonObject.get("NV212").toString();
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pvLogin = jsonObject.get("PVLOGIN").toString();
			N200ORService n200orService = (N200ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N200OR);
			int rs = n200orService.insertTabN200VND(pvNV201, pvNV204, pvNV207, pvNV208, pvNV209, pvNV212, fo100, pvLogin);
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabN200O")
	@Produces("application/json")
	public String listOfTabN200O(@QueryParam("FO100") int fo100, @QueryParam("PVLOGIN") String pvLogin) {
		try {
			N200ORService n200orService = (N200ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N200OR);
			return QbRestUtils.convertToJsonStringForProc(n200orService.listOfTabN200O(fo100, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
