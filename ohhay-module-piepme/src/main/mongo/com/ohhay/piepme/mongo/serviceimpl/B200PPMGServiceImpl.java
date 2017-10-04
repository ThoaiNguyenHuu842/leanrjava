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
import com.ohhay.piepme.mongo.entities.pieper.P300PPMG;
import com.ohhay.piepme.mongo.entities.reportpieper.B200PPMG;
import com.ohhay.piepme.mongo.service.B200PPMGService;
import com.ohhay.piepme.mongo.service.B300PPMGService;
import com.ohhay.piepme.mongo.service.P300PPMGService;


/**
 * @author ThoaiNH
 * create May 11, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_B200P)
@Scope("prototype")
public class B200PPMGServiceImpl implements B200PPMGService{
	@Autowired
	private TemplateService templateService;
	@Autowired
	private P300PPMGService p300Service;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private B300PPMGService b300ppmgService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Override
	public int insertTabB200(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			B200PPMG b200ppmg = templateService.findDocument(fo100, B200PPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.PIEPER_ID, pp300));
			if(b200ppmg == null){
				P300PPMG p300 = p300Service.getPieperDetail(fo100, pp300);
				if(p300 != null){
					int id = (int)sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.B200P);
					B200PPMG b200 = new B200PPMG(id, fo100, pp300, p300.getFo100(), new Date(adminPMGDao.getCurrentTime()));
					/*
					 * block pieper
					 */
					b300ppmgService.insertTabB300(fo100, pp300);
					int kq = templateService.saveDocument(fo100, b200, QbMongoCollectionsName.B200P);
					/*
					 * increase total reports
					 */
					int totalReport = p300.getTotalReports() + 1;
					templateService.updateOneField(p300.getFo100(), P300PPMG.class, p300.getId(), QbMongoFiledsName.TOTAL_REPORTS, totalReport, null);
					return kq;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}
