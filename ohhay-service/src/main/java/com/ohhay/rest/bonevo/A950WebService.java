package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.A950MG;
/**
 * @author Phong dt
 * date create: 10/08/2015
 */
@Path("a950WebService")
public class A950WebService {
	@GET
	@Path("getListA950")
	@Produces("application/json")
	public String getListA950() {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			List<A950MG> listA950s = templateMGService.findDocuments(0, A950MG.class, QbMongoFiledsName.PART);
			return QbRestUtils.convertToJsonStringForProc(listA950s);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
