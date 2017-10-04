package com.ohhay.piepme.mongo.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.entities.blockpieper.B300PPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300PPMG;
import com.ohhay.piepme.mongo.service.B200BPMGService;
import com.ohhay.piepme.mongo.service.B300BPMGService;
import com.ohhay.piepme.mongo.service.B300PPMGService;
import com.ohhay.piepme.mongo.service.P300PPMGService;


/**
 * @author ThoaiNH
 * create May 11, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_B300P)
@Scope("prototype")
public class B300PPMGServiceImpl implements B300PPMGService{
	@Autowired
	private TemplateService templateService;
	@Autowired
	private P300PPMGService p300Service;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Override
	public int insertTabB300(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			P300PPMG p300 = p300Service.getPieperDetail(fo100, pp300);
			if(p300 != null){
				int id = (int)sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.B300P);
				B300PPMG b300 = new B300PPMG(id, fo100, pp300, p300.getFo100(), new Date(adminPMGDao.getCurrentTime()));
				return templateService.saveDocument(fo100, b300, QbMongoCollectionsName.B300P);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
}
