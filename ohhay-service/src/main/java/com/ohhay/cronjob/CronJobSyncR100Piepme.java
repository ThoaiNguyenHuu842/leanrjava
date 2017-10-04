package com.ohhay.cronjob;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mysql.service.R100CentService;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.dao.R100BPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01;

/**
 * dong bo du lieu reach pieper qua mysql
 * @author ThoaiNH
 * create May 22, 2017
 */
@Service
public class CronJobSyncR100Piepme implements ICronJob {
	@Override
	public void run() {
		R100CentService r100CentService = (R100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R100CENT);
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		List<P300BPMG> list =  templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, P300BPMG.class, "PV301", new QbCriteria("REPORT_STT", 1));
		for(P300BPMG p300bpmg: list){
			try {
				R100BPMGDao r100bpmgDao = (R100BPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_R100BP);
				List<R100PSta01> listSta01 = r100bpmgDao.listOfTabR100B01(p300bpmg.getFo100(), p300bpmg.getId());
				if(listSta01 != null && listSta01.size() > 0){
					R100PSta01 r100pSta01 = listSta01.get(0);
					if(r100pSta01.getReaches05km() > 0)
						r100CentService.insertTabR1002017dis("SEN", p300bpmg.getPv301(), "NOP="+r100pSta01.getReaches05km(),
							      null, p300bpmg.getTotalReachs(), 5, p300bpmg.getId(), 0, p300bpmg.getFo100(), ApplicationConstant.PVLOGIN_ANONYMOUS);
					
					if(r100pSta01.getReaches10km() > 0)
						r100CentService.insertTabR1002017dis("SEN", p300bpmg.getPv301(), "NOP="+r100pSta01.getReaches10km(),
							      null, p300bpmg.getTotalReachs(), 10, p300bpmg.getId(), 0, p300bpmg.getFo100(), ApplicationConstant.PVLOGIN_ANONYMOUS);
					
					if(r100pSta01.getReaches30km() > 0)
						r100CentService.insertTabR1002017dis("SEN", p300bpmg.getPv301(), "NOP="+r100pSta01.getReaches30km(),
							      null, p300bpmg.getTotalReachs(), 30, p300bpmg.getId(), 0, p300bpmg.getFo100(), ApplicationConstant.PVLOGIN_ANONYMOUS);
					
					if(r100pSta01.getReaches100km() > 0)
						r100CentService.insertTabR1002017dis("SEN", p300bpmg.getPv301(), "NOP="+r100pSta01.getReaches100km(),
							      null, p300bpmg.getTotalReachs(), 100, p300bpmg.getId(), 0, p300bpmg.getFo100(), ApplicationConstant.PVLOGIN_ANONYMOUS);
					
					if((r100pSta01.getReaches() - r100pSta01.getReaches05km() - r100pSta01.getReaches10km()- r100pSta01.getReaches30km() - r100pSta01.getReaches100km()) > 0)
						r100CentService.insertTabR1002017dis("SEN", p300bpmg.getPv301(), "NOP="+(r100pSta01.getReaches() - r100pSta01.getReaches05km() - r100pSta01.getReaches10km()- r100pSta01.getReaches30km() - r100pSta01.getReaches100km()),
							      null, p300bpmg.getTotalReachs(), 111, p300bpmg.getId(), 0, p300bpmg.getFo100(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				}
				templateService.updateOneField(p300bpmg.getFo100(), P300BPMG.class, p300bpmg.getId(), "REPORT_STT", 0, null);
			} catch (Exception e) {
				// TODO: handle exception
				templateService.updateOneField(p300bpmg.getFo100(), P300BPMG.class, p300bpmg.getId(), "REPORT_STT", 0, null);
				e.printStackTrace();
			}
			
		}
	}

}
