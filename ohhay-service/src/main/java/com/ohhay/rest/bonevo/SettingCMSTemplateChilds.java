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
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.webchild.A500MG;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;

/**
 * @author Thoaivt
 * date create 4-9-2015	
 * get template Childs
 */
@Path("settingCMSTemplateChilds")
public class SettingCMSTemplateChilds {
	private static Logger log = Logger.getLogger(SettingCMSTemplateChilds.class);
	@GET
	@Path("templateChilds")
	@Produces("application/json")
	public String ahayCombtabc400(@QueryParam("fo100") int fo100) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			List<C500MG> listA500MG = templateMGService.findDocuments(fo100, C500MG.class, QbMongoFiledsName.PART,new QbCriteria(QbMongoFiledsName.FO100, fo100));
			for(C500MG c500mg: listA500MG)
				c500mg.setCv802FullForApp(c500mg.getScreenShot());
			return QbRestUtils.convertToJsonStringForProc(listA500MG);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
