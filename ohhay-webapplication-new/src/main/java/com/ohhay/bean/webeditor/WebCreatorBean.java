package com.ohhay.bean.webeditor;

import org.apache.log4j.Logger;
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
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.lego.entities.mongo.base.web.OhhayLegoWebBase;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThoaiNH 
 * create Oct 13, 2015
 */
@Controller
@Scope("prototype")
public class WebCreatorBean {
	private static Logger log = Logger.getLogger(WebCreatorBean.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;
	/**
	 * create topic content EVO if topic id = 0
	 * else load current topic to edit
	 * @return
	 */
	@RequestMapping(value = "/webCreatorBean.createTopicContentEvo", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse createTopicContentEvo(@ModelAttribute("topicId") int topicId) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			OhhayLegoWebBase e400mg = webLegoService.getWebTopicData(q100.getPo100(), topicId);
			if(e400mg  != null)
			{
				log.info("---e400mg:"+e400mg);
				jsonResponse.setResult(e400mg);
			}
			else
				jsonResponse.setResult(webLegoService.createWebEvo(q100.getPo100(), q100.getM900mg().getHv101(), true, 0));
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @param fo100
	 * @return
	 */
	@RequestMapping(value = "/webCreatorBean.create", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse createForViewer(@ModelAttribute("pe400") int pe400) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		OhhayLegoWebBase ohhayLegoWebBase = webLegoService.getWebEvoData(ApplicationConstant.FO100_SUPER_ADMIN, pe400);
		jsonResponse.setResult(ohhayLegoWebBase);
		log.info("---web data:"+ohhayLegoWebBase);
		return jsonResponse;
	}
	/**
	 * create web data for trial function
	 * @param fo100
	 * @return
	 */
	@RequestMapping(value = "/webCreatorBean.createTrial", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse createForTrial(@ModelAttribute("pe400") int pe400) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		OhhayLegoWebBase ohhayLegoWebBase = webLegoService.getWebEvoDataTrial(pe400);
		jsonResponse.setResult(ohhayLegoWebBase);
		log.info("---web data trial:"+ohhayLegoWebBase);
		return jsonResponse;
	}
}
