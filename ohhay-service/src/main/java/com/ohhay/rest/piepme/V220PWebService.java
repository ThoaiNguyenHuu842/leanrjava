package com.ohhay.rest.piepme;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.service.V220ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * mobile.bonevo.net/service/v220PWebService/
 * @author ThoaiNH
 * create May 6, 2017
 */
@Path("v220PWebService")
public class V220PWebService {
	private Logger log = Logger.getLogger(V220PWebService.class);
	/**
	 * goi sau khi login de lay voucher
	 * @param UUID String
	 * @param FO100 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("checkedTabV220OVN")
	@Produces("application/json")
	public String checkedTabV220OVN(String postString) {
		try {
			log.info("Post : createTabV130 --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			String uuid = jsonObject.getString("UUID");
			int fo100 = jsonObject.getInt("FO100"); 
			String pvLogin = jsonObject.getString("PVLOGIN");
			V220ORService v220ORService = (V220ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V220OR);
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			
			String code = v220ORService.checkedTabV220OVN(fo100, uuid, pvLogin);
			templateService.updateOneField(fo100, N100PMG.class, "NV118", code, null, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			return QbRestUtils.convertToJsonStringForFunc(code);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
}
