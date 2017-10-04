package com.ohhay.interceptor;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mysql.service.M350Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author ThoaiNH create Mar 01, 2015
 */
@Controller
@Scope("prototype")
public class WebSmtpBean {
	private static Logger log = Logger.getLogger(WebSmtpBean.class);
	@Autowired
	TemplateService templateService;

	/**
	 * update: 16/03/2016 fix bug title color is white in email send mail form
	 * contact of web template
	 */
	@RequestMapping(value = "/webSmtpBean.sendContact", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse sendContact(@ModelAttribute("fo100") String fo100,
			@ModelAttribute("email") String email, @ModelAttribute("content") String content, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Document document = Jsoup.parse(content);
		// don't reset CSS set bold for title
		Elements resetElements = document.select("span:not(.not-remove),font");
		// don't reset br set for field
		document.select("br:not(.not-remove)").remove();
		if (resetElements != null) {
			resetElements.attr("style", "");
			resetElements.attr("color", "");
		}
		if (email.equals("")) {
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			int fo100Rl = Integer.parseInt(AESUtils.decrypt(fo100));
			M900MG m900mg = templateService.findDocumentById(fo100Rl, fo100Rl, M900MG.class);
			log.info("F0100 : " + AESUtils.decrypt(fo100));
			if (m900mg != null)
				email = m900mg.getMv903Decrypt();
		}
		log.info("mail : " + email);
		log.info("--email content:" + document.html());
		M350Service m350Service = (M350Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350);
		int kq = m350Service.sendMailTabM350Confirm(ApplicationConstant.FO100_SUPER_ADMIN, email, null,
				ApplicationHelper.decodeTopicString("BONEVO - Email from your website"),
				ApplicationHelper.decodeTopicString(document.html()), ApplicationConstant.MV907_SUPER_ADMIN);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		jsonResponse.setResult(kq);
		return jsonResponse;
	}

}
