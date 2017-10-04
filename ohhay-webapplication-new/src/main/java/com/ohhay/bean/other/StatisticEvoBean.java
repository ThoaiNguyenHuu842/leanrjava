package com.ohhay.bean.other;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.R950MGService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author TuNt create date Mar 16, 2017 ohhay-webapplication-new change bean
 *         statistic
 */
@Controller
@Scope("prototype")
public class StatisticEvoBean {
	private static Logger log = Logger.getLogger(StatisticEvoBean.class);

	/**
	 * @param pnWebid
	 * @param pnDateCod
	 * @param pnDateFromString
	 * @param pnDateToString
	 * @return
	 */
	@RequestMapping(value = "/statisticBean.getchartEvo", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getChartEvo(@ModelAttribute("type") int type, @ModelAttribute("webId") int webId,
			@ModelAttribute("dateCod") int dateCod, @ModelAttribute("dateFromString") String dateFromString,
			@ModelAttribute("dateToString") String dateToString, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		log.info("---------param chart---------");
		log.info("---------type--------- :  " + type);
		log.info("---------webId--------- :  " + webId);
		log.info("---------dateCod---------: " + dateCod);
		log.info("---------dateFromString---------: " + dateFromString);
		log.info("---------dateToString---------: " + dateToString);
		log.info("---------end param chart---------: ");
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 == null) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(result.getAllErrors());
		} else {
			R950MGService r950mgService = (R950MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R950MG);
			switch (type) {
			case 0:
				jsonResponse.setResult(r950mgService.reportTab001(q100.getPo100(), webId, dateCod, dateFromString, dateToString));
				break;
			case 1:
				jsonResponse.setResult(r950mgService.reportTab002(q100.getPo100(), webId, dateCod, dateFromString, dateToString));
				break;
			case 2:
				jsonResponse.setResult(r950mgService.reportTab003(q100.getPo100(), webId, dateCod, dateFromString, dateToString));
				break;
			case 3:
				jsonResponse.setResult(r950mgService.reportTab004(q100.getPo100(), webId, dateCod, dateFromString, dateToString));
				break;
			case 4:
				jsonResponse.setResult(r950mgService.reportTab006(q100.getPo100(), webId, dateCod, dateFromString, dateToString));
				break;
			case 5:
				jsonResponse.setResult(r950mgService.reportTab005(q100.getPo100(), webId, dateCod, dateFromString, dateToString));
				break;
			default:
				break;
			}
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		}
		return jsonResponse;
	}

}
