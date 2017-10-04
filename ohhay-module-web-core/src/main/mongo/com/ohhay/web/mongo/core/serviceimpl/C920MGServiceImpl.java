package com.ohhay.web.mongo.core.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.LanguageService;
import com.ohhay.web.core.entities.mongo.webbase.C110MG;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.entities.mongo.webbase.C920MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.load.util.PropertyValue;
import com.ohhay.web.core.mongo.dao.C920MGDao;
import com.ohhay.web.core.mongo.service.C900MGService;
import com.ohhay.web.core.mongo.service.C920MGService;
import com.ohhay.web.core.utils.WebTemplateRule;

@Service(value = SpringBeanNames.SERVICE_NAME_C920MG)
@Scope("prototype")
public class C920MGServiceImpl implements C920MGService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_C920MG)
	C920MGDao c920mgDao;
	@Autowired
	TemplateService templateService;
	@Autowired
	C900MGService c900mgService;
	@Autowired
	LanguageService languageMGService;
	private static Logger log = Logger.getLogger(C920MGServiceImpl.class);
	@Override
	public int stornoTabC920(int fo100, int webID, int fc920, int extend) {
		// TODO Auto-generated method stub
		int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100;
		return c920mgDao.stornoTabC920(fo100i, webID, fc920, extend);
	}
	@Override
	public <T> int swapCn924(int fo100,  int webId, int fc920, int fc920t, int extend){
		// TODO Auto-generated method stub
		try {
			int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100; 
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			OhhayWebBase ohhayWebBase = (OhhayWebBase) templateService.findDocumentById(fo100i, webId,  WebTemplateRule.getWebClassFromExtend(extend));
			int cn924 = 0;
			int cn924t = 0;
			for (int i = 0; i < ohhayWebBase.getListC920mg().size(); i++) {
				C920MG c920mg = ohhayWebBase.getListC920mg().get(i);
				if (c920mg.getFc920() == fc920) 
					cn924 = c920mg.getCn924();
				if (c920mg.getFc920() == fc920t) 
					cn924t = c920mg.getCn924();
			}
			if(cn924 != 0 && cn924t != 0){
				c920mgDao.updateBoxCn924(fo100i, webId, fc920, cn924t, extend);
				c920mgDao.updateBoxCn924(fo100i, webId, fc920t, cn924, extend);
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> int copyBox(int fo100, int webId, int fc920, int extend) {
		try {
			int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100; 
			String appendId = String.valueOf(new Date().getTime());//du lieu dung de tap id moi 
			C920MG c920mgNew = null;
			
			boolean breakBoxPoint = false;//bat len khi tim thay box can copy
			/*
			 * 1) tim box can copy
			 */
			OhhayWebBase ohhayWebBase = (OhhayWebBase) templateService.findDocument(fo100i, WebTemplateRule.getWebClassFromExtend(extend), 
																					new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.ID,webId));
			int currentMaxFc920 = getMaxFc920OfWeb(ohhayWebBase);//fc920 lon nhat cua web hien tai
			//sort lai list c920 theo cn924 de cap nhat cn924 cho box can copy
			Collections.sort(ohhayWebBase.getListC920mg());
			LanguageService languageMGService = (LanguageService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
			List<LanguageMG> listC500mgs = ohhayWebBase.getListC500mg();//danh sach ngon ngu phai tao
			if (ohhayWebBase != null) {
				for(C920MG c920mg: ohhayWebBase.getListC920mg()){
					/*
					 * 2) cap nhat lai cn924 cho cac box co cn924 > cn924 cua box can copy
					 */
					if(breakBoxPoint)
						c920mgDao.updateBoxCn924(fo100i, webId, c920mg.getFc920(), c920mg.getCn924() + 1, extend);
					/*
					 * 3) tao box moi cho box can copy
					 */
					if (c920mg.getFc920() == fc920) {
						breakBoxPoint = true;
						c920mgNew = new C920MG(c920mg);
						/*
						 * 3.1) set lai cac du lieu moi: id, cn924, listC900mg
						 */
						c920mgNew.setVisible(0);//copy box nay luon hien
						c920mgNew.setOriginal(-1);
						c920mgNew.setCn924(c920mgNew.getCn924() + 1);//set new index
						c920mgNew.setFc920(currentMaxFc920 + 1);//set new id
						//tao du lieu ngon ngu
						for (int i = 0; i< listC500mgs.size(); i++) {
							LanguageMG c500mg = listC500mgs.get(i);
							/*
							 * 3.2) find map language properties of current web language
							 */
							OhhayWebLanguageBase languageBase = (OhhayWebLanguageBase) templateService.findDocument(fo100i, 
												WebTemplateRule.getWebLanguageClassFromExtend(extend),
												new QbCriteria(QbMongoFiledsName.LANGUAGEID,ApplicationHelper.convertToMD5(ohhayWebBase.getId() + c500mg.getCode())));
							Map<String, PropertyValue> mapProperties = languageMGService.getProperties(languageBase);
							if(mapProperties != null)
							{
								/*
								 * 3.3) create c110 new from old c110
								 */
								for(C900MG c900mg: c920mgNew.getListC900mg())
								{
									log.info("---push language:"+c900mg.getPc900());
									PropertyValue propertyValue = mapProperties.get(c900mg.getPc900());
									C110MG c110mg = new C110MG(c900mg.getPc900() + appendId, propertyValue.getValue(), 
															   propertyValue.getCv113(), propertyValue.getCn114(),
															   propertyValue.getCv115(), propertyValue.getCv116(), propertyValue.getCv117(), 
															   c920mgNew.getFc920(), c900mg.getFc850());
									languageMGService.pushOneLanguage(fo100i, ohhayWebBase.getId(), c500mg.getCode(), c110mg, WebTemplateRule.getWebClassFromExtend(extend));
									//tao moi id cho c900
									if(i == 0)
										c900mg.setPc900(c900mg.getPc900() + appendId);
								}
							}
						}							
					}
				}
				/*
				 * 4) save to db
				 */
				c920mgDao.pushBoxToWeb(fo100, webId, c920mgNew, extend, fo100i);
				log.info("---c920mgNew:"+c920mgNew);
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public <T> int copyElem(int fo100, int webId, int part, int elem, int extend) {
		try {
			int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100; 
			OhhayWebBase ohhayWebBase = (OhhayWebBase) templateService.findDocument(fo100i, WebTemplateRule.getWebClassFromExtend(extend), 
																		new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.ID,webId));
			C920MG c920mgCopy = null;
			C900MG c900mgCopy = null;
			String appendId = String.valueOf(new Date().getTime());
			String pc900Elem = null;//id elem need to copy
			/*
			 * 1) find part need to change
			 */
			try{
				c920mgCopy = ohhayWebBase.getListC920mg().get(part);
			}catch(Exception ex){
				log.error("---error box index when copy elem");
				return 0;
			}
			/*
			 * 2) create data for new C900MG
			 */
			if(c920mgCopy != null)
			{
				try{
					C900MG c900mg = c920mgCopy.getListC900mg().get(elem);
					pc900Elem = c900mg.getPc900();
					c900mgCopy = new C900MG(c900mg.getPc900()+ "" + appendId,
											c900mg.getCv901(), c900mg.getCv902(),
											c900mg.getCv904(), c900mg.getCv905(),
											c900mg.getCv906(),
											c900mg.getFc850());
				}
				catch(Exception ex){
					log.error("---error elem index when copy elem");
					return 0;
				}
			}
			c920mgCopy.getListC900mg().add(c900mgCopy);
			/*
			 * 3) pull current box
			 */
			c920mgDao.stornoTabC920Only(fo100i, webId, c920mgCopy.getFc920(), extend);
			/*
			 * 4) re-push current box
			 */
			c920mgDao.pushBoxToWeb(fo100, webId, c920mgCopy, extend, fo100i);
			/*
			 * 5) create language data
			 */
			List<LanguageMG> listC500mgs = ohhayWebBase.getListC500mg();//danh sach ngon ngu phai tao
			for(LanguageMG c500mg: listC500mgs){
				OhhayWebLanguageBase languageBase = (OhhayWebLanguageBase) templateService.findDocument(fo100i, WebTemplateRule.getWebLanguageClassFromExtend(extend),
																										new QbCriteria(QbMongoFiledsName.LANGUAGEID,
																										ApplicationHelper.convertToMD5(ohhayWebBase.getId() + c500mg.getCode())));
				Map<String, PropertyValue> mapProperties = languageMGService.getProperties(languageBase);
				PropertyValue propertyValue = mapProperties.get(pc900Elem);
				C110MG c110mg = new C110MG(c900mgCopy.getPc900(), propertyValue.getValue(), 
						   propertyValue.getCv113(), propertyValue.getCn114(),
						   propertyValue.getCv115(), propertyValue.getCv116(), propertyValue.getCv117(),
						   c920mgCopy.getFc920(), c900mgCopy.getFc850());
				languageMGService.pushOneLanguage(fo100i, ohhayWebBase.getId(), c500mg.getCode(), c110mg, WebTemplateRule.getWebClassFromExtend(extend));
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public <T> int deleteElem(int fo100, int webId, int part, int elem, int extend) {
		try {
			int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100; 
			OhhayWebBase ohhayWebBase = (OhhayWebBase) templateService.findDocument(fo100i, WebTemplateRule.getWebClassFromExtend(extend), 
																					new QbCriteria(QbMongoFiledsName.FO100, fo100i), new QbCriteria(QbMongoFiledsName.ID,webId));
			C920MG c920mgDelete = null;
			String pc900mgDelete = null;
			/*
			 * 1) find part need to change
			 */
			try{
				c920mgDelete = ohhayWebBase.getListC920mg().get(part);
			}catch(Exception ex){
				log.error("---error box index when copy elem");
				return 0;
			}
			/*
			 * 2) find c900mg delete
			 */
			if(c920mgDelete != null)
			{
				try{
					pc900mgDelete = c920mgDelete.getListC900mg().get(elem).getPc900();
					c920mgDelete.getListC900mg().remove(elem);
				}
				catch(Exception ex){
					log.error("---error elem index when copy elem");
					return 0;
				}
			}
			/*
			 * 3) pull current box
			 */
			c920mgDao.stornoTabC920Only(fo100i, webId, c920mgDelete.getFc920(), extend);
			/*
			 * 4) re-push current box
			 */
			c920mgDao.pushBoxToWeb(fo100, webId, c920mgDelete, extend, fo100i);
			/*
			 * 5) pull language data
			 */
			List<LanguageMG> listC500mgs = ohhayWebBase.getListC500mg();
			for(LanguageMG c500mg: listC500mgs)
				languageMGService.stornoC110Data(fo100i, ApplicationHelper.convertToMD5(webId + c500mg.getCode()), pc900mgDelete, extend);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	/**
	 * @param ohhayWebBase
	 * @return
	 */
	private int getMaxFc920OfWeb(OhhayWebBase ohhayWebBase){
		int max = 0;
		for(C920MG c920mg: ohhayWebBase.getListC920mg())
			if(c920mg.getFc920() > max)
				max = c920mg.getFc920();
		return max;
	}
	/*
	 * gon function lại với copyBox
	 * (non-Javadoc)
	 * @see com.ohhay.web.core.mongo.service.C920MGService#copyBoxByFC850(int, int, int, int)
	 */
	@Override
	public <T> int copyBoxByFC820(int fo100, int webId, int fc820, int extend) {
		try {
			int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100; 
			String appendId = String.valueOf(new Date().getTime());//du lieu dung de tap id moi 
			C920MG c920mgNew = null;
			
			boolean breakBoxPoint = false;//bat len khi tim thay box can copy
			/*
			 * 1) tim box can copy
			 */
			OhhayWebBase ohhayWebBase = (OhhayWebBase) templateService.findDocument(fo100i, WebTemplateRule.getWebClassFromExtend(extend), 
																					new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.ID,webId));
			int currentMaxFc920 = getMaxFc920OfWeb(ohhayWebBase);//fc920 lon nhat cua web hien tai
			//sort lai list c920 theo cn924 de cap nhat cn924 cho box can copy
			Collections.sort(ohhayWebBase.getListC920mg());
			LanguageService languageMGService = (LanguageService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
			List<LanguageMG> listC500mgs = ohhayWebBase.getListC500mg();//danh sach ngon ngu phai tao
			if (ohhayWebBase != null) {
				for(C920MG c920mg: ohhayWebBase.getListC920mg()){
					/*
					 * 2) cap nhat lai cn924 cho cac box co cn924 > cn924 cua box can copy
					 */
					if(breakBoxPoint)
						c920mgDao.updateBoxCn924(fo100i, webId, c920mg.getFc920(), c920mg.getCn924() + 1, extend);
					/*
					 * 3) tao box moi cho box can copy
					 */
					if (c920mg.getFc820() == fc820) {
						breakBoxPoint = true;
						c920mgNew = new C920MG(c920mg);
						/*
						 * 3.1) set lai cac du lieu moi: id, cn924, listC900mg
						 */
						c920mgNew.setVisible(0);//copy box nay luon hien
						c920mgNew.setOriginal(-1);
						c920mgNew.setCn924(c920mgNew.getCn924() + 1);//set new index
						c920mgNew.setFc920(currentMaxFc920 + 1);//set new id
						//tao du lieu ngon ngu
						for (int i = 0; i< listC500mgs.size(); i++) {
							LanguageMG c500mg = listC500mgs.get(i);
							/*
							 * 3.2) find map language properties of current web language
							 */
							OhhayWebLanguageBase languageBase = (OhhayWebLanguageBase) templateService.findDocument(fo100i, 
												WebTemplateRule.getWebLanguageClassFromExtend(extend),
												new QbCriteria(QbMongoFiledsName.LANGUAGEID,ApplicationHelper.convertToMD5(ohhayWebBase.getId() + c500mg.getCode())));
							Map<String, PropertyValue> mapProperties = languageMGService.getProperties(languageBase);
							if(mapProperties != null)
							{
								/*
								 * 3.3) create c110 new from old c110
								 */
								for(C900MG c900mg: c920mgNew.getListC900mg())
								{
									log.info("---push language:"+c900mg.getPc900());
									PropertyValue propertyValue = mapProperties.get(c900mg.getPc900());
									C110MG c110mg = new C110MG(c900mg.getPc900() + appendId, propertyValue.getValue(), 
															   propertyValue.getCv113(), propertyValue.getCn114(),
															   propertyValue.getCv115(), propertyValue.getCv116(), propertyValue.getCv117(), 
															   c920mgNew.getFc920(), c900mg.getFc850());
									languageMGService.pushOneLanguage(fo100i, ohhayWebBase.getId(), c500mg.getCode(), c110mg, WebTemplateRule.getWebClassFromExtend(extend));
									//tao moi id cho c900
									if(i == 0)
										c900mg.setPc900(c900mg.getPc900() + appendId);
								}
							}
						}							
					}
				}
				/*
				 * 4) save to db
				 */
				c920mgDao.pushBoxToWeb(fo100, webId, c920mgNew, extend, fo100i);
				log.info("---c920mgNew:"+c920mgNew);
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
}
