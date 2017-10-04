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
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.constant.PiepmeActivity;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.service.N100PMGService;
import com.ohhay.piepme.mongo.service.P300BRePMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;


/**
 * mobile.bonevo.net/service/p300BRePWebService/
 * service for commercial pieper
 * @author ThoaiNH
 * create Jun 22, 2017
 */
@Path("p300BRePWebService")
public class P300BRePWebService {
	private static Logger log = Logger.getLogger(P300BRePWebService.class);
	
	/**
	 * @deprecated replaced by {@link #createPieperV2(String)}
	 * @param FO100 int
	 * @param PP300 (optional) int = 0 is create new pieper, > 0 is update with pieper id
	 * @param PV301 String
	 * @param PN303 String
	 * @param PV304 String
	 * @param PV304THUMB String
	 * @param PV305 String
	 * @param PN306 int
	 * @param PV314 String
	 * @param PN309 float
	 * @param OTAGS String
	 * @param FT330 int
	 * @param LAT double
	 * @param LONG double
	 * @param ADDRESSFULLNAME String
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
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			int ft330 = jsonObject.getInt("FT330");
			int pp300 = 0;
			if(jsonObject.has("PP300"))
				pp300 = jsonObject.getInt("PP300");
			double latitude = Double.parseDouble(jsonObject.getString("LAT"));
			double longitude = Double.parseDouble(jsonObject.getString("LONG"));
			String addressFullName = jsonObject.getString("ADDRESSFULLNAME");
			P300BRePMGService p100Service = (P300BRePMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BREP);
			return QbRestUtils.convertToJsonStringForFunc(p100Service.createPieper(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, ft330, latitude, longitude, addressFullName));
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
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param FT310 int
	 * @param FT320 int
	 * @param FT330 int
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListPieperRE")
	@Produces("application/json")
	public String getListPieperRE(@QueryParam("LATITUDE") double pnLa, @QueryParam("LONGITUDE") double pnLong, @QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,
			@QueryParam("FT310") int ft310, @QueryParam("FT320") int ft320, @QueryParam("FT330") int ft330, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300BRePMGService p100Service = (P300BRePMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BREP);
			return QbRestUtils.convertToJsonStringForProc(p100Service.getListPieper(pnLa, pnLong, fo100, fo100f, pvSearch, sort, ft310, ft320, ft330, pnOffset, pnLimit));
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
			P300BRePMGService p100Service = (P300BRePMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BREP);
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
	 * @param FT330 int
	 * @param LAT double
	 * @param LONG double
	 * @param ADDRESSFULLNAME String
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
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			int ft330 = jsonObject.getInt("FT330");
			int pp300 = 0;
			if(jsonObject.has("PP300"))
				pp300 = jsonObject.getInt("PP300");
			double latitude = Double.parseDouble(jsonObject.getString("LAT"));
			double longitude = Double.parseDouble(jsonObject.getString("LONG"));
			String addressFullName = jsonObject.getString("ADDRESSFULLNAME");
			P300BRePMGService p100Service = (P300BRePMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BREP);
			return QbRestUtils.convertToJsonStringForFunc(p100Service.createPieperV2(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, ft330, latitude, longitude, addressFullName));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
