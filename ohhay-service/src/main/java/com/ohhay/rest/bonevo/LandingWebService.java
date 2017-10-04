package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.L400MG;

/**
 * @author ThoaiNH
 * date create: 29/06/2015
 */
@Path("landingWebService")
public class LandingWebService {
	private static Logger log = Logger.getLogger(LandingWebService.class);
	/*
	 * get list languages
	 */
	@GET
	@Path("loadListLandingWeb")
	@Produces("application/json")
	public String loadListLandingWeb(@QueryParam("fo100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			QbCriteria qbCriteria = new QbCriteria(QbMongoFiledsName.CV805,
					ApplicationConstant.OHHAY_NORMAL_LANDING_CV805);
			List<L400MG> listL400mgs = templateService
					.findDocuments(fo100, L400MG.class, new QbCriteria(
							QbMongoFiledsName.FO100, fo100), qbCriteria);
			for(L400MG l400mg: listL400mgs)
				l400mg.setCv802FullForApp(l400mg.getCv802Full());
			return QbRestUtils.convertToJsonStringForProc(listL400mgs);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	@POST
	@Path("changeLandingPageVisibility")
	@Produces("application/json")
	public String changeLandingPageVisibility(String postString) {
		try{
			log.info(AESUtils.decrypt(postString));
			JSONObject jsonObject = new JSONObject(postString);
			int webid = Integer.parseInt(jsonObject.get("webId").toString());
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int kq = 0;
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			L400MG l400mg = templateMGService
					.findDocument(fo100, L400MG.class, new QbCriteria(
							QbMongoFiledsName.ID, webid), new QbCriteria(
							QbMongoFiledsName.FO100, fo100));
			int newVisible = 0;
			if (l400mg.getVisible() == 0)
				newVisible = -1;
			try {
				log.info("---updateOneField:" + "L400MG.class" + ","
						+ "VISIBLE" + "," + newVisible + "," + null + ",_id:"
						+ webid + ",FO100:"
						+ fo100);
				kq = templateMGService
						.updateOneField(fo100, L400MG.class, "VISIBLE", newVisible, null, new QbCriteria(
								"_id", webid), new QbCriteria(
								"FO100", fo100));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
