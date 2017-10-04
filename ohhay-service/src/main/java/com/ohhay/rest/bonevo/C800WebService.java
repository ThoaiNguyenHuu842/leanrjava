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
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.mysql.service.C800Service;

/**
 * @author thoai nguyen
 * 
 */
@Path("c800WebService")
public class C800WebService {
	private static Logger log = Logger.getLogger(C800WebService.class);

	/*
	 * danh sach landing page
	 */
	@GET
	@Path("chayCombtabc800LgPages")
	@Produces("application/json")
	public String chayCombtabc800LgPages() {
		try {

			C800Service c800Service = (C800Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_C800);
			log.info("---chayCombtabc800:" + 0 + "," + 0 + "," + "" + ","
					+ ApplicationConstant.PVLOGIN_ANONYMOUS);
			List<ComtabItem> list = c800Service
					.chayCombtabc800LgPages(0, 0,"", ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * danh sach tempplate
	 */
	@GET
	@Path("chayCombtabc800")
	@Produces("application/json")
	public String chayCombtabc800(@QueryParam("fd000") int fd000, @QueryParam("fk100") int fk100, @QueryParam("source") String source) {
		try {
			C800Service c800Service = (C800Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_C800);
			log.info("---chayCombtabc800:" + fd000 + "," + fk100 + "," + source
					+ "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
			List<ComtabItem> list = c800Service
					.chayCombtabc800(fd000, fk100, source, ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * dong bo template mysql vao c800mg (cho RICSS)
	 */
	@POST
	@Path("syncOhhayTemplate")
	@Produces("application/json")
	public String syncOhhayTemplate(String postString) {
		try {
			log.info("postString: " + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fc800 = Integer.parseInt(jsonObject.get("fc800").toString());
			String key = jsonObject.get("key").toString();
			if (key.equals(ApplicationConstant.OHHAY_SYNC_TEMPLATE_KEY)) {
				TemplateService templateService = (TemplateService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				C800Service c800Service2 = (C800Service) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_C800);
				log.info("---chayGetElemTabC800:" + fc800 + ","
						+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				String elemString = c800Service2
						.chayGetElemTabC800(fc800, ApplicationConstant.PVLOGIN_ANONYMOUS);
				log.info("--ELEM STRING:" + elemString);
				int kq = templateService.insertWebStructure(0, "{" + elemString
						+ "}", QbMongoCollectionsName.C800);
				log.info(QbRestUtils.convertToJsonStringForFunc(kq));
				return QbRestUtils.convertToJsonStringForFunc(kq);
			}
			else
				return QbRestUtils.convertToJsonStringForFunc(0);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
