package com.ohhay.bean.setting;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.util.AESUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.PaymentCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.other.entities.ItemTabN750;
import com.ohhay.other.mysql.service.N750Service;
import com.ohhay.web.core.api.service.PaymentWallOrelService;

/**
 * @author ThoaiNH
 *
 */
@Controller
@Scope("prototype")
public class SettingPaymentBean {
	private static Logger log = Logger.getLogger(SettingPaymentBean.class);
	/**
	 * get PayMentWidget
	 * @return
	 */
	@RequestMapping(value = "/setting.getPayMentWidget", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse getPayMentWidget(@ModelAttribute("paymentCriteria") @Valid PaymentCriteria paymentCriteria, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		if (result.hasErrors()) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);	
			jsonResponse.setResult(result.getAllErrors());
		}
		else {
			int kq = 0;
			Q100 q100 = (Q100) AuthenticationHelper
					.getUserLogin();
			if (q100 != null) {
				PaymentWallOrelService paymentWallOrelService = (PaymentWallOrelService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_PAYMENTOREL);
				log.info(paymentCriteria);
				
				Date  userBirthDate  = DateHelper.convertStringToDate(paymentCriteria.getUserBirthDateString(), "dd/MM/yyyy");
				String html = paymentWallOrelService.getPayMentWidget(q100.getPo100(), paymentCriteria.getWidgetType(),
																	  paymentCriteria.getSkuid(), paymentCriteria.getUserAddress(),
																	  userBirthDate,paymentCriteria.getUserCity(),
																	  paymentCriteria.getUserCountry(), paymentCriteria.getUserZip(),
																	  paymentCriteria.getUserFisrtName(),paymentCriteria.getUserLastName(),
																	  paymentCriteria.getUserState(), paymentCriteria.getUserSex(),
																	  q100.getM900mg().getNv100Decrypt(),paymentCriteria.getUserEmail());
				kq = 1;
				jsonResponse.setResult2(AESUtils.decrypt(html));
				
			}
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		}
		return jsonResponse;
	}
	
	/**
	 * GET LIST ITEM COUNTRY
	 */
	@RequestMapping(value = "/setting.getListCountry", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getListCountry(){
		JacksonResponse jsonResponse = new JacksonResponse();
			Q100 q100 = (Q100) AuthenticationHelper
					.getUserLogin();
			if (q100 != null) {
				N750Service n750Service = (N750Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N750);
				List<ItemTabN750> listN750 = n750Service.nhayCombTabN750(q100.getQv101());
				if (listN750 != null && listN750.size() > 0) {
					jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
					jsonResponse.setResult(listN750);
					return jsonResponse;
				}
			}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;
	}
}
