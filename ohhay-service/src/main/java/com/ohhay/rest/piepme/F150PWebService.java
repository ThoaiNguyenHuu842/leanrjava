package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mysql.service.R100CentService;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.service.V220ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.constant.PiepmeActivity;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300PPMG;
import com.ohhay.piepme.mongo.service.F150PMGService;
import com.ohhay.piepme.mongo.service.N100PMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * mobile.bonevo.net/service/f150PWebService/
 * following user
 * @author TuNt
 * create date Oct 28, 2016
 */
@Path("f150PWebService")
public class F150PWebService {
	/**
	 * get all user follows pnFO100
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListOfTabF150")
	@Produces("application/json")
	public String getListOfTabF150(@QueryParam("FO100") int fo100,
								   @QueryParam("OFFSET") int offset,
								   @QueryParam("LIMIT") int limit){
		try {
			F150PMGService f150pmgService = (F150PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_F150P);
			return QbRestUtils.convertToJsonStringForProc(f150pmgService.listOfTabF150(fo100, offset, limit));
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get all user pnFO100 follows
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListOfTabF150T")
	@Produces("application/json")
	public String getListOfTabF150T(@QueryParam("FO100") int fo100,
									@QueryParam("OFFSET") int offset,
									@QueryParam("LIMIT") int limit){
		try {
			F150PMGService f150pmgService = (F150PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_F150P);
			return QbRestUtils.convertToJsonStringForProc(f150pmgService.listOfTabF150T(fo100, offset, limit));
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * check follow status
	 * @param FO100 int
	 * @param FO100T int
	 * @return
	 */
	@GET
	@Path("checkFollowStatus")
	@Produces("application/json")
	public String checkFollowStatus(@QueryParam("FO100") int fo100,
								    @QueryParam("FO100T") int fo100t){
		try {
			F150PMGService f150pmgService = (F150PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_F150P);
			return QbRestUtils.convertToJsonStringForProc(f150pmgService.checkFollowStatus(fo100, fo100t));
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * inser follow info
	 * fo100 click follow
	 * fo100 followed
	 * @param FO100 int 
	 * @param FO100T int
	 * @param FP300 int
	 * @param PIPER_TYPE String
	 * @return
	 */
	@POST
	@Path("insertTabF150")
	@Produces("application/json")
	public String insertTabF150(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			final int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			final int fo100t = Integer.parseInt(jsonObject.get("FO100T").toString());
			final int fp300 = Integer.parseInt(jsonObject.get("FP300").toString());
			final String piperType = jsonObject.getString("PIPER_TYPE");
			String uuid = null;
			if(jsonObject.has("UUID"))
				uuid = jsonObject.getString("UUID");
			/*
			 * 1) insert tracking
			 */
			if(jsonObject.has("DEVICE")){
				final String device = jsonObject.getString("DEVICE");
				Thread thread = new Thread(){
				    public void run(){
				    	TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				    	templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				    	N100PMGService n100pmgService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
				    	N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				    	if("P300B".equals(piperType)){
							P300BPMG p300ppmg = templateService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, fp300, P300BPMG.class);
							R100CentService r100CentService = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
							r100CentService.insertTabR1002017dis("FOL", p300ppmg.getPv301(), device, null, 0, n100pmgService.getDistanceFromEnterprise(fo100, p300ppmg.getFo100()), fp300, fo100, p300ppmg.getFo100(), n100pmg.getNv101());
				    	}
						//13/07/2017 update oracle
						n100pmgService.upgradeTabV220(fo100, PiepmeActivity.FOLLOW, n100pmg.getNv101());
				    }
				};
				thread.start();
			}
			/* 
			 * 2) insert following
			 */
			F150PMGService f150pmgService = (F150PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_F150P);
			return QbRestUtils.convertToJsonStringForFunc(f150pmgService.insertTabF150(fo100, fo100t, fp300, piperType, uuid));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * remove follow
	 * @param FO100 int
	 * @param FO100T int
	 * @return
	 */
	@POST
	@Path("removeF150")
	@Produces("application/json")
	public String removeF150(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100t = Integer.parseInt(jsonObject.get("FO100T").toString());
			F150PMGService f150pmgService = (F150PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_F150P);
			return QbRestUtils.convertToJsonStringForFunc(f150pmgService.storNoTabF150(fo100, fo100t));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
