package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.oracle.service.V170ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * mobile.bonevo.net/service/v170PWebService/
 * @author ThoaiNH
 * create Mar 16, 2017
 * get promotion voucher barcode
 */
@Path("v170PWebService")
public class V170PWebService {
	private Logger log = Logger.getLogger(V170PWebService.class);
	/**
	 * @param FO100 int
	 * @param FO100D int
	 * @param FP300 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("checkedTabV170")
	@Produces("application/json")
	public String checkedTabV170(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100d = Integer.parseInt(jsonObject.get("FO100D").toString());
			int fp300 = Integer.parseInt(jsonObject.get("FP300").toString());
			String pvlogin = jsonObject.get("PVLOGIN").toString();
			V170ORService v170orService = (V170ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V170OR);
			return QbRestUtils.convertToJsonStringForFunc(v170orService.checkedTabV170(fp300, fo100, fo100d, pvlogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FV170 int
	 * @param VV172 String
	 * @param VN175 int
	 * @param FO100U int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("usedItTabV170O")
	@Produces("application/json")
	public String usedItTabV170O(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fv170 = jsonObject.getInt("FV170");
			String vv172 = jsonObject.get("VV172").toString();
			int vn175 = Integer.parseInt(jsonObject.get("VN175").toString());
			int fo100u = Integer.parseInt(jsonObject.get("FO100U").toString());
			String pvlogin = jsonObject.get("PVLOGIN").toString();
			V170ORService v170orService = (V170ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V170OR);
			return QbRestUtils.convertToJsonStringForFunc(v170orService.usedItTabV170O(fv170, vv172, vn175, fo100u, pvlogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param FP100 int
	 * @param FO100D int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabV170")
	@Produces("application/json")
	public String listOfTabV170(@QueryParam("FO100") int fo100, @QueryParam("FP100") int fp100, @QueryParam("FO100D") int fo100d,@QueryParam("OFFSET") int offset,@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin){
		try {
			V170ORService v170orService = (V170ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V170OR);
			return QbRestUtils.convertToJsonStringForProc(v170orService.listOfTabV170(fo100, fp100, fo100d,offset,limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
