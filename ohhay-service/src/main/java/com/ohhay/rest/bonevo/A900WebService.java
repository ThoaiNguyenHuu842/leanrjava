package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.A400MG;
/**
 * @author Phong dt
 * date create: 10/08/2015
 */
@Path("a900WebService")
public class A900WebService {
	@GET
	@Path("getListA900")
	@Produces("application/json")
	public String getListA900(@QueryParam("fa950") int fa950) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			List<A400MG> listA400mgs = templateMGService.findDocuments(0, A400MG.class, QbMongoFiledsName.PART, 
																	  new QbCriteria(QbMongoFiledsName.FA950, fa950),
																	  new QbCriteria(QbMongoFiledsName.AN402, 1));
			return QbRestUtils.convertToJsonStringForProc(listA400mgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	
}
