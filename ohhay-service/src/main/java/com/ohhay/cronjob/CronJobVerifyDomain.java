package com.ohhay.cronjob;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.TXTRecord;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.entities.O200;
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.other.entities.mongo.domain.topic.U800MG;
import com.ohhay.other.mysql.service.O200Service;

/**
 * @author ThienND
 * created Feb 18, 2016
 * cronjob verify domain
 * run every 3 mins
 */
@Service
public class CronJobVerifyDomain implements ICronJob {
	private static Logger log = Logger.getLogger(CronJobVerifyDomain.class);
	public void run() {
		O200Service o200Service = (O200Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O200);
		List<O200> listO200s = o200Service.listOfTabO200(ApplicationConstant.PVLOGIN_ANONYMOUS);
		for(O200 o200: listO200s){
			Lookup lookup;
			try {
				lookup = new Lookup(o200.getOv201(), Type.TXT);
				try {
					lookup.setResolver(new SimpleResolver());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lookup.setCache(null);
				final Record[] records = lookup.run();
				if (lookup.getResult() == Lookup.SUCCESSFUL) {
					final StringBuilder builder = new StringBuilder();
					for (Record record : records) {
						final TXTRecord txt = (TXTRecord) record;
						builder.delete(0, builder.length());
						for (@SuppressWarnings("unchecked")
						Iterator<String> iterator = txt.getStrings().iterator(); iterator.hasNext();) {
							builder.append(iterator.next());
						}
						final String txt1 = builder.toString();
						if(txt1.equals(o200.getOv202())){
							log.info("CronJobVerifyDomain-Domain name ="+o200.getOv201());
							int kq = o200Service.updateTabO200(o200.getPo200(), ApplicationConstant.PVLOGIN_ANONYMOUS);
							log.info("CronJobVerifyDomain-MySQL-updateTabO200("+o200.getPo200()+"), result ="+kq);
							TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
							int source = o200.getSourceDomainRedirect();
							if ( source == 1){
								templateMGService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
								List<U920MG> listu920mgs = templateMGService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, U920MG.class,
										new QbCriteria(QbMongoFiledsName.UV921, o200.getOv201()));
								for(U920MG u920mg: listu920mgs){
									if (u920mg.getUv922().equals(o200.getOv202())){
										templateMGService.updateOneField(o200.getFo100(), U920MG.class, "UV922", o200.getOv202(), "UN923", 1, "UD926");
										log.info("CronJobVerifyDomain-Mongo-updateU920MG(fo100="+o200.getFo100()+",uv921="+o200.getOv201()+",uv922="+o200.getOv202()+")");
									} else {
										templateMGService.removeDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, (int)u920mg.getId(), U920MG.class);
										log.info("CronJobVerifyDomain-Mongo-removeU920MG(id="+u920mg.getId()+")");
									}
								}
							} else if (source == 2) {
								templateMGService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
								List<U800MG> listu800mgs = templateMGService.findDocuments(o200.getFo100(), U800MG.class,
										new QbCriteria(QbMongoFiledsName.UV801, o200.getOv201()),
										new QbCriteria(QbMongoFiledsName.FO100, o200.getFo100()));
								for(U800MG u800mg: listu800mgs){
									if (u800mg.getUv802().equals(o200.getOv202())){
										templateMGService.updateOneField(o200.getFo100(), U800MG.class, "UV802", o200.getOv202(), "UN803", 1, "UD926");
										log.info("CronJobVerifyDomain-Mongo-updateU800MG(fo100="+o200.getFo100()+",uv801="+o200.getOv201()+",uv802="+o200.getOv202()+")");										
									} else {
										templateMGService.removeDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, (int)u800mg.getId(), U800MG.class);
										log.info("CronJobVerifyDomain-Mongo-removeU800MG(id="+u800mg.getId()+")");
									}
								}
							}
						}
					}
				}
			} catch (TextParseException e) {
				e.printStackTrace();
			}
		}
	}
}
