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
import com.ohhay.piepme.mongo.entities.blockpieper.B300BPMG;
import com.ohhay.piepme.mongo.entities.blockpieper.B300CPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300CPMG;
import com.ohhay.piepme.mongo.service.B200BPMGService;
import com.ohhay.piepme.mongo.service.B300BPMGService;
import com.ohhay.piepme.mongo.service.B300CPMGService;
import com.ohhay.piepme.mongo.service.P300BPMGService;
import com.ohhay.piepme.mongo.service.P300CPMGService;


/**
 * @author ThoaiNH
 * create May 11, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_B300C)
@Scope("prototype")
public class B300CPMGServiceImpl implements B300CPMGService{
	@Autowired
	private TemplateService templateService;
	@Autowired
	private P300CPMGService p300Service;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Override
	public int insertTabB300(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			P300CPMG p300 = p300Service.getPieperDetail(fo100, pp300);
			if(p300 != null){
				int id = (int)sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.B300C);
				B300CPMG b300 = new B300CPMG(id, fo100, pp300, p300.getFo100(), new Date(adminPMGDao.getCurrentTime()));
				return templateService.saveDocument(fo100, b300, QbMongoCollectionsName.B300C);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}
