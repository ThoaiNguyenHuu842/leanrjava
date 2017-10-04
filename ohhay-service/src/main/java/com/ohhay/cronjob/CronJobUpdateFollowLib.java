package com.ohhay.cronjob;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.web.lego.entities.mongo.web.E920MG;
import com.ohhay.web.lego.service.BoxUtilsService;

/**
 * @author ThoaiNH
 * create Mar 18, 2016
 * update HTML publish of section that references from follow only library
 */
@Service
public class CronJobUpdateFollowLib implements ICronJob {
	private static Logger log = Logger.getLogger(CronJobUpdateFollowLib.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	private BoxUtilsService boxUtilsService;
	public void run() {
		List<E920MG> listNewLib = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E920MG.class, new QbCriteria("EDITABLE", 1), 
				new QbCriteria(QbMongoFiledsName.LIB_STT, "NEW"));
		log.info("---list lib size:"+listNewLib.size());
		for(E920MG e920mgLib: listNewLib){
			boxUtilsService.updateBoxUsingLib(e920mgLib);
			templateService.updateOneField(e920mgLib.getFo100(), E920MG.class, (int)e920mgLib.getId(), QbMongoFiledsName.LIB_STT, "UPDATED", null);
		}
	}
}
