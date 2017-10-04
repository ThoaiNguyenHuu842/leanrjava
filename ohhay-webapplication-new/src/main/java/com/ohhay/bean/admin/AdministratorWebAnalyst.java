package com.ohhay.bean.admin;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.AdministratorCMSCriteria;
import com.ohhay.core.criteria.AdministratorGeneralCriteria;
import com.ohhay.core.criteria.ChartPieAnalyst;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.core.mongo.service.E400MGService;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThoaiVt create date Mar 16, 2016 ohhay-webapplication-new
 */
@Controller
@Scope("prototype")
public class AdministratorWebAnalyst implements Serializable {
	Logger logger = Logger.getLogger(AdministratorWebAnalyst.class);

	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_E400MG)
	E400MGService e400mgService;
	
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	WebLegoService webLegoService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/adminWebAnalyst.loadWebAllWeb", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadCMS(
			@ModelAttribute(value = "adminWebAnalyst") AdministratorGeneralCriteria adminWebAnalystCriteria) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		logger.info("Data -- : " + adminWebAnalystCriteria);
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<E400MG> listE400s = e400mgService.getListAllsite(q100.getPo100(), adminWebAnalystCriteria.getOffset(),10,adminWebAnalystCriteria.getPvSearch());
			E400MG infoStatisticWeb = e400mgService.getStatisticWeb(q100.getPo100());
			logger.info("SIZE DATA : " + listE400s.size());
			logger.info("DTA : "+infoStatisticWeb.toString());
			jacksonResponse.setResult(listE400s);
			jacksonResponse.setResult2(infoStatisticWeb);
			return jacksonResponse;
		}
		return jacksonResponse;
	}

	@RequestMapping(value = "/adminWebAnalyst.loadPieChart", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse loadDataForPieChart() {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<ChartPieAnalyst> data = e400mgService.getChartData(q100.getPo100());
			logger.info("Data pie " + data);
			jacksonResponse.setResult(data);
		}
		return jacksonResponse;
	}

	@RequestMapping(value="/adminWebAnalyst.loadPieChartWebid",method=RequestMethod.POST)
	public @ResponseBody JacksonResponse loadDataOfPieChart(@ModelAttribute("dataPieChart") AdministratorGeneralCriteria administratorPieCriteria) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<ChartPieAnalyst> data = e400mgService.getCharOfWebid(q100.getPo100(),Integer.parseInt(administratorPieCriteria.getNameAccount()));
			logger.info("Data pie " + data);
			jacksonResponse.setResult(data);
		}
		return jacksonResponse;
	}
	
	@RequestMapping(value="/adminWebAnalyst.createToMysites",method=RequestMethod.POST)
	public @ResponseBody JacksonResponse getToMysites(@ModelAttribute("createToMysites") AdministratorCMSCriteria administratorCMSCriteria){
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100=AuthenticationHelper.getUserLogin();
		if(q100!=null)
		{
			int valueCheck = webLegoService.createBytemplate(q100.getPo100(),administratorCMSCriteria.getId(), false);
			jacksonResponse.setResult(valueCheck);
		}
		return jacksonResponse;
	}
}
