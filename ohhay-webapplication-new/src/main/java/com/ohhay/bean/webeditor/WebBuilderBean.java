package com.ohhay.bean.webeditor;

import java.util.Date;
import java.util.Map;

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
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.core.mongo.service.E400MGService;
import com.ohhay.web.lego.entities.mongo.topic.ET400MG;
import com.ohhay.web.lego.service.WebLegoService;
import com.oohhay.web.lego.utils.EvoWebType;
/**
 * @author ThoaiNH 
 * create Oct 13, 2015
 */
@Controller
@Scope("prototype")
public class WebBuilderBean {
	private static Logger log = Logger.getLogger(WebBuilderBean.class);
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private E400MGService e400mgService;
	/**
	 * @param ohhayLegoWebBase
	 * @return
	 */
	@RequestMapping(value = "/webBuilderBean.save", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse save(@ModelAttribute("html") String html, @ModelAttribute("webId") int webId, @ModelAttribute("data") String data,
			@ModelAttribute("apiCompSelector") String apiCompSelector, @ModelAttribute("editToolSelector") String editToolSelector, @ModelAttribute("mobileEditor") String mobileEditor) {
		log.info("--webId:"+webId);
		log.info("--data:"+data);
		log.info("--apiCompSelector:"+apiCompSelector);
		log.info("--editToolSelector:"+editToolSelector);
		log.info("--html:"+html);
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		int kq = 0;
		if (q100 != null)
		{
			if("on".equalsIgnoreCase(mobileEditor))
				kq = webLegoService.saveMobileVersion(q100.getPo100(), webId, html, data, apiCompSelector, editToolSelector, EvoWebType.EVOTYPE_WEB);
			else {
				Map<String, Long> returnData = webLegoService.save(q100.getPo100(), webId, html, data, apiCompSelector, editToolSelector, EvoWebType.EVOTYPE_WEB);
				if(returnData != null)
					kq = 1;
				jsonResponse.setResult2(returnData);
			}
		}
		jsonResponse.setResult(kq);
		return jsonResponse;
	}
	/**
	 * @param ohhayLegoWebBase
	 * @return
	 */
	@RequestMapping(value = "/webBuilderBean.saveTopicContent", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse saveTopicContent(@ModelAttribute("topicId") int topicId, @ModelAttribute("data") String data,
			@ModelAttribute("apiCompSelector") String apiCompSelector, @ModelAttribute("editToolSelector") String editToolSelector) {
		log.info("--topicId:"+topicId);
		log.info("--data:"+data);
		log.info("--apiCompSelector:"+apiCompSelector);
		log.info("--editToolSelector:"+editToolSelector);
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		int kq = 0;
		if (q100 != null)
		{
			ET400MG e400mg = templateService.findDocument(q100.getPo100(), ET400MG.class,new QbCriteria(QbMongoFiledsName.FM150, topicId));
			if(e400mg == null)
			{
				//create data for first time
				e400mg = new ET400MG();
				e400mg.setEl446(new Date());
				e400mg.setEl448(new Date());
				e400mg.setFo100(q100.getPo100());
				e400mg.setFm150(topicId);
				long webId = 0;
				try {
					webId = sequenceService.getNextSequenceId(q100.getPo100(), QbMongoCollectionsName.ET400);
					e400mg.setId(webId);
					templateService.saveDocument(q100.getPo100(), e400mg, QbMongoCollectionsName.ET400);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Map<String, Long> returnData = webLegoService.save(q100.getPo100(), e400mg.getId(), null, data, apiCompSelector, editToolSelector, EvoWebType.EVOTYPE_TOPIC);
			if(returnData != null)
				kq = 1;
			jsonResponse.setResult2(returnData);
		}
		jsonResponse.setResult(kq);
		return jsonResponse;
	}
	/**
	 * set site name for web
	 * @param webId
	 * @param siteName
	 * @return
	 */
	@RequestMapping(value = "/webBuilderBean.updateSiteName", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse updateSiteName(@ModelAttribute("webId") int webId, @ModelAttribute("siteName") String siteName) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		int kq = 0;
		if (q100 != null)
			kq = e400mgService.updateSiteName(q100.getPo100(), webId, siteName);
		jsonResponse.setResult(kq);
		return jsonResponse;
	}
}
