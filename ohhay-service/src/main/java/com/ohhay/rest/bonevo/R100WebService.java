package com.ohhay.rest.bonevo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ChartItemInfo;
import com.ohhay.core.mongo.service.R900MGService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mysql.service.R100Service;

/**
 * @author ThoaiNH
 *
 */
@Path("r100WebService")
public class R100WebService {
	private static Logger log = Logger.getLogger(R100WebService.class);

	/*
	 * report 01
	 */
	@GET
	@Path("getchart1")
	@Produces("application/json")
	public String getchart1(@QueryParam("fo100") int fo100, @QueryParam("rn120") int rn120) {
		try {
			R100Service r100Service = (R100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R100);
			log.info("--rhayReportTabR1001:" + fo100 + "," + rn120 + ","
					+ ApplicationConstant.PVLOGIN_ANONYMOUS);
			List<ChartItemInfo> listR100 = r100Service
					.rhayReportTabR1001(fo100, rn120, ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("---list chart:" + listR100.size());
			return QbRestUtils.convertToJsonStringForProc(listR100);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/*
	 * vote
	 */
	@POST
	@Path("vote")
	@Produces("application/json")
	public String vote(@Context HttpServletRequest request, String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			R100Service r100Service = (R100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R100);
			int fo100v = 0;
			String qv101 = ApplicationConstant.PVLOGIN_ANONYMOUS;
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			try {
				fo100v = Integer.parseInt(jsonObject.get("fo100v").toString());
				qv101 = jsonObject.get("qv101").toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			String ipAddress = request.getRemoteAddr();

			log.info("---rhayUpdateTabR100:" + fo100v + "," + ipAddress + ","
					+ qv101);
			int kq = r100Service
					.rhayUpdateTabR100Vote(fo100v, fo100, ipAddress, qv101);
			log.info("--kq:" + kq);
			// insert to mongo
			if (kq > 0) {
				R900MGService r900mgService = (R900MGService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_R900MG);
				log.info("---insertTabR900Vote:" + fo100v + "," + fo100);
				r900mgService.insertTabR900Vote(fo100v, fo100);
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);

		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
