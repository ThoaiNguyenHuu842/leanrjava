package com.ohhay.piepme.mongo.serviceimpl;

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
import com.ohhay.piepme.mongo.dao.PieperDao;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.PieperComment;
import com.ohhay.piepme.mongo.service.PieperService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

@Service(value = SpringBeanNames.SERVICE_NAME_PIEPER)
@Scope("prototype")
public class PieperServiceImpl implements PieperService{
	@Autowired
	private PieperDao pieperDao;
	@Autowired
	private AdminPMGDao adminPMGDao;
	private TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
	public PieperServiceImpl() {
		// TODO Auto-generated constructor stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
	}
	public int createPieper(int fo100, String pv301, int pn303, String pv304, String pv305,float pn309, String pv314, String otags, String collectionName){
		if(QbMongoCollectionsName.P300P.equals(collectionName) || QbMongoCollectionsName.P300C.equals(collectionName) || QbMongoCollectionsName.P300M.equals(collectionName)){
			try {
				Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
				N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				if(n100pmg != null){
					/*
					 * create AES with mac address
					 */
					SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
					/*
					 * create P300
					 */
					int p300Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, collectionName);
					Pieper pieper = new Pieper();
					pieper.setId(p300Id);
					pieper.setFo100(fo100);
					pieper.setPd308(timeCurrent);
					pieper.setPv301(pv301);
					pieper.setPn303(pn303);
					pieper.setPv304(pv304);
					pieper.setPv305(pv305);
					pieper.setPn309(pn309);
					pieper.setPv314(pv314);
	//				if(loc != null)
	//					pieper.setLocation(loc);
					try {
						String tags[] = AESUtils.decrypt(otags).split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
						for (int i = 0; i < tags.length; i++)
							tags[i] = tags[i].trim();
						pieper.setOtags(tags);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					/*
					 * create pv302
					 */
					String tempKey = n100pmg.getNv102().substring(0, 16);
					int fo100Length = String.valueOf(n100pmg.getFo100()).length();
					StringBuilder key2 = new StringBuilder(); 
					for(int i=0;i<16-fo100Length;i++)
						key2.append("0");
					key2.append(n100pmg.getFo100());
					AESUtilsPiepme aesUtilsPiepme = new AESUtilsPiepme(key2.toString(), key2.toString());
					pieper.setPv302(aesUtilsPiepme.encrypt(tempKey));
					if(templateService.saveDocument(fo100, pieper, collectionName) > 0)
						return p300Id;
					else
						return 0;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return 0;
	}
	@Override
	public int checkTabUserLike(String pnPiperType, int pnPiperId, int pnFO100) {
		// TODO Auto-generated method stub
		return pieperDao.checkTabUserLike(pnPiperType, pnPiperId, pnFO100);
	}
	@Override
	public int insertTabLike(String pnPiperType, int pnPiperId, int pnFO100) {
		// TODO Auto-generated method stub
		return pieperDao.insertTabLike(pnPiperType, pnPiperId, pnFO100);
	}
	@Override
	public int stornoTabLike(String pnPiperType, int pnPiperId, int pnFO100) {
		// TODO Auto-generated method stub
		return pieperDao.stornoTabLike(pnPiperType, pnPiperId, pnFO100);
	}
	@Override
	public int insertTabComment(String pnPiperType, int pnCommentID, int pnPiperId, int pnFO100, String pnCOMMENT) {
		// TODO Auto-generated method stub
		return pieperDao.insertTabComment(pnPiperType, pnCommentID, pnPiperId, pnFO100, pnCOMMENT);
	}
	@Override
	public int stornoTabComment(String pnPiperType, int pnPiperId, int pnComentId, int pnFO100) {
		// TODO Auto-generated method stub
		return pieperDao.stornoTabComment(pnPiperType, pnPiperId, pnComentId, pnFO100);
	}
	@Override
	public List<PieperComment> pieperListOfTabComment(String pnPiperType, int pnPiperId, int pnFO100, int offset,
			int limit) {
		// TODO Auto-generated method stub
		return pieperDao.pieperListOfTabComment(pnPiperType, pnPiperId, pnFO100, offset, limit);
	}
	@Override
	public PieperComment pieperListOfTabCommentDetail(String pnPiperType, int pnPiperId, int pnFO100, int commentId) {
		// TODO Auto-generated method stub
		return pieperDao.pieperListOfTabCommentDetail(pnPiperType, pnPiperId, pnFO100, commentId);
	}
	@Override
	public List<N100PMG> pieperListOfTabUserLike(String pnPiperType, int pnPiperId, int pnFO100, int offset, int limit) {
		// TODO Auto-generated method stub
		return pieperDao.pieperListOfTabUserLike(pnPiperType, pnPiperId, pnFO100, offset, limit);
	}
	@Override
	public int repiep(String pnPiperType, int pnPiperId, int pnFO100) {
		// TODO Auto-generated method stub
		return pieperDao.repiep(pnPiperType, pnPiperId, pnFO100);
	}
	@Override
	public int pieperUpdateTabPn306(String pnPiperType, int pnPiperId, int pnFO100, int pn306) {
		// TODO Auto-generated method stub
		return pieperDao.pieperUpdateTabPn306(pnPiperType, pnPiperId, pnFO100, pn306);
	}
	@Override
	public List<N100PMG> pieperListOfTabUserView(String pnPiperType, int pnPiperId, int pnFO100, int offset, int limit) {
		// TODO Auto-generated method stub
		return pieperDao.pieperListOfTabUserView(pnPiperType, pnPiperId, pnFO100, offset, limit);
	}

}
