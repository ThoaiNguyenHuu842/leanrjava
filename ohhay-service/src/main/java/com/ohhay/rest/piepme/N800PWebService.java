package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.N800MGService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * mobile.bonevo.net/service/n800PWebService/
 * storage notification info
 * @author TuNt
 * create date Dec 9, 2016
 * ohhay-service
 */
@Path("n800PWebService")
public class N800PWebService {
	Logger log = Logger.getLogger(N800PWebService.class);
	
	/**
	 * insert when push notification
	 * @param FO100 int nguoi thuc hien hanh dong
	 * @param FO100N int nguoi nhan thong bao
	 * @param NV801 String type of notify (comment, friend request...)
	 * @param NN802 String ref id
	 * @param NV803 String ref info
	 * @param NV804 String notification type
	 * @return
	 */
	@POST
	@Path("inserTabN800")
	@Produces("application/json")
	public String inserTabN800(String postString) {
		try {
			log.info("postString :" + postString);
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			int fo100n = json.getInt("FO100N");
			String nv801 = json.getString("NV801");
			int nn802 = json.getInt("NN802");;
			String nv803 = json.getString("NV803");
			String nv804 = json.getString("NV804");
			N800MGService n800mgService = (N800MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N800MG);
			return QbRestUtils.convertToJsonStringForFunc(n800mgService.insertTabN800(ApplicationConstant.DB_TYPE_PIEPME, fo100, fo100n, nv801, nn802, nv803, nv804));
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get list notification
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param NV804 String
	 * @return
	 */
	@GET
	@Path("listOfTabN800")
	@Produces("application/json")
	public String listOfTabN800(@QueryParam("FO100") int fo100, @QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit, @QueryParam("NV804") String nv804) {
		try {
			N800MGService n800mgService = (N800MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N800MG);
			return QbRestUtils.convertToJsonStringForProc(n800mgService.listOfTabN800(ApplicationConstant.DB_TYPE_PIEPME, fo100, offset, limit, nv804));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}	
	}
	/**
	 * push notification to all user relate with pnPiperId
	 * @param FO100 int  nguoi thuc hien hanh dong
	 * @param PIEPERTYPE String type of pieper
	 * @param PIPERID int id of pieper
	 * @param NV801 String type of notify (comment, friend request...)
	 * @param NN802 String ref id
	 * @param NV803 String ref info
	 * @param NV804 String notification type
	 * @return
	 */
	@POST
	@Path("insertTabN800PieperComment")
	@Produces("application/json")
	public String insertTabN800PieperComment(String postString) {
		try {
			log.info("postString :" + postString);
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			String pieperType = json.getString("PIEPERTYPE");
			int piperId = json.getInt("PIPERID");
			String nv801 = json.getString("NV801");
			int nn802 = json.getInt("NN802");;
			String nv803 = json.getString("NV803");
			String nv804 = json.getString("NV804");
			N800MGService n800mgService = (N800MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N800MG);
			return QbRestUtils.convertToJsonStringForFunc(n800mgService.insertTabN800PieperComment(ApplicationConstant.DB_TYPE_PIEPME, fo100, pieperType, piperId, nv801, nn802, nv803, nv804));
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
