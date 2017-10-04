package com.ohhay.bean.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThoaiNH
 * create Dec 29, 2015
 */
@Controller
@Scope("prototype")
public class EvoTemplateBean {
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;
	@Autowired
	private TemplateService templateService;
	/**
	 * load list template with en404 condition
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value = "/evoTemplateBean.loadListTemplateAdvanced ", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListTemplateAdvanced(@ModelAttribute("en404") int en404) {
		JacksonResponse jsonResponse = new JacksonResponse();
		List<E400MG> listE400 = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E400MG.class, new QbCriteria("EN404",en404));
		jsonResponse.setResult(listE400);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/evoTemplateBean.loadListTemplate", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListTemplate() {
		JacksonResponse jsonResponse = new JacksonResponse();
		List<E400MG> listE400 = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E400MG.class, new QbCriteria("EN402",1));
		jsonResponse.setResult(listE400);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}
	/**
	 * load list KUB templates
	 * @return
	 */
	@RequestMapping(value = "/evoTemplateBean.loadListLandingPage", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListLandingPage() {
		JacksonResponse jsonResponse = new JacksonResponse();
		List<E400MG> listE400 = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E400MG.class, new QbCriteria("EN404",2));
		jsonResponse.setResult(listE400);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}
	/**
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value = "/evoTemplateBean.createWeb", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse create(@ModelAttribute("templateId") int templateId) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			String returnMess = webLegoService.checkRightCreateWeb(q100.getAuthenticationRightInfo(), q100.getPo100());
			if(ApplicationConstant.RE_VAILD_RIGHT.equals(returnMess)){
				jsonResponse.setResult(webLegoService.createBytemplate(q100.getPo100(), templateId, true));
				jsonResponse.setResult2(q100.getQv101());
			}
			else
			{
				jsonResponse.setResult(-2);
				jsonResponse.setMesg(returnMess);
			}
		}
		else
			jsonResponse.setResult(-1);
		return jsonResponse;
	}
}
