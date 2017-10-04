package com.ohhay.bean.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.AdministratorBonevoImageCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.other.P500MG;
import com.ohhay.core.filesutil.EvoAWSFileUtils;
import com.ohhay.core.mongo.service.P500MGService;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;

@Controller
/**
 * @author ThoaiVt create date Apr 12, 2016 ohhay-webapplication-new
 */
@SuppressWarnings("unchecked")
public class AdministratorEvoImage implements Serializable {

	/**
	 * 
	 */
	@Autowired
	private TemplateService templateService;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private P500MGService p500Service;

	Logger logger=Logger.getLogger(AdministratorEvoImage.class);
	/**
	 * method get all image
	 * 
	 * @return
	 */
	@RequestMapping(value = "/adminEvoImage.getListImage", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getListBonevoImage() {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<P500MG> listDomain = (List<P500MG>) templateService.findDocuments(0, P500MG.class);
			jsr.setResult(listDomain);
		}
		return jsr;
	}

	/*
	 * load image public
	 */
	@RequestMapping(value = "/bonEvoImage.getListImage", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getListBonevoImagePublic(@ModelAttribute("offset")int offset, @ModelAttribute("limit")int limit) {
		JacksonResponse jsr = new JacksonResponse();
		
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
			List<P500MG> listDomain = p500Service.listOfTabP500(offset, limit);
			jsr.setResult(listDomain);
		return jsr;
	}
	
	/**
	 * @param bonevoImageCriteria
	 *            add image to Bonevo Image
	 * @return
	 */
	@RequestMapping(value = "/adminEvoImage.addImageBonevo", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse addBonevoImage(
			@ModelAttribute(value = "modelImageBonevo") AdministratorBonevoImageCriteria bonevoImageCriteria) {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		logger.info("DATA : "+bonevoImageCriteria.toString());
		Q100 q100 = AuthenticationHelper.getUserLogin();
		int id,result = 0;
		if (q100 != null) {
			try {
				//split base64 image
				bonevoImageCriteria.setPv501(bonevoImageCriteria.getPv501().split("\\,")[1]);
				//split base64 image thumbnail
				bonevoImageCriteria.setPv502(bonevoImageCriteria.getPv502().split("\\,")[1]);
				//init base 64 image
				byte[] btDataFil = new sun.misc.BASE64Decoder().decodeBuffer(bonevoImageCriteria.getPv501());
				//init base 64 thumbnail
				byte[] btDataFilTb = new sun.misc.BASE64Decoder().decodeBuffer(bonevoImageCriteria.getPv502());
				//init location File upload to amazon
				EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils("AS", q100.getPo100());
				//file name image
				String fileName = "image-bonevo/"+ ApplicationHelper.generateFileName();
				//file name thumnail
				String fileNameThumbnail = "image-bonevo/"+ ApplicationHelper.generateFileName()+"_thumb";
				//check ext
				if (bonevoImageCriteria.getExt() != null && bonevoImageCriteria.getExt().length() > 0){
					fileName += "." + bonevoImageCriteria.getExt();
					fileNameThumbnail +=  "." + bonevoImageCriteria.getExt();
				}
				//get link url image
				String urlImage = awsFileUtils.uploadObjectMutilPart(fileName, btDataFil);
				//get link url thumbnail
				String urlImageTb = awsFileUtils.uploadObjectMutilPart(fileNameThumbnail, btDataFilTb);
				//init p500s
				P500MG p500mg = new P500MG();
				id = (int) sequenceService.getNextSequenceId(0, QbMongoCollectionsName.P500);
				p500mg.setId(id);
				p500mg.setFo100(q100.getPo100());
				p500mg.setPv501(urlImage);
				p500mg.setPv502(urlImageTb);
				p500mg.setPd506(new Date());
				p500mg.setPd508(new Date());
				logger.info("DTF : "+p500mg.toString());
				result = templateService.saveDocument(0, p500mg, QbMongoCollectionsName.P500);
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
	 * delete image to Bonevo Image
	 * @return
	 */
	@RequestMapping(value = "/adminEvoImage.deleteImageBonevo", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse deleteBonevoImage(
			@ModelAttribute(value = "modelImageBonevo") AdministratorBonevoImageCriteria bonevoImageCriteria) {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		logger.info(bonevoImageCriteria.getId()+" sdsdsds");
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			int result = 0;
			try {
				result=templateService.removeDocumentById(0,bonevoImageCriteria.getId(),P500MG.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsr.setResult(result);
		}
		return jsr;
	}

}
