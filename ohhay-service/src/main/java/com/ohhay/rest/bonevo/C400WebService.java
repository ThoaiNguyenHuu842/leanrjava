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
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.DraftWebService;
import com.ohhay.web.core.mysql.service.C400Service;

/**
 * @author ThoaiNH
 * create: 10/10/2014
 * load list template
 */
@Path("c400WebService")
public class C400WebService {
	private static Logger log = Logger.getLogger(C400WebService.class);
	/**
	 * danh sach template
	 * @deprecated
	 */
	@GET
	@Path("ahayCombtabc400")
	@Produces("application/json")
	public String ahayCombtabc400(@QueryParam("fo100") int fo100,
			@QueryParam("pvLogin") String pvLogin) {
		try {
			C400Service c400Service = (C400Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_C400);
			List<ComtabItem> list = c400Service
					.ahayCombtabc400(fo100, pvLogin);
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/**
	 * cap nhat template
	 * @deprecated
	 */
	@POST
	@Path("changeTemplateC400")
	@Produces("application/json")
	public String changeTemplateC400(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int fc800 = Integer.parseInt(jsonObject.get("fc800").toString());
			int fd000 = Integer.parseInt(jsonObject.get("fd000").toString());
			String ov101 = jsonObject.get("ov101").toString();
			String qv101 = jsonObject.get("qv101").toString();
			DraftWebService draftWebMGService = (DraftWebService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_DRAFTWEBSERVICE);
			log.info("---changeTemplate:"+fo100+","+fc800+","+ov101+","+qv101);
			int kq =draftWebMGService.changeTemplate(fo100, fc800,fd000, ov101);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
