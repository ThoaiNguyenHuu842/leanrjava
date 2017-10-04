package com.ohhay.rest.bonevo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.LanguageService;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.web.W400MG;
import com.ohhay.web.core.entities.mongo.webchild.W500MG;
import com.ohhay.web.core.entities.mongo.weblanguage.W100MG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.W100CMG;
import com.ohhay.web.core.load.util.PropertyValue;
import com.ohhay.web.core.load.util.WebOhhay;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.web.load.tools.WebCreator;
import com.ohhay.web.other.api.service.WebinarisService;
import com.ohhay.web.other.mongo.service.W900MGService;

/**
 * @author ThoaiNH
 *
 */
@Path("w900WebService")
public class W900WebService {
	private static Logger log = Logger.getLogger(W900WebService.class);
	/*
	 * tao webinaris
	 */
	@POST
	@Path("createWebinaris")
	@Produces("application/json")
	public String createWebinaris(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String key = jsonObject.get("key").toString();
			int po100 = Integer.parseInt(jsonObject.get("po100").toString());
			String ov101 = jsonObject.get("ov101").toString();
			WebinarisService b050WbnService = (WebinarisService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_WEBINARIS);
			int kq = b050WbnService.createWebinaris(po100, ov101, 11, key);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * load html cho webinar room
	 */
	@GET
	@Path("getWebinarRoomHtml")
	@Produces("application/json")
	public String getWebinarRoomHtml(@QueryParam("key") String fw400String,
			@QueryParam("language") String language,
			@QueryParam("cn806") int cn806) {
		try {
			log.info("---getOhayHtml");
			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			int fw400 = Integer.parseInt(fw400String);
			W400MG w400mg = null;
			W500MG w500mg = null;
			C400MG c400mg = null;
			int webChildID = 0;
			Map<String, PropertyValue> mapProperties = new HashMap<String, PropertyValue>();
			LanguageService languageMGService = (LanguageService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
			// child page
			if (cn806 > 0) {
				w500mg = templateMGService.findDocument(0, W500MG.class,
						new QbCriteria(QbMongoFiledsName.PARID, fw400), new QbCriteria(QbMongoFiledsName.CN806,
								cn806));
				if (w500mg != null) {
					c400mg = new C400MG(w500mg);
					webChildID = w500mg.getId();
					W100CMG w100cmg = templateMGService.findDocument(0, W100CMG.class, new QbCriteria(QbMongoFiledsName.LANGUAGEID, ApplicationHelper.convertToMD5(w500mg.getId()+language)));
					languageMGService.getProperties(w100cmg);
				}
			}
			// home page
			else {
				w400mg = templateMGService.findDocumentById(0, fw400, W400MG.class);
				if (w400mg != null) {
					c400mg = new C400MG(w400mg);
					W100MG w100cmg = templateMGService.findDocument(0, W100MG.class, new QbCriteria(QbMongoFiledsName.LANGUAGEID, ApplicationHelper.convertToMD5(w400mg.getId()+language)));
					languageMGService.getProperties(w100cmg);
				}
			}
			WebCreateParam webParam = new WebCreateParam();
			webParam.setOhhayWebBase(c400mg);
			webParam.setKey(null);
			webParam.setMapProperties(mapProperties);
			webParam.setRole(ApplicationConstant.ROLE_VIEWER);
			WebCreator creator = new WebCreator(webParam);
			List<WebOhhay> list = new ArrayList<WebOhhay>();
			creator.getOhhay()
					.setHeader(
							"<link rel=\"stylesheet\" type=\"text/css\" href=\"https://oohhay.com/html/css/bootstrap/bootstrap.min.css\">"
									+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://oohhay.com/html/css/bootstrap/bootstrap-theme.min.css\">"
									+ "<script src=\"https://oohhay.com/html/js/jquery-1.11.1.min.js\"></script>"
									+ "<script type=\"text/javascript\" src=\"https://oohhay.com/html/js/ohhay-domain.js\"></script>"
									+ "<script src=\"https://oohhay.com/html/js/bootstrap.min.js\"></script>"
									+ creator.getOhhay().getHeader()
									+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://oohhay.com/html/css/style.css\">"
									+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://oohhay.com/html/css/font-awesome.min.css\">");
			list.add(creator.getOhhay());
			return QbRestUtils.convertToJsonStringForProc(list);

		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	@POST
	@Path("createWebinarRoom")
	@Produces("application/json")
	public String createWebinarRoom(String postString) {
		try {
			WebinarisService b050WbnService = (WebinarisService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_WEBINARIS);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int fc800 = Integer.parseInt(jsonObject.get("fc800").toString());
			String ov101 = jsonObject.get("ov101").toString();
			log.info("---createWebinarRoom:" + fo100 + "," + ov101
					+ "," + fc800);
			int kq = b050WbnService.createWebinarRoom(fo100, ov101, fc800);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	@GET
	@Path("findWebinarRoom")
	@Produces("application/json")
	public String findWebinarRoom(@QueryParam("fo100") int fo100) {
		try {
			W900MGService w900mgService = (W900MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_W900MG);
			List<W400MG> listW400mgs = w900mgService.findWebinarRoom(fo100);
			log.info("--findWebinarRoom:"+fo100);
			if (listW400mgs != null) {
				// clear not use data
				for (W400MG w400mg : listW400mgs)
					w400mg.getListC920mg().clear();
			}
			return QbRestUtils.convertToJsonStringForProc(listW400mgs);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	@GET
	@Path("findWebinaris")
	@Produces("application/json")
	public String findWebinaris(@QueryParam("fo100") int fo100) {
		try {
			W900MGService w900mgService = (W900MGService) ApplicationHelper
				    .findBean(SpringBeanNames.SERVICE_NAME_W900MG);
			W400MG w400mg = w900mgService.findWebinaris(fo100);
			String key = "";
			if (w400mg != null){
				key = w400mg.getWv403();
			}
			return QbRestUtils.convertToJsonStringForFunc(key);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
