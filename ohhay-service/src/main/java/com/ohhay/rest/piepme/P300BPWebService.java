package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mysql.service.R100CentService;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.constant.PiepmeActivity;
import com.ohhay.piepme.mongo.dao.P300BPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.report.R100BPMG;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.service.B100BPMGService;
import com.ohhay.piepme.mongo.service.B200BPMGService;
import com.ohhay.piepme.mongo.service.B300BPMGService;
import com.ohhay.piepme.mongo.service.N100PMGService;
import com.ohhay.piepme.mongo.service.P300BPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;


/**
 * mobile.bonevo.net/service/p300BWebService/
 * service for commercial pieper
 * @author ThoaiNH
 * create Mar 31, 2017
 */
@Path("p300BWebService")
public class P300BPWebService {
	private static Logger log = Logger.getLogger(P300BPWebService.class);
	
	/**
	 * @deprecated replaced by {@link #createPieperV2(String)}
	 * @param FO100 int
	 * @param PP300 (optional) int = 0 is create new pieper, > 0 is update with pieper id
	 * @param PV301 String
	 * @param PN303 String
	 * @param PV304THUMB String
	 * @param PV305 String
	 * @param PN306 int
	 * @param PV314 String
	 * @param PN309 float
	 * @param OTAGS String
	 * @param FT300 int
	 * @return: > 0 success, -1 user must set location info, = 0: user don't have sufficient privileges to create commercial pieper, <0: number of seconds user must wait before creating next PIEPER
	 */
	@POST
	@Path("createPieper")
	@Produces("application/json")
	public String createPieper(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pv301 = jsonObject.get("PV301").toString();
			int pn303 = Integer.parseInt(jsonObject.get("PN303").toString());
			String pv304 = jsonObject.get("PV304").toString();
			String pv304Thumb = jsonObject.get("PV304THUMB").toString();
			String pv305 = jsonObject.get("PV305").toString();
			int pn306 = Integer.parseInt(jsonObject.get("PN306").toString());
			String pv314 = jsonObject.get("PV314").toString();
			float pn309 = Float.parseFloat(jsonObject.getString("PN309"));
			String otags = null;
			int ft300 = 0;
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			if(jsonObject.has("FT300")){
				ft300 = jsonObject.getInt("FT300");
			}
			int pp300 = 0;
			if(jsonObject.has("PP300"))
				pp300 = jsonObject.getInt("PP300");
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			return QbRestUtils.convertToJsonStringForFunc(p100Service.createPieper(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, ft300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @deprecated replaced by {@link #getListPieperOwner(int, String, int, int, int, int)}
	 * list public pieper
	 * pnFO100F = 0 ingore, != 0 search theo pieper của pnFO100, 
	 * pnFO100 FO100 của user đang dùng
	 * pnSort filter: recently, just piep
	 * @param FO100 int
	 * @param FO100F int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return 
	 */
	@GET
	@Path("getListPieper")
	@Produces("application/json")
	public String getListPieper(@QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			log.info(fo100+","+fo100f+","+pvSearch+","+sort+","+pnOffset+","+pnLimit);
			return QbRestUtils.convertToJsonStringForProc(
					p100Service.getListPieperPublic(fo100, fo100f, AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * get commercial PIEPER detail
	 * @param FO100 int
	 * @param PP300 int 
	 * @param DEVICE String
	 * @return
	 */
	@GET
	@Path("getPieperDetail")
	@Produces("application/json")
	public String getPieperDetail(@QueryParam("FO100") final int fo100, @QueryParam("PP300") final int pp300, @QueryParam("DEVICE") final String device) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
		    Thread thread = new Thread(){
			    public void run(){
			    	TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
					templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
					P300BPMG p300BPMG = templateService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, pp300, P300BPMG.class);
					//exclude owner
					if(fo100 != p300BPMG.getFo100()){
						N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
						R100CentService r100CentService = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
						N100PMGService n100pmgService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
						StringBuilder otags = new StringBuilder();
						if(p300BPMG.getListOtags() != null){
							for(Otag otag: p300BPMG.getListOtags())
								otags.append(otag.getKey() + ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
						}
						r100CentService.insertTabR1002017dis("VIE", p300BPMG.getPv301(), device, otags.toString(), 0, n100pmgService.getDistanceFromEnterprise(fo100, p300BPMG.getFo100()), pp300, fo100, p300BPMG.getFo100(), n100pmg.getNv101());
						n100pmgService.upgradeTabV220(fo100, PiepmeActivity.VIEW, n100pmg.getNv101());
					}
			    }
			};
			thread.start();
			return QbRestUtils.convertToJsonStringForProc(p100Service.getPieperDetail(fo100,pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * get number of total pieper by otag that user pnFO100 hasn't reach before
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

			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			return QbRestUtils
					.convertToJsonStringForProc(p100Service.listSuggestedOtag(fo100, pvSearch,ApplicationHelper.getStemOtag(pvSearch), pnOffset, pnLimit));
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
	@Path("storNoTabP300p")  
	@Produces("application/json")
	public String storNoTabP300p(String postString) {
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = Integer.parseInt(json.getString("FO100"));
			int id = Integer.parseInt(json.getString("PP300"));
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			String s =  QbRestUtils.convertToJsonStringForFunc(templateService.removeRealDocument(fo100, P300BPMG.class, new QbCriteria(QbMongoFiledsName.ID, id) , new QbCriteria(QbMongoFiledsName.FO100, fo100)));
			log.info(s);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
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
	@Path("removePieper")
	@Produces("application/json")
	public String removePieper(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			return QbRestUtils.convertToJsonStringForFunc(p100Service.storNoTabP300P(fo100, pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * list piepers of all users pnFO100 following
	 * @param FO100 int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET String
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListPieperFollow")
	@Produces("application/json")
	public String getListPieperFollow(@QueryParam("FO100") int fo100, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			return QbRestUtils.convertToJsonStringForProc(
					p100Service.getListPieperFollow(fo100, AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param PP300 int
	 * @param PV301 String
	 * @param DEVICE String
	 * @param FO100K int
	 * @return
	 */
	@GET
	@Path("getPieper")
	@Produces("application/json")
	public String getPieper(@QueryParam("FO100") final int fo100, @QueryParam("PP300") final int pp300, @QueryParam("PV301") final String pv301, @QueryParam("DEVICE") final String device, @QueryParam("FO100K") final int fo100k) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			Thread thread = new Thread(){
			    public void run(){
			    	// insert mongodb
			    	try {
						TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
						R100BPMG r100pmg = templateService.findDocument(fo100, R100BPMG.class,
																	   new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria("FO100T", fo100k),
																	   new QbCriteria("FP300", pp300));
						if (r100pmg == null) {
							P300BPMG P300BPMG = templateService.findDocumentById(fo100k, pp300, P300BPMG.class);
							if (P300BPMG != null) {
								SequenceService sequenceService = (SequenceService) ApplicationHelper
										.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
								int newId = (int) sequenceService.getNextSequenceIdPiepMe(fo100,
										QbMongoCollectionsName.R100B);
								r100pmg = new R100BPMG();
								r100pmg.setId(newId);
								r100pmg.setFo100(fo100);
								r100pmg.setFo100t(fo100k);
								r100pmg.setRd106(new java.util.Date());
								r100pmg.setRn101(P300BPMG.getPa315() != null ? P300BPMG.getPa315().size() : 0);
								r100pmg.setRv102("SEN");
								templateService.saveDocument(fo100k, R100BPMG.class, QbMongoCollectionsName.R100B);
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
			    }
			};
			thread.start();
			return QbRestUtils.convertToJsonStringForProc(p100Service.getListPieperPublicOne(fo100, pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @deprecated replaced by {@link #getListPieperByCategoryV2(double, double, int, int, int, String, int, int, int)}
	 * @param FO100 int
	 * @param FO100F int
	 * @param FT300 int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListPieperByCategory")
	@Produces("application/json")
	public String getListPieperByCategory(@QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("FT300") int ft300, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			return QbRestUtils.convertToJsonStringForProc(
					p100Service.getListPieperPublicByCategory(fo100, fo100f, ft300, AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @deprecated replaced by {@link #getListPieperV3(double, double, int, int, String, int, int, int)}
	 * list commercial pieper
	 * pnFO100F = 0 ingore, != 0 search theo pieper của pnFO100, 
	 * pnFO100 FO100 của user đang dùng
	 * pnSort filter: recently, just piep
	 * @param LATITUDE double
	 * @param LONGITUDE double
	 * @param FO100 int
	 * @param FO100F int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListPieperV2")
	@Produces("application/json")
	public String getListPieperV2(@QueryParam("LATITUDE") double pnLa, @QueryParam("LONGITUDE") double pnLong, @QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			log.info(fo100+","+fo100f+","+pvSearch+","+sort+","+pnOffset+","+pnLimit);
			return QbRestUtils.convertToJsonStringForProc(p100Service.getListPieperPublicV2(pnLa, pnLong, fo100, fo100f, AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
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
			B300BPMGService b300bpmgService = (B300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B300B);
			return QbRestUtils.convertToJsonStringForFunc(b300bpmgService.insertTabB300(fo100, pp300));
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
			B200BPMGService b200bpmgService = (B200BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B200B);
			return QbRestUtils.convertToJsonStringForFunc(b200bpmgService.insertTabB200(fo100, pp300));
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
			B100BPMGService b100bpmgService = (B100BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100B);
			return QbRestUtils.convertToJsonStringForFunc(b100bpmgService.insertTabB100(fo100, fo100p));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param FT300 int
	 * @return  1 success, -1 user must set location info, = 0: user don't have sufficient privileges to create commercial pieper, <0: number of seconds user must wait before creating next PIEPER
	 */
	@POST
	@Path("checkRoleOnCreate")
	@Produces("application/json")
	public String checkRoleOnCreate(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int ft300 = Integer.parseInt(jsonObject.get("FT300").toString());
			P300BPMGDao p300ppmgDao = (P300BPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_P300BP);
			return QbRestUtils.convertToJsonStringForFunc(p300ppmgDao.checkRoleOnCreate(fo100,ft300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * list my commercial của user đang dùng
	 * pnSort filter: recently, just piep
	 * @param FO100 int
	 * @param PVSEARCH String
	 * @param FT300 int
	 * @param SORT int 1: recently pieper, 3: draft pieper, 2: COM
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return 
	 */
	@GET
	@Path("getListPieperOwner")
	@Produces("application/json")
	public String getListPieperOwner(@QueryParam("FO100") int fo100, @QueryParam("PVSEARCH") String pvSearch,  @QueryParam("FT300") int ft300, @QueryParam("SORT") int sort,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			return QbRestUtils.convertToJsonStringForProc(p100Service.getListPieperOwner(fo100, AESUtils.decrypt(pvSearch), ft300, sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * list commercial pieper
	 * pnFO100F = 0 ingore, != 0 search theo pieper của pnFO100, 
	 * pnFO100 FO100 của user đang dùng
	 * pnSort filter: recently, just piep
	 * @param LATITUDE double
	 * @param LONGITUDE double
	 * @param FO100 int
	 * @param FO100F int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListPieperV3")
	@Produces("application/json")
	public String getListPieperV3(@QueryParam("LATITUDE") double pnLa, @QueryParam("LONGITUDE") double pnLong, @QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			log.info(fo100+","+fo100f+","+pvSearch+","+sort+","+pnOffset+","+pnLimit);
			return QbRestUtils.convertToJsonStringForProc(p100Service.getListPieperPublicV3(pnLa, pnLong, fo100, fo100f, AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * get total hot, near commercial pieper
	 * pnFO100F = 0 ingore, != 0 search theo pieper của pnFO100, 
	 * pnFO100 FO100 của user đang dùng
	 * pnSort filter: recently, just piep
	 * @param LATITUDE double
	 * @param LONGITUDE double
	 * @param FO100 int
	 * @param FO100F int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @return
	 */
	@GET
	@Path("getSummaryInfoV3")
	@Produces("application/json")
	public String getSummaryInfoV3(@QueryParam("LATITUDE") double pnLa, @QueryParam("LONGITUDE") double pnLong, @QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			return QbRestUtils.convertToJsonStringForProc(p100Service.getSummaryInfoV3(pnLa, pnLong, fo100, fo100f, AESUtils.decrypt(pvSearch), sort));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param LATITUDE double
	 * @param LONGITUDE double
	 * @param FO100 int
	 * @param FO100F int
	 * @param FT300 int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListPieperByCategoryV2")
	@Produces("application/json")
	public String getListPieperByCategoryV2(@QueryParam("LATITUDE") double pnLa, @QueryParam("LONGITUDE") double pnLong, @QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("FT300") int ft300, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			return QbRestUtils.convertToJsonStringForProc(
					p100Service.getListPieperPublicByCategoryV2(pnLa, pnLong, fo100, fo100f, ft300, pvSearch, sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * list COM pieper enterprise send to customer
	 * @param pnFO100F = enterprise
	 * @param FO100 int
	 * @param FO100F int
	 * @param PVSEARCH String
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListPieperEOM")
	@Produces("application/json")
	public String getListPieperEOM(@QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			return QbRestUtils.convertToJsonStringForProc(p100Service.getListPieperPublicEOM(fo100, fo100f, pvSearch, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param PP300 (optional) int = 0 is create new pieper, > 0 is update with pieper id
	 * @param PV301 String
	 * @param PN303 String
	 * @param PV304THUMB String
	 * @param PV305 String
	 * @param PN306 int
	 * @param PV314 String
	 * @param PN309 float
	 * @param OTAGS String
	 * @param FT300 int
	 * @return: > 0 success, -1 user must set location info, = 0: user don't have sufficient privileges to create commercial pieper, <0: number of seconds user must wait before creating next PIEPER
	 */
	@POST
	@Path("createPieperV2")
	@Produces("application/json")
	public String createPieperV2(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pv301 = jsonObject.get("PV301").toString();
			int pn303 = Integer.parseInt(jsonObject.get("PN303").toString());
			String pv304 = jsonObject.get("PV304").toString();
			String pv304Thumb = jsonObject.get("PV304THUMB").toString();
			String pv305 = jsonObject.get("PV305").toString();
			int pn306 = Integer.parseInt(jsonObject.get("PN306").toString());
			String pv314 = jsonObject.get("PV314").toString();
			float pn309 = Float.parseFloat(jsonObject.getString("PN309"));
			String otags = null;
			int ft300 = 0;
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			if(jsonObject.has("FT300")){
				ft300 = jsonObject.getInt("FT300");
			}
			int pp300 = 0;
			if(jsonObject.has("PP300"))
				pp300 = jsonObject.getInt("PP300");
			P300BPMGService p100Service = (P300BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BP);
			return QbRestUtils.convertToJsonStringForFunc(p100Service.createPieperV2(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, ft300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
