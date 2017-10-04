package com.ohhay.rest.piepme;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.service.V220ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.constant.PiepmeActivity;
import com.ohhay.piepme.mongo.dao.P300PPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300PPMG;
import com.ohhay.piepme.mongo.entities.report.R100PMG;
import com.ohhay.piepme.mongo.service.B100PPMGService;
import com.ohhay.piepme.mongo.service.B200PPMGService;
import com.ohhay.piepme.mongo.service.B300PPMGService;
import com.ohhay.piepme.mongo.service.N100PMGService;
import com.ohhay.piepme.mongo.service.P300PPMGService;

/**
 * mobile.bonevo.net/service/p300PWebService/
 * service for public pieper
 * @author ThoaiNH 
 * create Sep 19, 2016 
 */
@Path("p300PWebService")
public class P300PPWebService {
	private static Logger log = Logger.getLogger(P300PPWebService.class);
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
	 * @param OTAGS (optional) String
	 * @return > 0 success, = 0: user don't have sufficient privileges to create public pieper , <0: number of seconds user must wait before creating next PIEPER
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
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			int pp300 = 0;
			if(jsonObject.has("PP300"))
				pp300 = jsonObject.getInt("PP300");
			P300PPMGService p100Service = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			int rs = p100Service.createPieper(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags);
			if(rs > 0){
				N100PMGService n100pmgService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
				//13/07/2017 update oracle
				n100pmgService.upgradeTabV220(fo100, PiepmeActivity.CREATE_PUBLIC_PIEPER, ApplicationConstant.PVLOGIN_ANONYMOUS);
			}
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @deprecated
	 * list public pieper
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
			P300PPMGService p100Service = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			return QbRestUtils.convertToJsonStringForProc(
					p100Service.getListPieperPublic(fo100, fo100f, AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @deprecated
	 * detail pieper
	 * not insert tracking for owner of pieper
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
			P300PPMGService p100Service = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
		    /*Thread thread = new Thread(){
			    public void run(){
			    	TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
					templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
					P300PPMG p300ppmg = templateService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, pp300, P300PPMG.class);
					//exclude owner
					if(fo100 != p300ppmg.getFo100()){
						N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
						R100CentService r100CentService = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
						r100CentService.insertTabR100("VIE", p300ppmg.getPV301Decrypt(), device, pp300, fo100, p300ppmg.getFo100(), n100pmg.getNv101());
					}
			    }
			};
			thread.start();*/
			return QbRestUtils.convertToJsonStringForProc(p100Service.getPieperDetail(fo100,pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * list otag suggestion that created by user
	 * search case insensitive otags start with wild-card
	 * origrinal otags have higher ranking score the stemmed otags
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

			P300PPMGService p100Service = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			return QbRestUtils
					.convertToJsonStringForProc(p100Service.listSuggestedOtag(fo100, pvSearch,ApplicationHelper.getStemOtag(pvSearch), pnOffset, pnLimit));
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
	@Path("storNoTabP300p")  
	@Produces("application/json")
	public String storNoTabP300p(String postString) {
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = Integer.parseInt(json.getString("FO100"));
			int id = Integer.parseInt(json.getString("PP300"));
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			String s =  QbRestUtils.convertToJsonStringForFunc(templateService.removeRealDocument(fo100, P300PPMG.class, new QbCriteria(QbMongoFiledsName.ID, id) , new QbCriteria(QbMongoFiledsName.FO100, fo100)));
			log.info(s);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
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
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			P300PPMGService p300ppmgService = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			return QbRestUtils.convertToJsonStringForFunc(p300ppmgService.storNoTabP300P(fo100, pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * 
	 * @param FO100 int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListPieperFollow")
	@Produces("application/json")
	public String getListPieperFollow(@QueryParam("FO100") int fo100, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300PPMGService p300Service = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			return QbRestUtils.convertToJsonStringForProc(
					p300Service.getListPieperFollow(fo100, AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * list piepers of all users pnFO100 following
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
			P300PPMGService p100Service = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			Thread thread = new Thread(){
			    public void run(){
			    	// insert mongodb
			    	try {
						TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
						R100PMG r100pmg = templateService.findDocument(fo100, R100PMG.class,
																	   new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria("FO100T", fo100k),
																	   new QbCriteria("FP300", pp300));
						if (r100pmg == null) {
							P300PPMG p300ppmg = templateService.findDocumentById(fo100k, pp300, P300PPMG.class);
							if (p300ppmg != null) {
								SequenceService sequenceService = (SequenceService) ApplicationHelper
										.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
								int newId = (int) sequenceService.getNextSequenceIdPiepMe(fo100,
										QbMongoCollectionsName.R100);
								r100pmg = new R100PMG();
								r100pmg.setId(newId);
								r100pmg.setFo100(fo100);
								r100pmg.setFo100t(fo100k);
								r100pmg.setRd106(new java.util.Date());
								r100pmg.setRn101(p300ppmg.getPa315() != null ? p300ppmg.getPa315().size() : 0);
								r100pmg.setRv102("SEN");
								templateService.saveDocument(fo100k, r100pmg, QbMongoCollectionsName.R100);
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
	 * get list public pieper
	 * not insert tracking for owner of pieper
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
	public String getListPieperV2(@QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300PPMGService p100Service = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			return QbRestUtils.convertToJsonStringForProc(
					p100Service.getListPieperPublicV2(fo100, fo100f, AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @param PP300 int
	 * @param DEVICE String
	 * @return
	 */
	@GET
	@Path("getPieperDetailV2")
	@Produces("application/json")
	public String getPieperDetailV2(@QueryParam("FO100") final int fo100, @QueryParam("PP300") final int pp300, @QueryParam("DEVICE") final String device) {
		try {
			P300PPMGService p100Service = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
		    /*Thread thread = new Thread(){
			    public void run(){
			    	TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
					templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
					P300PPMG p300ppmg = templateService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, pp300, P300PPMG.class);
					//exclude owner
					if(fo100 != p300ppmg.getFo100()){
						N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
						R100CentService r100CentService = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
						r100CentService.insertTabR100("VIE", p300ppmg.getPV301Decrypt(), device, pp300, fo100, p300ppmg.getFo100(), n100pmg.getNv101());
					}
			    }
			};
			thread.start();*/
			return QbRestUtils.convertToJsonStringForProc(p100Service.getPieperDetailV2(fo100,pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @deprecated
	 * user approve public pieper
	 * @param FO100 int
	 * @param PP300 int
	 * @param STT String
	 * @return 1 pieper da co du nguoi xac nhan 
	 */
	@POST
	@Path("confirmTabPA317")
	@Produces("application/json")
	public String confirmTabPA317(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			String stt = jsonObject.getString("STT");
			P300PPMGService p300ppmgService = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			return QbRestUtils.convertToJsonStringForFunc(p300ppmgService.confirmTabPA317(fo100, pp300, stt));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @deprecated
	 * info center calls to set list user can approve this pieper
	 * @param PP300 int
	 * @param FO100S JSONArray Int
	 * @return 
	 */
	@POST
	@Path("updateTabPA317")
	@Produces("application/json")
	public String updateTabPA317(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			JSONArray fo100s = jsonObject.getJSONArray("FO100S");
			List<Double> list = new ArrayList<Double>();
			for (int i = 0; i < fo100s.length(); i++)
				list.add(Double.parseDouble(fo100s.getString(i)));
			P300PPMGService p300ppmgService = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			return QbRestUtils.convertToJsonStringForFunc(p300ppmgService.updateTabPA317V2(pp300, list));
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
			B300PPMGService b300ppmgService = (B300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B300P);
			return QbRestUtils.convertToJsonStringForFunc(b300ppmgService.insertTabB300(fo100, pp300));
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
			B200PPMGService b200ppmgService = (B200PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B200P);
			return QbRestUtils.convertToJsonStringForFunc(b200ppmgService.insertTabB200(fo100, pp300));
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
			B100PPMGService b100ppmgService = (B100PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B100P);
			return QbRestUtils.convertToJsonStringForFunc(b100ppmgService.insertTabB100(fo100, fo100p));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @return = 1 success, = 0: user don't have sufficient privileges to create pieper , <0: number of seconds user must wait before creating next PIEPER
	 */ 
	@POST
	@Path("checkRoleOnCreate")
	@Produces("application/json")
	public String checkRoleOnCreate(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			P300PPMGDao p300ppmgDao = (P300PPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_P300PP);
			return QbRestUtils.convertToJsonStringForFunc(p300ppmgDao.checkRoleOnCreate(fo100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get my public pieper
	 * @param FO100 int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListPieperOwner")
	@Produces("application/json")
	public String getListPieperOwner(@QueryParam("FO100") int fo100, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,
			@QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300PPMGService p100Service = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			return QbRestUtils.convertToJsonStringForProc(
					p100Service.getListPieperOwner(fo100, AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
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
	 * @param OTAGS (optional) String
	 * @return > 0 success, = 0: user don't have sufficient privileges to create public pieper , <0: number of seconds user must wait before creating next PIEPER
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
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			int pp300 = 0;
			if(jsonObject.has("PP300"))
				pp300 = jsonObject.getInt("PP300");
			P300PPMGService p100Service = (P300PPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300PP);
			int rs = p100Service.createPieperV2(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags);
			if(rs > 0){
				N100PMGService n100pmgService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
				//13/07/2017 update oracle
				n100pmgService.upgradeTabV220(fo100, PiepmeActivity.CREATE_PUBLIC_PIEPER, ApplicationConstant.PVLOGIN_ANONYMOUS);
			}
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
