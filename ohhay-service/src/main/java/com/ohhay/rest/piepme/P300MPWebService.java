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
import com.ohhay.base.mysql.service.R100CentService;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.constant.PiepmeActivity;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300MMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.PieperImg;
import com.ohhay.piepme.mongo.service.N100PMGService;
import com.ohhay.piepme.mongo.service.P300MPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * mobile.bonevo.net/service/p300MWebService/
 * service for message(private) pieper
 * @author ThoaiNH
 * create Jun 3, 2016
 */
@Path("p300MWebService")
public class P300MPWebService {
	private static Logger log = Logger.getLogger(P300MPWebService.class);
	/**
	 * @param FO100 int
	 * @param PV301 String
	 * @param PN303 int
	 * @param PN306 int
	 * @param PV304 String
	 * @param PV304THUMB String
	 * @param PV305 String
	 * @param PV314 String
	 * @param PN309 int
	 * @param OTAGS String
	 * @param FO100R List<Integer>
	 * @param PIEPER_IMGS (optional) JSON array i.g: [{"URL":"","DES":"",RATIO:1.3}]
	 * @return
	 */
	@POST
	@Path("createPieper")
	@Produces("application/json")
	public String createPieper(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			final int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pv301 = jsonObject.get("PV301").toString();
			int pn303 = Integer.parseInt(jsonObject.get("PN303").toString());
			int pn306 = Integer.parseInt(jsonObject.get("PN306").toString());
			String pv304 = jsonObject.get("PV304").toString();
			String pv304Thumb = jsonObject.get("PV304THUMB").toString();
			String pv305 = jsonObject.get("PV305").toString();
			String pv314 = jsonObject.get("PV314").toString();
			float pn309 = Float.parseFloat(jsonObject.getString("PN309"));
			String otags = null;
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			List<Integer> listFO100R = new ArrayList<Integer>();
			if(jsonObject.has("FO100R"))
			{
				String fo100Rs[]  = jsonObject.get("FO100R").toString().split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
				for(String fo100String: fo100Rs)
				{
					int fo100R = Integer.parseInt(fo100String);
					listFO100R.add(fo100R);
				}
			}
			List<PieperImg> pieperImgs = new ArrayList<PieperImg>();
			if(jsonObject.has("PIEPER_IMGS")){
				JSONArray jsonArray = jsonObject.getJSONArray("PIEPER_IMGS");
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject img = jsonArray.getJSONObject(i);
					PieperImg pieperImg = new PieperImg(img.getString("URL"),img.getString("DES"),img.getDouble("RATIO"));
					pieperImgs.add(pieperImg);
				}
			}
			P300MPMGService p300Service = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			int rs = p300Service.createPieper(fo100, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, listFO100R, pieperImgs);
			/*
			 * nhan voucher tu piepme
			 */
			if(rs > 0){
				final List<PieperImg> pieperImgsTemp = new ArrayList<PieperImg>(pieperImgs);
				Thread thread = new Thread(){
				    public void run(){
				    	N100PMGService n100pmgService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
						//13/07/2017 update oracle
						if(pieperImgsTemp.size() > 0)
							n100pmgService.upgradeTabV220(fo100, PiepmeActivity.CREATE_LONG_MESSAGE_PIEPER, ApplicationConstant.PVLOGIN_ANONYMOUS);
						else
							n100pmgService.upgradeTabV220(fo100, PiepmeActivity.CREATE_MESSAGE_PIEPER, ApplicationConstant.PVLOGIN_ANONYMOUS);
				    }
				};
				thread.start();
			}
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * detail pieper
	 * @param FO100 int
	 * @param PP300 int
	 * @param SEEN int = 1 set date seen for message
	 * @return
	 */
	@GET
	@Path("getPieperDetail")
	@Produces("application/json")
	public String getPieperDetail(@QueryParam("FO100") int fo100, @QueryParam("PP300") int pp300, @QueryParam("SEEN") int pnSeen) {
		try {
			P300MPMGService p300pService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			return QbRestUtils.convertToJsonStringForProc(p300pService.getPieperDetail(fo100,pp300,pnSeen));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @deprecated replaced by {@link #listOfTabP300MConV2(int, String, int, int, int)}
	 * list conversation of user
	 * @param FO100 int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabP300MCon")
	@Produces("application/json")
	public String listOfTabP300MCon(@QueryParam("FO100") int fo100, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300MPMGService p300pService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			return QbRestUtils.convertToJsonStringForProc(p300pService.listOfTabP300MCon(fo100, pvSearch, sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @deprecated replaced by {@link #listOfTabP300MV2(int, int, String, int, int, int)}
	 * list piper in the conservation between pnFO100 & pnFO100R
	 * @param FO100 int
	 * @param FO100F int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabP300M")
	@Produces("application/json")
	public String listOfTabP300M(@QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300MPMGService p300pService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			return QbRestUtils.convertToJsonStringForProc(p300pService.listOfTabP300M(fo100, fo100f, pvSearch, sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * list conversation of user
	 * @param FO100 int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabP300MArchived")
	@Produces("application/json")
	public String listOfTabP300MArchived(@QueryParam("FO100") int fo100, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300MPMGService p300pService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			return QbRestUtils.convertToJsonStringForProc(p300pService.listOfTabP300MArchived(fo100, pvSearch, sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * remove pieper, only allow if 24h after creating
	 * @param FO100 int
	 * @param FO100F int
	 * @return
	 */
	@POST
	@Path("storNoTabP300MCon")
	@Produces("application/json")
	public String storNoTabP300MCon(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100f = Integer.parseInt(jsonObject.get("FO100F").toString());
			P300MPMGService p300pService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			return QbRestUtils.convertToJsonStringForFunc(p300pService.storNoTabP300MCon(fo100, fo100f));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * pnFO100 ARCHIVES conversation of pnFO100F
	 * @param FO100
	 * @param FO100F
	 * @return
	 */
	@POST
	@Path("archiveTabP300")
	@Produces("application/json")
	public String archiveTabP300(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100f = Integer.parseInt(jsonObject.get("FO100F").toString());
			P300MPMGService p300pService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			return QbRestUtils.convertToJsonStringForFunc(p300pService.archiveTabP300(fo100, fo100f));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * update trang thai SENT, RECEIVED, SEEN khi info center gui pieper
	 * @param FO100 int
	 * @param PP300 int
	 * @param DELIVERY_STT int 1 = SENT, 2 = RECEIVED, 3 = SEEN
	 * @return
	 */
	@POST
	@Path("updateDeliveryStt")
	@Produces("application/json")
	public String updateDeliveryStt(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			int deliverysStt = Integer.parseInt(jsonObject.get("DELIVERY_STT").toString());
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			if(deliverysStt != 3)
				return QbRestUtils.convertToJsonStringForFunc(templateService.updateOneField(fo100, P300MMG.class, pp300, "DELIVERY_STT", deliverysStt, null));
			else
				return QbRestUtils.convertToJsonStringForFunc(templateService.updateOneField(fo100, P300MMG.class, pp300, "DELIVERY_STT", deliverysStt, "PD309"));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param PV301 String
	 * @param PV302 String
	 * @param PN303 int
	 * @param PN306 int
	 * @param PV304 String
	 * @param PV304THUMB String
	 * @param PV305 String
	 * @param PV314 String
	 * @param PN309 int
	 * @param OTAGS String
	 * @param FO100R List<Integer>
	 * @param PIEPER_IMGS (optional) JSON array i.g: [{"URL":"","DES":"",RATIO:1.3}]
	 * @return
	 */
	@POST
	@Path("createPieperV2")
	@Produces("application/json")
	public String createPieperV2(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			final int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pv301 = jsonObject.get("PV301").toString();
			String pv302 = jsonObject.get("PV302").toString();
			int pn303 = Integer.parseInt(jsonObject.get("PN303").toString());
			int pn306 = Integer.parseInt(jsonObject.get("PN306").toString());
			String pv304 = jsonObject.get("PV304").toString();
			String pv304Thumb = jsonObject.get("PV304THUMB").toString();
			String pv305 = jsonObject.get("PV305").toString();
			String pv314 = jsonObject.get("PV314").toString();
			float pn309 = Float.parseFloat(jsonObject.getString("PN309"));
			String otags = null;
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			List<Integer> listFO100R = new ArrayList<Integer>();
			if(jsonObject.has("FO100R"))
			{
				String fo100Rs[]  = jsonObject.get("FO100R").toString().split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
				for(String fo100String: fo100Rs)
				{
					int fo100R = Integer.parseInt(fo100String);
					listFO100R.add(fo100R);
				}
			}
			List<PieperImg> pieperImgs = new ArrayList<PieperImg>();
			if(jsonObject.has("PIEPER_IMGS")){
				JSONArray jsonArray = jsonObject.getJSONArray("PIEPER_IMGS");
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject img = jsonArray.getJSONObject(i);
					PieperImg pieperImg = new PieperImg(img.getString("URL"),img.getString("DES"),img.getDouble("RATIO"));
					pieperImgs.add(pieperImg);
				}
			}
			P300MPMGService p300Service = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			int rs = p300Service.createPieperV2(fo100, pv301, pv302, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, listFO100R, pieperImgs);
			/*
			 * nhan voucher tu piepme
			 */
			if(rs > 0){
				final List<PieperImg> pieperImgsTemp = new ArrayList<PieperImg>(pieperImgs);
				Thread thread = new Thread(){
				    public void run(){
				    	N100PMGService n100pmgService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
						//13/07/2017 update oracle
						if(pieperImgsTemp.size() > 0)
							n100pmgService.upgradeTabV220(fo100, PiepmeActivity.CREATE_LONG_MESSAGE_PIEPER, ApplicationConstant.PVLOGIN_ANONYMOUS);
						else
							n100pmgService.upgradeTabV220(fo100, PiepmeActivity.CREATE_MESSAGE_PIEPER, ApplicationConstant.PVLOGIN_ANONYMOUS);
				    }
				};
				thread.start();
			}
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * JSON array with each object's field:
		 * @param FO100 int
		 * @param PV301 String
		 * @param PN303 int
		 * @param PN306 int
		 * @param PV304 String
		 * @param PV304THUMB String
		 * @param PV305 String
		 * @param PV314 String
		 * @param PN309 int
		 * @param OTAGS String
		 * @param FO100R List<Integer>
		 * @param PIEPER_IMGS (optional) JSON array i.g: [{"URL":"","DES":"",RATIO:1.3}]
	 * @return 1
	 */
	@POST
	@Path("createPieperMulti")
	@Produces("application/json")
	public String createPieperMulti(String postString) {
		try {
			log.info(postString);
			JSONArray data = new JSONArray(postString);
			List<Integer> listIds = new ArrayList<Integer>();
			for(int l = 0; l < data.length(); l++){
				JSONObject jsonObject = new JSONObject(data.get(l));
				final int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
				String pv301 = jsonObject.get("PV301").toString();
				int pn303 = Integer.parseInt(jsonObject.get("PN303").toString());
				int pn306 = Integer.parseInt(jsonObject.get("PN306").toString());
				String pv304 = jsonObject.get("PV304").toString();
				String pv304Thumb = jsonObject.get("PV304THUMB").toString();
				String pv305 = jsonObject.get("PV305").toString();
				String pv314 = jsonObject.get("PV314").toString();
				float pn309 = Float.parseFloat(jsonObject.getString("PN309"));
				String otags = null;
				if(jsonObject.has("OTAGS"))
					otags = jsonObject.get("OTAGS").toString();
				List<Integer> listFO100R = new ArrayList<Integer>();
				if(jsonObject.has("FO100R"))
				{
					String fo100Rs[]  = jsonObject.get("FO100R").toString().split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
					for(String fo100String: fo100Rs)
					{
						int fo100R = Integer.parseInt(fo100String);
						listFO100R.add(fo100R);
					}
				}
				List<PieperImg> pieperImgs = new ArrayList<PieperImg>();
				if(jsonObject.has("PIEPER_IMGS")){
					JSONArray jsonArray = jsonObject.getJSONArray("PIEPER_IMGS");
					for(int i = 0; i < jsonArray.length(); i++){
						JSONObject img = jsonArray.getJSONObject(i);
						PieperImg pieperImg = new PieperImg(img.getString("URL"),img.getString("DES"),img.getDouble("RATIO"));
						pieperImgs.add(pieperImg);
					}
				}
				P300MPMGService p300Service = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
				int newIds = p300Service.createPieper(fo100, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, listFO100R, pieperImgs);
				/*
				 * nhan voucher tu piepme
				 */
				if(newIds > 0){
					listIds.add(newIds);
					final List<PieperImg> pieperImgsTemp = new ArrayList<PieperImg>(pieperImgs);
					Thread thread = new Thread(){
					    public void run(){
					    	N100PMGService n100pmgService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
							//13/07/2017 update oracle
							if(pieperImgsTemp.size() > 0)
								n100pmgService.upgradeTabV220(fo100, PiepmeActivity.CREATE_LONG_MESSAGE_PIEPER, ApplicationConstant.PVLOGIN_ANONYMOUS);
							else
								n100pmgService.upgradeTabV220(fo100, PiepmeActivity.CREATE_MESSAGE_PIEPER, ApplicationConstant.PVLOGIN_ANONYMOUS);
					    }
					};
					thread.start();
				}
			}
			return QbRestUtils.convertToJsonStringForFunc(listIds);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * list conversation of user
	 * @param FO100 int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabP300MConV2")
	@Produces("application/json")
	public String listOfTabP300MConV2(@QueryParam("FO100") int fo100, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300MPMGService p300pService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			return QbRestUtils.convertToJsonStringForProc(p300pService.listOfTabP300MConV2(fo100, pvSearch, sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * list piper in the conservation between pnFO100 & pnFO100R
	 * @param FO100 int
	 * @param FO100F int
	 * @param PVSEARCH String
	 * @param SORT int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabP300MV2")
	@Produces("application/json")
	public String listOfTabP300MV2(@QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			P300MPMGService p300pService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			return QbRestUtils.convertToJsonStringForProc(p300pService.listOfTabP300MV2(fo100, fo100f, pvSearch, sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * pnFO100 pins a message
	 * @param FO100
	 * @param PP300
	 * @return
	 */
	@POST
	@Path("pinTabP300M")
	@Produces("application/json")
	public String pinTabP300M(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			P300MPMGService p300pService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			return QbRestUtils.convertToJsonStringForFunc(p300pService.pinTabP300M(fo100, pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * pnFO100 unpins a message
	 * @param FO100
	 * @param PP300
	 * @return
	 */
	@POST
	@Path("unpinTabP300M")
	@Produces("application/json")
	public String unpinTabP300M(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			P300MPMGService p300pService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			return QbRestUtils.convertToJsonStringForFunc(p300pService.unpinTabP300M(fo100, pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
