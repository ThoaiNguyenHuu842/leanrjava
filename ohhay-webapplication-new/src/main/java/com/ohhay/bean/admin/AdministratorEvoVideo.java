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
import com.ohhay.core.entities.mongo.other.P700MG;
import com.ohhay.core.filesutil.EvoAWSFileUtils;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author ThoaiVt create date Apr 23, 2016 ohhay-webapplication-new
 */
@Controller
@SuppressWarnings("unchecked")
public class AdministratorEvoVideo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(AdministratorEvoVideo.class);

	@Autowired
	private TemplateService templateService;
	@Autowired
	private SequenceService sequenceService;

	
	/*
	 * load List Video public
	 */
	
	@RequestMapping(value = "/bonEvoVideo.getListVideo")
	public @ResponseBody JacksonResponse getListPublicVideoBonevo(){
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		List<P700MG> listVideoBonevo = (List<P700MG>) templateService.findDocuments(0, P700MG.class);
		jacksonResponse.setResult(listVideoBonevo);
		return jacksonResponse;
	}

	/*
	 * add video to mongo
	 */
	@RequestMapping(value = "/adminEvoVideo.addVideoData", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse addVideoData(
			@ModelAttribute("administratorVideoCriteria") AdministratorBonevoImageCriteria administratorVideoCriteria) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		Q100 q100 = AuthenticationHelper.getUserLogin();
		int id=0,result=0;
		if (q100 != null) {
			try {
				// split binary video
				administratorVideoCriteria.setPv501(administratorVideoCriteria.getPv501().split("\\,")[1]);
				//split base64 image thumbnail
				administratorVideoCriteria.setPv502(administratorVideoCriteria.getPv502().split("\\,")[1]);
				//binary video
				byte[] btDataFil = new sun.misc.BASE64Decoder().decodeBuffer(administratorVideoCriteria.getPv501());
				//init base  thumbnail
				byte[] btDataFilTb = new sun.misc.BASE64Decoder().decodeBuffer(administratorVideoCriteria.getPv502());
				// init location File upload to amazon
				EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils("AS", 0);
				// file name image
				String fileName = "video-bonevo/" + ApplicationHelper.generateFileName();
				//file name thumnail
				String fileNameThumbnail = "video-bonevo/"+ ApplicationHelper.generateFileName()+"_thumb";
				if (administratorVideoCriteria.getExt() != null && administratorVideoCriteria.getExt().length() > 0) {
					//append extention cho video
					fileName += "." + administratorVideoCriteria.getExt();
//					fileNameThumbnail +=  "." + administratorVideoCriteria.getExt();
				}
				// get link url image
				String urlVideo = awsFileUtils.uploadObjectMutilPart(fileName, btDataFil);
				String urlVideoTb = awsFileUtils.uploadObjectMutilPart(fileNameThumbnail, btDataFilTb);
				logger.info("URL LINK : " + urlVideo);
				//init p700
				P700MG p700mg = new P700MG();
				id = (int) sequenceService.getNextSequenceId(0, QbMongoCollectionsName.P700);
				p700mg.setId(id);
				p700mg.setFo100(q100.getPo100());
				p700mg.setPv701(urlVideo);
				p700mg.setPv702(urlVideoTb);
				p700mg.setPd708(new Date());
				logger.info("DTF : "+p700mg.toString());
				result = templateService.saveDocument(0, p700mg, QbMongoCollectionsName.P700);
				jacksonResponse.setResult(result);
			} catch (Exception s) {
				s.printStackTrace();
			}
		}
		return jacksonResponse;
	}

	/**
	 * get list video
	 * 
	 * @return
	 */
	@RequestMapping(value = "/adminEvoVideo.deleteVideo")
	public @ResponseBody JacksonResponse deleteVideoBonevo(
			@ModelAttribute(value = "modelImageBonevo") AdministratorBonevoImageCriteria bonevoImageCriteria) {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		logger.info(bonevoImageCriteria.getId() + " sdsdsds");
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			int result = 0;
			try {
				result = templateService.removeDocumentById(0, bonevoImageCriteria.getId(), P700MG.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsr.setResult(result);
		}
		return jsr;
	}
}
