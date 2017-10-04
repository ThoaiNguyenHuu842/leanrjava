package com.ohhay.piepme.mongo.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.dao.P300BRePMGDao;
import com.ohhay.piepme.mongo.dao.R100BPMGDao;
import com.ohhay.piepme.mongo.entities.other.T300PMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BRePMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.service.P300BPMGService;
import com.ohhay.piepme.mongo.service.P300BRePMGService;

@Service(value = SpringBeanNames.SERVICE_NAME_P300BREP)
@Scope("prototype")
public class P300BRePMGServiceImpl implements P300BRePMGService {
	private static Logger log = Logger.getLogger(P300BRePMGServiceImpl.class);
	@Autowired
	private P300BRePMGDao p300BMGDao;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private P300BPMGService p300bpmgService;

	@Override
	public int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314,
			String otags, int ft330, double latitude, double longitude, String addressFullName) {

		pp300 = p300bpmgService.createPieper(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, T300PMG.FT300_RE);
		if (pp300 > 0) {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			final GeoDataPointMG location = new GeoDataPointMG(longitude, latitude, addressFullName);
			templateService.updateOneField(fo100, P300BPMG.class, "LOC", location, null, new QbCriteria(QbMongoFiledsName.ID, pp300),
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			templateService.updateOneField(fo100, P300BPMG.class, "FT330", ft330, null, new QbCriteria(QbMongoFiledsName.ID, pp300),
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
		}
		return pp300;
	}

	@Override
	public List<Pieper> getListPieper(double pnLa, double pnLong, final int fo100, int fo100f, String pvSearch, int sort, int ft310, int ft320, int ft330,
			int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		final List<Pieper> listPieper = p300BMGDao.getListPieper(pnLa, pnLong, fo100, fo100f, pvSearch, ApplicationHelper.getStemString(pvSearch), sort, ft310,
				ft320, ft330, pnOffset, pnLimit);
		if (listPieper != null) {
			Thread thread = new Thread() {
				public void run() {
					R100BPMGDao r100bpmgDao = (R100BPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_R100BP);
					TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
					templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
					for (Pieper pieper : listPieper) {
						P300BPMG p300bpmg = templateService.findDocumentById(pieper.getFo100(), pieper.getId(), P300BPMG.class);
						r100bpmgDao.insertTabR100B(fo100, p300bpmg.getId(), p300bpmg.getFo100(), p300bpmg.getPa315().size(), "SEN");
					}
				}
			};
			thread.start();
		}
		return listPieper;
	}

	@Override
	public P300BRePMG getPieperDetail(int fo100, int pp100) {
		// TODO Auto-generated method stub
		return p300BMGDao.getPieperDetail(fo100, pp100);
	}

	@Override
	public int createPieperV2(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, int ft330, double latitude, double longitude, String addressFullName) {
		// TODO Auto-generated method stub
		if(ft330 > 0){
			pp300 = p300bpmgService.createPieperV2(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, T300PMG.FT300_RE);
			if (pp300 > 0) {
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				final GeoDataPointMG location = new GeoDataPointMG(longitude, latitude, addressFullName);
				templateService.updateOneField(fo100, P300BPMG.class, "LOC", location, null, new QbCriteria(QbMongoFiledsName.ID, pp300),
						new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateService.updateOneField(fo100, P300BPMG.class, "FT330", ft330, null, new QbCriteria(QbMongoFiledsName.ID, pp300),
						new QbCriteria(QbMongoFiledsName.FO100, fo100));
			}
			return pp300;
		}
		return 0;
	}
}
