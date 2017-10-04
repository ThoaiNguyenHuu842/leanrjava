package com.ohhay.rest.topic;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.M200MG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M960MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author TuNt create date Jun 17, 2016 ohhay-service
 */
@Path("topicInfo")
public class TopicInfoService {

	/**
	 * @param MV203
	 * @return
	 */
	@GET
	@Path("/getTopicInfo")
	@Produces("application/json")
	public String getTopicInfo(@QueryParam("MV203") String MV203) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			M200MG m200mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, M200MG.class,
					new QbCriteria(QbMongoFiledsName.MV203, MV203));
			List<M960MG> listM960 = new ArrayList<M960MG>();
			if (m200mg != null) {
				templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
				M900MG m900mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, M900MG.class,
						new QbCriteria(QbMongoFiledsName.ID, m200mg.getFo100()));

				listM960.add(m900mg.getM960mg());
			}
			return QbRestUtils.convertToJsonStringForProc(listM960);
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

}
