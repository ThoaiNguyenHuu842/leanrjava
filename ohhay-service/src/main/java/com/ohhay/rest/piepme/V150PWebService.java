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
import com.ohhay.core.oracle.service.V150ORService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * mobile.bonevo.net/service/v150PWebService/
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-service
 */
@Path("v150PWebService")
public class V150PWebService {

	private Logger log = Logger.getLogger(V150PWebService.class);
	/**
	 * @param VN154 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("createTabV150O")
	@Produces("application/json")
	public String createTabV150O(String postString) {
		try {
			log.info("Post : createTabV150O --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			int vn154 = jsonObject.getInt("VN154"); 
			String pvLogin = jsonObject.getString("PVLOGIN");
			V150ORService v150orService = (V150ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V150OR);
			return QbRestUtils.convertToJsonStringForFunc(v150orService.createTabV150O(vn154, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FV150 int
	 * @param VN152 int
	 * @param VN155 int
	 * @param VN156 int
	 * @param VN157 int
	 * @param VN158 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("confirmTabV150O")
	@Produces("application/json")
	public String confirmTabV150(String postString) {
		try {
			log.info("Post : confirmTabV150 --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fv150 = jsonObject.getInt("FV150");
			int vn152 = jsonObject.getInt("VN152");
			int vn155 = jsonObject.getInt("VN155");
			int vn156 = jsonObject.getInt("VN156");
			int vn157 = jsonObject.getInt("VN157");
			int vn158 = jsonObject.getInt("VN158"); 
			String pvLogin = jsonObject.getString("PVLOGIN");
			V150ORService v150orService = (V150ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V150OR);
			return QbRestUtils.convertToJsonStringForFunc(v150orService.confirmTabV150O(fv150, vn152, vn155, vn156, vn157, vn158, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param VN154 int
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabV150O")
	@Produces("application/json")
	public String listOfTabV150(@QueryParam("VN154") int vn154, @QueryParam("FO100") int fo100,@QueryParam("OFFSET") int offset,@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLogin){
		try {
			V150ORService v150orService = (V150ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V150OR);
			return QbRestUtils.convertToJsonStringForProc(v150orService.listOfTabV150O(vn154, fo100,offset,limit, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100
	 * @param PVLOGIN
	 * @return 0 = khong nam trong chuong trinh voucher; 
	 * 		   1 = co, nhung chua xac nhan; 
	 * 		   2 = da duoc xac nhan;
	 */
	@POST
	@Path("checkiTabV150O")
	@Produces("application/json")
	public String checkiTabV150O(String postString) {
		try {
			log.info("Post : checkiTabV150O --" +postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100"); 
			String pvLogin = jsonObject.getString("PVLOGIN");
			V150ORService v150orService = (V150ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V150OR);
			return QbRestUtils.convertToJsonStringForFunc(v150orService.checkiTabV150O(fo100, pvLogin));
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	
}
