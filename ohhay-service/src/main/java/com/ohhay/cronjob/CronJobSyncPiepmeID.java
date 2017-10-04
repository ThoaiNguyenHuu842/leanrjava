package com.ohhay.cronjob;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.N100OR;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.oracle.service.N100ORService;
import com.ohhay.core.utils.ApplicationHelper;


/**
 * @author ThoaiNH
 * create Mar 30, 2017
 */
@Service
public class CronJobSyncPiepmeID implements ICronJob{
	private static Logger log = Logger.getLogger(CronJobSyncPiepmeID.class);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		log.info("--run CronJobSyncPiepmeID");
		N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
		List<N100OR> list = n100orService.listOfTabN100OPIE(0, 100, ApplicationConstant.PVLOGIN_ANONYMOUS);
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		for(N100OR n100or: list){
			try {
				log.info(n100or);
				/*
				 * 1) update decentral data
				 */
				Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
				log.info("---udpateTabQ100Piep:"+ n100or.getFo100() + "," + n100or.getNv107() + "," + n100or.getNv112() + "," +n100or.getNd117()+ "," + n100or.getNv118() + "," + n100or.getNv119() + "," + n100or.getNv126() + "," + n100or.getNv128() + "," + n100or.getNd129() + "," + n100or.getVv503() + "," + n100or.getNv101());
				q100Service.udpateTabQ100Piep(n100or.getFo100(), n100or.getNv107(), n100or.getNv112(), n100or.getNd117(), n100or.getNv118(), n100or.getNv119(), n100or.getNv126(), n100or.getNv128(), n100or.getNd129(), n100or.getVv503(), n100or.getNv106());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
