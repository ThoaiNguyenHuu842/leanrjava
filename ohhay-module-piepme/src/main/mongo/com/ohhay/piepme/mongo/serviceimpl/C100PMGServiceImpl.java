package com.ohhay.piepme.mongo.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mongodb.Mongo;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.MongoDBManager;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.dao.Q100Dao;
import com.ohhay.core.oracle.dao.N100ORDao;
import com.ohhay.core.oracle.service.V220ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.C100PMGDao;
import com.ohhay.piepme.mongo.entities.interaction.C100PMG;
import com.ohhay.piepme.mongo.entities.other.P150PMG;
import com.ohhay.piepme.mongo.nestedentities.C100ACPMG;
import com.ohhay.piepme.mongo.service.C100PMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH
 * create Sep 21, 2016
 */
@Service(value = SpringBeanNames.SERVICE_NAME_C100P)
@Scope("prototype")
public class C100PMGServiceImpl implements C100PMGService {
	@Autowired
	private TemplateService templateService;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private C100PMGDao c100pmgDao;
	@Autowired
	private AdminPMGDao adminPMGDao;
	public C100PMGServiceImpl() {
		// TODO Auto-generated constructor stub
		templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
	}

	public int sendRequest(int fo100, int fo100f) {
		// TODO Auto-generated method stub
		if (checkFriendStatus(fo100, fo100f) == 0) {
			C100PMG c100pmg = new C100PMG();
			try {
				c100pmg.setId((int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.C100));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c100pmg.setFo100(fo100);
			c100pmg.setFo100f(fo100f);
			c100pmg.setCv101(C100PMG.NC);
			c100pmg.setCd168(new Date(adminPMGDao.getCurrentTime()));
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			/*
			 * 
			 * save to DB
			 */
			String urlA = MongoDBManager.mapFO100PiepmeDB.get(fo100);
			if(urlA == null)
				urlA = MongoDBManager.getUserURIMongoPiepme(fo100);
			String urlB = MongoDBManager.mapFO100PiepmeDB.get(fo100f);
			if(urlB == null)
				urlB = MongoDBManager.getUserURIMongoPiepme(fo100f);
			/*
			 * check if two user have the same server
			 */
			if(urlA.equalsIgnoreCase(urlB))
				templateService.saveDocument(fo100, c100pmg, QbMongoCollectionsName.C100);
			else
			{
				if(urlA != null)
					c100pmg.setCv102(AESUtils.encrypt(urlA));
				if(urlB != null)
					c100pmg.setCv102f(AESUtils.encrypt(urlB));
				templateService.saveDocument(fo100, c100pmg, QbMongoCollectionsName.C100);
				templateService.saveDocument(fo100f, c100pmg, QbMongoCollectionsName.C100);
			}
			return c100pmg.getId();
		}else
			return 0;

	}

	@Override
	public int confirmRequest(int fo100f, int fc100) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		C100PMG c100pmg = templateService.findDocumentById(fo100f, fc100, C100PMG.class);
		if(c100pmg != null){
			c100pmg.setCv101(C100PMG.AC);
			c100pmg.setCd166(new Date(adminPMGDao.getCurrentTime()));
			N100PMG n100pmg = templateService.findDocument(c100pmg.getFo100(), N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, c100pmg.getFo100()));
			if(n100pmg != null)
				c100pmg.setCv104(ApplicationHelper.createPiepmeKey02(n100pmg.getNv102(), n100pmg.getFo100()));
			
			N100PMG n100pmgF = templateService.findDocument(c100pmg.getFo100f(), N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, c100pmg.getFo100f()));
			if(n100pmgF != null)
				c100pmg.setCv104f(ApplicationHelper.createPiepmeKey02(n100pmgF.getNv102(), n100pmgF.getFo100()));
			templateService.saveDocument(c100pmg.getFo100(), c100pmg, QbMongoCollectionsName.C100);
			
			if(!MongoDBManager.hasTheSamePiepmeServer(c100pmg.getFo100(), c100pmg.getFo100f()))
				templateService.updateOneField(c100pmg.getFo100f(), C100PMG.class, QbMongoFiledsName.CV101, C100PMG.AC, "CD166",
					new QbCriteria(QbMongoFiledsName.ID, fc100), new QbCriteria(QbMongoFiledsName.FO100F, fo100f));
			//auto set 3 first friends to secure contacts
			c100pmgDao.updateTabSecureContactAuto(fc100);
			return 1;
		}
		return -1;
	}

	@Override
	public int cancelRequest(int fo100f, int fc100) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		C100PMG c100pmg = templateService.findDocument(fo100f, C100PMG.class, new QbCriteria(QbMongoFiledsName.ID, fc100), new QbCriteria(QbMongoFiledsName.FO100, fo100f));
		if(c100pmg == null)
			c100pmg = templateService.findDocument(fo100f, C100PMG.class, new QbCriteria(QbMongoFiledsName.ID, fc100), new QbCriteria(QbMongoFiledsName.FO100F, fo100f));
		if(c100pmg != null){
			templateService.removeDocumentById(c100pmg.getFo100(), fc100, C100PMG.class);
			if(!MongoDBManager.hasTheSamePiepmeServer(c100pmg.getFo100(), c100pmg.getFo100f()))
				templateService.removeDocumentById(c100pmg.getFo100f(), fc100, C100PMG.class);
			return 1;
		}
		return -1;
	}

	@Override
	public int checkFriendStatus(int fo100a, int fo100b) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		C100PMG c100pmg = templateService.findDocument(fo100a, C100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100a), new QbCriteria(QbMongoFiledsName.FO100F, fo100b));
		if (c100pmg == null)
			c100pmg = templateService.findDocument(fo100a, C100PMG.class, new QbCriteria(QbMongoFiledsName.FO100F, fo100a),
					new QbCriteria(QbMongoFiledsName.FO100, fo100b));
		if (c100pmg != null) {
			if (C100PMG.AC.equals(c100pmg.getCv101()))
				return 3;
			else if (c100pmg.getFo100() == fo100a)
				return 1;
			else
				return 2;
		}
		return 0;
	}

	@Override
	public List<C100PMG> listOfTabC100Friend(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return c100pmgDao.listOfTabC100Friend(fo100, offset, limit);
	}

	@Override
	public List<C100PMG> listOfTabC100Request(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return c100pmgDao.listOfTabC100Request(fo100, offset, limit);
	}

	@Override
	public List<C100PMG> listOfTabC100SentRequest(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return c100pmgDao.listOfTabC100SentRequest(fo100, offset, limit);
	}

	@Override
	public int updateTabC100Nick(int fo100, int fo100f, String cv103) {
		// TODO Auto-generated method stub
		if(MongoDBManager.hasTheSamePiepmeServer(fo100, fo100f))
			return c100pmgDao.updateTabC100Nick(fo100, fo100, fo100f, cv103);
		else {
			c100pmgDao.updateTabC100Nick(fo100, fo100, fo100f, cv103);
			return c100pmgDao.updateTabC100Nick(fo100f, fo100, fo100f, cv103);
		}
	}

	@Override
	public int unfriend(int fo100a, int fo100b) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		C100PMG c100pmg = templateService.findDocument(fo100a, C100PMG.class,
													   new QbCriteria(QbMongoFiledsName.FO100, fo100a), 
													   new QbCriteria(QbMongoFiledsName.FO100F, fo100b), 
													   new QbCriteria( QbMongoFiledsName.CV101, C100PMG.AC));
		if (c100pmg == null)
			c100pmg = templateService.findDocument(fo100a, C100PMG.class, 
												   new QbCriteria(QbMongoFiledsName.FO100F, fo100a),
												   new QbCriteria(QbMongoFiledsName.FO100, fo100b), 
												   new QbCriteria( QbMongoFiledsName.CV101, C100PMG.AC));
		if(c100pmg != null)
		{
			int kq = templateService.removeDocumentById(fo100a, c100pmg.getId(), C100PMG.class);
			if(!MongoDBManager.hasTheSamePiepmeServer(fo100a, fo100b))
				templateService.removeDocumentById(fo100b, c100pmg.getId(), C100PMG.class);
			return kq;
		}
		return 0;
	}

	@Override
	public List<C100ACPMG> listOfTabC100RecentAc(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return c100pmgDao.listOfTabC100RecentAc(fo100, offset, limit);
	}

	@Override
	public int updateTabSecureContact(int fo100, List<Double> fc100s) {
		// TODO Auto-generated method stub
		return c100pmgDao.updateTabSecureContact(fo100, fc100s);
	}

	@Override
	public List<C100PMG> listOfTabC100Secure(int fo100) {
		// TODO Auto-generated method stub
		return c100pmgDao.listOfTabC100Secure(fo100);
	}

	@Override
	public List<C100PMG> listOfTabC100FriendExcludeSecure(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return c100pmgDao.listOfTabC100FriendExcludeSecure(fo100, offset, limit);
	}

	@Override
	public int insertReferrerCode(int fo100, String code) {
		// TODO Auto-generated method stub
		Q100Dao q100Dao = (Q100Dao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_Q100);
		/*
		 * 1) get FO100 from piepme id
		 */
		int fo100F = q100Dao.getTabQ100PiepId(code.trim(), ApplicationConstant.PVLOGIN_ANONYMOUS);
		if(fo100 > 0){
			if(fo100 != fo100F){
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				N100PMG n100pmgF = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100F));
				if(n100pmg != null && n100pmgF != null){
					if(n100pmg.getFo100Re() != 0)
						return -2;
					if(n100pmgF.getListFO100RE() == null)
						n100pmgF.setListFO100RE(new ArrayList<Integer>());
					n100pmgF.getListFO100RE().add(fo100);
					/*
					 * 2) active voucher
					 */
					if(n100pmgF.getListFO100RE().size() == 3){
						V220ORService v220ORService = (V220ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_V220OR);
						v220ORService.activateTabV220O(n100pmgF.getFo100(), n100pmgF.getNv101());
						/*
						 * update stt
						 */
					    n100pmgF.setNv118("SU");
					}
					/*
					 * update 13/07/2017
					 * 3) update oracle
					 */
					N100ORDao n100orDao = (N100ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_N100OR);
					n100orDao.updateTabN100CODE(fo100, code.trim(), n100pmg.getNv101());
					/*
					 * 4) auto request friend
					 */
					sendRequest(fo100, n100pmgF.getFo100());
					if(templateService.saveDocument(fo100, n100pmgF, QbMongoCollectionsName.N100) > 0)
					{
						n100pmg.setFo100Re(n100pmgF.getFo100());
						templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
						return n100pmgF.getFo100();
					}
				}
			}
			else
				return -2;
		}
		return -1;
	}

	@Override
	public List<C100PMG> listOfTabC100FriendHis(int fo100, int fo100f, long timeStamp, int offset, int limit) {
		// TODO Auto-generated method stub
		return c100pmgDao.listOfTabC100FriendHis(fo100, fo100f, timeStamp, offset, limit);
	}

	@Override
	public List<Integer> listOfTabC100FriendDel(int fo100, long time) {
		// TODO Auto-generated method stub
		return c100pmgDao.listOfTabC100FriendDel(fo100, time);
	}

	@Override
	public List<C100PMG> listOfTabC100FriendEdu(int fo100, int ft100, int ft110, int offset, int limit) {
		// TODO Auto-generated method stub
		return c100pmgDao.listOfTabC100FriendEdu(fo100, ft100, ft110, offset, limit);
	}

	@Override
	public List<C100PMG> listOfTabC100FriendAdm(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return c100pmgDao.listOfTabC100FriendAdm(fo100, offset, limit);
	}
}
