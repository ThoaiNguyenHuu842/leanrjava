package com.ohhay.rest.bonevo;

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
import com.ohhay.core.entities.oracle.V500OR;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.service.V500ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.A400MG;

/**
 * @author ThoaiNH
 *
 */
@Path("v500ORWebService")
public class V500ORWebService {
	private static Logger log = Logger.getLogger(V500ORWebService.class);
	/*@POST
	@Path("updateTabV500")
	@Produces("application/json")
	public String updateTabV500(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int pnFO100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int pnFV500 = Integer.parseInt(jsonObject.get("fv500").toString());
			String pvKEYID = jsonObject.get("keyId").toString();
			int pnNN116 = Integer.parseInt(jsonObject.get("nn116").toString());
			V500ORService v500orService = (V500ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V500OR);
			log.info("---updateTabV500:"+pnFO100+","+pnFV500+","+pvKEYID+","+pnNN116+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
			int kq = v500orService.updateTabV500EVO(pnFO100, pnFV500,pvKEYID, pnNN116, ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}*/
	@GET
	@Path("listOfTabV500")
	@Produces("application/json")
	public String listOfTabV500(@QueryParam("realSkuID") String realSkuID) {
		try {
			V500ORService v500orService = (V500ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V500OR);
			log.info("---listOfTabV500:"+realSkuID+","+null+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
			List<V500OR> list = v500orService.listOfTabV500(realSkuID, null, ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
