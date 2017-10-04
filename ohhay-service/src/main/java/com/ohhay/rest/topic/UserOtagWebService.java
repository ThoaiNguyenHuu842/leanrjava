package com.ohhay.rest.topic;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.O150MG;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author TuNt create date Jun 15, 2016 ohhay-service
 */
@Path("userOtag")
public class UserOtagWebService {
	Logger logger = Logger.getLogger(UserOtagWebService.class);
	TemplateService templateService = (TemplateService) ApplicationHelper
			.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
	SequenceService sequenceService = (SequenceService) ApplicationHelper
			.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);

	@POST
	@Path("/remove")
	@Produces("application/json")
	public String remove(String postString) {
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = Integer.parseInt(json.getString("fo100"));
			int kq = templateService.removeDocuments(fo100, O150MG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	@POST
	@Path("/add")
	@Produces("application/json")
	public String Add(String postString){
		try {
			int kq = 0;
			templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			JSONObject json = new JSONObject(postString);
			String allTagString = json.getString("allTagString");
			int fo100 = Integer.parseInt(json.getString("fo100"));
			
			logger.info("String NEED : "+allTagString);
			
			String otags[] = allTagString.trim().split(";");
			for(String otag: otags){
			//check otag !""
				long o150NewId = sequenceService.getNextSequenceIdTopic(fo100, QbMongoCollectionsName.O150);
				O150MG o150mg = new O150MG();
				o150mg.setFo100(fo100);
				o150mg.setOv151(otag);
				o150mg.setOl158(new Date());
				o150mg.setId((int)o150NewId);
				
				kq += templateService.saveDocument(fo100, o150mg, QbMongoCollectionsName.O150);
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return QbRestUtils.getErrorStatus();
	}
	
	@GET
	@Path("load")
	@Produces("application/json")
	public String load(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateMGService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			List<O150MG> list = (List<O150MG>) templateService.findDocuments(fo100, O150MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
