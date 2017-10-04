package com.ohhay.rest.piepme;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.entities.other.T200PMG;

/**
 * mobile.bonevo.net/service/t200PWebService/
 * channel
 * @author ThoaiNH
 * create Feb 17, 2017
 */
@Path("t200PWebService")
public class T200PWebService {
	private TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
	private Logger logger = Logger.getLogger(T200PWebService.class);
	/**
	 * @param FO100 int
	 * @param TV201 String
	 * @return
	 */
	@POST
	@Path("insertTabT200")
	@Produces("application/json")
	public String insertTabT200(String postString) {
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			
			String tv201 = jsonObject.get("TV201").toString();
			T200PMG t200pmg = new T200PMG();
			t200pmg.setId((int)sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T200));
			t200pmg.setFo100(fo100);
			t200pmg.setTv201(tv201);
			t200pmg.setTl206(new Date());
			t200pmg.setTl208(new Date());
			return QbRestUtils.convertToJsonStringForFunc(templateService.saveDocument(fo100, t200pmg, QbMongoCollectionsName.T200));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param PT200 int
	 * @return
	 */
	@POST
	@Path("removeT200")
	@Produces("application/json")
	public String removeT200(String postString) {
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt200 = Integer.parseInt(jsonObject.get("PT200").toString());
			if(pt200 != 0)
				return QbRestUtils.convertToJsonStringForFunc(templateService.removeDocuments(fo100, T200PMG.class,
															new QbCriteria(QbMongoFiledsName.ID, pt200), new QbCriteria(QbMongoFiledsName.FO100, fo100)));
			else
				return QbRestUtils.convertToJsonStringForFunc(templateService.removeDocuments(fo100, T200PMG.class,
															new QbCriteria(QbMongoFiledsName.FO100, fo100)));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param TV201 String
	 * @return
	 */
	@POST
	@Path("checkTabT200")
	@Produces("application/json")
	public String checkTabT200(String postString) {
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String tv201 = jsonObject.get("TV201").toString();
			T200PMG t200pmg = templateService.findDocument(fo100, T200PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria("TV201", tv201));
			return QbRestUtils.convertToJsonStringForProc(t200pmg);
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
	@Path("getListT200")
	@Produces("application/json")
	public String getListT200(@QueryParam("FO100") int fo100) {
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			if(fo100 != 0)
				return QbRestUtils.convertToJsonStringForProc(templateService.findDocuments(fo100, T200PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100)));
			else
				return QbRestUtils.convertToJsonStringForProc(templateService.findDocuments(fo100, T200PMG.class));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
}
