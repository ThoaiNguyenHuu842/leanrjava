package com.ohhay.web.core.api.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationRightInfo;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.dao.LanguageDao;
import com.ohhay.web.core.api.service.LanguageService;
import com.ohhay.web.core.api.service.WebChildService;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.web.T400MG;
import com.ohhay.web.core.entities.mongo.webbase.C110MG;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.entities.mongo.webbase.C920MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.weblanguage.C100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.T100MG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.C100CMG;
import com.ohhay.web.core.load.util.PropertyValue;
import com.ohhay.web.core.utils.WebTemplateRule;

@Service(value = SpringBeanNames.SERVICE_NAME_LANGUAGE)
@Scope("prototype")
public class LanguageServiceImpl implements LanguageService {
	private static Logger log = Logger.getLogger(LanguageServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_LANGUAGE)
	LanguageDao dao;
	@Autowired
	TemplateService templateService;
	/*
	 * tao properties C110MG cho element C900
	 */
	public C110MG createC110ForElement(C900MG c900mg, int fc920, int fc850) {
		C110MG c110mg = null;
		if (c900mg != null && c900mg.getCv902() != null) {
			// data text type
			if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_TEXT)) {
				c110mg = new C110MG();
				c110mg.setCv111(c900mg.getPc900());
				c110mg.setCv112(c900mg.getCv905());
				c110mg.setFc920(fc920);
				c110mg.setFc850(fc850);
			}
			else
			// data link type
			if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_LINK)) {
				c110mg = new C110MG();
				c110mg.setCv111(c900mg.getPc900());
				c110mg.setCv112(c900mg.getCv905());
				c110mg.setFc920(fc920);
				c110mg.setFc850(fc850);
			}
			else
			// group
			if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_GROUP)) {
				c110mg = new C110MG();
				c110mg.setCv111(c900mg.getPc900());
				c110mg.setCv112(c900mg.getCv906());
				c110mg.setFc920(fc920);
				c110mg.setFc850(fc850);

			}
			// sub item
			else if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_GALLERY_ITEM)) {
				c110mg = new C110MG();
				c110mg.setCv111(c900mg.getPc900());
				c110mg.setCv112(c900mg.getCv906());
				c110mg.setFc920(fc920);
				c110mg.setFc850(fc850);
			}
			// bg color
			else if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_BG_COLOR)) {
				c110mg = new C110MG();
				c110mg.setCv111(c900mg.getPc900());
				c110mg.setCv112(c900mg.getCv905());
				c110mg.setFc920(fc920);
				c110mg.setFc850(fc850);
			}
			else
			// data image type
			if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_IMAGE)) {
				c110mg = new C110MG();
				c110mg.setCv111(c900mg.getPc900());
				c110mg.setCv112(c900mg.getCv905());
				c110mg.setFc920(fc920);
				c110mg.setFc850(fc850);
			}
		}
		return c110mg;
	}

	/*
	 * tao ngon ngu cho web type collectionName co webID = webid, danh sach box
	 * la listC920mgs (dùng cho lần đầu tạo tk)
	 */
	@Override
	public int createLanguage(int webId, int fo100, List<C920MG> listC920mgs, String languageCode, String languageName, String colectionName) {
		// TODO Auto-generated method stub
		try {
			int fo100i = fo100;//fo100 use to know insert DB center or user DB
			if(colectionName.equals(QbMongoCollectionsName.A900) || colectionName.equals(QbMongoCollectionsName.A500))
				fo100i = 0;
			/*
			 * 1) tao du lieu ngon ngu C500 cho web
			 */
			createLanguageC450(fo100i, webId, colectionName, languageCode, languageName);
			/*
			 * 2) create list C110MG
			 */
			TemplateService mgService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			List<C110MG> listC110mgs = new ArrayList<C110MG>();
			for (C920MG c920mg : listC920mgs) {
				if(c920mg.getListC900mg() != null){
					for (C900MG c900mg : c920mg.getListC900mg()) {
						C110MG c110mg = createC110ForElement(c900mg, c920mg.getFc920(), c900mg.getFc850());
						if (c110mg != null)
							listC110mgs.add(c110mg);
					}
				}
			}
			/*
			 * 3) create c100
			 */
			C100MG c100mg = new C100MG();
			c100mg.setWebID(webId);
			c100mg.setFo100(fo100);
			c100mg.setLanguageID(ApplicationHelper.convertToMD5(webId+ languageCode));
			c100mg.setCv101(languageCode);
			c100mg.setLiC110mgs(listC110mgs);
			mgService.saveDocument(fo100i, c100mg, WebTemplateRule.getWebLanguageCollection(colectionName));
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.dao.LanguageMGDao#createLanguageC500(int,
	 * java.lang.String, java.lang.String, java.lang.String) tao ngon ngu trong
	 * C500 cua web
	 */
	@Override
	public int createLanguageC450(int fo100, int webId, String colectionName, String languageCode, String languageName) {
		// TODO Auto-generated method stub
		return dao.createLanguageC450(fo100, webId, colectionName, languageCode, languageName);
	}

	/*
	 * tao ngon ngu cho subWWeb cua web co FO100 = fo100
	 */
	private int createLanguageForSubWeb(int fo100, String languageCode, String languageName, String colectionName) {
		try {
			TemplateService mgService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			switch (colectionName) {
			case QbMongoCollectionsName.C900: {
				List<C500MG> listL400mgs = new ArrayList<C500MG>();
				listL400mgs = mgService.findDocuments(fo100,C500MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				if (listL400mgs != null) {
					for (C500MG l400mg : listL400mgs) {
						log.info("--creating language C500 id:"
								+ l400mg.getId());
						cloneLanguage(l400mg.getId(), fo100, languageCode, languageName, QbMongoCollectionsName.C500);
					}
				}
			}
				break;

			default:
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.dao.LanguageMGDao#cloneLanguage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String) clone ra ngon ngu
	 * khac tu ngon ngu hien tai, dung cho khi add ngon ngu
	 */
	@Override
	public int cloneLanguage(int webId, int fo100, String languageCode, String languageName, String colectionName) {
		// TODO Auto-generated method stub
		try {
			TemplateService mgService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);

			int kq = 0;
			switch (colectionName) {
			/*
			 * tao ngon ngu cho home page
			 */
			case QbMongoCollectionsName.C900: {
				C100MG c100mg = null;
				C400MG c400mg = mgService.findDocumentById(fo100, webId, C400MG.class);
				if (c400mg != null) {
					List<LanguageMG> listC500mgs = c400mg.getListC500mg();
					if (listC500mgs != null && listC500mgs.size() > 0) {
						// find first C100
						String languageCodeCr = c400mg.getListC500mg().get(0)
								.getCode();
						C100MG c100mgcr = mgService
								.findDocument(fo100, C100MG.class, new QbCriteria(
										QbMongoFiledsName.LANGUAGEID,
										ApplicationHelper.convertToMD5(c400mg
												.getId() + languageCodeCr)));
						// create new c100 from first c100
						c100mg = new C100MG(c100mgcr);
						c100mg.setCv101(languageCode);
						c100mg.setLanguageID(ApplicationHelper
								.convertToMD5(c400mg.getId() + languageCode));
						kq = mgService.saveDocument(fo100,c100mg, QbMongoCollectionsName.C100);
						if (kq > 0) {
							/*
							 * create c450 language
							 */
							createLanguageC450(fo100, webId, colectionName, languageCode, languageName);
							/*
							 * create language for sub-webs page
							 */
							createLanguageForSubWeb(c400mg.getFo100(), languageCode, languageName, colectionName);
						}
					}
				}
			}
				break;
			/*
			 * tao ngon ngu cho lading page
			 */
			case QbMongoCollectionsName.L900: {

			}
				break;
			/*
			 * tao ngon ngu cho child home page
			 */
			case QbMongoCollectionsName.C500: {
				C500MG c500mg = mgService.findDocumentById(fo100,webId, C500MG.class);
				C100CMG c100cmgNew = null;
				if (c500mg != null) {
					List<LanguageMG> listC500mgs = c500mg.getListC500mg();
					if (listC500mgs != null && listC500mgs.size() > 0) {
						// find first C100
						String languageCodeCr = c500mg.getListC500mg().get(0)
								.getCode();
						C100CMG c100cmg = mgService
								.findDocument(fo100, C100CMG.class, new QbCriteria(
										QbMongoFiledsName.LANGUAGEID,
										ApplicationHelper.convertToMD5(c500mg
												.getId() + languageCodeCr)));
						// create new c100 from first c100
						c100cmgNew = new C100CMG(c100cmg);
						c100cmgNew.setCv101(languageCode);
						c100cmgNew.setLanguageID(ApplicationHelper
								.convertToMD5(c500mg.getId() + languageCode));
						kq = mgService
								.saveDocument(fo100,c100cmgNew, QbMongoCollectionsName.C100C);
						// create c500
						if (kq > 0)
							createLanguageC450(fo100, webId, colectionName, languageCode, languageName);
					}
				}
			}
				break;
			/*
			 * tao ngon ngu cho test web
			 */
			case QbMongoCollectionsName.T900: {
				T400MG t400mg = mgService.findDocumentById(fo100,webId, T400MG.class);
				T100MG t100mg = null;
				if (t400mg != null) {
					List<LanguageMG> listC500mgs = t400mg.getListC500mg();
					if (listC500mgs != null && listC500mgs.size() > 0) {
						// find first C100
						String languageCodeCr = t400mg.getListC500mg().get(0)
								.getCode();
						T100MG t100mgcr = mgService
								.findDocument(fo100, T100MG.class, new QbCriteria(
										QbMongoFiledsName.LANGUAGEID,
										ApplicationHelper.convertToMD5(t400mg
												.getId() + languageCodeCr)));
						// create new c100 from first c100
						t100mg = new T100MG(t100mgcr);
						t100mg.setCv101(languageCode);
						t100mg.setLanguageID(ApplicationHelper
								.convertToMD5(t400mg.getId() + languageCode));
						kq = mgService
								.saveDocument(fo100,t100mg, QbMongoCollectionsName.T100);
						// create c500
						if (kq > 0)
							createLanguageC450(fo100, webId, colectionName, languageCode, languageName);
					}
				}
			}
				break;
			default:
				break;
			}
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.dao.LanguageMGDao#deleteLanguage(int, java.lang.String,
	 * java.lang.String) xoa ngon ngu C100,C500 trong webid
	 */
	@Override
	public int deleteLanguage(int fo100, int webId, String colectionName, String languageCode) {
		// TODO Auto-generated method stub
		try {
			log.info("---deleteLanguage:webid" + webId
					+ ", collection name:" + colectionName + ", langcode:"
					+ languageCode);
			switch (colectionName) {
			case QbMongoCollectionsName.C900: {
				/*
				 * 1) delete language for home page
				 */
				// remove c100
				TemplateService templateMGService = (TemplateService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateMGService.removeDocuments(fo100, C100MG.class, new QbCriteria(
						QbMongoFiledsName.LANGUAGEID, ApplicationHelper
								.convertToMD5(webId + languageCode)));
				// remove C450
				deleteLanguageC450(fo100, webId, languageCode, colectionName);
				/*
				 * 2) delete language for child page
				 */
				WebChildService childMGService = (WebChildService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_WEBCHILD);
				List<C500MG> listC500mgs = childMGService
						.getListLitteWeb(C500MG.class, webId, null, 1, fo100);
				for (C500MG c500mg : listC500mgs) {
					// remove c100c
					TemplateService templateMGService2 = (TemplateService) ApplicationHelper
							.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
					templateMGService2
							.removeDocuments(fo100, C100CMG.class, new QbCriteria(
									QbMongoFiledsName.LANGUAGEID,
									ApplicationHelper.convertToMD5(c500mg
											.getId() + languageCode)));
					// remove C450
					deleteLanguageC450(fo100, c500mg.getId(), languageCode, QbMongoCollectionsName.C500);
				}
			}
				break;

			default:
				break;
			}
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteLanguageC450(int fo100, int webId, String languageCode, String colectionName) {
		// TODO Auto-generated method stub
		return dao.deleteLanguageC450(fo100, webId, languageCode, colectionName);
	}

	@Override
	public <T> int pushAllLanguage(int webId, int fo100, C920MG c920mg, Class<T> mClass, C920MG c920mgOld, String appendID, String collectionName) {
		// TODO Auto-generated method stub
		try {

			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			/*
			 * 1) find current web languages
			 */
			OhhayWebBase ohhayWebBase = (OhhayWebBase) templateMGService
					.findDocumentById(fo100,webId, mClass);
			if (ohhayWebBase != null) {
				/*
				 * 2) find all language of this web
				 */
				List<LanguageMG> listC500mgs = ohhayWebBase.getListC500mg();
				if (listC500mgs != null && listC500mgs.size() > 0) {
					for (LanguageMG c500mg : listC500mgs) {
						/*
						 * 2) find map language properties of current web language
						 */
						OhhayWebLanguageBase languageBase = (OhhayWebLanguageBase) templateMGService.findDocument(fo100, 
											WebTemplateRule.getWebLanguageClassFromCollectionName(WebTemplateRule.getWebLanguageCollection(collectionName)),
											new QbCriteria(QbMongoFiledsName.LANGUAGEID,
														  ApplicationHelper.convertToMD5(ohhayWebBase.getId() + c500mg.getCode())));
						Map<String, PropertyValue> mapProperties = this.getProperties(languageBase);
						if(mapProperties != null)
						{
							/*
							 * 3) create c110 new from old c110
							 */
							for(C900MG c900mgOld: c920mgOld.getListC900mg())
							{
								log.info("---push language:"+c900mgOld.getPc900());
								PropertyValue propertyValue = mapProperties.get(c900mgOld.getPc900());
								C110MG c110mg = new C110MG(c900mgOld.getPc900() + appendID, propertyValue.getValue(), 
										   propertyValue.getCv113(), propertyValue.getCn114(),
										   propertyValue.getCv115(), propertyValue.getCv116(), propertyValue.getCv117(), 
										   c920mg.getFc920(), c900mgOld.getFc850());
								pushOneLanguage(fo100, ohhayWebBase.getId(), c500mg.getCode(), c110mg, mClass);
							}
						}
					}
				}
			}
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.dao.LanguageMGDao#pushOneLanguage(int, java.lang.String,
	 * com.ohhay.entities.mongo.C110MG) push language to C100 (chua lam cho
	 * L100, B100, W100)
	 */
	@Override
	public <T> int pushOneLanguage(int fo100, int webId, String languageCode, C110MG c110mg, Class<T> mClass) {
		// TODO Auto-generated method stub
		return dao.pushOneLanguage(fo100, webId, languageCode, c110mg, mClass);
	}

	@Override
	public Map<String, PropertyValue> getProperties(OhhayWebLanguageBase ohhayWebLanguageBase) {
		// TODO Auto-generated method stub
		Map<String, PropertyValue> mapProperties = new HashMap<String, PropertyValue>();
		if(ohhayWebLanguageBase != null){
			List<C110MG> listC110mgs = ohhayWebLanguageBase.getLiC110mgs();
			/*
			 * 2) create map properties
			 */
			if (listC110mgs != null) {
				for (int i = 0; i < listC110mgs.size(); i++) {
					C110MG c110mg = listC110mgs.get(i);
					if(c110mg != null){
						PropertyValue c110Value = new PropertyValue(i,c110mg.getCv112(),c110mg.getCv113(),c110mg.getCn114(),c110mg.getCv115(),c110mg.getCv116(),c110mg.getCv117());
						mapProperties.put(c110mg.getCv111(), c110Value);
					}
				}
			}
		}
		return mapProperties;
	}

	/*@Override
	public int addLanguage(LanguageMG c500mg, int fo100, AuthenticationRightInfo authenticationRightInfo, boolean onApp) {
		// TODO Auto-generated method stub
		try{
			int role = 0;
			if(onApp == true)
				role = ApplicationConstant.RE_VAILD_RIGHT;
			else
				role = checkRightAddLanguage(authenticationRightInfo, fo100);
			if(role == ApplicationConstant.RE_VAILD_RIGHT){
				int kq = 0;
				// insert language
				QbCriteria criteria = new QbCriteria("FO100", fo100);
				C400MG c400mg = templateService
						.findDocument(fo100, C400MG.class, criteria);
				// check language has create yet or no
				boolean hasCreate = false;
				for (LanguageMG c500mg2 : c400mg.getListC500mg()) {
					if (c500mg2.getCode().equals(c500mg.getCode()))
						hasCreate = true;
				}
				if (hasCreate == false)
					kq = cloneLanguage(c400mg.getId(), c400mg.getFo100(), c500mg
									.getCode(), c500mg.getText(), QbMongoCollectionsName.C900);
				else
					kq = -2;// has create
				return kq;
			}
			return role;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return -3;
		}
	}*/

	@Override
	public int removeLanguage(LanguageMG c500mg, int fo100) {
		// TODO Auto-generated method stub
		try{
			QbCriteria criteria = new QbCriteria(QbMongoFiledsName.FO100, fo100);
			C400MG c400mg = templateService
					.findDocument(fo100, C400MG.class, criteria);
			log.info("---deleteLanguage:" + c400mg.getId() + ","
					+ QbMongoCollectionsName.C900 + "," + c500mg.getCode());
			return deleteLanguage(fo100,c400mg.getId(), QbMongoCollectionsName.C900, c500mg
							.getCode());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return 0;
		}
	}
	
	/*@Override
	public int checkRightAddLanguage(AuthenticationRightInfo authenticationRightInfo, int fo100) {
		// TODO Auto-generated method stub
		log.info(authenticationRightInfo);
		if(authenticationRightInfo.isMUL_LAN_BAS() == false && authenticationRightInfo.isMUL_LAN_PRO() == false)
			return ApplicationConstant.RE_MUST_UPGRADE; //khong duoc quyen tao ngon ngu
		else if(authenticationRightInfo.isMUL_LAN_PRO() == true)
			return ApplicationConstant.RE_VAILD_RIGHT; //tao khong gioi han
		else {
			QbCriteria criteria = new QbCriteria(QbMongoFiledsName.FO100, fo100);
			C400MG c400mg = templateService.findDocument(fo100, C400MG.class, criteria);
			int totalLanguage = c400mg.getListC500mg().size();
			if(totalLanguage >= 4)
				return ApplicationConstant.RE_MUST_UPGRADE_TO_PRO;
			return ApplicationConstant.RE_VAILD_RIGHT;
		}
	}*/

	@Override
	public int stornoC110Data(int fo100, String languageID, String cv111, int extend) {
		// TODO Auto-generated method stub
		return dao.stornoC110Data(fo100, languageID, cv111, extend);
	}

	@Override
	public int addLanguage(LanguageMG c500, int fo100, AuthenticationRightInfo authenticationRightInfo, boolean appCall) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkRightAddLanguage(AuthenticationRightInfo authenticationRightInfo, int fo100) {
		// TODO Auto-generated method stub
		return 0;
	}
}
