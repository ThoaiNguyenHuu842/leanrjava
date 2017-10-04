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
import com.ohhay.core.entities.oracle.N000OR;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.service.N100BusPMGService;
import com.ohhay.piepme.mongo.service.N100PMGService;

/**
 * @author ThoaiNH
 * create Jun 15, 2017
 */
@Path("n100PBusWebService")
public class N100PBusWebService {
	private static Logger log = Logger.getLogger(N100PBusWebService.class);
	/**
	 * @param UUID String
	 * @return
	 */
	@GET
	@Path("getN100ByUUID")
	@Produces("application/json")
	public String getN100ByUUID(@QueryParam("UUID") String uuid) {
		try {
			N100BusPMGService n100BusPService = (N100BusPMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100BUSP);
			return QbRestUtils.convertToJsonStringForProc(n100BusPService.getN100ByUUID(uuid));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param NICKNAME String
	 * @param SECURITYNUMBER String
	 * @return
	 */
	@GET
	@Path("getN100ByNV101")
	@Produces("application/json")
	public String getN100ByNV101(@QueryParam("NICKNAME") String nickName,
			@QueryParam("SECURITYNUMBER") String securityNum) {
		try {
			N100BusPMGService n100BusPService = (N100BusPMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100BUSP);
			return QbRestUtils.convertToJsonStringForProc(n100BusPService.getN100ByNV101(nickName, securityNum));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * find user by wildcard user name (not-login)
	 * @param PVSEARCH String
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabN100Sug")
	@Produces("application/json")
	public String listOfTabN100Sug(@QueryParam("PVSEARCH") String pvSearch, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit) {
		try {
			N100BusPMGService n100BusPService = (N100BusPMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100BUSP);
			return QbRestUtils.convertToJsonStringForProc(n100BusPService.listOfTabN100Sug(pvSearch, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * find user by wildcard user name (login)
	 * @param FO100 int
	 * @param PVSEARCH String
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listSuggestedEnterprise")
	@Produces("application/json")
	public String listSuggestedEnterprise(@QueryParam("FO100") int fo100, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit) {
		try {
			N100BusPMGService n100BusPService = (N100BusPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100BUSP);
			return QbRestUtils.convertToJsonStringForProc(n100BusPService.listSuggestedEnterprise(fo100, pvSearch, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param 
	 * FO100 int
	 * NV106 String
	 * NV107 String
	 * NV108 String
	 * KV101 String
	 * KV102 String 
	 * KV107 String
	 * NV201 String
	 * NV204 String 
	 * NV207 String 
	 * NV208 String 
	 * NV209 String 
	 * NV212 String
	 * PVLOGIN String
	 * @return
	 */
	@POST
	@Path("update")
	@Produces("application/json")
	public String update(String postString){
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			String nv106 = json.getString("NV106");
			String nv107 = json.getString("NV107");
			String nv108 = json.getString("NV108");
			String kv101 = json.getString("KV101");
			String kv102 = json.getString("KV102");
			String kv107 = json.getString("KV107");
			String nv201 = json.getString("NV201");
			String nv204 = json.getString("NV204");
			String nv207 = json.getString("NV207");
			String nv208 = json.getString("NV208");
			String nv209 = json.getString("NV209");
			String nv212 = json.getString("NV212");
			String login = json.getString("PVLOGIN");
			N100BusPMGService n100BusPService = (N100BusPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100BUSP);
			return QbRestUtils.convertToJsonStringForFunc((n100BusPService.update(fo100, nv106, nv107, nv108, kv101, kv102, kv107, nv201, nv204, nv207, nv208, nv209, nv212, login)));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * update UUID after confirm OTP successfully
	 * @param FO100 int
	 * @param FP150 int
	 * @param UUID String
	 * @return
	 */
	@POST
	@Path("updateUUID")
	@Produces("application/json")
	public String updateUUID(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fp150 = (int) Double.parseDouble(jsonObject.get("FP150").toString());
			String uuid = jsonObject.get("UUID").toString();
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			int kq = n100pService.updateUUID(uuid, N000OR.NV002_DN, fo100, fp150);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
