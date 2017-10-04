package com.ohhay.piepme.mongo.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.AESUtilsPiepme;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.P300PPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300PPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.service.P300PPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author TuNt 
 * create date Nov 7, 2016 ohhay-module-piepme
 */
@Service(value = SpringBeanNames.SERVICE_NAME_P300PP)
@Scope("prototype")
public class P300PPMGServiceImpl implements P300PPMGService {
	@Autowired
	private P300PPMGDao p300PMGDao;
	@Autowired 
	private TemplateService templateService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	public List<Pieper> getListPieperPublic(int fo100, int fo100f, String pvSearch,  int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		return p300PMGDao.getListPieperPublic(fo100, fo100f, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), sort, pnOffset, pnLimit);
	}

	@Override
	public List<String> listSuggestedOtag(int fo100, String pvSearch,String pvSearchStem, int offset, int limit) {
		// TODO Auto-generated method stub
		return p300PMGDao.listSuggestedOtag(fo100, pvSearch,pvSearchStem, offset, limit);
	}

	@Override
	public P300PPMG getPieperDetail(int fo100, int pp100) {
		// TODO Auto-generated method stub
		return p300PMGDao.getPieperDetail(fo100, pp100);
	}

	@Override
	public int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags) {
		// TODO Auto-generated method stub
		Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		int stt = 1;
		//kiem tra truoc khi piep
		if(pn306 == 0)
			stt = p300PMGDao.checkRoleOnCreate(fo100);
		if(stt == 1){
			try {
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				if (n100pmg != null) {
					List<Otag> listOtag = new ArrayList<Otag>();
					try {
						String tags[] = AESUtils.decrypt(otags).split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
						for (int i = 0; i < tags.length; i++)
						{
							if(tags[i].trim().length() > 0){
								Otag otag = new Otag();
								otag.setKey(tags[i].trim());
								otag.setKeyStem(ApplicationHelper.getStemOtag(tags[i].toLowerCase()));
								listOtag.add(otag);
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					if(pp300 == 0){
						P300PPMG p300mg = new P300PPMG();
						SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
						int p300Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.P300P);
						p300mg.setId(p300Id);
						p300mg.setFo100(fo100);
						p300mg.setPd308(timeCurrent);
						p300mg.setPv301(pv301);
						p300mg.setPn303(pn303);
						p300mg.setPv304(pv304);
						p300mg.setPv304Thumb(pv304Thumb);
						p300mg.setPv305(pv305);
						p300mg.setPn309(pn309);
						p300mg.setPv314(pv314);
						p300mg.setPn306(pn306);
						List<Date> pa315 = new ArrayList<Date>();
						pa315.add(timeCurrent);
						p300mg.setPa315(pa315);
						p300mg.setPd316(pa315.get(0));
						p300mg.setListOtags(listOtag);
						/*
						 * create pv302
						 */
						String tempKey = n100pmg.getNv102().substring(0, 16);
						int fo100Length = String.valueOf(n100pmg.getFo100()).length();
						StringBuilder key2 = new StringBuilder();
						for (int i = 0; i < 16 - fo100Length; i++)
							key2.append("0");
						key2.append(n100pmg.getFo100());
						AESUtilsPiepme aesUtilsPiepme = new AESUtilsPiepme(key2.toString(), key2.toString());
						p300mg.setPv302(aesUtilsPiepme.encrypt(tempKey));
						if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300P) > 0)
							return p300mg.getId();
					}
					else {
						P300PPMG p300mg = templateService.findDocument(fo100, P300PPMG.class, new QbCriteria(QbMongoFiledsName.ID, pp300), new QbCriteria(QbMongoFiledsName.FO100, fo100));
						if(p300mg != null && p300mg.getPn306() == 1){
							p300mg.setPd306(timeCurrent);
							p300mg.setPv301(pv301);
							p300mg.setPn303(pn303);
							p300mg.setPv304(pv304);
							p300mg.setPv304Thumb(pv304Thumb);
							p300mg.setPv305(pv305);
							p300mg.setPn309(pn309);
							p300mg.setPv314(pv314);
							p300mg.setListOtags(listOtag);
							if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300P) > 0)
								return p300mg.getId();
						}
					}
					return 0;
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return 0;
		}
		else
			return stt;
	}

	@Override
	public int storNoTabP300P(int fo100, int pp300) {
		// TODO Auto-generated method stub
		return p300PMGDao.storNoTabP300P(fo100, pp300);
	}

	@Override
	public List<Pieper> getListPieperFollow(int fo100, String pvSearch, int sort, int pnOffset,
			int pnLimit) {
		// TODO Auto-generated method stub
		return p300PMGDao.getListPieperFollow(fo100, pvSearch, sort, pnOffset, pnLimit);
	}

	@Override
	public List<Pieper> getListPieperPublicOne(int fo100, int pp300) {
		// TODO Auto-generated method stub
		return p300PMGDao.getListPieperPublicOne(fo100, pp300);
	}

	@Override
	public P300PPMG getPieperDetailV2(int fo100, int pp100) {
		// TODO Auto-generated method stub
		return p300PMGDao.getPieperDetailV2(fo100, pp100);
	}

	@Override
	public List<Pieper> getListPieperPublicV2(int fo100, int fo100f, String pvSearch, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		List<Pieper> list = p300PMGDao.getListPieperPublicV2(fo100, fo100f, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), sort, pnOffset, pnLimit);
		return list;
	}

	@Override
	public int confirmTabPA317(int fo100, int pp300, String stt) {
		// TODO Auto-generated method stub
		return p300PMGDao.confirmTabPA317(fo100, pp300, stt);
	}

	@Override
	public int updateTabPA317V2(int pp300, List<Double> pa317) {
		// TODO Auto-generated method stub
		return p300PMGDao.updateTabPA317V2(pp300, pa317);
	}

	@Override
	public List<Pieper> getListPieperOwner(int fo100, String pvSearch, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		return p300PMGDao.getListPieperOwner(fo100, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), sort, pnOffset, pnLimit);
	}

	@Override
	public int createPieperV2(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags) {
		// TODO Auto-generated method stub
		Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
		int stt = 1;
		//kiem tra truoc khi piep
		if(pn306 == 0)
			stt = p300PMGDao.checkRoleOnCreate(fo100);
		if(stt == 1){
			try {
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				if (n100pmg != null) {
					List<Otag> listOtag = new ArrayList<Otag>();
					try {
						String tags[] = AESUtils.decrypt(otags).split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
						for (int i = 0; i < tags.length; i++)
						{
							if(tags[i].trim().length() > 0){
								Otag otag = new Otag();
								otag.setKey(tags[i].trim());
								otag.setKeyStem(ApplicationHelper.getStemOtag(tags[i].toLowerCase()));
								listOtag.add(otag);
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					if(pp300 == 0){
						P300PPMG p300mg = new P300PPMG();
						SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
						int p300Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.P300P);
						p300mg.setId(p300Id);
						p300mg.setFo100(fo100);
						p300mg.setPd308(timeCurrent);
						p300mg.setPv301(pv301);
						p300mg.setPn303(pn303);
						p300mg.setPv304(pv304);
						p300mg.setPv304Thumb(pv304Thumb);
						p300mg.setPv305(pv305);
						p300mg.setPn309(pn309);
						p300mg.setPv314(pv314);
						p300mg.setPn306(pn306);
						List<Date> pa315 = new ArrayList<Date>();
						pa315.add(timeCurrent);
						p300mg.setPa315(pa315);
						p300mg.setPd316(pa315.get(0));
						p300mg.setListOtags(listOtag);
						/*
						 * create pv302
						 */
						p300mg.setPv302(Pieper.PV302_OFF);
						if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300P) > 0)
							return p300mg.getId();
					}
					else {
						P300PPMG p300mg = templateService.findDocument(fo100, P300PPMG.class, new QbCriteria(QbMongoFiledsName.ID, pp300), new QbCriteria(QbMongoFiledsName.FO100, fo100));
						if(p300mg != null && p300mg.getPn306() == 1){
							p300mg.setPd306(timeCurrent);
							p300mg.setPv301(pv301);
							p300mg.setPn303(pn303);
							p300mg.setPv304(pv304);
							p300mg.setPv304Thumb(pv304Thumb);
							p300mg.setPv305(pv305);
							p300mg.setPn309(pn309);
							p300mg.setPv314(pv314);
							p300mg.setListOtags(listOtag);
							if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300P) > 0)
								return p300mg.getId();
						}
					}
					return 0;
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return 0;
		}
		else
			return stt;
	}

}
