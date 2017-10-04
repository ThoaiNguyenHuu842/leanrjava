package com.ohhay.rest.topic;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.util.json.JSONArray;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M960MG;
import com.ohhay.core.entities.mongo.profile.M970MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mongo.service.M960MGService;

/**
 * @author ThoaiNH
 * create Jun 27, 2016
 * service for setting style of logo, title,... topic new
 */
@Path("coverEditorWebService")
public class StyleEditorWebService {
	
	
	
	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("/saveStyle")
	@Produces("application/json")
	public String saveStyle(String postString) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M960MGService m960mgService = (M960MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M960MG);
			
			JSONObject json = new JSONObject(postString);
			int fo100 = Integer.parseInt(json.getString("fo100"));
			String mv971 = json.getString("mv971");
			String mv972 = json.getString("mv972");
			String mv973 = json.getString("mv973");
			String txtdes = json.getString("txtdes");
			
			M970MG m970mg = new M970MG();
			m970mg.setMv971(mv971);
			m970mg.setMv972(mv972);
			m970mg.setMv973(mv973);
			
			
			M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
			m900mg.setM970mg(m970mg);
			m900mg.getM960mg().setMv966(txtdes);

		
			return QbRestUtils.convertToJsonStringForFunc(templateService.saveDocument(fo100, m900mg, QbMongoCollectionsName.M900));
		} catch (JSONException e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param fo100
	 * @return
	 */
	@GET
	@Path("/getStyle")
	@Produces("application/json")
	public String getStyle(@QueryParam("fo100") int fo100) {
		try{
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
			

			JSONObject result = new JSONObject();
			result.put("m970",m900mg.getM970mg());
			result.put("mv966",m900mg.getM960mg().getMv966());

			
			return QbRestUtils.convertToJsonStringForProc(result);
		}catch(Exception e){
			return QbRestUtils.getErrorStatus();
		}
	}
}
