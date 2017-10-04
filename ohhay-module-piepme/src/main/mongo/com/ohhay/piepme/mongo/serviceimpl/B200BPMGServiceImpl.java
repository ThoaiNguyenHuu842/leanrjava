package com.ohhay.piepme.mongo.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.reportpieper.B200BPMG;
import com.ohhay.piepme.mongo.entities.reportpieper.B200PPMG;
import com.ohhay.piepme.mongo.service.B200BPMGService;
import com.ohhay.piepme.mongo.service.B300BPMGService;
import com.ohhay.piepme.mongo.service.P300BPMGService;


/**
 * @author ThoaiNH
 * create May 11, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_B200B)
@Scope("prototype")
public class B200BPMGServiceImpl implements B200BPMGService{
	@Autowired
	private TemplateService templateService;
	@Autowired
	private P300BPMGService p300Service;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private B300BPMGService b300bpmgService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Override
	public int insertTabB200(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			B200BPMG b200pmg = templateService.findDocument(fo100, B200BPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.PIEPER_ID, pp300));
			if(b200pmg == null){
				P300BPMG p300 = p300Service.getPieperDetail(fo100, pp300);
				if(p300 != null){
					int id = (int)sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.B200B);
					B200BPMG b200 = new B200BPMG(id, fo100, pp300, p300.getFo100(), new Date(adminPMGDao.getCurrentTime()));
					/*
					 * block PIEPER
					 */
					b300bpmgService.insertTabB300(fo100, pp300);
					return templateService.saveDocument(fo100, b200, QbMongoCollectionsName.B200B);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}
