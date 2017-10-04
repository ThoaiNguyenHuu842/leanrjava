package com.ohhay.rest.piepme;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONObject;
import org.springframework.data.domain.Sort.Direction;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.MongoDBManager;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.entities.blockaccount.B100BPMG;
import com.ohhay.piepme.mongo.entities.blockaccount.B100CPMG;
import com.ohhay.piepme.mongo.entities.blockaccount.B100PPMG;
import com.ohhay.piepme.mongo.entities.blockpieper.B300BPMG;
import com.ohhay.piepme.mongo.entities.blockpieper.B300CPMG;
import com.ohhay.piepme.mongo.entities.blockpieper.B300PPMG;
import com.ohhay.piepme.mongo.entities.other.P150PMG;
import com.ohhay.piepme.mongo.entities.other.S250PMG;
import com.ohhay.piepme.mongo.entities.reportpieper.B200BPMG;
import com.ohhay.piepme.mongo.entities.reportpieper.B200CPMG;
import com.ohhay.piepme.mongo.entities.reportpieper.B200PPMG;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH
 * create May 5, 2017
 */
@Path("adminPWebService")
public class AdminPWebService {
	/**
	 * @return
	 */
	@GET
	@Path("mapFO100PiepmeDB")
	@Produces("application/json")
	public String mapFO100PiepmeDB() {
		try {
			return new JSONObject(MongoDBManager.mapFO100PiepmeDB).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100
	 * @return
	 */
	@POST
	@Path("resetOTP")
	@Produces("application/json")
	public String resetOTP(String postString){
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100");
			int result = 0;
			if(fo100 > 0){
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				result = templateService.removeRealDocument(fo100, P150PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			}
			return QbRestUtils.convertToJsonStringForFunc(result);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100
	 * @return
	 */
	@GET
	@Path("getListOTP")
	@Produces("application/json")
	public String getListOTP(@QueryParam("FO100") int fo100) {
		try {
			List<P150PMG> listResult = new ArrayList<>();
			if(fo100 > 0){
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				listResult = templateService.findDocuments(fo100, P150PMG.class, "PD156", Direction.DESC, 0, 1, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			}
			return QbRestUtils.convertToJsonStringForProc(listResult);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100
	 * @return
	 */
	@POST
	@Path("unlimitedPiep")
	@Produces("application/json")
	public String unlimitedPiep(String postString){
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100");
			int result = 0;
			if(fo100 > 0){
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				S250PMG s250mg = templateService.findDocumentById(fo100, 1, S250PMG.class);
				ArrayList<Integer> fo100QBS = s250mg.getFo100QBS();
				fo100QBS.add(fo100);
				result = templateService.updateOneField(fo100, S250PMG.class, 1, "FO100_QBS", fo100QBS, null);
			}
			return QbRestUtils.convertToJsonStringForFunc(result);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100
	 * @return
	 */
	@POST
	@Path("limitedPiep")
	@Produces("application/json")
	public String limitedPiep(String postString){
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100");
			int result = 0;
			if(fo100 > 0){
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				S250PMG s250mg = templateService.findDocumentById(fo100, 1, S250PMG.class);
				ArrayList<Integer> fo100QBS = s250mg.getFo100QBS();
				try{
					int index = fo100QBS.indexOf(fo100);
					fo100QBS.remove(index);
					result = templateService.updateOneField(fo100, S250PMG.class, 1, "FO100_QBS", fo100QBS, null);
				}catch(ArrayIndexOutOfBoundsException e){
					e.printStackTrace();
				}
			}
			return QbRestUtils.convertToJsonStringForFunc(result);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100
	 * @return
	 */
	@POST
	@Path("resetAllBlockCommercial")
	@Produces("application/json")
	public String resetAllBlockCommercial(String postString) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100");
			if(fo100 > 0){
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				templateService.removeDocuments(fo100, B100BPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateService.removeDocuments(fo100, B200BPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateService.removeDocuments(fo100, B300BPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				return QbRestUtils.convertToJsonStringForFunc(1);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			QbRestUtils.getErrorStatus();
		}
		return QbRestUtils.convertToJsonStringForFunc(0); 
	}
	/**
	 * @param FO100
	 * @return
	 */
	@POST
	@Path("resetAllBlockPublic")
	@Produces("application/json")
	public String resetAllBlockPublic(String postString) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100");
			if(fo100 > 0){
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				templateService.removeDocuments(fo100, B100PPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateService.removeDocuments(fo100, B200PPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateService.removeDocuments(fo100, B300PPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				return QbRestUtils.convertToJsonStringForFunc(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			QbRestUtils.getErrorStatus();
		}
		return QbRestUtils.convertToJsonStringForFunc(0); 
	}
	/**
	 * @param FO100
	 * @return
	 */
	@POST
	@Path("resetAllBlockCircle")
	@Produces("application/json")
	public String resetAllBlockCircle(String postString) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100");
			if(fo100 > 0){
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				templateService.removeDocuments(fo100, B100CPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateService.removeDocuments(fo100, B200CPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateService.removeDocuments(fo100, B300CPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				return QbRestUtils.convertToJsonStringForFunc(1);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			QbRestUtils.getErrorStatus();
		}
		return QbRestUtils.convertToJsonStringForFunc(0); 
	}
	
	/**
	 * @param FO100 
	 * @return
	 */
	@GET
	@Path("getTotalEnterprise")
	@Produces("application/json")
	public String getTotalEnterprise(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForFunc(templateService.count(fo100, N100PMG.class, new QbCriteria("K100_CON.KV105", "CON")));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @return
	 */
	@GET
	@Path("getSystemInfo")
	@Produces("application/json")
	public String getSystemInfo() {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForProc(templateService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN_PIEPME, 1, S250PMG.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
