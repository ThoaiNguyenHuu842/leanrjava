package com.ohhay.bean.setting;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThoaiNH
 * create Jan 5, 2016
 * controller for SEO function
 */
@Controller
@Scope("prototype")
public class SettingSEOBean {
	private static Logger log = Logger.getLogger(SettingSEOBean.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;
	/**
	 * save new domain
	 */
	@RequestMapping(value = "/seo.save", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse saveDomain(@ModelAttribute("imgBase64") String imgBase64, @ModelAttribute("imgBase64Fav") String imgBase64Fav,  @ModelAttribute("webId") int webId,
													@ModelAttribute("title") String title, @ModelAttribute("description") String description, @ModelAttribute("googleTrackingScript") String googleTrackingScript, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			int kq = webLegoService.updateSEOinfo(q100.getPo100(), webId, title, description, imgBase64, imgBase64Fav, q100.getM900mg().getRegion(), googleTrackingScript);
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}

}
