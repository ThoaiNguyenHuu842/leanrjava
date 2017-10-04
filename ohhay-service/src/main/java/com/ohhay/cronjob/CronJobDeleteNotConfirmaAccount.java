package com.ohhay.cronjob;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.entities.O100;
import com.ohhay.other.mysql.service.O100Service;


/**
 * @author ThoaiNH
 * create Mar 30, 2016
 */
@Service
public class CronJobDeleteNotConfirmaAccount implements ICronJob {
	private static Logger log = Logger.getLogger(CronJobDeleteNotConfirmaAccount.class);
	public void run() {
		O100Service o100Service = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
		List<O100> listO100des = o100Service.listOfTabO100Del(ApplicationConstant.PVLOGIN_ANONYMOUS);
		for(O100 o100: listO100des)
			o100Service.stornoTabO100(o100.getPo100(), ApplicationConstant.PVLOGIN_ANONYMOUS);
	}
}
