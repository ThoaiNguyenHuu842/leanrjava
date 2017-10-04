package com.ohhay.piepme.mongo.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.ohhay.core.oracle.service.V130ORService;
import com.ohhay.core.utils.AESUtilsPiepme;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.channel.T110PMG;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.P300BPMGDao;
import com.ohhay.piepme.mongo.dao.R100BPMGDao;
import com.ohhay.piepme.mongo.entities.other.T300PMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BSummaryInfo;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.K100PMG;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.service.P300BPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;


@Service(value = SpringBeanNames.SERVICE_NAME_P300BP)
@Scope("prototype")
public class P300BPMGServiceImpl implements P300BPMGService {
	private static Logger log = Logger.getLogger(P300BPMGServiceImpl.class);
	@Autowired
	private P300BPMGDao p300BMGDao;
	@Autowired 
	private TemplateService templateService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Autowired
	private SequenceService sequenceService;
	public List<Pieper> getListPieperPublic(int fo100, int fo100f, String pvSearch,  int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		return p300BMGDao.getListPieperPublic(fo100, fo100f, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), sort, pnOffset, pnLimit);
	}

	@Override
	public List<String> listSuggestedOtag(int fo100, String pvSearch,String pvSearchStem, int offset, int limit) {
		// TODO Auto-generated method stub
		return p300BMGDao.listSuggestedOtag(fo100, pvSearch,pvSearchStem, offset, limit);
	}

	@Override
	public P300BPMG getPieperDetail(int fo100, int pp100) {
		// TODO Auto-generated method stub
		return p300BMGDao.getPieperDetail(fo100, pp100);
	}

	@Override
	public int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, int ft300) {
		Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		int roleCreatePieper = 0;
		if (n100pmg != null)
		{
			/*
			 * 1) ca nhan co quyen piep bai BDS
			 */
			if(ft300 == T300PMG.FT300_RE && n100pmg.getNn120() == 1)
				roleCreatePieper = 1;
			/*
			 * 2) la doanh nghiep
			 */
			else if(n100pmg.getK100Con() != null && K100PMG.KV105_CONFIRMED.equals(n100pmg.getK100Con().getKv105()))
				roleCreatePieper = 1;
		}
		/*
		 * 3) kiem tra thoi gian tao truoc khi piep
		 */
		if(pn306 == 0 && roleCreatePieper == 1)
			roleCreatePieper = p300BMGDao.checkRoleOnCreate(fo100, ft300);
		if(roleCreatePieper == 1){
			try {
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
					int p300Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.P300B);
					P300BPMG p300mg = new P300BPMG();
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
					if(n100pmg.getLocation() != null)
						p300mg.setLocation(n100pmg.getLocation());
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
					/*
					 * promotion status
					 */
					V130ORService v130orService = (V130ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V130OR);
					log.info("---createTabV130:"+p300Id +","+ fo100 +","+ n100pmg.getNv101());
					int proStt = v130orService.createTabV130(p300Id, fo100, n100pmg.getNv101());
					p300mg.setPromotionStt(proStt);
					/*
					 * pieper categories
					 */
					p300mg.setFt300(ft300);
					if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300B) > 0)
						return p300Id;
				}
				else {
					P300BPMG p300mg = templateService.findDocument(fo100, P300BPMG.class, new QbCriteria(QbMongoFiledsName.ID, pp300), new QbCriteria(QbMongoFiledsName.FO100, fo100));
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
						/*
						 * pieper categories
						 */
						p300mg.setFt300(ft300);
						if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300B) > 0)
							return p300mg.getId();
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return 0;
		}
		else
			return roleCreatePieper;
	}

	@Override
	public int storNoTabP300P(int fo100, int pp300) {
		// TODO Auto-generated method stub
		return p300BMGDao.storNoTabP300P(fo100, pp300);
	}

	@Override
	public List<Pieper> getListPieperFollow(int fo100, String pvSearch, int sort, int pnOffset,
			int pnLimit) {
		// TODO Auto-generated method stub
		return p300BMGDao.getListPieperFollow(fo100, pvSearch, sort, pnOffset, pnLimit);
	}

	@Override
	public List<Pieper> getListPieperPublicOne(int fo100, int pp300) {
		// TODO Auto-generated method stub
		return p300BMGDao.getListPieperPublicOne(fo100, pp300);
	}

	@Override
	public List<Pieper> getListPieperPublicByCategory(int fo100, int fo100f, int ft300, String pvSearch, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		return p300BMGDao.getListPieperPublicByCategory(fo100, fo100f, ft300, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), sort, pnOffset, pnLimit);
	}

	@Override
	public List<Pieper> getListPieperPublicV2(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		return p300BMGDao.getListPieperPublicV2(pnLa, pnLong,fo100, fo100f, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), sort, pnOffset, pnLimit);
	}

	@Override
	public List<Pieper> getListPieperOwner(int fo100, String pvSearch, int ft300, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		return p300BMGDao.getListPieperOwner(fo100, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), ft300, sort, pnOffset, pnLimit);
	}

	@Override
	public List<Pieper> getListPieperPublicV3(double pnLa, double pnLong, final int fo100, int fo100f, String pvSearch, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		final List<Pieper> listPieper = p300BMGDao.getListPieperPublicV3(pnLa, pnLong, fo100, fo100f, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), sort, pnOffset, pnLimit);
		Thread thread = new Thread(){
		    public void run(){
		    	R100BPMGDao r100bpmgDao = (R100BPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_R100BP);
		    	TemplateService templateService = (TemplateService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		    	for(Pieper pieper: listPieper){
					P300BPMG p300bpmg = templateService.findDocumentById(pieper.getFo100(), pieper.getId(), P300BPMG.class);
		    		r100bpmgDao.insertTabR100B(fo100, p300bpmg.getId(), p300bpmg.getFo100(), p300bpmg.getPa315().size(), "SEN");
		    	}
		    }
		};
		thread.start();
		return listPieper;
	}

	@Override
	public P300BSummaryInfo getSummaryInfoV3(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, int sort) {
		// TODO Auto-generated method stub
		return p300BMGDao.getSummaryInfoV3(pnLa, pnLong, fo100, fo100f, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), sort);
	}

	@Override
	public List<Pieper> getListPieperPublicByCategoryV2(double pnLa, double pnLong, final int fo100, int fo100f, int ft300, String pvSearch, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		final List<Pieper> listPieper = p300BMGDao.getListPieperPublicByCategoryV2(pnLa, pnLong, fo100, fo100f, ft300, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), sort, pnOffset, pnLimit);
		Thread thread = new Thread(){
		    public void run(){
		    	R100BPMGDao r100bpmgDao = (R100BPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_R100BP);
		    	TemplateService templateService = (TemplateService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		    	for(Pieper pieper: listPieper){
					P300BPMG p300bpmg = templateService.findDocumentById(pieper.getFo100(), pieper.getId(), P300BPMG.class);
		    		r100bpmgDao.insertTabR100B(fo100, p300bpmg.getId(), p300bpmg.getFo100(), p300bpmg.getPa315().size(), "SEN");
		    	}
		    }
		};
		thread.start();
		return listPieper;
	}

	@Override
	public List<Pieper> getListPieperPublicEOM(final int fo100, int fo100f, String pvSearch, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		final List<Pieper> listPieper = p300BMGDao.getListPieperPublicEOM(fo100, fo100f, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), pnOffset, pnLimit);
		Thread thread = new Thread(){
		    public void run(){
		    	R100BPMGDao r100bpmgDao = (R100BPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_R100BP);
		    	TemplateService templateService = (TemplateService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		    	for(Pieper pieper: listPieper){
					P300BPMG p300bpmg = templateService.findDocumentById(pieper.getFo100(), pieper.getId(), P300BPMG.class);
		    		r100bpmgDao.insertTabR100B(fo100, p300bpmg.getId(), p300bpmg.getFo100(), p300bpmg.getPa315().size(), "SEN");
		    	}
		    }
		};
		thread.start();
		return listPieper;
	}

	@Override
	public int createPieperV2(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, int ft300) {
		// TODO Auto-generated method stub
		Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		int roleCreatePieper = 0;
		if (n100pmg != null)
		{
			/*
			 * 1) ca nhan co quyen piep bai BDS
			 */
			if(ft300 == T300PMG.FT300_RE && n100pmg.getNn120() == 1)
				roleCreatePieper = 1;
			/*
			 * 2) la doanh nghiep
			 */
			else if(n100pmg.getK100Con() != null && K100PMG.KV105_CONFIRMED.equals(n100pmg.getK100Con().getKv105()))
				roleCreatePieper = 1;
		}
		/*
		 * 3) kiem tra thoi gian tao truoc khi piep
		 */
		if(pn306 == 0 && roleCreatePieper == 1)
			roleCreatePieper = p300BMGDao.checkRoleOnCreate(fo100, ft300);
		if(roleCreatePieper == 1){
			try {
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
						int p300Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.P300B);
						P300BPMG p300mg = new P300BPMG();
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
						if(n100pmg.getLocation() != null)
							p300mg.setLocation(n100pmg.getLocation());
						/*
						 * create pv302
						 */
						p300mg.setPv302(Pieper.PV302_OFF);
						/*
						 * promotion status
						 */
						V130ORService v130orService = (V130ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V130OR);
						log.info("---createTabV130:"+p300Id +","+ fo100 +","+ n100pmg.getNv101());
						int proStt = v130orService.createTabV130(p300Id, fo100, n100pmg.getNv101());
						p300mg.setPromotionStt(proStt);
						/*
						 * pieper categories
						 */
						p300mg.setFt300(ft300);
						if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300B) > 0)
							return p300Id;
					}
					else {
						P300BPMG p300mg = templateService.findDocument(fo100, P300BPMG.class, new QbCriteria(QbMongoFiledsName.ID, pp300), new QbCriteria(QbMongoFiledsName.FO100, fo100));
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
							/*
							 * pieper categories
							 */
							p300mg.setFt300(ft300);
							if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300B) > 0)
								return p300mg.getId();
						}
					}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return 0;
		}
		else
			return roleCreatePieper;
	}
}
