package com.ohhay.bean.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.criteria.AdministratorBonevoImageCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.other.P500MG;
import com.ohhay.core.entities.mongo.other.P600MG;
import com.ohhay.core.filesutil.EvoAWSFileUtils;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author ThoaiVt create date Apr 21, 2016 ohhay-webapplication-new
 */
@Controller
@SuppressWarnings("unchecked")
public class AdministratorBackgroundImageBean implements Serializable {
Logger logger=Logger.getLogger(AdministratorBackgroundImageBean.class);
	
	@Autowired
	TemplateService templateService;
	@Autowired
	private SequenceService sequenceService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/adminBackground.loadListBackground", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getListBackground() {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<P600MG> p600mgs = (List<P600MG>) templateService.findDocuments(0, P600MG.class);
			jacksonResponse.setResult(p600mgs);
		}
		return jacksonResponse;
	}

	/*
	 * load background public
	 */
	@RequestMapping(value = "/bonevoBackgroundFree.getListImage", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getListBonevoImagePublic() {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		List<P600MG> p600mgs = (List<P600MG>) templateService.findDocuments(0, P600MG.class);
		jsr.setResult(p600mgs);
		return jsr;
	}
	

	/**
	 * @param bonevoImageCriteria
	 *            add background to Bonevo Background
	 * @return
	 */
	@RequestMapping(value = "/adminBackground.addIBonevoBackground", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse addBonevoImage(
			@ModelAttribute(value = "modelImageBonevo") AdministratorBonevoImageCriteria bonevoImageCriteria) {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		logger.info("DATA : " + bonevoImageCriteria.toString());
		Q100 q100 = AuthenticationHelper.getUserLogin();
		int id;
		int result = 0;
		if (q100 != null) {
			try {
				// split base64 image background
				bonevoImageCriteria.setPv501(bonevoImageCriteria.getPv501().split("\\,")[1]);
				// init base 64 image
				byte[] btDataFil = new sun.misc.BASE64Decoder().decodeBuffer(bonevoImageCriteria.getPv501());
				// init location File upload to amazon
				EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils("AS", q100.getPo100());
				// file name image
				String fileName = "background-bonevo/" + ApplicationHelper.generateFileName();
				// check ext
				if (bonevoImageCriteria.getExt() != null && bonevoImageCriteria.getExt().length() > 0) {
					fileName += "." + bonevoImageCriteria.getExt();
				}
				// get link url image
				String urlImage = awsFileUtils.uploadObjectMutilPart(fileName, btDataFil);
				// init p600MG
				P600MG p600mg = new P600MG();
				id = (int) sequenceService.getNextSequenceId(0, QbMongoCollectionsName.P600);
				p600mg.setId(id);
				p600mg.setFo100(q100.getPo100());
				p600mg.setPv601(urlImage);
				p600mg.setPd506(new Date());
				p600mg.setPd508(new Date());
				logger.info("DTF : " + p600mg.toString());
				result = templateService.saveDocument(0, p600mg, QbMongoCollectionsName.P600);
				jsr.setResult(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsr.setResult(result);
			}

		}
		return jsr;
	}

	/**
	 * @param bonevoImageCriteria
	 *            delete image to Bonevo Background
	 * @return
	 */
	@RequestMapping(value = "/adminBackground.deleteBackgroundBonevo", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse deleteBonevoImage(
			@ModelAttribute(value = "modelImageBonevo") AdministratorBonevoImageCriteria bonevoImageCriteria) {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		logger.info(bonevoImageCriteria.getId() + " sdsdsds");
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			int result = 0;
			try {
				result = templateService.removeDocumentById(0, bonevoImageCriteria.getId(), P600MG.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsr.setResult(result);
		}
		return jsr;
	}

}
