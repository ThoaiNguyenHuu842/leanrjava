package com.ohhay.bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.AdministratorCMSCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.core.entities.mongo.web.A400MG;
import com.ohhay.web.core.entities.mongo.web.A950MG;
import com.ohhay.web.core.mongo.service.E400MGService;
import com.ohhay.web.lego.entities.mongo.base.web.OhhayLegoWebBase;
import com.ohhay.web.lego.entities.mongo.web.E400MG;

/**
 * @author ThoaiVt create date Feb 23, 2016 ohhay-webapplication-new
 */
@Controller
@Scope("prototype")
public class AdministratorCMSBean implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(AdministratorCMSBean.class);

	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_E400MG)
	E400MGService e400mgService;

	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	private TemplateService templateService;

	@RequestMapping(value = "/adminTemplateCMS.loadListTemplateCMS", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadCMS(@ModelAttribute(value = "offset") int offset,
			@ModelAttribute(value = "limit") int limit) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<E400MG> listE400s = e400mgService.getListTemplateCMS(q100.getPo100(), offset, limit);
			jacksonResponse.setResult(listE400s);
			return jacksonResponse;
		}
		return jacksonResponse;
	}

	/*
	 * ThoaiVt 02/03/2016 update status Template EN402
	 */
	@RequestMapping(value = "/adminTemplateCMS.changePublishTemplate", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse updatePublishTemplate(
			@ModelAttribute(value = "administratorCMSCriteria") AdministratorCMSCriteria administratorCMSCriteria) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		
		// check q100
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			// result value
			int result = templateService.updateOneField(q100.getPo100(), E400MG.class, administratorCMSCriteria.getId(),
					QbMongoFiledsName.EN402, administratorCMSCriteria.getEn402(), null);
			jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			jacksonResponse.setResult(result);
		}
		return jacksonResponse;
	}

	/*
	 * ThoaiVt 02/03/2016 update image template EV403, EV401
	 */
	@RequestMapping(value = "/adminTemplateCMS.editTemplate", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse updateUrlImage(
			@ModelAttribute(value = "administratorCMSCriteria") AdministratorCMSCriteria administratorCMSCriteria) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		// check q100
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			// find template
			E400MG e400mg = templateService.findDocumentById(q100.getPo100(), administratorCMSCriteria.getId(),
					E400MG.class);
			// set value field in E400MG
			e400mg.setEv403(administratorCMSCriteria.getEv403());
			e400mg.setEv401(administratorCMSCriteria.getEv401());
			logger.info(e400mg.toString());
			// result data value
			int result = templateService.saveDocument(q100.getPo100(), e400mg, QbMongoCollectionsName.E400);
			jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			jacksonResponse.setResult(result);
		}
		return jacksonResponse;
	}

	@RequestMapping(value = "/adminTemplateCMS.listCategorys", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse listCategory(@ModelAttribute (value = "idcategory") int idcategory) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			logger.info(idcategory);
			List<A950MG> a950mgs = templateService.findDocuments(q100.getPo100(), A950MG.class);
			jacksonResponse.setResult(a950mgs);
		}
		return jacksonResponse;
	}

	/**
	 * @author : ThoaiVt
	 * @created : 31-07-2017
	 * @param AdministratorCMSCriteria administratorCMSCriteria
	 * @return
	 */
	@RequestMapping(value = "/adminTemplateCMS.updateCategorys", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse updateCategory(
			@RequestBody AdministratorCMSCriteria administratorCMSCriteria) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			logger.info(administratorCMSCriteria);
			templateService.updateOneField(q100.getPo100(), E400MG.class, administratorCMSCriteria.getId(),
					QbMongoFiledsName.FA950S, administratorCMSCriteria.getListCategory(), null);
		}
		return jacksonResponse;
	}
}
