package com.ohhay.rest.bonevo;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ChartItemInfo2;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.AdminService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.other.mysql.service.O100Service;

/**
 * @author thoai nguyen
 * date create: 11/07/2015
 */
@Path("adminWebService")
public class AdminWebservice {
	private static Logger log = Logger.getLogger(AdminWebservice.class);
	/**
	 * @param postString: url of website want to get color
	 * @return
	 */
	@POST
	@Path("removeAccount")
	@Produces("application/json")
	public String updateWebColorByUrl(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String phone = jsonObject.get("phone").toString();
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocument(0, M900MG.class, new QbCriteria(QbMongoFiledsName.MV907, phone));
			if(m900mg != null){ 
				int po100 = m900mg.getId();
				System.out.println("--po100:"+po100);
				O100Service o100Service = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
				o100Service.stornoTabO100(po100,"");
				M900MGService m900mgService = (M900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
				m900mgService.storNotTabM900(po100);
			}
			return QbRestUtils.convertToJsonStringForFunc(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/**
	 * get report o!hay account
	 * @param pdDATEFString
	 * @param pdDATETString
	 * @return
	 */
	@GET
	@Path("reportWebDaily")
	@Produces("application/json")
	public String reportWebDaily(@QueryParam("dateF") String pdDATEFString, @QueryParam("dateT") String pdDATETString) {
		try {
			AdminService adminService = (AdminService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_ADMIN);
			Date dateF = DateHelper.convertStringToDate(pdDATEFString);
			Date dateT = DateHelper.convertStringToDate(pdDATETString);
			log.info("---reportWebDaily:"+DateHelper.toSQLDate(dateF)+","+DateHelper.toSQLDate(dateT)+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
			List<ChartItemInfo2> list = adminService.reportWebDaily(DateHelper.toSQLDate(dateF), DateHelper.toSQLDate(dateT), ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

}
