package com.ohhay.piepme.mongo.serviceimpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.piepme.mongo.channel.T110PMG;
import com.ohhay.piepme.mongo.channel.T120PMG;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.T110PMGDao;
import com.ohhay.piepme.mongo.dao.T120PMGDao;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;
import com.ohhay.piepme.mongo.service.T120PMGService;
/**
 * @author ThoaiNH
 * create Aug 3, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_T120P)
@Scope("prototype")
public class T120PMGServiceImpl implements T120PMGService{
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private TemplateService templateService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Autowired
	private T120PMGDao t120pmgDao;
	@Autowired
	private SequenceService sequenceService;
	@Override
	public List<N100SummaryInfo> listOfTabT120Users(int pnFO100, int pnFT110, String pnTV129, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		return t120pmgDao.listOfTabT120Users(pnFO100, pnFT110, pnTV129, pnOffset, pnLimit);
	}

	@Override
	public int comfirmT120(int fo100, int pt120) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			T120PMG t120pmg = templateService.findDocumentById(fo100, pt120, T120PMG.class);
			if(t120pmg != null){
				T110PMG t110pmg = templateService.findDocumentById(fo100, t120pmg.getFt110(), T110PMG.class);
				if(t110pmg != null && (t110pmg.getFo100() == fo100 || t110pmg.getFo100s().contains(pt120)))
				{
					t120pmg.setTl122(new Date(adminPMGDao.getCurrentTime()));
					templateService.saveDocument(fo100, t120pmg, QbMongoCollectionsName.T120);
					
					int pt110 = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T110);
					T110PMG t110EOMNew = new T110PMG(pt110, t120pmg.getFt100(), t120pmg.getFt110(), fo100, t120pmg.getTv129());
					return templateService.saveDocument(fo100, t110EOMNew, QbMongoCollectionsName.T110);
				}
				else
					return -1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int rejectT120(int fo100, int pt120) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			T120PMG t120pmg = templateService.findDocumentById(fo100, pt120, T120PMG.class);
			if(t120pmg != null){
				T110PMG t110pmg = templateService.findDocumentById(fo100, t120pmg.getFt110(), T110PMG.class);
				if(t110pmg != null && (t110pmg.getFo100() == fo100 || t110pmg.getFo100s().contains(pt120)))
				{
					t120pmg.setTl123(new Date(adminPMGDao.getCurrentTime()));
					return templateService.saveDocument(fo100, t120pmg, QbMongoCollectionsName.T120);
				}
				else
					return -1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}
