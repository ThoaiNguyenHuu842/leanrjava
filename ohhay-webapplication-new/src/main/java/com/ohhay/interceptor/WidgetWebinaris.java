package com.ohhay.interceptor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mysql.service.M350Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author ThoaiNH
 * create Jan 22, 2016
 */
@Controller
@Scope("prototype")
public class WidgetWebinaris {
	private static Logger log = Logger.getLogger(WidgetWebinaris.class);

	/**
	 * send mail form contact of web template
	 */
	@RequestMapping(value = "/widgetWebinaris.loadProjectData", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse sendContact(@ModelAttribute("pId") String pId) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		jsonResponse.setResult(1);
		return jsonResponse;
	}

}
