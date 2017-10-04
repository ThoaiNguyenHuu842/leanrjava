package com.ohhay.core.mongo.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.P950MG;
import com.ohhay.core.filesutil.EvoAWSFileUtils;
import com.ohhay.core.mongo.service.ImageAlbumService;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * create Oct 28, 2015
 * service form image album module
 */
@Service(value = SpringBeanNames.SERVICE_NAME_IMAGE_ALBUM)
@Scope("prototype")
public class ImageAlbumServiceImpl implements ImageAlbumService {
	@Autowired
	TemplateService templateService;
	@Override
	public P950MG upload(int fo100, String region, int webId, String imgBase64, String src, String ext) {
		// TODO Auto-generated method stub
		String imgContent = imgBase64.split("\\,")[1];
		@SuppressWarnings("restriction")
		byte[] btDataFile;
		try {
			/*
			 * 1) upload to aws
			 */
			btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(imgContent);
			EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils(region, fo100);
			String fileName = ApplicationHelper.generateFileName();
			if(ext != null && ext.length() > 0)
				fileName += "." + ext;
			String url = awsFileUtils.uploadObjectMutilPartImgAlbum(webId, fileName, btDataFile);
			/*
			 * 2) save to mongo
			 */
			P950MG p950mg = new P950MG();
			SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE); 
			try {
				long p950NewId = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.P950);
				p950mg.setFo100(fo100);
				p950mg.setId(p950NewId);
				p950mg.setWebId(webId);
				p950mg.setPv951(fileName);
				p950mg.setSrc(src);
				templateService.saveDocument(fo100 , p950mg, QbMongoCollectionsName.P950);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return p950mg;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int remove(int pp950, int fo100, String region) {
		// TODO Auto-generated method stub
		return templateService.removeDocumentById(fo100, pp950, P950MG.class);
	}

}
