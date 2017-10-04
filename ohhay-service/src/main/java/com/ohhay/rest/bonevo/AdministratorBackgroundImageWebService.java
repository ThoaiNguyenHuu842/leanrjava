package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.P600MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * create Jul 8, 2016
 */
@Path("administratorBackgroundImageWebService")
public class AdministratorBackgroundImageWebService {
	/**
	 * @return
	 */
	@GET
	@Path("load")
	@Produces("application/json")
	public String load() {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			List<P600MG> p600mgs = (List<P600MG>) templateService.findDocuments(0, P600MG.class);
			return QbRestUtils.convertToJsonStringForProc(p600mgs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
