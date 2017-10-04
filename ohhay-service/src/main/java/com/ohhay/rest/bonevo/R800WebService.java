package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.R800MG;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mongo.service.R800MGService;

/**
 * @author ThongQB
 * date create: 03/09/2015
 */
@Path("r800WebService")
public class R800WebService {
	private static Logger log = Logger.getLogger(R800WebService.class);
	@POST
	@Path("insertTabR800Topic")
	@Produces("application/json")
	public String insertTabR800Topic(String postString) {
		try {
			R800MGService r800Service = (R800MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R800);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int fm150 = Integer.parseInt(jsonObject.get("fm150").toString());
			int fo100r = Integer.parseInt(jsonObject.get("fo100r").toString());
			String rv801 = jsonObject.get("rv801").toString();
			log.info("---insertTabR800:"+fo100+","+fm150+","+fo100r+","+rv801);
			int kq =  r800Service.insertTabR800(fo100, fm150, fo100r, rv801);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	@GET
	@Path("getListReportTopic")
	@Produces("application/json")
	public String getListReportTopic(@QueryParam("fo100") int fo100, @QueryParam("offset") int offset, @QueryParam("limit") int limit) {
		try {
			R800MGService r800mgService = (R800MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R800MG);
			List<R800MG> kq = r800mgService.getListOfTabR800(fo100, offset, limit);
			log.info("R800Service: "+QbRestUtils.convertToJsonStringForProc(kq));
			if(kq != null)
				return QbRestUtils.convertToJsonStringForProc(kq);	
			else
				return QbRestUtils.getErrorStatus();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	
}
