package com.ohhay.rest.bonevo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.shop.J910MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * date create: 25/04/2015
 * update mail orel call this
 */
@Path("j910WebService")
public class J910WebService {
	private static Logger log = Logger.getLogger(J910WebService.class);
	@POST
	@Path("updateTabnJn916")
	@Produces("application/json")
	public String updateTabnJn916(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			int kq = templateService.updateOneField(fo100, J910MG.class, fo100, "JN916", 1, null);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

}
