package com.ohhay.piepme.mongo.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.piepme.mongo.dao.P150PMGDao;
import com.ohhay.piepme.mongo.entities.other.P150PMG;
import com.ohhay.piepme.mongo.nestedentities.P160PMG;
import com.ohhay.piepme.mongo.service.P150PMGService;
import com.ohhay.piepme.mongo.service.P300MPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author TuNt
 * create date Mar 6, 2017 
 * ohhay-module-piepme
 */
@Service(value = SpringBeanNames.SERVICE_NAME_P150P)
@Scope("prototype")
public class P150PMGServiceImpl implements P150PMGService{
	private static Logger log = Logger.getLogger(P150PMGServiceImpl.class);
	@Autowired
	private P150PMGDao p150pmgDao;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private P300MPMGService p300mpmgService;
	@Override
	public String insertTabP150(int fo100, String pv152) {
		// TODO Auto-generated method stub
		String kq = p150pmgDao.insertTabP150(fo100, pv152);
		//send OTP to 3 friends
		if("1.0".equals(kq) || "1".equals(kq)){
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			P150PMG p150MG = templateService.findDocumentWithMaximunCriteria(fo100, P150PMG.class, QbMongoFiledsName.ID,
																			  new QbCriteria(QbMongoFiledsName.FO100, fo100), 
																			  new QbCriteria("PV152", pv152));
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			log.info(p150MG);
			if(p150MG != null && n100pmg != null){
				for(P160PMG p160pmg: p150MG.getPa151())
					p300mpmgService.sendOTPCode(p160pmg.getFo100(), n100pmg.getNv106(), p160pmg.getOtpCode());
			}
		}
		return kq;
	}

	@Override
	public String confirmTabP150(int fo100, String otpCode, String pv152) {
		// TODO Auto-generated method stub
		return p150pmgDao.confirmTabP150(fo100, otpCode, pv152);
	}

	@Override
	public String insertTabP150V2(int fo100, String pv152) {
		// TODO Auto-generated method stub
		String kq = p150pmgDao.insertTabP150(fo100, pv152);
		//send OTP to 3 friends
		if("1.0".equals(kq) || "1".equals(kq)){
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			P150PMG p150MG = templateService.findDocumentWithMaximunCriteria(fo100, P150PMG.class, QbMongoFiledsName.ID,
																			  new QbCriteria(QbMongoFiledsName.FO100, fo100), 
																			  new QbCriteria("PV152", pv152));
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			log.info(p150MG);
			if(p150MG != null && n100pmg != null){
				for(P160PMG p160pmg: p150MG.getPa151())
					p300mpmgService.sendOTPCodeV2(p160pmg.getFo100(), n100pmg.getNv106(), p160pmg.getOtpCode());
			}
		}
		return kq;
	}

}
