package com.ohhay.rest.bonevo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.UserProfile;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M910MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * date create: 14/08/2015
 * new process for friend request
 */
@Path("m920WebService")
public class M920WebService {
	private static Logger log = Logger.getLogger(M920WebService.class);
	/**
	 * @param contacts ex: 0914615540db09033873686db+849189878878
	 * get o!hay profile from list phone contact
	 * @return
	 */
	@GET
	@Path("getListUserFromContact")
	@Produces("application/json")
	public String getListUserFromContact(@QueryParam("contacts") String contacts) {
		try {
			StringBuilder hv101s = new StringBuilder("");
			String []jsonArrayContact = contacts.split("qb");
			Set<M900MG> listM900MGs = new HashSet<M900MG>();
			for (String mv907: jsonArrayContact){
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				M900MG m900mg = templateService.findDocument(0, M900MG.class, new QbCriteria(QbMongoFiledsName.MV907,mv907));
				if(m900mg != null){
					M900MGService m900mgService = (M900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
					m900mg.getUrlAvarta();
					listM900MGs.add(m900mg);
				}
			}
			return QbRestUtils.convertToJsonStringForProc(listM900MGs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
