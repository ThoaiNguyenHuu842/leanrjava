package com.ohhay.rest.topic;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.M150MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mongo.service.M150MGEmailService;

/**
 * @author ThoaiNH
 * create Jun 16, 2014
 */
@Path("m150MGWebService")
public class M150MGWebService {
	private static Logger log = Logger.getLogger(M150MGWebService.class);
	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("shareTopicByEmail")
	@Produces("application/json")
	public String shareTopicByEmail(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String listMails = jsonObject.get("listMails").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int fm150 = Integer.parseInt(jsonObject.get("fm150").toString());
			M150MGEmailService m150mgEmailService = (M150MGEmailService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M150MGEMAIL);
			log.info("--send:"+fo100+","+listMails+","+fm150);
			int kq = m150mgEmailService.send(fo100, listMails.split(","), fm150);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/**
	 * @return
	 */
	@GET
	@Path("getAllTopic")
	@Produces("application/json")
	public String getAllTopic() {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			return QbRestUtils.convertToJsonStringForProc(templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, M150MG.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
