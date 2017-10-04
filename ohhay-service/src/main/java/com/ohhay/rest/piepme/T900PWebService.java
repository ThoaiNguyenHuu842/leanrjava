package com.ohhay.rest.piepme;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.T900OR;
import com.ohhay.core.oracle.service.T900ORService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * mobile.bonevo.net/service/t900PWebService/
 * @author ThoaiVt Mar 29, 2017
 */
@Path("t900PWebService")
public class T900PWebService {
	/**
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabT900OPEN")
	@Produces("application/json")
	public String listOfTabT900OPEN(@QueryParam("FO100") int fo100,@QueryParam("OFFSET") int offset,@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLOGIN) {
		try {
			T900ORService t900orService = (T900ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_T900OR);
			List<T900OR> t900ors = t900orService.listOfTabT900OPEN(fo100,offset,limit, pvLOGIN);
			// templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForProc(t900ors);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabT900CONF")
	@Produces("application/json")
	public String listOfTabT900CONF(@QueryParam("FO100") int fo100,@QueryParam("OFFSET") int offset,@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLOGIN) {
		try {
			T900ORService t900orService = (T900ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_T900OR);
			List<T900OR> t900ors = t900orService.listOfTabT900CONF(fo100,offset,limit, pvLOGIN);
			// templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForProc(t900ors);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	

	/**
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabT900APP")
	@Produces("application/json")
	public String listOfTabT900APP(@QueryParam("FO100") int fo100,@QueryParam("OFFSET") int offset,@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLOGIN) {
		try {
			T900ORService t900orService = (T900ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_T900OR);
			List<T900OR> t900ors = t900orService.listOfTabT900APP(fo100,offset,limit, pvLOGIN);
			// templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForProc(t900ors);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
