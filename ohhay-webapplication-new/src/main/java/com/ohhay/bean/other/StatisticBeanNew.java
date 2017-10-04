package com.ohhay.bean.other;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.ChartCriteriaNew;
import com.ohhay.core.entities.ChartItemInfo2;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.other.mysql.service.R900ServiceNew;

/**
 * @author ThoaiNH statistics bean
 */
@Controller
@Scope("prototype")
public class StatisticBeanNew {
	private static Logger log = Logger.getLogger(StatisticBeanNew.class);

	/**
	 * chart all type
	 * 
	 * @param chartCiteria
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/statisticBean.getchart1New", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse getChart1New(@ModelAttribute("chartCiteria") ChartCriteriaNew chartCiteria,
			BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		log.info("---------param chart---------" + chartCiteria);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		log.info("resut--" + result.hasErrors());
		if (q100 == null) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(result.getAllErrors());
		} else {
			log.info(chartCiteria);
			R900ServiceNew r900Service = (R900ServiceNew) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R900_NEW);
			List<ChartItemInfo2> list = null;
			switch (chartCiteria.getType()) {
			case 0: // tong
				jsonResponse.setResult(r900Service.reportTabR950V(q100.getPo100(), chartCiteria.getPnRN905(),
						chartCiteria.getPnRN906(), chartCiteria.getPnINTER(), chartCiteria.getPdFRDAT(),
						chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS));
				break;
			case 1: // trinh duyet
				list = r900Service.reportTabR950B(q100.getPo100(), chartCiteria.getPnRN905(), chartCiteria.getPnRN906(),
						chartCiteria.getPvRV951(), chartCiteria.getPnINTER(), chartCiteria.getPdFRDAT(),
						chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				jsonResponse.setResult(list);
				break;
			case 2:// thiet bi
				jsonResponse.setResult(r900Service.reportTabR950D(q100.getPo100(), chartCiteria.getPnRN905(),
						chartCiteria.getPnRN906(), chartCiteria.getPvRV951(), chartCiteria.getPnINTER(),
						chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS));
				break;
			case 3:// hệ điều hành
				jsonResponse.setResult(r900Service.reportTabR950S(q100.getPo100(), chartCiteria.getPnRN905(),
						chartCiteria.getPnRN906(), chartCiteria.getPvRV951(), chartCiteria.getPnINTER(),
						chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS));
				break;
			case 4:// objects
				jsonResponse.setResult(r900Service.reportTabR950J(q100.getPo100(), chartCiteria.getPnRN905(),
						chartCiteria.getPnRN906(), chartCiteria.getPvRV951(), chartCiteria.getPnINTER(),
						chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS));
				break;
			case 5:// url links
				jsonResponse.setResult(r900Service.reportTabR950U(q100.getPo100(), chartCiteria.getPnRN905(),
						chartCiteria.getPnRN906(), chartCiteria.getPvRV951(), chartCiteria.getPnINTER(),
						chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS));
				break;
			case 6:// quốc gia
				jsonResponse.setResult(r900Service.reportTabR950L(q100.getPo100(), chartCiteria.getPnRN905(),
						chartCiteria.getPnRN906(), chartCiteria.getPvRV951(), chartCiteria.getPnINTER(),
						chartCiteria.getPdFRDAT(), chartCiteria.getPdTODAT(), ApplicationConstant.PVLOGIN_ANONYMOUS));
				break;
			default:
				break;
			}
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		}
		log.info("result chart --- " + jsonResponse.getResult());
		return jsonResponse;
	}
}
