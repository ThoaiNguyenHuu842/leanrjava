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
import com.ohhay.piepme.mongo.dao.P300MPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300MMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.nestedentities.P300ConInfo;
import com.ohhay.piepme.mongo.nestedentities.PieperImg;
import com.ohhay.piepme.mongo.service.P300MPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author TuNt
 * create date Nov 7, 2016
 * ohhay-module-piepme
 */
@Service(value = SpringBeanNames.SERVICE_NAME_P300MP)
@Scope("prototype")
public class P300MPMGServiceImpl implements P300MPMGService {
	@Autowired
	private P300MPMGDao p300MMGDao;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Autowired
	private SequenceService sequenceService;
	@Override
	public P300MMG getPieperDetail(int fo100, int pp300, int pnSeen) {
		// TODO Auto-generated method stub
		return p300MMGDao.getPieperDetail(fo100, pp300, pnSeen);
	}
	@Override
	public int createPieper(int fo100, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, List<Integer> listFO100R, List<PieperImg> listImgs) {
		// TODO Auto-generated method stub
		try {
			Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if (n100pmg != null) {
				/*
				 * create P300
				 */
				int p200Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.P300M);
				P300MMG p300mg = new P300MMG();
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
				p300mg.setListFO100R(listFO100R);
				p300mg.setDeliveryStt(P300MMG.DELIVERY_STT_SENT);
				// if(loc != null)
				// pieper.setLocation(loc);
				try {
					String tags[] = AESUtils.decrypt(otags).split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
					List<Otag> listOtag = new ArrayList<Otag>();
					for (int i = 0; i < tags.length; i++)
					{
						if(tags[i].trim().length() > 0){
							Otag otag = new Otag();
							otag.setKey(tags[i].trim());
							otag.setKeyStem(ApplicationHelper.getStemOtag(tags[i].toLowerCase()));
							listOtag.add(otag);
						}
					}
					p300mg.setListOtags(listOtag);
				} catch (Exception e) {
					// TODO: handle exception
				}
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
				p300mg.setPieperImgs(listImgs);
				if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300M) > 0)
					return p200Id;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<P300ConInfo> listOfTabP300MCon(int fo100, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		return p300MMGDao.listOfTabP300MCon(fo100, pvSearch, sort, offset, limit);
	}
	@Override
	public List<P300MMG> listOfTabP300M(int fo100, int fo100f, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		return p300MMGDao.listOfTabP300M(fo100, fo100f, pvSearch, sort, offset, limit);
	}
	@Override
	public List<P300MMG> listOfTabP300MArchived(int fo100, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		return p300MMGDao.listOfTabP300MArchived(fo100, pvSearch, sort, offset, limit);
	}
	@Override
	public int archiveTabP300(int fo100, int fo100f) {
		// TODO Auto-generated method stub
		return p300MMGDao.archiveTabP300(fo100, fo100f);
	}
	@Override
	public int storNoTabP300MCon(int fo100, int fo100f) {
		// TODO Auto-generated method stub
		return p300MMGDao.storNoTabP300MCon(fo100, fo100f);
	}
	@Override
	public int sendOTPCode(int fo100,  String nickName, String otpCode) {
		// TODO Auto-generated method stub
		int fo100Admin = ApplicationConstant.FO100_SUPER_ADMIN_PIEPME;
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100Admin, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100Admin));
		/*
		 * create pv302
		 */
		String tempKey = n100pmg.getNv102().substring(0, 16);
		int fo100Length = String.valueOf(n100pmg.getFo100()).length();
		StringBuilder key2 = new StringBuilder();
		for (int i = 0; i < 16 - fo100Length; i++)
			key2.append("0");
		key2.append(n100pmg.getFo100());
		AESUtilsPiepme aesUtilsPiepmeKey = new AESUtilsPiepme(key2.toString(), key2.toString());		
		String pv302 = aesUtilsPiepmeKey.encrypt(tempKey);
		
		AESUtilsPiepme aesUtilsPiepme = new AESUtilsPiepme(pv302.substring(0, 16), pv302.substring(0, 16));
		
		String pv301 = aesUtilsPiepme.encrypt(otpCode+" là mã xác nhận OTP áp dụng cho tài khoản có Nickname "+nickName+". Bạn vui lòng thông báo OTP này đến chủ tài khoản "+nickName+", cảm ơn bạn!");
		P300MPMGService p300Service = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
		List<Integer> listFO100R = new ArrayList<Integer>();
		listFO100R.add(fo100);
		return p300Service.createPieper(fo100Admin, pv301, 1, null, null, pv301, 0, 0, pv301, null, listFO100R, null);
	}
	@Override
	public int createPieperV2(int fo100, String pv301, String pv302, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, List<Integer> listFO100R, List<PieperImg> listImgs) {
		// TODO Auto-generated method stub
		try {
			Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if (n100pmg != null) {
				/*
				 * create P300
				 */
				int p200Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.P300M);
				P300MMG p300mg = new P300MMG();
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
				p300mg.setListFO100R(listFO100R);
				p300mg.setDeliveryStt(P300MMG.DELIVERY_STT_SENT);
				// if(loc != null)
				// pieper.setLocation(loc);
				try {
					String tags[] = AESUtils.decrypt(otags).split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
					List<Otag> listOtag = new ArrayList<Otag>();
					for (int i = 0; i < tags.length; i++)
					{
						if(tags[i].trim().length() > 0){
							Otag otag = new Otag();
							otag.setKey(tags[i].trim());
							otag.setKeyStem(ApplicationHelper.getStemOtag(tags[i].toLowerCase()));
							listOtag.add(otag);
						}
					}
					p300mg.setListOtags(listOtag);
				} catch (Exception e) {
					// TODO: handle exception
				}
				/*
				 * create pv302
				 */
				p300mg.setPv302(pv302);
				p300mg.setPieperImgs(listImgs);
				if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300M) > 0)
					return p200Id;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int sendOTPCodeV2(int fo100, String nickName, String otpCode) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		/*
		 * create pv302
		 */
		String tempKey = n100pmg.getNv102().substring(0, 16);
		int fo100Length = String.valueOf(n100pmg.getFo100()).length();
		StringBuilder key2 = new StringBuilder();
		for (int i = 0; i < 16 - fo100Length; i++)
			key2.append("0");
		key2.append(n100pmg.getFo100());
		AESUtilsPiepme aesUtilsPiepmeKey = new AESUtilsPiepme(key2.toString(), key2.toString());		
		String pv302 = aesUtilsPiepmeKey.encrypt(tempKey);
		
		AESUtilsPiepme aesUtilsPiepme = new AESUtilsPiepme(pv302.substring(0, 16), pv302.substring(0, 16));
		
		String pv301 = aesUtilsPiepme.encrypt(otpCode+" là mã xác nhận OTP áp dụng cho tài khoản có Nickname "+nickName+". Bạn vui lòng thông báo OTP này đến chủ tài khoản "+nickName+", cảm ơn bạn!");
		P300MPMGService p300Service = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
		List<Integer> listFO100R = new ArrayList<Integer>();
		listFO100R.add(fo100);
		return p300Service.createPieperV2(ApplicationConstant.FO100_SUPER_ADMIN_PIEPME, pv301, Pieper.PV302_PIE, 1, null, null, pv301, 0, 0, pv301, null, listFO100R, null);
	}
	@Override
	public int messageByAdmin(int fo100, String message) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		/*
		 * create pv302
		 */
		String tempKey = n100pmg.getNv102().substring(0, 16);
		int fo100Length = String.valueOf(n100pmg.getFo100()).length();
		StringBuilder key2 = new StringBuilder();
		for (int i = 0; i < 16 - fo100Length; i++)
			key2.append("0");
		key2.append(n100pmg.getFo100());
		AESUtilsPiepme aesUtilsPiepmeKey = new AESUtilsPiepme(key2.toString(), key2.toString());		
		String pv302 = aesUtilsPiepmeKey.encrypt(tempKey);
		
		AESUtilsPiepme aesUtilsPiepme = new AESUtilsPiepme(pv302.substring(0, 16), pv302.substring(0, 16));
		
		String pv301 = aesUtilsPiepme.encrypt(message);
		P300MPMGService p300Service = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
		List<Integer> listFO100R = new ArrayList<Integer>();
		listFO100R.add(fo100);
		return p300Service.createPieperV2(ApplicationConstant.FO100_SUPER_ADMIN_PIEPME, pv301, Pieper.PV302_PIE, 1, null, null, pv301, 0, 0, pv301, null, listFO100R, null);
	}
	@Override
	public List<P300ConInfo> listOfTabP300MConV2(int fo100, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		return p300MMGDao.listOfTabP300MConV2(fo100, pvSearch, sort, offset, limit);
	}
	@Override
	public List<P300MMG> listOfTabP300MV2(int fo100, int fo100f, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		return p300MMGDao.listOfTabP300MV2(fo100, fo100f, pvSearch, sort, offset, limit);
	}
	@Override
	public int pinTabP300M(int fo100, int pp300) {
		// TODO Auto-generated method stub
		return p300MMGDao.pinTabP300M(fo100, pp300);
	}
	@Override
	public int unpinTabP300M(int fo100, int pp300) {
		// TODO Auto-generated method stub
		return p300MMGDao.unpinTabP300M(fo100, pp300);
	}
}
