package com.ohhay.bean.other;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.ChartCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.other.mysql.service.R900Service;

/** 
 * @author ThoaiNH
 * statistics bean
 */
@Controller
@Scope("prototype")
public class StatisticBean {
	private static Logger log = Logger.getLogger(StatisticBean.class);
	/**
	 * chart all type
	 * @param chartCiteria
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/statisticBean.getchart1", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse getChart1(@ModelAttribute("chartCiteria") ChartCriteria chartCiteria, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (result.hasErrors() || q100 == null) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(result.getAllErrors());
		}
		else {
			log.info(chartCiteria);
			R900Service r900Service = (R900Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R900);
			switch (chartCiteria.getType()) {
			case 0: case 4:
				log.info("--reportTabR9004:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9004(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 1:
				log.info("--reportTabR9001:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9001(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 2:
				log.info("--reportTabR9002:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9002(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 3:
				log.info("--reportTabR9003:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9003(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 5:
				log.info("--reportTabR9005:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9005(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 6:
				log.info("--reportTabR9006:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9006(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 7:
				log.info("--reportTabR9007:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9007(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 8:
				log.info("--reportTabR9008:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9008(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 9:
				log.info("--reportTabR9009:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9009(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 10:
				log.info("--reportTabR9010:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9010(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 11:
				log.info("--reportTabR9011:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9011(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 12:
				log.info("--reportTabR9012:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9012(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 13:
				log.info("--reportTabR9013:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9013(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 14:
				log.info("--reportTabR9014:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9014(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			case 15:
				log.info("--reportTabR9015:" + q100.getPo100() + ",null,null,"+ chartCiteria.getRv957() +","+ chartCiteria.getRn120() +","+ chartCiteria.getWebId()+ "," + q100.getQv101());
				jsonResponse.setResult(r900Service.reportTabR9015(q100.getPo100(), null, null, chartCiteria.getRv957(), chartCiteria.getRn120(), chartCiteria.getWebId(), q100.getQv101()));
				break;
			default:
				break;
			}
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		}
		return jsonResponse;
	}
}
