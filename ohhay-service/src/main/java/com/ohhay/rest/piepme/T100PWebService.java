package com.ohhay.rest.piepme;

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
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.nestedentities.COMInfo;
import com.ohhay.piepme.mongo.service.T100PMGService;

/**
 * mobile.bonevo.net/service/t100PWebService/
 * user's OTAGS service
 * @author ThoaiNH 
 * create Sep 21, 2016 
 */
@Path("t100PWebService")
public class T100PWebService {
	private TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
	private Logger logger = Logger.getLogger(T100PWebService.class);
	/**
	 * @param FO100 int
	 * @param PT100 int
	 * @param FIELD String
	 * @param VALUE String
	 * @return
	 */
	@POST
	@Path("updateOneField")
	@Produces("application/json")
	public String updateOneField(String postString) {
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt100 = Integer.parseInt(jsonObject.get("PT100").toString());
			String field = jsonObject.get("FIELD").toString();
			String value = jsonObject.get("VALUE").toString();
			int kq = templateService.updateOneField(fo100, T100PMG.class, field, value, "TL146", new QbCriteria(
													QbMongoFiledsName.ID, pt100), new QbCriteria(
												    QbMongoFiledsName.FO100, fo100));
			try {
				if("TV101".equals(field)){
					templateService.updateOneField(fo100, T100PMG.class, "TV105", AESUtils.decrypt(value), "TL146", new QbCriteria(
							QbMongoFiledsName.ID, pt100), new QbCriteria(
						    QbMongoFiledsName.FO100, fo100));
					templateService.updateOneField(fo100, T100PMG.class, "TV105_STEM",ApplicationHelper.getStemOtag(AESUtils.decrypt(value).toLowerCase()), "TL146", new QbCriteria(
							QbMongoFiledsName.ID, pt100), new QbCriteria(
						    QbMongoFiledsName.FO100, fo100));
				}
				
				templateService.setAccessDBcentPiepme(true);
				templateService.updateOneField(fo100, T100PMG.class, field, value, "TL146", new QbCriteria(
						QbMongoFiledsName.ID, pt100), new QbCriteria(
					    QbMongoFiledsName.FO100, fo100));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int 
	 * @param TV101 String
	 * @param TV102 String
	 * @param TV103 String
	 * @return
	 */
	@POST
	@Path("createT100")
	@Produces("application/json")
	public String createT100(String postString) {
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		try {
			SequenceService sequenceService = (SequenceService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
			T100PMGService t100pmgService = (T100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_T100P);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String tv101 = jsonObject.get("TV101").toString();
			String tv102 = null;
			if (jsonObject.has("TV102"))
				tv102 = jsonObject.get("TV102").toString();
			String tv103 = jsonObject.get("TV103").toString();
			int kq = t100pmgService.createT100(fo100, tv101, tv102, tv103);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int 
	 * @param PT100 int
	 * @return
	 */
	@POST
	@Path("removeT100")
	@Produces("application/json")
	public String removeT100(String postString) {
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt100 = Integer.parseInt(jsonObject.get("PT100").toString());
			T100PMGService t100pmgService = (T100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T100P);
			return QbRestUtils.convertToJsonStringForFunc(t100pmgService.removeT100(fo100, pt100));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int 
	 * @return
	 */
	@GET
	@Path("getListT100")
	@Produces("application/json")
	public String getListT100(@QueryParam("FO100") int fo100, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			T100PMGService t100pmgService = (T100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T100P);
			return QbRestUtils.convertToJsonStringForProc(t100pmgService.getListT100(fo100, pnOffset, pnLimit));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @deprecated
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("getListT100Def")
	@Produces("application/json")
	public String getListT100Def(@QueryParam("FO100") int fo100) {
		try {
			T100PMGService t100pmgService = (T100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T100P);
			return QbRestUtils.convertToJsonStringForProc(t100pmgService.getListT100Def(fo100, 0, 10000));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param PT100 int
	 * @param TV101 String
	 * @param TV102 String
	 * @param TV103 String
	 * @return
	 */
	@POST
	@Path("update")
	@Produces("application/json")
	public String update(String postString) {
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt100 = Integer.parseInt(jsonObject.get("PT100").toString());
			String tv101 = jsonObject.get("TV101").toString();
			String tv102 = jsonObject.get("TV102").toString();
			String tv103 = jsonObject.get("TV103").toString();
			T100PMG t100pmg = templateService.findDocument(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.ID, pt100), 
																				 new QbCriteria(QbMongoFiledsName.FO100, fo100));
			int kq = 0;
			if(t100pmg != null){
				t100pmg.setTv101(tv101);
				t100pmg.setTv102(tv102);
				t100pmg.setTv103(tv103);
				kq = templateService.saveDocument(fo100, t100pmg, QbMongoCollectionsName.T100);
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * register royal customer
	 * @param FO100 int
	 * @param PIEPME_ID String
	 * @return -1 PIEPME_ID is invalid or account has been registered yet, > 0 success, =0 error
	 
	@POST
	@Path("registerLoyaltyCustomer")
	@Produces("application/json")
	public String registerLoyaltyCustomer(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String piepmeId = jsonObject.get("PIEPME_ID").toString();
			T100PMGService t100pmgService = (T100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T100P);
			int kq = t100pmgService.registerLoyaltyCustomer(fo100, piepmeId);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}*/
	
	/**
	 * @param FO100 int 
	 * @param TV106 String
	 * @return
	 */
	@POST
	@Path("updateCOMBanner")
	@Produces("application/json")
	public String updateCOMBanner(String postString) {
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String tv106 = jsonObject.get("TV106").toString();
			T100PMGService t100pmgService = (T100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T100P);
			return QbRestUtils.convertToJsonStringForFunc(t100pmgService.updateCOMBanner(fo100, tv106));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * nhan notification tu doanh nghiep hay khong
	 * @param FO100 int 
	 * @param PT100 int
	 * @param EOM_STT String 'ON' or 'OFF'
	 * @return
	 */
	@POST
	@Path("setEomNotification")
	@Produces("application/json")
	public String setEomNotification(String postString) {
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt100 = Integer.parseInt(jsonObject.get("PT100").toString());
			String stt = jsonObject.get("EOM_STT").toString();
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForFunc(templateService.updateOneField(fo100, T100PMG.class, pt100, "EOM_STT",stt, "TL146"));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int 
	 * @param COLOR_CODE String
	 * @param CHANNEL_TIT String
	 * @param SHOP_TIT String
	 * @param SHOP_STT int 0/1
	 * @return
	 */
	@POST
	@Path("updateCOMInfo")
	@Produces("application/json")
	public String updateCOMInfo(String postString) {
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			int kq = -1;
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String colorCode = jsonObject.get("COLOR_CODE").toString();
			String channelTit = jsonObject.get("CHANNEL_TIT").toString();
			String shopTit = jsonObject.get("SHOP_TIT").toString();
			int shopStt = Integer.parseInt(jsonObject.get("SHOP_STT").toString());
			
			T100PMG t100pmg = templateService.findDocument(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria("TV104", T100PMG.TYPE_COM));
			if(t100pmg != null){
				COMInfo coMinfo = new COMInfo(colorCode, channelTit, shopTit, shopStt);
				t100pmg.setComInfo(coMinfo);
				kq = templateService.saveDocument(fo100,t100pmg, QbMongoCollectionsName.T100);
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("getCOMInfo")
	@Produces("application/json")
	public String getCOMInfo(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			T100PMG t100pmg = templateService.findDocument(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria("TV104", T100PMG.TYPE_COM));
			if(t100pmg != null)
				return QbRestUtils.convertToJsonStringForProc(t100pmg.getComInfo());
			return QbRestUtils.convertToJsonStringForProc(null);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
}
