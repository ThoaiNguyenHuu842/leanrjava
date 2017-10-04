package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.lego.entities.mongo.web.E400MG;

/**
 * @author ThienND created Jan 19, 2016
 */
@Path("e400MGWebService")
public class E400MGWebService {
	private static Logger log = Logger.getLogger(E400MGWebService.class);

	@GET
	@Path("getListE400MG")
	@Produces("application/json")
	public String getListE400MG(@QueryParam("fo100") int fo100) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			List<E400MG> listE400s = templateMGService.findDocuments(fo100, E400MG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			return QbRestUtils.convertToJsonStringForProc(listE400s);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
