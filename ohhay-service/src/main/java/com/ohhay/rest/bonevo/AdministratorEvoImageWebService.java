package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.P500MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * create Jul 11, 2016
 */
@Path("administratorEvoImageWebService")
public class AdministratorEvoImageWebService {
	/**
	 * @return
	 */
	@GET
	@Path("getListImage")
	@Produces("application/json")
	public String getListImage() {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			List<P500MG> listDomain = (List<P500MG>) templateService.findDocuments(0, P500MG.class);
			return QbRestUtils.convertToJsonStringForProc(listDomain);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
