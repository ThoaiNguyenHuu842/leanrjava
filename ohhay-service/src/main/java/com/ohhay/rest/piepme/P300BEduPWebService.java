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
import com.ohhay.piepme.mongo.service.P300BEduPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;


/**
 * mobile.bonevo.net/service/p300BEduPWebService/
 * service for EDU pieper
 * @author ThoaiNH
 * create Jul 29, 2017
 */
@Path("p300BEduPWebService")
public class P300BEduPWebService {
	private static Logger log = Logger.getLogger(P300BEduPWebService.class);
	/**
	 * @param FO100 int
	 * @param PP300 (optional) int = 0 is create new pieper, > 0 is update with pieper id
	 * @param PV301 String title
	 * @param PN303 String
	 * @param PV304THUMB String
	 * @param PV305 String
	 * @param PV314 String
	 * @param PN309 float
	 * @param OTAGS String tu khoa bat
	 * @param FT110 int id COM
	 * @param TV119 String 'EOM' - gui cho COM1, 'EOM_PH' - gui cho COM2 phu huynh, 'EOM_HS' - gui cho COM2 hoc sinh
	 * @return: > 0 success, >= 0: user don't have sufficient privileges to create EDU pieper
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
			String pv314 = jsonObject.get("PV314").toString();
			float pn309 = Float.parseFloat(jsonObject.getString("PN309"));
			String otags = null;
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			int pp300 = 0;
			if(jsonObject.has("PP300"))
				pp300 = jsonObject.getInt("PP300");
			int ft110 = Integer.parseInt(jsonObject.get("FT110").toString());
			String tv119 = jsonObject.get("TV119").toString();
			P300BEduPMGService p300bEduPMGService = (P300BEduPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BEDUP);
			return QbRestUtils.convertToJsonStringForFunc(p300bEduPMGService.createPieperEdu(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn309, pv314, otags, ft110, tv119));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * danh sach pieper thuoc COM1 hoac COM2
	 * @param FO100 int
	 * @param FT110 int id channel 
	 * @param TV119 String 'EOM', 'EOM_PH' or 'EOM_HS'
	 * @param PVSEARCH String
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListPieper")
	@Produces("application/json")
	public String getListPieperEM(@QueryParam("FO100") int fo100, @QueryParam("FT110") int ft110, @QueryParam("TV119") String tv119,
			@QueryParam("PVSEARCH") String pvSearch, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300BEduPMGService p300bEduPMGService = (P300BEduPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BEDUP);
			return QbRestUtils.convertToJsonStringForProc(p300bEduPMGService.getListPieper(fo100, ft110, tv119, pvSearch, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * get commercial Edu detail
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
			P300BEduPMGService p300bEduPMGService = (P300BEduPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300BEDUP);
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
			return QbRestUtils.convertToJsonStringForProc(p300bEduPMGService.getPieperDetail(fo100,pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
