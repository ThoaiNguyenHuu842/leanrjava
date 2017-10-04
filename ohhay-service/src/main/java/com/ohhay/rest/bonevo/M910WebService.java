package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M910MG;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * date create: 21/05/2015
 *
 */
@Path("m910WebService")
public class M910WebService {
	private static Logger log = Logger.getLogger(M910WebService.class);
	@GET
	@Path("getListContact")
	@Produces("application/json")
	public String getListContact(@QueryParam("fo100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			List<M910MG> listM910mgs = templateService.findDocuments(fo100, M910MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			for(M910MG m910mg: listM910mgs){
				if(m910mg.getFo100c() > 0)
				{
					M900MG m900mg = templateService.findDocumentById(m910mg.getFo100c(), m910mg.getFo100c(), M900MG.class);
					if(m900mg != null)
						m910mg.setAvatar(m900mg.getUrlAvarta());
				}
			}
			return QbRestUtils.convertToJsonStringForProc(listM910mgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	@POST
	@Path("updateContact")
	@Produces("application/json")
	public String updateContactAndShare(String postString) {
		try {
			JSONObject jsonRootObject = new JSONObject(postString);
			log.info(jsonRootObject.toString());
			org.json.JSONArray jsonArrayContact = jsonRootObject
					.getJSONArray("CONTACT");
			int fo100 = Integer.parseInt(jsonRootObject.get("_id").toString());
			log.info("---fo100:"+fo100);
			for (int i = 0; i < jsonArrayContact.length(); i++) {
				/*
				 * 1) get json info
				 */
				JSONObject object = jsonArrayContact.getJSONObject(i);
				String mv907 = object.get("MV907").toString().replace(" ", "");
				String mv911 = object.get("MV911").toString();
				int mn912 =  Integer.parseInt(object.get("MN912").toString());
				/*
				 * 2) find contact is exists or no
				 */
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
				M910MG m910mg = new M910MG();
				M910MG m910mgCurrent = templateService.findDocument(fo100, M910MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.MV907, mv907));
				/*
				 * 3) set old id if contact is exists else set new id
				 */
				if(m910mgCurrent == null)
				{
					int newId = (int) sequenceService.getNextSequenceId(fo100, "M910");
					m910mg.setId(newId);
				}
				else
					m910mg.setId(m910mgCurrent.getId());
				m910mg.setFo100(fo100);
				/*
				 * 4) find fo100 of contact
				 */
				M900MG m900mg = templateService.findDocument(fo100, M900MG.class, new QbCriteria(QbMongoFiledsName.MV907,mv907));
				if(m900mg != null)
				{
					m910mg.setFo100c(m900mg.getId());
				}
				m910mg.setMv907(mv907);
				m910mg.setMv911(mv911);
				/*
				 * 5) role share
				 */
				if(mn912 == 1)
					m910mg.setMn912(1);
				templateService.saveDocument(fo100, m910mg, QbMongoCollectionsName.M910);
			}
			return QbRestUtils.convertToJsonStringForFunc(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
