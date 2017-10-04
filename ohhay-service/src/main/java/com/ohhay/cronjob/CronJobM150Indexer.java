package com.ohhay.cronjob;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.LuceneConstant;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.M150MG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.shop.V250SMG;
import com.ohhay.core.mongo.dao.V250SMGDao;
import com.ohhay.core.mongo.service.M150MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.lucene.M150Luncene;
import com.ohhay.other.lucene.V250SLuncene;

/**
 * @author ThoaiNH
 * crate 29/11/2014
 * cronjob index topic o!hay
 */
@Service
public class CronJobM150Indexer implements ICronJob {
	private static Logger log = Logger.getLogger(CronJobM150Indexer.class);
	public void run() {
		M150MGService m150mgService = (M150MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M150MG);
		// get list index
		List<M150MG> list = m150mgService.findM150Index(300);
		log.info("---list m150 index size:"+list.size());
		if (list.size() > 0) {
			try {
				TemplateService fieldService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				fieldService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
				for (M150MG m150mg : list) {
					M150Luncene indexer = new M150Luncene(
							ApplicationConstant.INDEXPATH_M150);
					indexer.createIndex();
					indexer.createIndexWriter();
					  if(m150mg.getMd165() == null){
						 try{
							log.info("--update index m150 id:"+m150mg.getId());
							int privacy = -1;//not load role
							if(m150mg.getM160mg() != null)
							{
								if(m150mg.getM160mg().getMn201() == 1)
									privacy = LuceneConstant.M150_PRIVACY_PUBLIC;
								else
									privacy = LuceneConstant.M150_PRIVACY_PRIVATE;
							}
							if(privacy == -1)
							{
								TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
								templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
								M900MG m900mg = templateService.findDocumentById(m150mg.getFo100(), m150mg.getFo100(), M900MG.class);
								if(m900mg.getM950mg().getMn951() == 1)
									privacy = LuceneConstant.M150_PRIVACY_PUBLIC;
								else
									privacy = LuceneConstant.M150_PRIVACY_PRIVATE;
							}
							m150mg.setPrivacy(privacy);
							indexer.updateM150MG(m150mg);
							// update trang thai da danh chi muc
							fieldService.updateOneField(m150mg.getFo100(), M150MG.class, m150mg.getId(),QbMongoFiledsName.MN159, 0, "MD169");
						}
						catch(NullPointerException ex)
						{
							ex.printStackTrace();
						}
					}
					else
					{
						log.info("--remove index m150 id:"+m150mg.getId());
						indexer.deleteM150(m150mg);
						TemplateService templateService2 = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						templateService2.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
						templateService2.removeDocumentById(m150mg.getFo100(), m150mg.getId(), M150MG.class);
					}
					indexer.closeIndexWriter();
				}
				
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				log.info("---------------------------------------END 1 TASK INDEX AT " + dateFormat.format(date) + "-----------------------------------------------");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	public void run2() {
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
