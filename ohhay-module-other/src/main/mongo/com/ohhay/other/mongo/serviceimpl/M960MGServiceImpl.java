package com.ohhay.other.mongo.serviceimpl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M960MG;
import com.ohhay.core.filesutil.AWSFileUtils;
import com.ohhay.core.filesutil.AWSFileUtilsWithoutSession;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mongo.service.M960MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_M960MG)
@Scope("prototype")
public class M960MGServiceImpl implements M960MGService {
	private static Logger log = Logger.getLogger(M960MGServiceImpl.class);

	@Autowired
	TemplateService templateService;

	@Override
	public int saveInfoTopic(M900MG m900mg, int po100) {
		// TODO Auto-generated method stub
		int kq = 0;
		if(m900mg.getM960mg().getImgBase64()!=null && m900mg.getM960mg().getImgBase64().length()>0){
			//upload image topic
			try {
				String imgContent = m900mg.getM960mg().getImgBase64().split("\\,")[1];
				byte[] btDataFile;
				btDataFile = new sun.misc.BASE64Decoder()
						.decodeBuffer(imgContent);
				log.info("#############"+btDataFile);
				AWSFileUtils awsFileUtils = new AWSFileUtils();
				String fileName = ApplicationHelper.generateFileName();
				awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
				//fileName =  TemplateRule.OHHAY_SERVER_LINK+ fileName;
				m900mg.getM960mg().setMv964(fileName);
				log.info("---------"+fileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
		return 1;
	}
	
	@Override
	public int saveInfoTopicWithoutSession(M900MG m900mg, int po100, String region) {
		if(m900mg.getM960mg().getImgBase64()!=null && m900mg.getM960mg().getImgBase64().length()>0){
			try {
				String imgContent = m900mg.getM960mg().getImgBase64().split("\\,")[1];
				byte[] btDataFile;
				btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(imgContent);
				log.info("#############"+btDataFile);
				AWSFileUtilsWithoutSession awsFileUtilsWithoutSession = new AWSFileUtilsWithoutSession(po100, region);
				String fileName = ApplicationHelper.generateFileName();
				awsFileUtilsWithoutSession.uploadObjectMutilPart(fileName, btDataFile);
				m900mg.getM960mg().setMv964(fileName);
				log.info("---------"+fileName);
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
		}
		templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
		return 1;
	}
	
	
	/**
	 * @param save coverImage for topic
	 * @param result
	 * @return
	 */
	public int saveCoverImage(String image,int po100){
		int kq = 0;
		try {
			M900MG m900mg =  templateService.findDocumentById(po100, po100, M900MG.class);
			if(m900mg!=null){
				if(m900mg.getM960mg()!=null){
					m900mg.getM960mg().setMv965(image);
				}else{
					M960MG	m960mg = new M960MG();
					m960mg.setMv965(image);
					m900mg.setM960mg(m960mg);
				}
				
				kq = templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
			}
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * @param save coverDescription for topic
	 * @param result
	 * @return
	 */
	public int saveCoverDescription(String descript,int po100){
		int kq = 0;
		try {
			log.info("decript: "+descript+"//"+po100);
			M900MG m900mg =  templateService.findDocumentById(po100, po100, M900MG.class);
			if(m900mg!=null){
				if(m900mg.getM960mg()!=null){
					if(m900mg.getM960mg().getMv966()!=null){
						m900mg.getM960mg().setMv966(descript);
					}
					else{
						M960MG	m960mg = new M960MG();
						m960mg = m900mg.getM960mg();
						m960mg.setMv966(descript);
						m900mg.setM960mg(m960mg);
					}
				}else{
					M960MG	m960mg = new M960MG();
					m960mg.setMv966(descript);
					m900mg.setM960mg(m960mg);
				}
				kq = templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
			}
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public String checkRegion(int po100){
		try {
			String val= "";
			M900MG m900mg =  templateService.findDocumentById(po100, po100, M900MG.class);
			if(m900mg!=null){
				val = m900mg.getImgLinkCloudFront();
			}
			return val;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	
}
