package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ohhay.base.mysql.service.R100CentService;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.service.R100BPMGService;

/**
 * mobile.bonevo.net/service/r100BPWebService/
 * statistic for commercial pieper
 * @author TuNt
 * create date Mar 15, 2017
 * ohhay-service
 */
@Path("r100BPWebService")
public class R100BPWebService {

	/**
	 * deatil statistic of a pieper
	 * @param FO100 int
	 * @param FP300 int
	 * @return
	 */
	@GET
	@Path("listOfTabR100B01")
	@Produces("application/json")
	public String listOfTabR100B01(@QueryParam("FO100") int fo100, @QueryParam("FP300") int fp300){
		try {
			R100BPMGService r100bpmgService = (R100BPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R100BP);
			return QbRestUtils.convertToJsonStringForProc(r100bpmgService.listOfTabR100B01(fo100, fp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100
	 * @param PVLOGIN
	 * @return
	 */
	@GET
	@Path("listOfTabR100Dash")
	@Produces("application/json")
	public String listOfTabR100Dash(@QueryParam("FO100") int fo100, @QueryParam("PVLOGIN") String pvLogin){
		try {
			
			R100CentService r100 = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			return QbRestUtils.convertToJsonStringForProc(r100.listOfTabR100Dash(fo100, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * engagement per day
	 * @param FO100
	 * @param OFFSET
	 * @param LIMIT
	 * @param PVLOGIN
	 * @return
	 */
	@GET
	@Path("listOfTabR100Eng")
	@Produces("application/json")
	public String listOfTabR100Eng(@QueryParam("FO100") int fo100,@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin){
		try {
			
			R100CentService r100 = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			return QbRestUtils.convertToJsonStringForProc(r100.listOfTabR100Eng(fo100, offset, limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * reachs and views
	 * @param FO100
	 * @param OFFSET
	 * @param LIMIT
	 * @param PVLOGIN
	 * @return
	 */
	@GET
	@Path("listOfTabR100Revi")
	@Produces("application/json")
	public String listOfTabR100Revi(@QueryParam("FO100") int fo100,@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin){
		try {
			
			R100CentService r100 = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			return QbRestUtils.convertToJsonStringForProc(r100.listOfTabR100Revi(fo100, offset, limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * likes and follows
	 * @param FO100
	 * @param OFFSET
	 * @param LIMIT
	 * @param PVLOGIN
	 * @return
	 */
	@GET
	@Path("listOfTabR100Lifo")
	@Produces("application/json")
	public String listOfTabR100Lifo(@QueryParam("FO100") int fo100,@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin){
		try {
			
			R100CentService r100 = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
			return QbRestUtils.convertToJsonStringForProc(r100.listOfTabR100Lifo(fo100, offset, limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
