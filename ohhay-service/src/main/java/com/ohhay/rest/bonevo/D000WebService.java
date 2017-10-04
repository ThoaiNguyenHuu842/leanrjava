package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mysql.service.D000Service;

/**
 * @author Thiện
 *
 */
@Path("d000WebService")
public class D000WebService {
	private static Logger log = Logger.getLogger(D000WebService.class);
	/*
	 * list jobs
	 */
	@GET
	@Path("combTabD000")
	@Produces("application/json")
	public String combTabD000(@QueryParam("dv002") String dv002,@QueryParam("pvLOGIN") String pvLogin){
		try {
			D000Service d000se = (D000Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_D000);
			log.info("---combTabD000:"+dv002+","+pvLogin);
			List<ComtabItem> list = d000se.combTabD000(dv002,pvLogin);
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
}
