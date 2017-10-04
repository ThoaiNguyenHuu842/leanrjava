package com.ohhay.rest.bonevo;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.WebChildService;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;

/**
 * @author ThoaiNH
 *
 */
@Path("webChildWebService")
public class WebChildWebService {
	private static Logger log = Logger.getLogger(WebChildWebService.class);
	/*
	 * copy web child
	 */
	@POST
	@Path("copyWebChild")
	@Produces("application/json")
	public String copyWebChild(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int fc500 = Integer.parseInt(jsonObject.get("fc500").toString());
			String ov101 = jsonObject.get("ov101").toString();
			WebChildService webChildService = (WebChildService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_WEBCHILD);
			log.info("---copyWebChild:" + fc500 + "," + fo100 + ","
					+ ov101);
			int kq = webChildService.copyWebChild(fc500, fo100, ov101);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * remove web child
	 */
	@POST
	@Path("removeWebChild")
	@Produces("application/json")
	public String removeWebChild(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int fc500 = Integer.parseInt(jsonObject.get("fc500").toString());
			String ov101 = jsonObject.get("ov101").toString();
			WebChildService webChildService = (WebChildService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_WEBCHILD);
			log.info("---deleteWebChild:" + fc500 + "," + fo100 + ","
					+ ov101);
			int kq = webChildService.deleteWebChild(fc500, fo100, ov101);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * load list web child
	 */
	@GET
	@Path("loadListWebChild")
	@Produces("application/json")
	public String loadListWebChild(@QueryParam("fo100") int fo100, @QueryParam("visible") int visible) {
		try {
			WebChildService l900mgService = (WebChildService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_WEBCHILD);
			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			// find web parent id
			C400MG c400mg = templateMGService
					.findDocument(fo100, C400MG.class, new QbCriteria(
							QbMongoFiledsName.FO100, fo100));
			// find web child
			List<C500MG> listC500mgs = new ArrayList<C500MG>();
			listC500mgs = l900mgService.getListLitteWeb(C500MG.class, c400mg
					.getId(), null, visible, fo100);
			// clear not use data
			for (C500MG c500mg : listC500mgs) {
				c500mg.getListC920mg().clear();
				c500mg.setCv802(c500mg.getCv802Full());
			}
			return QbRestUtils.convertToJsonStringForProc(listC500mgs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/*
	 * change visibility web child page
	 */
	@POST
	@Path("changeWebChildVisibility")
	@Produces("application/json")
	public String changeWebChildVisibility(String postString) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int fc500 = Integer.parseInt(jsonObject.get("fc500").toString());
			int currentVisible = Integer.parseInt(jsonObject
					.get("currentVisible").toString());
			int newVisible = 0;
			if (currentVisible == 0)
				newVisible = -1;
			log.info("---updateOneField:" + "C500MG.class" + ","
					+ "VISIBLE" + "," + newVisible + "," + null + ",_id:"
					+ fc500 + ",FO100:" + fo100);
			int kq = templateMGService
					.updateOneField(fo100, C500MG.class, QbMongoFiledsName.VISIBLE, newVisible, null, new QbCriteria(
							QbMongoFiledsName.ID, fc500), new QbCriteria(
							QbMongoFiledsName.FO100, fo100));
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
