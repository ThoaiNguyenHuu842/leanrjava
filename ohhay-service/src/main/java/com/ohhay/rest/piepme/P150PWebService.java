package com.ohhay.rest.piepme;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.service.P150PMGService;

/**
 * mobile.bonevo.net/service/p150PWebService/
 * send OTP to 3 secure contact, confirm OTP
 * @author TuNt  
 * create date Mar 6, 2017
 * ohhay-service
 */
@Path("p150PWebService")
public class P150PWebService {
	/**
	 * @deprecated replaced by {@link #insertTabP150V2(String)}
	 * save OTP to 3 contacts
	 * @param FO100 int
	 * @param PV152 String
	 * @return  - 2 da yeu cuoi gui OTP (trong 24 tieng) nhung chua xac nhan xong -> co the nhap tiep, k can gui lai OTP
				 -1 da gui OTP trong 24 tieng truoc do (da xac nhan sai qua 3 lan hoac da xac nhan thanh cong) phai cho 24 tieng sau moi thuc hien lai duoc 
				 0 chua set secure accounts
				 1 gui thanh cong
	 */
	@POST
	@Path("insertTabP150")
	@Produces("application/json")
	public String insertTabP150(String postString) {
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			String pv152 = json.getString("PV152");
			P150PMGService p150pmgService = (P150PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P150P);
			return QbRestUtils.convertToJsonStringForFunc(p150pmgService.insertTabP150(fo100, pv152));
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * save OTP to 3 contacts
	 * @param FO100 int
	 * @param PV152 String
	 * @return  - 2 da yeu cuoi gui OTP (trong 24 tieng) nhung chua xac nhan xong -> co the nhap tiep, k can gui lai OTP
				 -1 da gui OTP trong 24 tieng truoc do (da xac nhan sai qua 3 lan hoac da xac nhan thanh cong) phai cho 24 tieng sau moi thuc hien lai duoc 
				 0 chua set secure accounts
				 1 gui thanh cong
	 */
	@POST
	@Path("insertTabP150V2")
	@Produces("application/json")
	public String insertTabP150V2(String postString) {
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			String pv152 = json.getString("PV152");
			P150PMGService p150pmgService = (P150PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P150P);
			return QbRestUtils.convertToJsonStringForFunc(p150pmgService.insertTabP150V2(fo100, pv152));
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * confirm OTP
	 * @param FO100 int
	 * @param OTPCODE String
	 * @param PV152 String
	 * @return 	 - 3: confirm error
				 - 2: chua yeu cau gui OTP hoac OTP da het han
				 - 1: da confirm sai 3 lan, phai cho 24 tieng sau
				 0: nhap trung voi OTP da duoc confirm truoc do
				 1: nhap OTP lan 1 thanh cong
				 2: nhap OTP lan 2 thanh cong -> update UUID
	 */
	@POST
	@Path("confirmTabP150")
	@Produces("application/json")
	public String confirmTabP150(String postString) {
		try {
			JSONObject json = new JSONObject(postString);
			int fo100 = json.getInt("FO100");
			String otpCode = json.get("OTPCODE").toString();
			String pv152 = json.get("PV152").toString();
			P150PMGService p150pmgService = (P150PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P150P);
			return QbRestUtils.convertToJsonStringForFunc(p150pmgService.confirmTabP150(fo100, otpCode, pv152));
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
