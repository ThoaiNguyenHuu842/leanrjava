package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.other.entities.R150;
import com.ohhay.other.mysql.service.R150Service;

@Path("r150WebService")
public class R150WebService {
	private static Logger log = Logger.getLogger(R150WebService.class);

	/*
	 * load list what is new
	 */
	@GET
	@Path("rhayReportTabR150")
	@Produces("application/json")
	public String rhayReportTabR150(@QueryParam("fo100") int fo100, @QueryParam("rd154") String rd154, @QueryParam("offset") int offset, @QueryParam("limit") int limit, @QueryParam("pvlogin") String pvlogin) {
		try {
			R150Service r150Service = (R150Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R150);
			
			log.info("--rhayReportTabR150:" + fo100+ "," + DateHelper.stringToSQLDate(rd154, "yyyy-MM-dd") + "," +offset+ "," +limit + "," + pvlogin);
			
			List<R150> listR150 = r150Service.rhayReportTabR150(fo100,DateHelper.stringToSQLDate(rd154, "yyyy-MM-dd"), offset, limit, pvlogin);

			log.info("---list:" + listR150.size());
			
			return QbRestUtils.convertToJsonStringForProc(listR150);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	
	@GET
	@Path("rhayReportTabR150full")
	@Produces("application/json")
	public String rhayReportTabR150full(@QueryParam("fo100") int fo100, @QueryParam("offset") int offset, @QueryParam("limit") int limit, @QueryParam("pvlogin") String pvlogin) {
		try {
			R150Service r150Service = (R150Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R150);
			
			log.info("--rhayReportTabR150:" + fo100 + "," +offset+ "," +limit + "," + pvlogin);
			
			List<R150> listR150 = r150Service.rhayReportTabR150hist(fo100, offset, limit, pvlogin);

			log.info("---list:" + listR150.size());
			
			return QbRestUtils.convertToJsonStringForProc(listR150);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
