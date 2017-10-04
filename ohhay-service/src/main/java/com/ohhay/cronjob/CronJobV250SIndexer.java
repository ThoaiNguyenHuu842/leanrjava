package com.ohhay.cronjob;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.shop.V250SMG;
import com.ohhay.core.mongo.dao.V250SMGDao;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.lucene.V250SLuncene;

/**
 * @author ThoaiNH
 * crate 17/06/2016
 * cronjob index shop online
 */
@Service(value = SpringBeanNames.SERVICE_NAME_CRONJOBV250SINDEXER)
@Scope("prototype")
public class CronJobV250SIndexer implements ICronJob {
	private static Logger log = Logger.getLogger(CronJobV250SIndexer.class);
	public void run() {
		V250SMGDao v250smgDao = (V250SMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V250SDAO);
		// get list index
		List<V250SMG> list = v250smgDao.findV250Index(1000);
		log.info("---list v250 index size:"+list.size());
		if (list.size() > 0) {
			V250SLuncene indexer = new V250SLuncene(ApplicationConstant.INDEXPATH_V250);
			try {
				indexer.createIndex();
				indexer.createIndexWriter();
				TemplateService fieldService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				fieldService.setOperation(ApplicationConstant.DB_TYPE_SHOP);
				for (V250SMG v250mg : list) {
					indexer.updateV250MG(v250mg);
					// update trang thai da danh chi muc
					fieldService.updateOneField(v250mg.getFo100(), V250SMG.class, (int)v250mg.getId(), "VN270" ,0, "VD279");
				}
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				log.info("---------------------------------------END 1 TASK INDEX AT " + dateFormat.format(date) + "-----------------------------------------------");
				indexer.closeIndexWriter();
			} catch (Exception ex) {
				ex.printStackTrace();
				indexer.closeIndexWriter();
			}
		}
	}
}
