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
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.entities.other.T300PMG;
import com.ohhay.piepme.mongo.service.T300PMGService;


/**
 * mobile.bonevo.net/service/t300PWebService/
 * @author ThoaiNH
 * create Mar 31, 2017
 */
@Path("t300PWebService")
public class T300PWebService {
	private TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
	private Logger logger = Logger.getLogger(T300PWebService.class);
	/**
	 * @param FO100 int
	 * @param TV301 String
	 * @param TV302 String
	 * @return
	 */
	@POST
	@Path("insertTabT300")
	@Produces("application/json")
	public String insertTabT300(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int pt300 = Integer.parseInt(jsonObject.get("PT300").toString());
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String tv301 = jsonObject.get("TV301").toString();
			String tv302 = jsonObject.get("TV302").toString();
			String otags = jsonObject.get("OTAGS").toString();
			T300PMGService t300pmgService = (T300PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T300P);
			return QbRestUtils.convertToJsonStringForFunc(t300pmgService.insertT300(pt300, fo100, tv301, tv302, otags));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param PT300 int
	 * @return
	 */
	@POST
	@Path("removeT300")
	@Produces("application/json")
	public String removeT300(String postString) {
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt300 = Integer.parseInt(jsonObject.get("PT300").toString());
			return QbRestUtils.convertToJsonStringForFunc(templateService.removeDocuments(fo100, T300PMG.class, new QbCriteria(QbMongoFiledsName.ID, pt300)));
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
	@Path("getListT300")
	@Produces("application/json")
	public String getListT300(@QueryParam("FO100") int fo100) {
		try {
			T300PMGService t300pmgService = (T300PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T300P);
			return QbRestUtils.convertToJsonStringForProc(t300pmgService.listOfTabT300(fo100));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
}
