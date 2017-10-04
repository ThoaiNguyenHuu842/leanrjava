package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.service.R100CPMGService;

/**
 * mobile.bonevo.net/service/r100CPWebService/
 * statistic for circle pieper
 * @author TuNt
 * create date Mar 15, 2017
 * ohhay-service
 */
@Path("r100CPWebService")
public class R100CPWebService {

	/**
	 * deatil statistic of a pieper
	 * @param FO100 int
	 * @param FP300 int
	 * @return
	 */
	@GET
	@Path("listOfTabR100C01")
	@Produces("application/json")
	public String listOfTabR100C01(@QueryParam("FO100") int fo100, @QueryParam("FP300") int fp300){
		try {
			R100CPMGService r100cpmgService = (R100CPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R100C);
			return QbRestUtils.convertToJsonStringForProc(r100cpmgService.listOfTabR100C01(fo100, fp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
}
