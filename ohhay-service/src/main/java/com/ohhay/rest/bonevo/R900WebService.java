package com.ohhay.rest.bonevo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mysql.service.R900Service;

/**
 * @author ThoaiNH
 * date create: 20/07/2015
 */
@Path("r900WebService")
public class R900WebService {
	private static Logger log = Logger.getLogger(R900WebService.class);
	/**
	 * @param postString
	 * @param fo100:  id chủ topic
	 * @param fo100t: id nguoi dang xem chua login thi = 0
	 * @param objectName: object name
	 * @return
	 */
	@POST
	@Path("insertTabR900Topic")
	@Produces("application/json")
	public String insertTabR900Topic(String postString) {
		try {
			R900Service r900Service = (R900Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R900);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int fo100t = Integer.parseInt(jsonObject.get("fo100t").toString());
			String objectName = jsonObject.get("objectName").toString();
			String ip = jsonObject.get("ip").toString();
			String device = "MOBILE";
			log.info("---insertTabR900:"+fo100+","+ip+","+objectName+","+"oo"+","+"TOPIC"+","+device+","+0+","+fo100t+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
			int kq =  r900Service.insertTabR900(fo100, ip, objectName, "oo", "TOPIC", device, 0, fo100t, ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
