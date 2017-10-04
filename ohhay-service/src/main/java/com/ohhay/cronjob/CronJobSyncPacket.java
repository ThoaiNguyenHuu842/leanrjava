package com.ohhay.cronjob;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mysql.dao.O100CentDao;
import com.ohhay.base.mysql.service.O100CentService;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.oracle.N100OR;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.oracle.service.N100ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mysql.dao.O100Dao;

/**
 * @author ThoaiNH
 * date create: 08/07/2015
 * update: pay with digistore 25/03/2016
 */
@Service
public class CronJobSyncPacket implements ICronJob{
	private static Logger log = Logger.getLogger(CronJobSyncPacket.class);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		log.info("--run CronJobSyncPacket");
		N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
		List<N100OR> list = n100orService.listOfTabN100OH(0, 100, ApplicationConstant.PVLOGIN_ANONYMOUS);
		O100Dao o100Dao = (O100Dao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_O100);
		O100CentDao o100CentDao = (O100CentDao) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.REPOSITORY_NAME_O100CENT);
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		for(N100OR n100or: list){
			try {
				log.info(n100or);
				/*
				 * 1) update decentral data
				 */
				Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
				log.info("---qhayToolsUpdateTabQ100pkg:"+n100or.getFo100()+","+n100or.getNd117()+","+n100or.getVv503()+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
				q100Service.qhayToolsUpdateTabQ100pkg(n100or.getFo100(), n100or.getNd117(), n100or.getVv503(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * 1.2) 18/11/2016 update to mongoDB
				 */
				templateService.updateOneField(n100or.getFo100(), M900MG.class, n100or.getFo100(), QbMongoFiledsName.MV926,n100or.getVv503(), null);
				/*
				 * 2) 25/02/2015 update center o100 designer
				 */
				if(n100or.getVv503().equals(ApplicationConstant.PACKET_DESIGNER) ){
					log.info("---updateTabO100D:"+n100or.getFo100()+","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
					o100Dao.updateTabO100D(n100or.getFo100(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				}
				/*
				 * 3) update o100 center
				 */
				log.info("---ohayInsertTabO100Bon:"+n100or.getFo100()+","+ null+","+ n100or.getNv106()+","+ null+","+ null+","+ null+","+
						n100or.getNd114()+","+ n100or.getNn115()+","+ null+","+ n100or.getNv127()+","+ 
						n100or.getNv128()+","+ n100or.getNd129()+","+ n100or.getNv130()+","+ 
						n100or.getNv131()+","+ n100or.getNd132()+","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				o100CentDao.ohayInsertTabO100Bon(n100or.getFo100(), null, n100or.getNv106(), null, null, null,
												n100or.getNd114(), n100or.getNn115(), null, n100or.getNv127(), 
												n100or.getNv128(), n100or.getNd129(), n100or.getNv130(), 
												n100or.getNv131(), n100or.getNd132(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * 4) update o100 account
				 */
				O100CentService o100CentService = (O100CentService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100CENT);
				log.info("---getValTabO100MySql:"+n100or.getFo100()+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
				String uri = o100CentService.getValTabO100MySql(n100or.getFo100(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				log.info("---ohayInsertTabO100Bon:"+n100or.getFo100()+","+ null+","+ null+","+ null+","+ n100or.getNd117()+","+ null+","+
						 n100or.getNv109()+","+ n100or.getNv112()+","+ n100or.getNd114()+","+ n100or.getNn115()+","+ n100or.getNv126()+","+ 
						 n100or.getNv127()+","+ n100or.getNv128()+","+ n100or.getNd129()+","+ n100or.getNv130()+","+ n100or.getNv133()+","+ 
						 n100or.getNv134()+","+ n100or.getNv135()+","+ n100or.getNv136()+","+ n100or.getNv137()+","+ n100or.getNv138()+","+ 
						 n100or.getNv139()+","+ null+","+ 0+","+ n100or.getFk100()+","+ 0+","+ ApplicationConstant.PVLOGIN_ANONYMOUS+","+ uri);
				o100Dao.ohayInsertTabO100Bon(n100or.getFo100(), null, n100or.getNv106(), null, n100or.getNd117(), null,
											 n100or.getNv109(), n100or.getNv112(), n100or.getNd114(), n100or.getNn115(), n100or.getNv126(), 
											 n100or.getNv127(), n100or.getNv128(), n100or.getNd129(), n100or.getNv130(), n100or.getNv133(), 
											 n100or.getNv134(), n100or.getNv135(), n100or.getNv136(), n100or.getNv137(), n100or.getNv138(), 
											 n100or.getNv139(), null, 0, n100or.getFk100(), 0, ApplicationConstant.PVLOGIN_ANONYMOUS, uri);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
