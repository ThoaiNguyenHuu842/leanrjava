package com.ohhay.cronjob;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.SessionConstant;
import com.ohhay.other.entities.W000;
import com.ohhay.other.entities.mongo.other.W000MG;
import com.ohhay.other.entities.mongo.report.R100MG;
import com.ohhay.other.entities.mongo.report.R110MG;
import com.ohhay.other.entities.mongo.report.R120MG;
import com.ohhay.other.mysql.service.R100Service;
import com.ohhay.other.mysql.service.W000Service;

/**
 * @author ThoaiNH
 * crate 29/11/2014
 * cronjob sync mongo and mysql
 */
@Service
public class CronJobSyncDB implements ICronJob {
	private static Logger log = Logger.getLogger(CronJobSyncDB.class);
	public void run() {
		// dong bo danh sach domain hien thi trang home (noi anh Phong cho ham khac)
		//syncW000();
		// dong bo cac report
		syncR100();
		deleteOldPicture();
	}
	private void deleteOldPicture()
	{
		//TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
	}
/*	*//**
	 * danh sach domain hien thi trang home
	 *//*
	private void syncW000() {
		W000Service w000Service = (W000Service) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_W000);
		SequenceService sequenceService = (SequenceService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
		TemplateService templateMGService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		List<W000> listW000s = w000Service
				.listOfTabW000(null, ApplicationConstant.PVLOGIN_ANONYMOUS);
		for (W000 w000 : listW000s) {
			W000MG w000mg = new W000MG(w000);
			try {
				w000mg.setId(sequenceService
						.getNextSequenceId(QbMongoCollectionsName.W000));
				templateMGService.saveDocument(0, w000mg, QbMongoCollectionsName.W000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/

	/**
	 * cac report,sau khi sync xoa cac report truoc do, chi giu lai report trong
	 * ngay
	 */
	private void syncR100() {
		TemplateService templateMGService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		List<R100MG> listR100mgs = templateMGService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, R100MG.class);
		for (R100MG r100mg : listR100mgs) {
			List<R110MG> liR110mgs = r100mg.getListR110mgs();
			Iterator<R110MG> i = liR110mgs.iterator();
			while (i.hasNext()) {
				R110MG r110mg = i.next();
				for (R120MG r120mg : r110mg.getListR120mgs()) {
					R100Service r100Service = (R100Service) ApplicationHelper
							.findBean(SpringBeanNames.SERVICE_NAME_R100);
					log.info("---rhayUpdateTabR100Call:"
							+ r100mg.getId() + "," + r110mg.getDay() + ","
							+ r110mg.getMonth() + "," + r110mg.getYear() + ","
							+ r120mg.getTotal() + ","
							+ SessionConstant.USER_LOGIN);
					try {
						r100Service.rhayUpdateTabR100Call(r100mg.getId(), r110mg.getDay(), r110mg.getMonth(), r110mg.getYear(), r120mg.getTotal(), SessionConstant.USER_LOGIN);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				// remove if old day
				if (checkOldR110(r110mg) == true)
					i.remove();
			}
			templateMGService.saveDocument(ApplicationConstant.FO100_SUPER_ADMIN, r100mg, QbMongoCollectionsName.R100);
		}
		log.info("----XONG----");
	}

	/**
	 * @param r110mg
	 * @return
	 */
	private boolean checkOldR110(R110MG r110mg) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String ss = dateFormat.format(date);
		String s[] = ss.split("/");
		int day = Integer.parseInt(s[0]);
		int month = Integer.parseInt(s[1]);
		int year = Integer.parseInt(s[2]);
		if (r110mg.getDay() == day && r110mg.getMonth() == month && r110mg.getYear() == year)
			return false;
		return true;
	}
}
