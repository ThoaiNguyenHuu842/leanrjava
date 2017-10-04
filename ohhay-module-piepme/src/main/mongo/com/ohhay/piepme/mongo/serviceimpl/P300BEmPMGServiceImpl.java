package com.ohhay.piepme.mongo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.dao.P300BEmPMGDao;
import com.ohhay.piepme.mongo.dao.R100BPMGDao;
import com.ohhay.piepme.mongo.entities.other.T300PMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BEmPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.service.P300BEmPMGService;
import com.ohhay.piepme.mongo.service.P300BPMGService;


/**
 * @author ThoaiNH
 * create Jul 29, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_P300BEMP)
@Scope("prototype")
public class P300BEmPMGServiceImpl implements P300BEmPMGService {
	private static Logger log = Logger.getLogger(P300BEmPMGServiceImpl.class);
	@Autowired
	private P300BEmPMGDao p300BMGDao;
	@Autowired 
	private TemplateService templateService;
	@Autowired
	private P300BPMGService p300bpmgService;
	@Override
	public int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, String otagAds, double latitude, double longitude, String addressFullName) {
		// TODO Auto-generated method stub
		pp300 = p300bpmgService.createPieper(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, T300PMG.FT300_EM);
		if(pp300 > 0){
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			final GeoDataPointMG location = new GeoDataPointMG(longitude, latitude, addressFullName);
			templateService.updateOneField(fo100, P300BPMG.class, "LOC", location, null, new QbCriteria(QbMongoFiledsName.ID, pp300), new QbCriteria(QbMongoFiledsName.FO100, fo100));
			List<Otag> listOtagAd = new ArrayList<Otag>();
			try {
				String tags[] = AESUtils.decrypt(otagAds).split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
				for (int i = 0; i < tags.length; i++)
				{
					if(tags[i].trim().length() > 0){
						Otag otag = new Otag();
						otag.setKey(tags[i].trim());
						otag.setKeyStem(ApplicationHelper.getStemOtag(tags[i].toLowerCase()));
						listOtagAd.add(otag);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			templateService.updateOneField(fo100, P300BPMG.class, "OTAG_ADS", listOtagAd, null, new QbCriteria(QbMongoFiledsName.ID, pp300), new QbCriteria(QbMongoFiledsName.FO100, fo100));
		}
		return pp300;
	}

	@Override
	public List<Pieper> getListPieper(double pnLa, double pnLong, final int fo100, int fo100f, String pvSearch, String pvSearchAd, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		final List<Pieper> listPieper = p300BMGDao.getListPieper(pnLa, pnLong, fo100, fo100f, pvSearch, ApplicationHelper.getStemString(pvSearch),
				pvSearchAd, ApplicationHelper.getStemString(pvSearchAd), sort, pnOffset, pnLimit);
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
	public P300BEmPMG getPieperDetailEm(int fo100, int pp100) {
		// TODO Auto-generated method stub
		return p300BMGDao.getPieperDetailEm(fo100, pp100);
	}

	@Override
	public int createPieperV2(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, String otagAds, double latitude, double longitude, String addressFullName) {
		// TODO Auto-generated method stub
		pp300 = p300bpmgService.createPieperV2(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, T300PMG.FT300_EM);
		if(pp300 > 0){
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			final GeoDataPointMG location = new GeoDataPointMG(longitude, latitude, addressFullName);
			templateService.updateOneField(fo100, P300BPMG.class, "LOC", location, null, new QbCriteria(QbMongoFiledsName.ID, pp300), new QbCriteria(QbMongoFiledsName.FO100, fo100));
			List<Otag> listOtagAd = new ArrayList<Otag>();
			try {
				String tags[] = AESUtils.decrypt(otagAds).split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
				for (int i = 0; i < tags.length; i++)
				{
					if(tags[i].trim().length() > 0){
						Otag otag = new Otag();
						otag.setKey(tags[i].trim());
						otag.setKeyStem(ApplicationHelper.getStemOtag(tags[i].toLowerCase()));
						listOtagAd.add(otag);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			templateService.updateOneField(fo100, P300BPMG.class, "OTAG_ADS", listOtagAd, null, new QbCriteria(QbMongoFiledsName.ID, pp300), new QbCriteria(QbMongoFiledsName.FO100, fo100));
		}
		return pp300;
	}
}
