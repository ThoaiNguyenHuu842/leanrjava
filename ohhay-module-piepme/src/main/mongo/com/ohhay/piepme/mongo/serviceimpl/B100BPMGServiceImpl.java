package com.ohhay.piepme.mongo.serviceimpl;

import java.util.Date;
import java.util.List;

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
import com.ohhay.piepme.mongo.dao.B100BPMGDao;
import com.ohhay.piepme.mongo.entities.blockaccount.B100BPMG;
import com.ohhay.piepme.mongo.service.B100BPMGService;
/**
 * @author ThoaiNH
 * create May 11, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_B100B)
@Scope("prototype")
public class B100BPMGServiceImpl implements B100BPMGService{
	@Autowired
	private TemplateService templateService;
	@Autowired
	private B100BPMGDao b100bpmgDao;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	public int insertTabB100(int fo100, int fo100p){
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			B100BPMG check =  templateService.findDocument(fo100, B100BPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.FO100P, fo100p));
			if(check == null){
				int id = (int)sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.B100B);
				B100BPMG b100bpmg = new B100BPMG(id, fo100, fo100p, new Date(adminPMGDao.getCurrentTime()));
				return templateService.saveDocument(fo100, b100bpmg, QbMongoCollectionsName.B100B);
			}else{
				return -1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.ohhay.piepme.mongo.service.B100BPMGService#getListB100B(int, int, int)
	 */
	@Override
	public List<B100BPMG> getListB100B(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return b100bpmgDao.getListB100B(fo100, offset, limit);
	}
	/* (non-Javadoc)
	 * @see com.ohhay.piepme.mongo.service.B100BPMGService#storNoTabB100(int, int)
	 */
	@Override
	public int storNoTabB100(int fo100, int fo100p) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return templateService.removeDocuments(fo100, B100BPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.FO100P, fo100p));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
}
