package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.entities.ItemTabN750;
import com.ohhay.other.mysql.service.N750Service;

/**
 * @author ThoaiNH
 *
 */
@Path("n750WebService")
public class N750WebService {
	private static Logger log = Logger.getLogger(N750WebService.class);
	/*
	 * combo box language when register account ohhay
	 */ 
	@GET
	@Path("nhayCombtabn750")
	@Produces("application/json")
	public String ahayCombtabn750() {
		try {
			N750Service n750se = (N750Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N750);
			log.info("---nhayCombTabN750:"+ApplicationConstant.PVLOGIN_ANONYMOUS);
			List<ItemTabN750> list = n750se.nhayCombTabN750(ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/*
	 * combo box language int localize function
	 */
	@GET
	@Path("nhayCombtabn750Set")
	@Produces("application/json")
	public String ahayCombtabn750Set() {
		try {
			N750Service n750se = (N750Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N750);
			log.info("---nhayCombTabN750Set:"+ApplicationConstant.PVLOGIN_ANONYMOUS);
			List<ItemTabN750> list = n750se.nhayCombTabN750Set(ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
