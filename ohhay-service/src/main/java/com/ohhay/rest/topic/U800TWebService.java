package com.ohhay.rest.topic;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

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
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.other.entities.mongo.domain.topic.U800MG;
import com.ohhay.other.mongo.service.U800MGService;
import com.ohhay.other.mysql.service.O200Service;

/**
 * @author ThoaiNH
 * create Jun 8, 2016
 * service manage domain for topic
 */
@Path("u800TWebService")
public class U800TWebService {
	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("changeDomainMn814")
	@Produces("application/json")
	public String changeDomainMn814(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int index = Integer.parseInt(jsonObject.get("INDEX").toString());
			int mn814 = Integer.parseInt(jsonObject.get("MN814").toString());
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			U800MGService u800MGService = (U800MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_U800MG);
			return QbRestUtils.convertToJsonStringForFunc(u800MGService.changeDomainOhhayFunction(index, mn814,fo100));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param fo100
	 * @return
	 */
	@GET
	@Path("getListDomain")
	@Produces("application/json")
	public String getListDomain(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateMGService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			List<U800MG> listU800mg = templateMGService.findDocuments(fo100, U800MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			return QbRestUtils.convertToJsonStringForProc(listU800mg);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("saveDomain")
	@Produces("application/json")
	public String saveDomain(String postString) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateMGService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
			
			JSONObject jsonObject = new JSONObject(postString);
			String domainName = jsonObject.get("OV201").toString().trim().toLowerCase();
			String mv903 = jsonObject.get("MV903").toString().trim().toLowerCase();
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			
			List<U920MG> listU920mg = templateMGService.findDocuments(fo100, U920MG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100),
					new QbCriteria(QbMongoFiledsName.UV921, domainName));
			int kq = 0;
			if (listU920mg.size() > 0){
				kq = -1;
			} else {
				String verificationCode = "";
				O200Service o200Service = (O200Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O200);
				verificationCode = o200Service.insertTabO200(fo100, domainName, "T", ApplicationConstant.PVLOGIN_ANONYMOUS);
				try {
					long newId = sequenceService.getNextSequenceIdTopic(fo100, QbMongoCollectionsName.U800);
					U800MG u800mg = new U800MG();
					u800mg.setId(newId);
					u800mg.setFo100(fo100);
					u800mg.setUv801(domainName.trim().toLowerCase());
					u800mg.setUv802(verificationCode);
					u800mg.setUn803(0);
					u800mg.setMv903(mv903);
					templateMGService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
					kq = templateMGService.saveDocument(fo100, u800mg, QbMongoCollectionsName.U800);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("deleteDomain")
	@Produces("application/json")
	public String deleteDomain(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String ov202 = jsonObject.get("OV202").toString();
			int id = Integer.parseInt(jsonObject.get("ID").toString());
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			O200Service o200Service = (O200Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O200);
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateMGService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			int kq1 = o200Service.stornoTabO200(ov202, ApplicationConstant.PVLOGIN_ANONYMOUS);
			if (kq1 > 0) {
				int kq2 = templateMGService.removeDocuments(fo100, U800MG.class,
						new QbCriteria(QbMongoFiledsName.ID, id),
						new QbCriteria(QbMongoFiledsName.FO100, fo100));
				return QbRestUtils.convertToJsonStringForFunc(kq2);
			}
			return QbRestUtils.convertToJsonStringForFunc(kq1);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
