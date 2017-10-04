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
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.service.V220ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.constant.PiepmeActivity;
import com.ohhay.piepme.mongo.entities.pieper.P300PPMG;
import com.ohhay.piepme.mongo.service.N100PMGService;
import com.ohhay.piepme.mongo.service.PieperService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * mobile.bonevo.net/service/pieperWebService/
 * common service for all pieper type
 * @author ThoaiNH
 * create Nov 11, 2016
 */
@Path("pieperWebService")
public class PieperWebService {
	/**
	 * @param PIPER_TYPE String
	 * @param FO100 int
	 * @param PV301 String
	 * @param PN303 int
	 * @param PV304 String
	 * @param PV305 String
	 * @param PV314 String
	 * @param PN309 int
	 * @param OTAGS String
	 * @return
	 */
	@POST
	@Path("createPieper")
	@Produces("application/json")
	public String createPieper(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String piperType = jsonObject.getString("PIPER_TYPE");
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pv301 = jsonObject.get("PV301").toString();
			int pn303 = Integer.parseInt(jsonObject.get("PN303").toString());
			String pv304 = jsonObject.get("PV304").toString();
			String pv305 = jsonObject.get("PV305").toString();
			String pv314 = jsonObject.get("PV314").toString();
			float pn309 = Float.parseFloat(jsonObject.getString("PN309"));
			String otags = null;
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
//			GeoDataPointMG loc = null;
//			if(jsonObject.has("LONGITUDE") && jsonObject.has("LATITUDE")){
//				try {
//					Double longitude = Double.parseDouble(jsonObject.getString("LONGITUDE"));
//					Double latitude = Double.parseDouble(jsonObject.getString("LATITUDE"));
//					loc = new GeoDataPointMG(longitude,latitude);
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//			}
			PieperService pieperService = (PieperService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForFunc(pieperService.createPieper(fo100, pv301, pn303, pv304, pv305, pn309, pv314, otags, piperType));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * kiem trang pnFO100 co like PIEPER hay k
	 * @param FO100 int
	 * @param PIPER_TYPE String
	 * @param PIPER_ID int
	 * @return
	 */
	@POST
	@Path("checkTabUserLike")
	@Produces("application/json")
	public String checkTabUserLike(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String piperType = jsonObject.get("PIPER_TYPE").toString();
			int piperId = Integer.parseInt(jsonObject.get("PIPER_ID").toString());
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForFunc(pieperService.checkTabUserLike(piperType, piperId, fo100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param PIPER_TYPE String
	 * @param PIPER_ID int
	 * @param DEVICE String
	 * @return total like current
	 */
	@POST
	@Path("insertTabLike")
	@Produces("application/json")
	public String insertTabLike(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			final int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String piperType = jsonObject.get("PIPER_TYPE").toString();
			final int piperId = Integer.parseInt(jsonObject.get("PIPER_ID").toString());
			/*
			 * insert tracking
			 */
			if(QbMongoCollectionsName.P300B.equals(piperType) && jsonObject.has("DEVICE")){
				final String device = jsonObject.getString("DEVICE");
				Thread thread = new Thread(){
				    public void run(){
				    	N100PMGService n100pmgService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
				    	TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
						P300PPMG p300ppmg = templateService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, piperId, P300PPMG.class);
						N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
						R100CentService r100CentService = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
						//update mysql
						r100CentService.insertTabR1002017dis("LIK", p300ppmg.getPv301(), device, null, 0, n100pmgService.getDistanceFromEnterprise(fo100, p300ppmg.getFo100()), piperId, fo100, p300ppmg.getFo100(), n100pmg.getNv101());
						//13/07/2017 update oracle
						n100pmgService.upgradeTabV220(fo100, PiepmeActivity.LIKE, n100pmg.getNv101());
				    }
				};
				thread.start();
			}
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForFunc(pieperService.insertTabLike(piperType, piperId, fo100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * remove like from pieper
	 * @param FO100 int
	 * @param PIPER_TYPE String
	 * @param PIPER_ID int
	 * @return
	 */
	@POST
	@Path("stornoTabLike")
	@Produces("application/json")
	public String stornoTabLike(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String piperType = jsonObject.get("PIPER_TYPE").toString();
			int piperId = Integer.parseInt(jsonObject.get("PIPER_ID").toString());
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForFunc(pieperService.stornoTabLike(piperType, piperId, fo100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * insert, update comment
	 * @param PIPER_TYPE String
	 * @param COMMENT_ID int
	 * @param PIPER_ID int
	 * @param FO100 int
	 * @param COMMENT String
	 * @return
	 */
	@POST
	@Path("insertTabComment")
	@Produces("application/json")
	public String insertTabComment(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String piperType = jsonObject.getString("PIPER_TYPE");
			int commentID = jsonObject.getInt("COMMENT_ID");
			int piperId = jsonObject.getInt("PIPER_ID");
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String comment = jsonObject.getString("COMMENT");
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForFunc(pieperService.insertTabComment(piperType, commentID, piperId, fo100, comment));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * remove comment
	 * @param PIPER_TYPE String
	 * @param PIPER_ID int
	 * @param COMMENT_ID String
	 * @param FO100 int
	 * @return
	 */
	@POST
	@Path("stornoTabComment")
	@Produces("application/json")
	public String stornoTabComment(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String piperType = jsonObject.getString("PIPER_TYPE");
			int piperId = jsonObject.getInt("PIPER_ID");
			int commentID = jsonObject.getInt("COMMENT_ID");
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForFunc(pieperService.stornoTabComment(piperType, piperId, commentID, fo100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * lay danh sach comment cua topic pnTopicId
	 * @param PIPER_TYPE String
	 * @param PIPER_ID int
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("pieperListOfTabComment")
	@Produces("application/json")
	public String pieperListOfTabComment(@QueryParam("PIPER_TYPE") String piperType,@QueryParam("PIPER_ID") int piperId, @QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForProc(
					pieperService.pieperListOfTabComment(piperType, piperId, fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * deatail of comment
	 * @param PIPER_TYPE String
	 * @param PIPER_ID int
	 * @param FO100 int
	 * @param COMMENT_ID int
	 * @return
	 */
	@GET
	@Path("pieperListOfTabCommentDetail")
	@Produces("application/json")
	public String pieperListOfTabCommentDetail(@QueryParam("PIPER_TYPE") String piperType,@QueryParam("PIPER_ID") int piperId, @QueryParam("FO100") int fo100,
			@QueryParam("COMMENT_ID") int commentId) {
		try {
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForProc(
					pieperService.pieperListOfTabCommentDetail(piperType, piperId, fo100, commentId));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param URL String
	 * @return
	 */
	@GET
	@Path("getInfoFromURL")
	@Produces("application/json")
	public String getInfoFromURL(@QueryParam("URL") String url) {
		try {
			return QbRestUtils.convertToJsonStringForProc(ApplicationHelper.getInfoFromURL(url));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * list users liked a pieper
	 * @param PIPER_TYPE String
	 * @param PIPER_ID int
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("pieperListOfTabUserLike")
	@Produces("application/json")
	public String pieperListOfTabUserLike(@QueryParam("PIPER_TYPE") String piperType,@QueryParam("PIPER_ID") int piperId, @QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForProc(
					pieperService.pieperListOfTabUserLike(piperType, piperId, fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * repiep a pieper
	 * @param PIPER_TYPE String
	 * @param PIPER_ID int
	 * @param FO100 int
	 * @return
	 */
	@POST
	@Path("repiep")
	@Produces("application/json")
	public String repiep(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String piperType = jsonObject.getString("PIPER_TYPE");
			int piperId = jsonObject.getInt("PIPER_ID");
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForFunc(pieperService.repiep(piperType, piperId, fo100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * set draft pieper to live
	 * @param PIPER_TYPE String
	 * @param PIPER_ID int
	 * @param FO100 int
	 * @return
	 */
	@POST
	@Path("setDraftPieperToLive")
	@Produces("application/json")
	public String setDraftPieperToLive(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String piperType = jsonObject.getString("PIPER_TYPE");
			int piperId = jsonObject.getInt("PIPER_ID");
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForFunc(pieperService.pieperUpdateTabPn306(piperType, piperId, fo100, 0));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * list users viewed a pieper
	 * @param PIPER_TYPE String
	 * @param PIPER_ID int
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("pieperListOfTabUserView")
	@Produces("application/json")
	public String pieperListOfTabUserView(@QueryParam("PIPER_TYPE") String piperType,@QueryParam("PIPER_ID") int piperId, @QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			PieperService pieperService = (PieperService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PIEPER);
			return QbRestUtils.convertToJsonStringForProc(
					pieperService.pieperListOfTabUserView(piperType, piperId, fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
