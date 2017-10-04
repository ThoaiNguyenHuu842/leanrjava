package com.ohhay.piepme.test;

import java.util.Date;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.channel.T100PMG;

/**
 * @author JavaDigest
 * 
 */
public class Test {
	public static void save(String title, String image, String otag) throws Exception {
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		SequenceService sequenceService = (SequenceService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
		int newT100Id = (int) sequenceService
				.getNextSequenceIdPiepMe(1352, QbMongoCollectionsName.T100);
		T100PMG t100mg = new T100PMG();
		t100mg.setId(newT100Id);
		t100mg.setFo100(1352);
		t100mg.setTv101(AESUtils.encrypt(otag));
		t100mg.setTv102(image);
		t100mg.setTv103(title);
		t100mg.setTv104("DEF");
		t100mg.setTl146(new Date());
		t100mg.setTl148(new Date());
		int kq = templateService
				.saveDocument(1352, t100mg, QbMongoCollectionsName.T100);
		try {
			templateService.setAccessDBcentPiepme(true);
			templateService
					.saveDocument(0, t100mg, QbMongoCollectionsName.T100);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(AESUtils.encrypt("Thời trang"));
		System.out.println("---end---");

	}
}