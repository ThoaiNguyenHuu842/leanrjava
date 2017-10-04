package com.ohhay.rest.topic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.amazonaws.services.cloudwatch.model.Statistic;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.ChartCriteria;
import com.ohhay.core.criteria.ChartCriteriaNew;
import com.ohhay.core.entities.ChartItemInfo2;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mysql.service.R900Service;
import com.ohhay.other.mysql.service.R900ServiceNew;

/**
 * @author TuNt
 * create date Jun 17, 2016
 * ohhay-service
 */
@Path("statisticTopicWebService")
public class StatisticTopicWebService {

	/**
	 * @param postString
	 * @return
	 */
	@GET
	@Path("/getChart")
	@Produces("application/json")
	public String statistic(@QueryParam("FO100") int fo100 , @QueryParam("QV101") String qv101, @QueryParam("TYPE") int type, @QueryParam("RN120") int rn120){
		Logger log = Logger.getLogger(Statistic.class);
		R900Service r900Service = (R900Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R900);
		try{
			ChartCriteria chartCiteria = new ChartCriteria();
			chartCiteria.setType(type);
			chartCiteria.setRn120(rn120);
			
			Q100 q100 = new Q100();
			q100.setPo100(fo100);
			q100.setQv101(qv101);
			List<ChartItemInfo2> result = new ArrayList<ChartItemInfo2>();
			
			switch (chartCiteria.getType()) {
			case 7:
				log.info("--reportTabR9007:" + q100.getPo100() + ",null,null,"+ "TOPIC" +","+ chartCiteria.getRn120() +","+ 0+ "," + q100.getQv101());
				result = (r900Service.reportTabR9007(q100.getPo100(), null, null, "TOPIC", chartCiteria.getRn120(), 0, q100.getQv101()));
				break;
			case 8:
				log.info("--reportTabR9008:" + q100.getPo100() + ",null,null,"+ "TOPIC" +","+ chartCiteria.getRn120() +","+ 0+ "," + q100.getQv101());
				result = (r900Service.reportTabR9008(q100.getPo100(), null, null, "TOPIC", chartCiteria.getRn120(), 0, q100.getQv101()));
				break;
			case 9:
				log.info("--reportTabR9009:" + q100.getPo100() + ",null,null,"+ "TOPIC" +","+ chartCiteria.getRn120() +","+ 0+ "," + q100.getQv101());
				result = (r900Service.reportTabR9009(q100.getPo100(), null, null, "TOPIC", chartCiteria.getRn120(), 0, q100.getQv101()));
				break;
			case 10:
				log.info("--reportTabR9010:" + q100.getPo100() + ",null,null,"+ "TOPIC" +","+ chartCiteria.getRn120() +","+ 0+ "," + q100.getQv101());
				result = (r900Service.reportTabR9010(q100.getPo100(), null, null, "TOPIC", chartCiteria.getRn120(), 0, q100.getQv101()));
				break;
			case 11:
				log.info("--reportTabR9011:" + q100.getPo100() + ",null,null,"+ "TOPIC" +","+ chartCiteria.getRn120() +","+ 0+ "," + q100.getQv101());
				result = (r900Service.reportTabR9011(q100.getPo100(), null, null, "TOPIC", chartCiteria.getRn120(), 0, q100.getQv101()));
				break;
			}
			ChartItemInfo2 chartItemInfo2 = new ChartItemInfo2();
			return QbRestUtils.convertToJsonStringForProc(chartItemInfo2, result);
			
		}catch(Exception e){
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * insert tracking for topic
	 * @param postString
	 * @return
	 */
	@POST
	@Path("insertTracking")
	@Produces("application/json")
	public String insertTracking(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(AESUtils.decrypt(jsonObject.get("fo100AES").toString()));//chu topic
			int fo100t = Integer.parseInt(AESUtils.decrypt(jsonObject.get("fo100TAES").toString()));//user login
			String objectName = jsonObject.get("objectName").toString();
			String ipAddress = jsonObject.get("ipAddress").toString();
			String device = jsonObject.get("device").toString();
			String browser = jsonObject.get("browser").toString();
			String os = jsonObject.get("os").toString();
			R900Service r900Service = (R900Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R900);
			return QbRestUtils.convertToJsonStringForProc(r900Service.insertTabR900V1(fo100, ipAddress, objectName, "oo", "TOPIC", device, browser, os, 0, fo100t, ApplicationConstant.PVLOGIN_ANONYMOUS));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return QbRestUtils.getErrorStatus();
	}
	
	
	/**
	 * @param postString
	 * @return
	 */
	@GET
	@Path("/getChartNew")
	@Produces("application/json")
	public String statisticNew(@QueryParam("pnFO100") int pnFO100, @QueryParam("pnRN905") int pnRN905,@QueryParam("pnRN906") int pnRN906 , @QueryParam("pvRV951") String pvRV951, @QueryParam("pnINTER") int pnINTER, @QueryParam("pdFRDAT") String pdFRDAT,@QueryParam("pdTODAT") String pdTODAT,@QueryParam("type") int type){
		Logger log = Logger.getLogger(Statistic.class);
		R900ServiceNew r900ServiceNew = (R900ServiceNew) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R900_NEW);
		try{
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
			ChartCriteriaNew chartCiteria = new ChartCriteriaNew();
			chartCiteria.setPnRN905(pnRN905);
			chartCiteria.setPvRV951((!pvRV951.trim().equals("")? pvRV951 : null));
			chartCiteria.setPnRN906(pnRN906);
			chartCiteria.setPnINTER(pnINTER);
			chartCiteria.setPdFRDAT((!pdFRDAT.trim().equals(null) ? pdFRDAT : null));
			chartCiteria.setPdTODAT((!pdTODAT.trim().equals(null) ? pdTODAT : null));
			chartCiteria.setType(type);
			
			log.info("Item chart : "+chartCiteria.toString());
			
			List<ChartItemInfo2> result = new ArrayList<ChartItemInfo2>();
			
			switch (chartCiteria.getType()) {
			case 7:
				log.info("--reportTabR950V:" + pnFO100+","+chartCiteria.getPnRN905()+","+chartCiteria.getPvRV951()+","+ chartCiteria.getPnINTER()+","+chartCiteria.getPdFRDAT()+","+chartCiteria.getPdTODAT()+","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				result = r900ServiceNew.reportTabR950V(pnFO100,chartCiteria.getPnRN905(),chartCiteria.getPnRN906(), chartCiteria.getPnINTER(), chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				break;
			case 8:
				log.info("--reportTabR950L:" + pnFO100+","+chartCiteria.getPnRN905()+","+chartCiteria.getPvRV951()+","+ chartCiteria.getPnINTER()+","+chartCiteria.getPdFRDAT()+","+chartCiteria.getPdTODAT()+","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				result = r900ServiceNew.reportTabR950L(pnFO100, chartCiteria.getPnRN905(),chartCiteria.getPnRN906(), chartCiteria.getPvRV951(), chartCiteria.getPnINTER(), chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				break;
			case 9:
				log.info("--reportTabR950D:" + pnFO100+","+chartCiteria.getPnRN905()+","+chartCiteria.getPvRV951()+","+ chartCiteria.getPnINTER()+","+chartCiteria.getPdFRDAT()+","+chartCiteria.getPdTODAT()+","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				result = r900ServiceNew.reportTabR950D(pnFO100, chartCiteria.getPnRN905(),chartCiteria.getPnRN906(), chartCiteria.getPvRV951(), chartCiteria.getPnINTER(), chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				break;
			case 10:
				log.info("--reportTabR950B:" + pnFO100+","+chartCiteria.getPnRN905()+","+chartCiteria.getPvRV951()+","+ chartCiteria.getPnINTER()+","+chartCiteria.getPdFRDAT()+","+chartCiteria.getPdTODAT()+","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				result = r900ServiceNew.reportTabR950B(pnFO100, chartCiteria.getPnRN905(),chartCiteria.getPnRN906(), chartCiteria.getPvRV951(), chartCiteria.getPnINTER(), chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				break;
			case 11:
				log.info("--reportTabR950S:" + pnFO100+","+chartCiteria.getPnRN905()+","+chartCiteria.getPvRV951()+","+ chartCiteria.getPnINTER()+","+chartCiteria.getPdFRDAT()+","+chartCiteria.getPdTODAT()+","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				result = r900ServiceNew.reportTabR950S(pnFO100, chartCiteria.getPnRN905(),chartCiteria.getPnRN906(), chartCiteria.getPvRV951(), chartCiteria.getPnINTER(), chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				break;
			case 12:
				log.info("--reportTabR950J:" + pnFO100+","+chartCiteria.getPnRN905()+","+chartCiteria.getPvRV951()+","+ chartCiteria.getPnINTER()+","+chartCiteria.getPdFRDAT()+","+chartCiteria.getPdTODAT()+","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				result = r900ServiceNew.reportTabR950J(pnFO100, chartCiteria.getPnRN905(),chartCiteria.getPnRN906(), chartCiteria.getPvRV951(), chartCiteria.getPnINTER(), chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				break;
			}
			ChartItemInfo2 chartItemInfo2 = new ChartItemInfo2();
			return QbRestUtils.convertToJsonStringForProc(chartItemInfo2, result);
			
		}catch(Exception e){
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * insert tracking for topic new
	 * @param postString
	 * @return
	 */
	@POST
	@Path("insertTrackingNew")
	@Produces("application/json")
	public String insertTrackingNew(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(AESUtils.decrypt(jsonObject.get("fo100AES").toString()));//chu topic
//			int fo100t = Integer.parseInt(AESUtils.decrypt(jsonObject.get("fo100TAES").toString()));//user login
			String ipAddress = jsonObject.get("ipAddress").toString();
			String sessionId = jsonObject.get("sessionId").toString();
			String objectName = (!jsonObject.get("objectName").toString().trim().equals("") ? jsonObject.get("objectName").toString().trim() : null);
			int refid = jsonObject.getInt("refid");
			String language = jsonObject.get("language").toString();
			String browser = jsonObject.get("browser").toString();
			String device = jsonObject.get("device").toString();
			String os = jsonObject.get("os").toString();
			String pvRV908 =  jsonObject.get("pvRV908").toString();
			R900Service r900Service = (R900Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R900);
			return QbRestUtils.convertToJsonStringForProc(r900Service.insertTabR900a(fo100, ipAddress, sessionId,objectName, refid, language,pvRV908,browser,device,os, ApplicationConstant.PVLOGIN_ANONYMOUS));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return QbRestUtils.getErrorStatus();
	}
}
