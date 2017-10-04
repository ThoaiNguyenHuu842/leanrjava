package com.ohhay.rest.piepme;

import java.sql.Date;

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
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.service.R100BPMGService;
import com.ohhay.piepme.mongo.service.R100PMGService;

/**
 * mobile.bonevo.net/service/r100PWebService/
 * statistic for public pieper
 * @author ThoaiNH 
 * create Feb 16, 2017
 */
@Path("r100PWebService")
public class R100PWebService {

	private static Logger log = Logger.getLogger(R100PWebService.class);

	/**
	 * @deprecated replcate by {@link R100PWebService#r2017insertTabR100(String)}
	 * info center call when insert new pieper
	 * @param RV101 String
	 * @param RV103 String
	 * @param RV105 String
	 * @param FP100 int
	 * @param FO100V int
	 * @param FO100K int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("insertTabR100")
	@Produces("application/json")
	public String insertTabR100(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			log.info(jsonObject);
			int fp100 = Integer.parseInt(jsonObject.get("FP100").toString());
			int fo100v = Integer.parseInt(jsonObject.get("FO100V").toString());
			int fo100k = Integer.parseInt(jsonObject.get("FO100K").toString());
			R100BPMGService r100bpmgService = (R100BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R100BP);
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			P300BPMG p300bpmg = templateService.findDocumentById(fo100k, fp100, P300BPMG.class);
			int rn101 = 1;
			if(p300bpmg != null && p300bpmg.getPa315() != null)
				rn101 = p300bpmg.getPa315().size();
			r100bpmgService.insertTabR100B(fo100v, fp100, fo100k, rn101, "SEN");
			return QbRestUtils.convertToJsonStringForFunc(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FP100 int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabR100Pie")
	@Produces("application/json")
	public String listOfTabR100Pie(@QueryParam("FP100") int fp100, @QueryParam("PVLOGIN") String pvLogin) {
		try {
			R100CentService r100CentService = (R100CentService) ApplicationHelper
					.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			return QbRestUtils.convertToJsonStringForProc(r100CentService.listOfTabR100Pie(fp100, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100K int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOftabR100Dev")
	@Produces("application/json")
	public String listOftabR100Dev(@QueryParam("FO100K") int fo100k, @QueryParam("PVLOGIN") String pvlogin) {
		try {
			R100CentService r100CentService = (R100CentService) ApplicationHelper
					.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			return QbRestUtils.convertToJsonStringForProc(r100CentService.listOftabR100Dev(fo100k, pvlogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @param FP300 int
	 * @return
	 */
	@GET
	@Path("listOftabR10001")
	@Produces("application/json")
	public String listOftabR10001(@QueryParam("FO100") int fo100k, @QueryParam("FP300") int fp300) {
		try {
			R100PMGService r100pmgService = (R100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R100P);
			return QbRestUtils.convertToJsonStringForProc(r100pmgService.listOfTabR10001(fo100k, fp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @param PDFRDAT Date(dd-MM-yyyy)
	 * @param PDTODAT Date(dd-MM-yyyy)
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabR100Wee")
	@Produces("application/json")
	public String listOfTabR100Wee(@QueryParam("FO100") int fo100, @QueryParam("PDFRDAT") String pdFRDAT,
			@QueryParam("PDTODAT") String pdTODAT, @QueryParam("PVLOGIN") String pvLogin) {
		try {
			R100CentService r100CentService = (R100CentService) ApplicationHelper
					.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			Date pdFRDATDate = DateHelper.stringToSQLDate(pdFRDAT,"dd-MM-yyyy");			
			Date pdTODATDate = DateHelper.stringToSQLDate(pdTODAT,"dd-MM-yyyy");
			return QbRestUtils
					.convertToJsonStringForProc(r100CentService.listOfTabR100Wee(fo100, pdFRDATDate, pdTODATDate, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @param PDFRDAT Date(dd-MM-yyyy)
	 * @param PDTODAT Date(dd-MM-yyyy)
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabR100Hrs")
	@Produces("application/json")
	public String listOfTabR100Hrs(@QueryParam("FO100") int fo100, @QueryParam("PDFRDAT") String pdFRDAT,
			@QueryParam("PDTODAT") String pdTODAT, @QueryParam("PVLOGIN") String pvLogin) {
		try {
			R100CentService r100CentService = (R100CentService) ApplicationHelper
					.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			Date pdFRDATDate = DateHelper.stringToSQLDate(pdFRDAT,"dd-MM-yyyy");
			Date pdTODATDate = DateHelper.stringToSQLDate(pdTODAT,"dd-MM-yyyy");
			return QbRestUtils
					.convertToJsonStringForProc(r100CentService.listOfTabR100Hrs(fo100, pdFRDATDate, pdTODATDate, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param PDFRDAT Date(dd-MM-yyyy)
	 * @param PDTODAT Date(dd-MM-yyyy)
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabR100Vlf")
	@Produces("application/json")
	public String listOfTabR100Vlf(@QueryParam("PDFRDAT") String pdFRDAT, @QueryParam("PDTODAT") String pdTODAT,
			@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin) {
		try {
			R100CentService r100CentService = (R100CentService) ApplicationHelper
					.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			Date pdFRDATDate = DateHelper.stringToSQLDate(pdFRDAT,"dd-MM-yyyy");			
			Date pdTODATDate = DateHelper.stringToSQLDate(pdTODAT,"dd-MM-yyyy");
			log.info(limit);
			return QbRestUtils
					.convertToJsonStringForProc(r100CentService.listOfTabR100Vlf(pdFRDATDate, pdTODATDate, limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @param PDFRDAT Date(dd-MM-yyyy)
	 * @param PDTODAT Date(dd-MM-yyyy)
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabR100Det")
	@Produces("application/json")
	public String listOfTabR100Det(@QueryParam("FO100") int fo100, @QueryParam("PDFRDAT") String pdFRDAT,
			@QueryParam("PDTODAT") String pdTODAT, @QueryParam("PVLOGIN") String pvLogin) {
		try {
			R100CentService r100CentService = (R100CentService) ApplicationHelper
					.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			Date pdFRDATDate = DateHelper.stringToSQLDate(pdFRDAT,"dd-MM-yyyy");			
			Date pdTODATDate = DateHelper.stringToSQLDate(pdTODAT,"dd-MM-yyyy");
			return QbRestUtils
					.convertToJsonStringForProc(r100CentService.listOfTabR100Det(fo100, pdFRDATDate, pdTODATDate, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param PDFRDAT Date(dd-MM-yyyy)
	 * @param PDTODAT Date(dd-MM-yyyy)
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabR100Sen")
	@Produces("application/json")
	public String listOfTabR100Sen(@QueryParam("PDFRDAT") String pdFRDAT, @QueryParam("PDTODAT") String pdTODAT,
			@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin) {
		try {
			R100CentService r100CentService = (R100CentService) ApplicationHelper
					.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			Date pdFRDATDate = DateHelper.stringToSQLDate(pdFRDAT,"dd-MM-yyyy");			
			Date pdTODATDate = DateHelper.stringToSQLDate(pdTODAT,"dd-MM-yyyy");
			return QbRestUtils
					.convertToJsonStringForProc(r100CentService.listOfTabR100Sen(pdFRDATDate, pdTODATDate, limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @param PDFRDAT Date(dd-MM-yyyy)
	 * @param PDTODAT Date(dd-MM-yyyy)
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabR100Vie")
	@Produces("application/json")
	public String listOfTabR100Vie(@QueryParam("FO100") int fo100, @QueryParam("PDFRDAT") String pdFRDAT,
			@QueryParam("PDTODAT") String pdTODAT, @QueryParam("LIMIT") int limit,
			@QueryParam("PVLOGIN") String pvLogin) {
		try {
			R100CentService r100CentService = (R100CentService) ApplicationHelper
					.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			Date pdFRDATDate = DateHelper.stringToSQLDate(pdFRDAT,"dd-MM-yyyy");			
			Date pdTODATDate = DateHelper.stringToSQLDate(pdTODAT,"dd-MM-yyyy");
			return QbRestUtils.convertToJsonStringForProc(
					r100CentService.listOfTabR100Vie(fo100, pdFRDATDate, pdTODATDate, limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param RV101 String
	 * @param RV103 String
	 * @param RV105 String
	 * @param RV106 String
	 * @param RN107 int
	 * @param FP100 int
	 * @param FO100V int
	 * @param FO100K int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("r2017insertTabR100")
	@Produces("application/json")
	public String r2017insertTabR100(String postString) {
/*		try {
			JSONObject jsonObject = new JSONObject(postString);
			log.info(jsonObject);
			String rv101 = jsonObject.get("RV101").toString();
			String rv103 = jsonObject.get("RV103").toString();
			String rv105 = jsonObject.get("RV105").toString();
			String rv106 = jsonObject.get("RV106").toString();
			int rn107 = Integer.parseInt(jsonObject.get("RN107").toString());
			int fp100 = Integer.parseInt(jsonObject.get("FP100").toString());
			int fo100v = Integer.parseInt(jsonObject.get("FO100V").toString());
			int fo100k = Integer.parseInt(jsonObject.get("FO100K").toString());
			String pvlogin = jsonObject.get("PVLOGIN").toString();
			R100CentService r100CentService = (R100CentService) ApplicationHelper
					.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			return QbRestUtils.convertToJsonStringForFunc(
					r100CentService.insertTabR1002017(rv101, rv103, rv105, rv106, rn107, fp100, fo100v, fo100k, pvlogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}*/
		try {
			JSONObject jsonObject = new JSONObject(postString);
			log.info(jsonObject);
			int fp100 = Integer.parseInt(jsonObject.get("FP100").toString());
			int fo100v = Integer.parseInt(jsonObject.get("FO100V").toString());
			int fo100k = Integer.parseInt(jsonObject.get("FO100K").toString());
			R100BPMGService r100bpmgService = (R100BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R100BP);
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			P300BPMG p300bpmg = templateService.findDocumentById(fo100k, fp100, P300BPMG.class);
			int rn101 = 1;
			if(p300bpmg != null && p300bpmg.getPa315() != null)
				rn101 = p300bpmg.getPa315().size();
			r100bpmgService.insertTabR100B(fo100v, fp100, fo100k, rn101, "SEN");
			return QbRestUtils.convertToJsonStringForFunc(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * reachs and views
	 * @param FO100
	 * @param OFFSET
	 * @param LIMIT
	 * @param PVLOGIN
	 * @return
	 */
	@GET
	@Path("statlistOfTabR100Piep")
	@Produces("application/json")
	public String statlistOfTabR100Piep(@QueryParam("FO100") int fo100, @QueryParam("PVLOGIN") String pvLogin){
		try {
			R100CentService r100 = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			return QbRestUtils.convertToJsonStringForProc(r100.listOfTabR100Piep(fo100, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
