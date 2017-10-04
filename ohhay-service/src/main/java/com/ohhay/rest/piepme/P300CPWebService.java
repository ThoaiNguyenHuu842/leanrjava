package com.ohhay.rest.piepme;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.service.V220ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.constant.PiepmeActivity;
import com.ohhay.piepme.mongo.entities.pieper.P300CPMG;
import com.ohhay.piepme.mongo.service.B100CPMGService;
import com.ohhay.piepme.mongo.service.B200CPMGService;
import com.ohhay.piepme.mongo.service.B300CPMGService;
import com.ohhay.piepme.mongo.service.N100PMGService;
import com.ohhay.piepme.mongo.service.P300CPMGService;

/**
 * mobile.bonevo.net/service/p300CWebService/
 * service for circle pieper
 * @author TuNt
 * create date Nov 5, 2016
 * ohhay-service
 */
@Path("p300CWebService")
public class P300CPWebService {
	private static Logger log = Logger.getLogger(N100PWebService.class);
	/**
	 * @deprecated replaced by {@link #createPieperV2(String)}
	 * @param FO100 int
	 * @param PP300 (optional) int = 0 is create new pieper, > 0 is update with pieper id
	 * @param PV301 String
	 * @param PN303 int
	 * @param PN306 int
	 * @param PV304 String
	 * @param PV304THUMB String
	 * @param PV305 String
	 * @param PV314 String
	 * @param PN309 int
	 * @param FG100S List<Integer>
	 * @param FO100S List<Integer>
	 * @param OTAGS String
	 * @return
	 */
	@POST
	@Path("createPieper")
	@Produces("application/json")
	public String createPieper(String postString) {
		try {
			log.info("--postString:"+postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pv301 = jsonObject.get("PV301").toString();
			int pn303 = Integer.parseInt(jsonObject.get("PN303").toString());
			int pn306 = Integer.parseInt(jsonObject.get("PN306").toString());
			String pv304 = jsonObject.get("PV304").toString();
			String pv304Thumb = jsonObject.get("PV304THUMB").toString();
			String pv305 = jsonObject.get("PV305").toString();
			String pv314 = jsonObject.get("PV314").toString();
			float pn309 = Float.parseFloat(jsonObject.getString("PN309"));
			/*
			 * list group can see this pieper
			 */
			List<Integer> listFG100s = new ArrayList<Integer>();
			if(jsonObject.has("FG100S") && jsonObject.get("FG100S").toString() != null){
				String fg100String = jsonObject.get("FG100S").toString();
				String fg100arr[] = fg100String.split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
				for(String fg100: fg100arr){
					try {
						listFG100s.add(Integer.parseInt(fg100));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			/*
			 * list friends can see this pieper
			 */
			List<Integer> listFO100s = new ArrayList<Integer>();
			if(jsonObject.has("FO100S") && jsonObject.get("FO100S").toString() != null){
				String fo100String = jsonObject.get("FO100S").toString();
				String fo100arr[] = fo100String.split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
				for(String fo100s: fo100arr){
					try {
						listFO100s.add(Integer.parseInt(fo100s));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
;			String otags = null;
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			int pp300 = 0;
			if(jsonObject.has("PP300"))
				pp300 = jsonObject.getInt("PP300");
			P300CPMGService p200pmgService = (P300CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300CP);
			int rs = p200pmgService.createPieper(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, listFG100s, listFO100s);
			if(rs > 0){
				N100PMGService n100pmgService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
				//13/07/2017 update oracle
				n100pmgService.upgradeTabV220(fo100, PiepmeActivity.CREATE_CIRCLE_PIEPER, ApplicationConstant.PVLOGIN_ANONYMOUS);
			}
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * list circle pieper ps: lay ca pieper cua pnFO100
	 * @param FO100 int
	 * @param FO100T int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListCirclePieper")
	@Produces("application/json")
	public String getListCirclePieper(@QueryParam("FO100") int fo100, @QueryParam("FO100T") int fo100t, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300CPMGService p200pmgService = (P300CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300CP);
			return QbRestUtils.convertToJsonStringForProc(p200pmgService.getListCirclePieper(fo100, fo100t , AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * detail pieper circle
	 * @param FO100 int
	 * @param PP200 int
	 * @return
	 */
	@GET
	@Path("getPieperDetail")
	@Produces("application/json")
	public String getPieperDetail(@QueryParam("FO100") int fo100, @QueryParam("PP300") int pp200) {
		try {
			P300CPMGService p200Service = (P300CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300CP);
			return QbRestUtils.convertToJsonStringForProc(p200Service.getPieperDetail(fo100,pp200));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * remove PIEPER document from physical storage
	 * @param FO100 int
	 * @param PP300 int
	 * @return
	 */
	@POST
	@Path("removePieper")
	@Produces("application/json")
	public String removePieper(String postString) {
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			int id = json.getInt("PP300");
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				return QbRestUtils.convertToJsonStringForFunc(templateService.removeRealDocument(fo100, P300CPMG.class, new QbCriteria(QbMongoFiledsName.ID, id),new QbCriteria(QbMongoFiledsName.FO100, fo100)));
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * list otag suggestion that created by user: wildcard search & stem-search
	 * search case insensitive otags start with pvSearch 
	 * @param FO100 int
	 * @param PVSEARCH String
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListSuggestedOtag")
	@Produces("application/json")
	public String getListSuggestedOtag(@QueryParam("FO100") int fo100, @QueryParam("PVSEARCH") String pvSearch,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {

			P300CPMGService p300CPService = (P300CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300CP);
			return QbRestUtils
					.convertToJsonStringForProc(p300CPService.listSuggestedOtag(fo100, pvSearch,ApplicationHelper.getStemOtag(pvSearch), pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * remove pieper, only allow if 24h after creating
	 * @param FO100 int
	 * @param PP300 int
	 * @return
	 */
	@POST
	@Path("storNoTabP300c")
	@Produces("application/json")
	public String storNoTabP300p(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			P300CPMGService p300cpmgService = (P300CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300CP);
			return QbRestUtils.convertToJsonStringForFunc(p300cpmgService.storNoTabP300C(fo100, pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100
	 * @param PP300
	 * @return
	 */
	@POST
	@Path("blockPieper")
	@Produces("application/json")
	public String blockPieper(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			B300CPMGService b300cpmgService = (B300CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B300C);
			return QbRestUtils.convertToJsonStringForFunc(b300cpmgService.insertTabB300(fo100, pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100
	 * @param PP300
	 * @return
	 */
	@POST
	@Path("reportPieper")
	@Produces("application/json")
	public String reportPieper(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			B200CPMGService b200cpmgService = (B200CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B200C);
			return QbRestUtils.convertToJsonStringForFunc(b200cpmgService.insertTabB200(fo100, pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 user login
	 * @param FO100P need to block
	 * @return
	 */
	@POST
	@Path("blockAccount")
	@Produces("application/json")
	public String blockAccount(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100p = Integer.parseInt(jsonObject.get("FO100P").toString());
			B100CPMGService b100cpmgService = (B100CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100C);
			return QbRestUtils.convertToJsonStringForFunc(b100cpmgService.insertTabB100(fo100, fo100p));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param PP300 (optional) int = 0 is create new pieper, > 0 is update with pieper id
	 * @param PV301 String
	 * @param PN303 int
	 * @param PN306 int
	 * @param PV304 String
	 * @param PV304THUMB String
	 * @param PV305 String
	 * @param PV314 String
	 * @param PN309 int
	 * @param FG100S List<Integer>
	 * @param FO100S List<Integer>
	 * @param OTAGS String
	 * @return
	 */
	@POST
	@Path("createPieperV2")
	@Produces("application/json")
	public String createPieperV2(String postString) {
		try {
			log.info("--postString:"+postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pv301 = jsonObject.get("PV301").toString();
			int pn303 = Integer.parseInt(jsonObject.get("PN303").toString());
			int pn306 = Integer.parseInt(jsonObject.get("PN306").toString());
			String pv304 = jsonObject.get("PV304").toString();
			String pv304Thumb = jsonObject.get("PV304THUMB").toString();
			String pv305 = jsonObject.get("PV305").toString();
			String pv314 = jsonObject.get("PV314").toString();
			float pn309 = Float.parseFloat(jsonObject.getString("PN309"));
			/*
			 * list group can see this pieper
			 */
			List<Integer> listFG100s = new ArrayList<Integer>();
			if(jsonObject.has("FG100S") && jsonObject.get("FG100S").toString() != null){
				String fg100String = jsonObject.get("FG100S").toString();
				String fg100arr[] = fg100String.split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
				for(String fg100: fg100arr){
					try {
						listFG100s.add(Integer.parseInt(fg100));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			/*
			 * list friends can see this pieper
			 */
			List<Integer> listFO100s = new ArrayList<Integer>();
			if(jsonObject.has("FO100S") && jsonObject.get("FO100S").toString() != null){
				String fo100String = jsonObject.get("FO100S").toString();
				String fo100arr[] = fo100String.split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
				for(String fo100s: fo100arr){
					try {
						listFO100s.add(Integer.parseInt(fo100s));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
;			String otags = null;
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			int pp300 = 0;
			if(jsonObject.has("PP300"))
				pp300 = jsonObject.getInt("PP300");
			P300CPMGService p200pmgService = (P300CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300CP);
			return QbRestUtils.convertToJsonStringForFunc(p200pmgService.createPieperV2(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, listFG100s, listFO100s));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
