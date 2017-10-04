package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.service.R100VMGService;

/**
 * mobile.bonevo.net/service/r100VWebService
 * @author ThoaiVt
 * @date 07-07-2017
 */
@Path("r100VWebService")
public class R100VWebService {

	/**
	 * @param fo100 int
	 * @param pv300 int
	 * @return
	 */
	@GET
	@Path("listOfTabR100V01")
	@Produces("application/json")
	public String listOfTabR100V01(@QueryParam("FO100") int fo100, @QueryParam("PV300") int pv300) {
		try {
			R100VMGService r100vpmgService = (R100VMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R100V);
			return QbRestUtils.convertToJsonStringForProc(r100vpmgService.listOfTabR100V01(fo100, pv300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @param PV300 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabR100V02")
	@Produces("application/json")
	public String listOfTabR100V02(@QueryParam("FO100") int fo100, @QueryParam("PV300") int pv300,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			R100VMGService r100vpmgService = (R100VMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R100V);
			return QbRestUtils
					.convertToJsonStringForProc(r100vpmgService.listOfTabR100V02(fo100, pv300, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @param PV300 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabR100V03")
	@Produces("application/json")
	public String listOfTabR100V03(@QueryParam("FO100") int fo100, @QueryParam("PV300") int pv300,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			R100VMGService r100vpmgService = (R100VMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R100V);
			return QbRestUtils
					.convertToJsonStringForProc(r100vpmgService.listOfTabR100V03(fo100, pv300, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @param PV300 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabR100V04")
	@Produces("application/json")
	public String listOfTabR100V04(@QueryParam("FO100") int fo100, @QueryParam("PV300") int pv300,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			R100VMGService r100vpmgService = (R100VMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R100V);
			return QbRestUtils
					.convertToJsonStringForProc(r100vpmgService.listOfTabR100V04(fo100, pv300, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
