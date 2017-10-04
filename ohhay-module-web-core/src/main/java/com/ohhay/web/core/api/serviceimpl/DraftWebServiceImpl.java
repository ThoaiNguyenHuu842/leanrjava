package com.ohhay.web.core.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.mongo.service.AdminMGService;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.DraftWebService;
import com.ohhay.web.core.api.service.LanguageService;
import com.ohhay.web.core.entities.mongo.web.A400MG;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.web.T400MG;
import com.ohhay.web.core.entities.mongo.webchild.A500MG;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.webchild.T500MG;
import com.ohhay.web.core.entities.mongo.weblanguage.A100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.C100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.T100MG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.A100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.C100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.T100CMG;
import com.ohhay.web.core.entities.mongo.webshort.C550MG;
import com.ohhay.web.core.entities.mongo.webshort.C950MG;
import com.ohhay.web.core.entities.mongo.webshort.T550MG;
import com.ohhay.web.core.entities.mongo.webshort.T950MG;
import com.ohhay.web.core.mysql.service.T400Service;
import com.ohhay.web.core.mysql.service.T500Service;
import com.ohhay.web.core.mysql.service.T920Service;

@Service(value = SpringBeanNames.SERVICE_NAME_DRAFTWEBSERVICE)
@Scope("prototype")
public class DraftWebServiceImpl implements DraftWebService {
	@Autowired
	TemplateService templateService;
	@Autowired
	AdminMGService adminMGService;
	@Autowired
	SequenceService sequenceService;
	private static Logger log = Logger.getLogger(DraftWebServiceImpl.class);
	@Override
	public int applyNewTemplate(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			//find test web page
			T400MG t400mg = templateMGService.findDocument(fo100,T400MG.class,new QbCriteria(QbMongoFiledsName.FO100,
					fo100));
			if(t400mg != null){
				T100MG t100mg = templateMGService.findDocument(fo100,T100MG.class, new QbCriteria(QbMongoFiledsName.WEBID, t400mg.getId()));
				SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
				int  fc400New = (int) sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.C900);
				//update to mongo
				if(fc400New > 0){
					/*
					 * 1) remove old C900, C100, C950
					 */
					templateMGService.removeDocuments(fo100,C400MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,C100MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,C950MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					/*
					 * 2) remove old C500, C100C, C550
					 */
					templateMGService.removeDocuments(fo100,C500MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,C100CMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,C550MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					/*
					 * 3) insert new C900, C100
					 */
					C400MG c400mg  = new C400MG(t400mg);
					c400mg.setId(fc400New);
					C100MG c100mg = new C100MG(t100mg);
					c100mg.setWebID(fc400New);
					c100mg.setLanguageID(ApplicationHelper.convertToMD5(fc400New+c100mg.getCv101()));
					templateMGService.saveDocument(fo100,c400mg, QbMongoCollectionsName.C900);
					templateMGService.saveDocument(fo100,c100mg, QbMongoCollectionsName.C100);
					/*
					 * 4) insert new C500, C100C
					 */
					List<T500MG> listT500mgs = templateMGService.findDocuments(fo100,T500MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					List<T100CMG> listT100cmgs = templateMGService.findDocuments(fo100,T100CMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					for(int i=0;i<listT500mgs.size();i++){
						T500MG t500mg = listT500mgs.get(i);
						T100CMG t100cmg = listT100cmgs.get(i);
						SequenceService sequenceService2 = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
						int  fc500New = (int) sequenceService2.getNextSequenceId(fo100, QbMongoCollectionsName.C500);
						//create new C500
						C500MG c500mg = new C500MG(t500mg);
						//set new id and parent id
						c500mg.setId(fc500New);
						c500mg.setWebParentId(fc400New);
						//insert to mongo
						templateMGService.saveDocument(fo100,c500mg, QbMongoCollectionsName.C500);
						//create new C100C
						C100CMG c100cmg = new C100CMG(t100cmg);
						c100cmg.setWebID(fc500New);
						c100cmg.setLanguageID(ApplicationHelper.convertToMD5(fc500New + c100cmg.getCv101()));
						templateMGService.saveDocument(fo100,c100cmg, QbMongoCollectionsName.C100C);
					}
					/*
					 * 5) remove T900, T100, T950
					 */
					templateMGService.removeDocuments(fo100,T400MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,T100MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,T950MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					/*
					 * 6) remove T500, T100C, T550
					 */
					templateMGService.removeDocuments(fo100,T500MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,T100CMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,T550MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					return 1;
				}
			}
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int changeTemplate(int fo100, int fc800, int fd000, String ov101) {
		// TODO Auto-generated method stub
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			LanguageService languageMGService= (LanguageService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
			T400Service t400Service = (T400Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T400);
			int kq = 1;
			try {
				/*
				 * 0) remove old draft
				 */
				templateMGService.removeDocuments(fo100,T400MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateMGService.removeDocuments(fo100,T100MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateMGService.removeDocuments(fo100,T500MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
				templateMGService.removeDocuments(fo100,T100CMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
				/*
				 * 1) create T900
				 */
				log.info("---DraftWebDaoImpl insertTabT400:"+0+","+null+","+null+","+null+","+null+","+null+","+fo100+","+fc800+","+fd000+","+ov101);
				int ft400 = t400Service.insertTabT400(0, null, null, null, null, null, fo100, fc800,fd000, ov101);
				if( ft400 > 0)
				{
					T920Service t920Service = (T920Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T920);
					log.info("---DraftWebDaoImpl getelemTabT920:"+ft400+","+ov101);
					String json = t920Service.getelemTabT920(ft400, ov101);
					log.info("---DraftWebDaoImpl html string:"+json);
					if(templateMGService.insertWebStructure(fo100, "{" + json + "}", QbMongoCollectionsName.T900) > 0)
					{
						/*
						 * 2) get web now
						 */
						C400MG c400mg =templateMGService.findDocument(fo100,C400MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
						/*
						 * 3) get list language now
						 */
						List<LanguageMG> listCrLanguages = c400mg.getListC500mg();
						//find default language
						String languageCode = null;
						String languageName = null;
						if(listCrLanguages != null)
						{
							LanguageMG  languageMG = listCrLanguages.get(0);
							if(languageMG !=null){
								languageCode = languageMG.getCode();
								languageName = languageMG.getText();
							}
						}
						/*
						 * 4) create one language for T900
						 */
						T400MG t400mg = templateMGService.findDocument(fo100, T400MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
						//create first language
						LanguageMG firstLaguage = listCrLanguages.get(0);
						languageMGService.createLanguage(t400mg.getId(), fo100, t400mg.getListC920mg(), firstLaguage.getCode(), firstLaguage.getText(), QbMongoCollectionsName.T900);
						/*
						 * 5) insert child page
						 */
						T500Service t500Service = (T500Service) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_T500);
						log.info("---DraftWebDaoImpl listOfTabT500:" + fo100
								+ "," + ft400 + "," + ov101);
						List<ComtabItem> listComtabItems = t500Service
								.listOfTabT500(fo100, ft400, ov101);
						for (ComtabItem comtabItem : listComtabItems) {
							TemplateService templateMGService2 = (TemplateService) ApplicationHelper
									.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
							T920Service t920Service2 = (T920Service) ApplicationHelper
									.findBean(SpringBeanNames.SERVICE_NAME_T920);
							log.info("---DraftWebDaoImpl chayGetelemTabT920Child:"+ comtabItem.getVal() + ","+ ov101);
							String elemStringChild = t920Service2.getelemTabT920Child(comtabItem.getVal(), ov101).replace("©", "copy");
							log.info("---DraftWebDaoImpl html t920 child:" + elemStringChild);
							int kq2 = templateMGService2.insertWebStructure(fo100, "{"+ elemStringChild + "}", QbMongoCollectionsName.T500);
							if (kq2 > 0) {
								/*
								 * ---- 2.6) insert language as T100CMG
								 */
								T500MG t500mg = templateMGService.findDocumentById(fo100,comtabItem.getVal(), T500MG.class);
								/*
								 * ---- 2.7 create new language
								 */
								languageMGService.createLanguage(t500mg.getId(), t500mg.getFo100(), t500mg.getListC920mg(), languageCode,languageName, QbMongoCollectionsName.T500);
							}
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				kq = 0;
			}
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}

	
	}

	@Override
	public int changeTemplateA900(int fo100, int fa900, String ov101) {
		// TODO Auto-generated method stub
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			int kq = 1;
			try {
				/*
				 * 0) remove old draft
				 */
				templateMGService.removeDocuments(fo100,T400MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateMGService.removeDocuments(fo100,T100MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
				templateMGService.removeDocuments(fo100,T500MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
				templateMGService.removeDocuments(fo100,T100CMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
				/*
				 * 1) find template data, new id for T900, first language of website
				 */
				SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
				int newId = (int) sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.T900);
				A400MG a400mg = templateMGService.findDocumentById(0, fa900, A400MG.class);
				A100MG a100mg = templateMGService.findDocument(0, A100MG.class, new QbCriteria("WEBID", fa900));
				C400MG c400mg = templateMGService.findDocument(fo100, C400MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
				List<LanguageMG> listCrLanguages = c400mg.getListC500mg();
				//find default language
				String languageCode = null;
				String languageName = null;
				if(listCrLanguages != null)
				{
					LanguageMG  languageMG = listCrLanguages.get(0);
					if(languageMG !=null){
						languageCode = languageMG.getCode();
						languageName = languageMG.getText();
					}
				}
				/*
				 * 2) create T900 data
				 */
				T400MG t400mg = new T400MG(a400mg);
				t400mg.setFa900(a400mg.getId());
				t400mg.setId(newId);
				t400mg.setFo100(fo100);
				t400mg.setCv808(a400mg.getAv403());
				List<LanguageMG> listLanguageMG = new ArrayList<LanguageMG>();
				LanguageMG languageMG = new LanguageMG();
				languageMG.setCode(languageCode);
				languageMG.setText(languageName);
				listLanguageMG.add(languageMG);
				t400mg.setListC500mg(listLanguageMG);
				/*
				 * 3) create language data
				 */
				T100MG t100mg = new T100MG(a100mg);
				t100mg.setFo100(fo100);
				t100mg.setWebID(newId);
				t100mg.setLanguageID(ApplicationHelper.convertToMD5(newId+languageCode));
				t100mg.setCv101(languageCode);
				templateMGService.saveDocument(fo100, t400mg, QbMongoCollectionsName.T900);
				templateMGService.saveDocument(fo100, t100mg, QbMongoCollectionsName.T100);
				/*
				 * 5) insert child page
				 */
				List<A500MG> listA500MG = templateMGService.findDocuments(0, A500MG.class, new QbCriteria(QbMongoFiledsName.PARID, fa900));
				for(A500MG a500mg: listA500MG){
					//web data
					T500MG t500mg = new T500MG(a500mg);
					int t500IdNew = (int) sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.T500);
					t500mg.setFa500(a500mg.getId());
					t500mg.setId(t500IdNew);
					t500mg.setFo100(fo100);
					t500mg.setHv101(ApplicationHelper.convertToMD5(ov101));
					t500mg.setListC500mg(listLanguageMG);
					//language data
					A100CMG a100cmg = templateMGService.findDocument(0, A100CMG.class, new QbCriteria(QbMongoFiledsName.WEBID, a500mg.getId()));
					T100CMG t100cmg = new T100CMG(a100cmg);
					t100cmg.setWebID(t500IdNew);
					t100cmg.setLanguageID(ApplicationHelper.convertToMD5(t500IdNew + languageCode));
					t100cmg.setFo100(fo100);
					t100cmg.setCv101(languageCode);
					templateMGService.saveDocument(fo100, t500mg, QbMongoCollectionsName.T500);
					templateMGService.saveDocument(fo100, t100cmg, QbMongoCollectionsName.T100C);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				kq = 0;
			}
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}

	
	}

	@Override
	public int applyNewTemplateA900(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			//find test web page
			T400MG t400mg = templateMGService.findDocument(fo100,T400MG.class,new QbCriteria(QbMongoFiledsName.FO100,
					fo100));
			if(t400mg != null){
				T100MG t100mg = templateMGService.findDocument(fo100, T100MG.class, new QbCriteria(QbMongoFiledsName.WEBID, t400mg.getId()));
				log.info("---t100 draft:"+t100mg);
				SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
				int fc400New = (int) sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.C900);
				//update to mongo
				if(fc400New > 0){
					/*
					 * 1) remove old C900, C100, C950
					 */
					templateMGService.removeDocuments(fo100,C400MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,C100MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,C950MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					/*
					 * 2) remove old C500, C100C, C550
					 */
					templateMGService.removeDocuments(fo100,C500MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,C100CMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(fo100,C550MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					/*
					 * 3) insert new C900, C100
					 */
					C400MG c400mg  = new C400MG(t400mg);
					c400mg.setId(fc400New);
					C100MG c100mg = new C100MG(t100mg);
					c100mg.setWebID(fc400New);
					c100mg.setLanguageID(ApplicationHelper.convertToMD5(fc400New+c100mg.getCv101()));
					log.info("---C100MG created:"+ c100mg);
					templateMGService.saveDocument(fo100,c400mg, QbMongoCollectionsName.C900);
					templateMGService.saveDocument(fo100,c100mg, QbMongoCollectionsName.C100);
					/*
					 * 4) insert new C500, C100C
					 */
					List<T500MG> listT500mgs = templateMGService.findDocuments(fo100,T500MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					List<T100CMG> listT100cmgs = templateMGService.findDocuments(fo100,T100CMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					for(T500MG t500mg: listT500mgs){
						T100CMG t100cmg = null;
						for(T100CMG t100cmg2: listT100cmgs)
							if(t100cmg2.getWebID() == t500mg.getId())
							{
								t100cmg = t100cmg2;
								break;
							}
						log.info("---t100c draft:"+t100cmg);
						if(t100cmg != null && t500mg != null){
							int fc500New = (int) sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.C500);
							//create new C500
							C500MG c500mg = new C500MG(t500mg);
							//set new id and parent id
							c500mg.setId(fc500New);
							c500mg.setWebParentId(fc400New);
							//insert to mongo
							templateMGService.saveDocument(fo100,c500mg, QbMongoCollectionsName.C500);
							//create new C100C
							C100CMG c100cmg = new C100CMG(t100cmg);
							log.info("---c100c created:"+c100cmg);
							c100cmg.setWebID(fc500New);
							c100cmg.setLanguageID(ApplicationHelper.convertToMD5(fc500New + c100cmg.getCv101()));
							templateMGService.saveDocument(fo100,c100cmg, QbMongoCollectionsName.C100C);
						}
					}
					/*
					 * 5) remove T900, T100, T950
					 */
					/*templateMGService.removeDocuments(T400MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(T100MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(T950MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));*/
					/*
					 * 6) remove T500, T100C, T550
					 */
					/*templateMGService.removeDocuments(T500MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(T100CMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));
					templateMGService.removeDocuments(T550MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100));*/
					return 1;
				}
			}
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int copyWebChild(int ft500, int fo100, String ov101) {
		// TODO Auto-generated method stub
		try{
			T500MG t500mg = templateService.findDocument(fo100, T500MG.class, new QbCriteria(QbMongoFiledsName.ID, ft500),new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if(t500mg != null){
				List<T100CMG> listC100cmgs = templateService.findDocuments(fo100,T100CMG.class, new QbCriteria(QbMongoFiledsName.WEBID, t500mg.getId()));
				int ft500New = (int) sequenceService.getNextSequenceId(fo100, "T500");
				log.info("--new ft500:"+ft500New);
				//copy current languages
				for(T100CMG c100cmg: listC100cmgs){
					T100CMG c100cmgNew = new T100CMG(c100cmg);
					String languageIDNew = ApplicationHelper.convertToMD5(ft500New + c100cmg.getCv101());
					c100cmgNew.setLanguageID(languageIDNew);
					c100cmgNew.setWebID(ft500New);
					templateService.saveDocument(fo100,c100cmgNew,QbMongoCollectionsName.T100C);
				}
				//copy current web page
				T500MG t500mgNew = new T500MG(t500mg);
				t500mgNew.setId(ft500New);
				t500mgNew.setOrginal(-1);
				int cn806New = adminMGService.getNewWebDraftChildCn806(fo100);
				t500mgNew.setCn806(cn806New);
				templateService.saveDocument(fo100, t500mgNew, QbMongoCollectionsName.T500);
				return 1;
			}
			return -1;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}




}
