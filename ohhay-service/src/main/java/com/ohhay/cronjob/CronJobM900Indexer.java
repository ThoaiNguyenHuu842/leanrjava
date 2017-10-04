package com.ohhay.cronjob;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.other.lucene.M900Luncene;
import com.ohhay.other.mysql.service.N100Service;

/**
 * @author ThoaiNH
 * crate 29/11/2014
 * cronjob index user o!hay
 * update 24/092015: use server of ADMIN to get list m900mg
 */
@Service
public class CronJobM900Indexer implements ICronJob {
	private static Logger log = Logger.getLogger(CronJobM900Indexer.class);
	public void run() {
		M900MGService m900mgService = (M900MGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
		// get list index
		List<M900MG> list = m900mgService.findM900Index(1000);
		if (list.size() > 0) {
			M900Luncene indexer = new M900Luncene(ApplicationConstant.INDEXPATH_M900);
			try {
				indexer.createIndex();
				indexer.createIndexWriter();
				TemplateService fieldService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				for (M900MG m900mg : list) {
					try{
						if(m900mg.getMd965() == null){
							indexer.updateM900MG(m900mg);
							N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
							// update mysql
							log.info("nhayUpdateTabN100"
									+ m900mg.getId()
									+ ","
									+ m900mg.getMv901()
									+ ","
									+ m900mg.getMv902()
									+ ","
									+ AESUtils.encrypt(ApplicationHelper.removeAccent(AESUtils.encrypt(m900mg.getMv901()))) + ","
									+ AESUtils.encrypt(ApplicationHelper.removeAccent(AESUtils.encrypt(m900mg.getMv902()))) + "," + m900mg.getMv905() + ","
									+ m900mg.getMv903Decrypt() + ","
									+ m900mg.getMv907() + ","
									+ DateHelper.toSQLDate(m900mg.getMd904()) + ","
									+ m900mg.getMv908() + "," 
									+ m900mg.getMn906()+","+m900mg.getMn912()+","+m900mg.getM920MG().getVal()+","
									+ m900mg.getMv903Decrypt());
							n100Service.nhayUpdateTabN100(m900mg.getId(), m900mg
											.getMv901(), m900mg.getMv902(), AESUtils.encrypt(ApplicationHelper
											.removeAccent(AESUtils.encrypt(m900mg.getMv901()))), AESUtils.encrypt(ApplicationHelper
											.removeAccent(AESUtils.encrypt(m900mg.getMv902()))), m900mg.getMv905(), m900mg
											.getMv903Decrypt(), m900mg.getMv907(), DateHelper
											.toSQLDate(m900mg.getMd904()), m900mg
											.getMv908(),m900mg.getMn906(),m900mg.getMn912(), m900mg.getM920MG().getVal(), m900mg.getMv901Decrypt());
							// update status
							fieldService.updateOneField(m900mg.getId(), M900MG.class, m900mg.getId(), "MN909", 0, "ML950");
							// update dn center
							fieldService.updateOneField(0, M900MG.class, m900mg.getId(), "MN909", 0, "ML950");
						}
						else
						{
							indexer.deleteM900(m900mg);
							TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
							templateService.removeDocumentById(m900mg.getId(), m900mg.getId(), M900MG.class);
							// update db center
							templateService.removeDocumentById(0, m900mg.getId(), M900MG.class);
						}
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				log.info("---------------------------------------END 1 TASK INDEX AT "+ dateFormat.format(date)+ "-----------------------------------------------");
				indexer.closeIndexWriter();
			} catch (Exception ex) {
				ex.printStackTrace();
				indexer.closeIndexWriter();
			}
		}

	}
}
