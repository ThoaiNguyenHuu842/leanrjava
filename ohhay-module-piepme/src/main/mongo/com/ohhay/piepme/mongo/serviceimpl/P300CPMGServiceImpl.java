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
import com.ohhay.piepme.mongo.dao.P300CPMGDao;
import com.ohhay.piepme.mongo.dao.R100CPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300CPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.service.P300CPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author TuNt
 * create date Nov 7, 2016
 * ohhay-module-piepme
 */
@Service(value = SpringBeanNames.SERVICE_NAME_P300CP)
@Scope("prototype")
public class P300CPMGServiceImpl implements P300CPMGService{
	@Autowired
	private P300CPMGDao p300CPMGDao;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Override
	public List<Pieper> getListCirclePieper(final int fo100, int fo100t, String pvSearch, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		final List<Pieper> listPieper = p300CPMGDao.getListCirclePieper(fo100, fo100t, pvSearch, sort, pnOffset, pnLimit);
		Thread thread = new Thread(){
		    public void run(){
		    	R100CPMGDao r100bpmgDao = (R100CPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_R100C);
		    	TemplateService templateService = (TemplateService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		    	for(Pieper pieper: listPieper){
					P300CPMG p300 = templateService.findDocumentById(pieper.getFo100(), pieper.getId(), P300CPMG.class);
		    		r100bpmgDao.insertTabR100C(fo100, p300.getId(), p300.getFo100(), p300.getPa315().size(), "SEN");
		    	}
		    }
		};
		thread.start();
		return listPieper;
	}
	@Override
	public P300CPMG getPieperDetail(int fo100,int pp200) {
		// TODO Auto-generated method stub
		return p300CPMGDao.getPieperDetail(fo100,pp200);
	}
	@Override
	public int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, List<Integer> listFG100S, List<Integer> listFO100S) {
		// TODO Auto-generated method stub
		try {
			Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
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
					/*
					 * create AES with mac address
					 */
					SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
					/*
					 * create P300
					 */
					int p200Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.P300C);
					P300CPMG p300mg = new P300CPMG();
					p300mg.setId(p200Id);
					p300mg.setFo100(fo100);
					p300mg.setPd308(timeCurrent);
					p300mg.setPv301(pv301);
					p300mg.setPn303(pn303);
					p300mg.setPv304(pv304);
					p300mg.setPv304Thumb(pv304Thumb);
					p300mg.setPv305(pv305);
					p300mg.setPn306(pn306);
					p300mg.setPn309(pn309);
					p300mg.setPv314(pv314);
					p300mg.setListFG100S(listFG100S);
					p300mg.setListFO100S(listFO100S);
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
					AESUtilsPiepme aesUtilsPiepme = new AESUtilsPiepme(
							key2.toString(), key2.toString());
					p300mg.setPv302(aesUtilsPiepme.encrypt(tempKey));
					if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300C) > 0)
						return p200Id;
				}
				else {
					P300CPMG p300mg = templateService.findDocument(fo100, P300CPMG.class, new QbCriteria(QbMongoFiledsName.ID, pp300), new QbCriteria(QbMongoFiledsName.FO100, fo100));
					if(p300mg != null && p300mg.getPn306() == 1){
						p300mg.setPd306(timeCurrent);
						p300mg.setPv301(pv301);
						p300mg.setPn303(pn303);
						p300mg.setPv304(pv304);
						p300mg.setPv304Thumb(pv304Thumb);
						p300mg.setPv305(pv305);
						p300mg.setPn306(pn306);
						p300mg.setPn309(pn309);
						p300mg.setPv314(pv314);
						p300mg.setListFG100S(listFG100S);
						p300mg.setListFO100S(listFO100S);
						p300mg.setListOtags(listOtag);
						if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300C) > 0)
							return pp300;
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
	@Override
	public List<String> listSuggestedOtag(int fo100, String pvSearch, String pvSearchStem, int offset, int limit) {
		// TODO Auto-generated method stub
		return p300CPMGDao.listSuggestedOtag(fo100, pvSearch, pvSearchStem, offset, limit);
	}
	@Override
	public int storNoTabP300C(int fo100, int pp300) {
		// TODO Auto-generated method stub
		return p300CPMGDao.storNoTabP300C(fo100, pp300);
	}
	@Override
	public int createPieperV2(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, List<Integer> listFG100S, List<Integer> listFO100S) {
		// TODO Auto-generated method stub
		try {
			Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
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
					/*
					 * create AES with mac address
					 */
					SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
					/*
					 * create P300
					 */
					int p200Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.P300C);
					P300CPMG p300mg = new P300CPMG();
					p300mg.setId(p200Id);
					p300mg.setFo100(fo100);
					p300mg.setPd308(timeCurrent);
					p300mg.setPv301(pv301);
					p300mg.setPn303(pn303);
					p300mg.setPv304(pv304);
					p300mg.setPv304Thumb(pv304Thumb);
					p300mg.setPv305(pv305);
					p300mg.setPn306(pn306);
					p300mg.setPn309(pn309);
					p300mg.setPv314(pv314);
					p300mg.setListFG100S(listFG100S);
					p300mg.setListFO100S(listFO100S);
					List<Date> pa315 = new ArrayList<Date>();
					pa315.add(timeCurrent);
					p300mg.setPa315(pa315);
					p300mg.setPd316(pa315.get(0));
					p300mg.setListOtags(listOtag);
					/*
					 * create pv302
					 */
					p300mg.setPv302(Pieper.PV302_FRI);
					if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300C) > 0)
						return p200Id;
				}
				else {
					P300CPMG p300mg = templateService.findDocument(fo100, P300CPMG.class, new QbCriteria(QbMongoFiledsName.ID, pp300), new QbCriteria(QbMongoFiledsName.FO100, fo100));
					if(p300mg != null && p300mg.getPn306() == 1){
						p300mg.setPd306(timeCurrent);
						p300mg.setPv301(pv301);
						p300mg.setPn303(pn303);
						p300mg.setPv304(pv304);
						p300mg.setPv304Thumb(pv304Thumb);
						p300mg.setPv305(pv305);
						p300mg.setPn306(pn306);
						p300mg.setPn309(pn309);
						p300mg.setPv314(pv314);
						p300mg.setListFG100S(listFG100S);
						p300mg.setListFO100S(listFO100S);
						p300mg.setListOtags(listOtag);
						if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300C) > 0)
							return pp300;
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

}
