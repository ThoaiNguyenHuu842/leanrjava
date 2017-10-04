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
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.channel.T110PMG;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.P300BEduPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300BEduPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.service.P300BEduPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH
 * create Jul 29, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_P300BEDUP)
@Scope("prototype")
public class P300BEduPMGServiceImpl implements P300BEduPMGService{
	private static Logger log = Logger.getLogger(P300BPMGServiceImpl.class);
	@Autowired 
	private TemplateService templateService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private P300BEduPMGDao p300bEduPMGDao;
	@Override
	public int createPieperEdu(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, float pn309, String pv314, String otags, int ft110, String tv119) {

		// TODO Auto-generated method stub
		Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		int roleCreatePieper = 0;
		if (n100pmg != null)
		{
			T110PMG t110pmg = templateService.findDocumentById(fo100, ft110, T110PMG.class);
			/*
			 * 1) quyen tao pieper trong channel ft110
			 */
			if(t110pmg != null && (t110pmg.getFo100() == fo100 || (t110pmg.getFo100s() != null && t110pmg.getFo100s().contains(fo100))))
				roleCreatePieper = 1;
			else
				roleCreatePieper = -1;
		}
		/*
		 * 2) kiem tra thoi gian tao truoc khi piep
		 */
		/*if(pn306 == 0 && roleCreatePieper == 1)
			roleCreatePieper = p300BMGDao.checkRoleOnCreate(fo100, ft300);*/
		if(roleCreatePieper == 1){
			try {
					/*
					 * 3) Tao pieper
					 */
					int rs = 0;
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
						p300mg.setPn306(2);
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
						if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300B) > 0)
							rs = p300Id;
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
							if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300B) > 0)
								rs = p300mg.getId();
						}
					}
					/*
					 * 4) cap nhat thong tin EDU
					 */
					if(rs > 0){
						templateService.updateOneField(fo100, P300BPMG.class, rs, "FT110", ft110, null);
						templateService.updateOneField(fo100, P300BPMG.class, rs, "TV119", tv119, null);
					}
					return rs;
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
	public List<Pieper> getListPieper(int pnFO100, int pnFT110, String pnTV119, String pvSearch, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		return p300bEduPMGDao.getListPieper(pnFO100, pnFT110, pnTV119, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), pnOffset, pnLimit);
	}
	@Override
	public P300BEduPMG getPieperDetail(int fo100, int pp100) {
		// TODO Auto-generated method stub
		return p300bEduPMGDao.getPieperDetail(fo100, pp100);
	}

}
