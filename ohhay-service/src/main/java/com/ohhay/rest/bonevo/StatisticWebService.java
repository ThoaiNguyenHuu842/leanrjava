package com.ohhay.rest.bonevo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.ChartCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mysql.service.R100Service;
import com.ohhay.other.mysql.service.R900Service;
/**
 * @author ThoaiNH
 * statistic web serivce
 * date create: 12/07/2015
 */
@Path("statisticWebService")
public class StatisticWebService {
	private static Logger log = Logger.getLogger(StatisticWebService.class);
	@GET
	@Path("getchart")
	@Produces("application/json")
	public String getchart(@QueryParam("fo100") int fo100, @QueryParam("rn120") int rn120, @QueryParam("type") int type, 
						    @QueryParam("ov101") String ov101) {
		try {
			ChartCriteria chartCiteria = new ChartCriteria(rn120, type, null);
			log.info(chartCiteria);
			R100Service r100Service = (R100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R100);
			R900Service r900Service = (R900Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R900);
			switch (chartCiteria.getType()) {
			case 0: case 4:
				log.info("--reportTabR9004:" + fo100 + ",null,null," + chartCiteria.getRn120() + "," + ov101);
				return QbRestUtils.convertToJsonStringForProc(r900Service.reportTabR9004(fo100, null, null, chartCiteria.getRn120(), ov101));
			case 1:
				log.info("--reportTabR9001:" + fo100 + ",null,null," + chartCiteria.getRn120() + "," + ov101);
				return QbRestUtils.convertToJsonStringForProc(r900Service.reportTabR9001(fo100, null, null, chartCiteria.getRn120(), ov101));
			case 2:
				log.info("--reportTabR9002:" + fo100 + ",null,null," + chartCiteria.getRn120() + "," + ov101);
				return QbRestUtils.convertToJsonStringForProc(r900Service.reportTabR9002(fo100, null, null, chartCiteria.getRn120(), ov101));
			case 3:
				log.info("--reportTabR9003:" + fo100 + ",null,null," + chartCiteria.getRn120() + "," + ov101);
				return QbRestUtils.convertToJsonStringForProc(r900Service.reportTabR9003(fo100, null, null, chartCiteria.getRn120(), ov101));
			case 5:
				log.info("--reportTabR9005:" + fo100 + ",null,null," + chartCiteria.getRn120() + "," + ov101);
				return QbRestUtils.convertToJsonStringForProc(r900Service.reportTabR9005(fo100, null, null, chartCiteria.getRn120(), ov101));
			case 6:
				log.info("--reportTabR9006:" + fo100 + ",null,null," + chartCiteria.getRn120() + "," + ov101);
				return QbRestUtils.convertToJsonStringForProc(r900Service.reportTabR9006(fo100, null, null, chartCiteria.getRn120(), ov101));
			case 7:
				log.info("--reportTabR9007:" + fo100 + ",null,null," + chartCiteria.getRn120() + "," + ov101);
				return QbRestUtils.convertToJsonStringForProc(r900Service.reportTabR9007(fo100, null, null, chartCiteria.getRn120(), ov101));
			case 8:
				log.info("--reportTabR9008:" + fo100 + ",null,null," + chartCiteria.getRn120() + "," + ov101);
				return QbRestUtils.convertToJsonStringForProc(r900Service.reportTabR9008(fo100, null, null, chartCiteria.getRn120(), ov101));
			case 9:
				log.info("--reportTabR9009:" + fo100 + ",null,null," + chartCiteria.getRn120() + "," + ov101);
				return QbRestUtils.convertToJsonStringForProc(r900Service.reportTabR9009(fo100, null, null, chartCiteria.getRn120(), ov101));
			default:
				return QbRestUtils.convertToJsonStringForProc(null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
