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
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.mysql.service.C400Service;



/**
 * @author Thoaivt
 * date create 4-9-2015
 * get sreenshot 
 */
@Path("settingCMSService")
public class SettingCMSService {
	private static Logger log = Logger.getLogger(SettingCMSService.class);
	@GET
	@Path("loadWebHomeInfo")
	@Produces("application/json")
	public String ahayCombtabc400(@QueryParam("fo100") int fo100,
			@QueryParam("pvLogin") String pvLogin) {
		try {
			TemplateService templateMGService=(TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			C400MG c400mg = templateMGService.findDocument(fo100, C400MG.class, QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.FO100,fo100));
			c400mg.setCv802FullForApp(c400mg.getScreenShot());
			return QbRestUtils.convertToJsonStringForProc(c400mg);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
