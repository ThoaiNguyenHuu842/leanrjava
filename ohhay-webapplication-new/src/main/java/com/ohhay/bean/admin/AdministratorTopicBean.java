package com.ohhay.bean.admin;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.other.M200MG;
import com.ohhay.core.filesutil.AWSFileUtils;
import com.ohhay.core.filesutil.EvoAWSFileUtils;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author TuNt create date Jul 1, 2016 ohhay-webapplication-new
 */
@Controller
@Scope("prototype")
public class AdministratorTopicBean implements Serializable {
	@Autowired
	private TemplateService templateService;
	Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/adminAccountBean.loadListM200", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListM200() {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			List<M200MG> list = templateService.findDocuments(q100.getPo100(), M200MG.class);
			jacksonResponse.setResult(list);
		}
		return jacksonResponse;
	}

	@RequestMapping(value = "/adminAccountBean.saveM200", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse saveM200(@ModelAttribute(value = "mv201") String mv201,
			@ModelAttribute(value = "mv202Base") String mv202Base, @ModelAttribute(value = "mv204") String mv204,
			@ModelAttribute(value = "id") int id, @ModelAttribute(value = "fo100")int fo100) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		log.info("data= ----" +mv201+ mv204 +id +fo100);
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			M200MG m200mg = templateService.findDocumentById(q100.getPo100(), id, M200MG.class);
			m200mg.setMv201(mv201);
			log.info("data= ----" +mv201 + mv204 +id);
			if(mv202Base!=null && mv202Base.length()>0){
				try {
					String imgContent = mv202Base.split("\\,")[1];
					byte[] btDataFile;
					btDataFile = new sun.misc.BASE64Decoder()
							.decodeBuffer(imgContent);
					EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils("AS",fo100);
					String fileName = ApplicationHelper.generateFileName();
					String link =awsFileUtils.uploadObjectMutilPartImgAlbum(0,fileName, btDataFile);
					m200mg.setMv202(link);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			m200mg.setMv204(mv204);
			int result = templateService.saveDocument(q100.getPo100(), m200mg, QbMongoCollectionsName.M200);
			jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			jacksonResponse.setResult(result);
			
			
		}
		return jacksonResponse;
	}

}
