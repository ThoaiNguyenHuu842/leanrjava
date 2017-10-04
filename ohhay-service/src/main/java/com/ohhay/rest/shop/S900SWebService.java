package com.ohhay.rest.shop;

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
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;
import com.ohhay.core.entities.mongo.shop.S900MG;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author TuNt
 * create date Aug 25, 2016
 * ohhay-service
 */
@Path("s900sWebService")
public class S900SWebService {
	Logger log = Logger.getLogger(S900SWebService.class);
	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("registerShop")
	@Produces("application/json")
	public String registerShop(String postString){
		try {
			TemplateService template = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			template.setOperation(ApplicationConstant.DB_TYPE_SHOP);
			JSONObject json = new JSONObject(postString);
			log.info("post String ---"+postString);
			int fo100 = Integer.parseInt(json.getString("fo100"));
			S900MG s900mg = template.findDocument(fo100, S900MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if(s900mg!=null)
				return QbRestUtils.convertToJsonStringForFunc(-2);
			else
				s900mg = new S900MG();
			String sv901 = json.getString("sv901");
			String sv902 = json.getString("sv902");
			String sv903 = json.getString("sv903");
			String sv904 = json.getString("sv904");
			String sv905 = json.getString("sv905");
			String sv906 = json.getString("sv906");
			String sv907 = json.getString("sv907");
			String sv908 = json.getString("sv908");
			Double longitude = Double.parseDouble(json.getString("longitude"));
			Double latitude = Double.parseDouble(json.getString("latitude"));
			SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
			int id = (int) sequenceService.getNextSequenceIdShop(fo100, QbMongoCollectionsName.S900);
			s900mg.setId(id);
			s900mg.setFo100(fo100);
			s900mg.setSv901(sv901);
			s900mg.setSv902(sv902);
			s900mg.setSv903(sv903);
			s900mg.setSv904(sv904);
			s900mg.setSv905(sv905);
			s900mg.setSv906(sv906);
			s900mg.setSv907(sv907);
			s900mg.setSv908(sv908);
			s900mg.setSl946(new Date());
			s900mg.setSl948(new Date());
			GeoDataPointMG geoDataPointMG = new GeoDataPointMG(longitude,latitude,null);
			s900mg.setLocation(geoDataPointMG);
			log.info("s900 save --"+s900mg);
			int result = template.saveDocument(fo100, s900mg, QbMongoCollectionsName.S900);
			return QbRestUtils.convertToJsonStringForFunc(result);
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param fo100
	 * @return
	 */
	@GET
	@Path("getShopProfile")
	@Produces("application/json")
	public String getShopProfile(@QueryParam("FO100") int fo100){
		try{
			TemplateService template = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			template.setOperation(ApplicationConstant.DB_TYPE_SHOP);
			S900MG s900mg = template.findDocument(fo100, S900MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			return QbRestUtils.convertToJsonStringForProc(s900mg);
		}catch (Exception e){
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
}
