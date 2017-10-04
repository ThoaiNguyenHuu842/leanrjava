package com.ohhay.web.core.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.LanguageService;
import com.ohhay.web.core.api.service.WebLandingService;
import com.ohhay.web.core.entities.mongo.web.A400MG;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.web.L400MG;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.weblanguage.A100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.L100MG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.C100CMG;
import com.ohhay.web.core.mysql.service.L400Service;
import com.ohhay.web.core.mysql.service.L920Service;

@Service(value = SpringBeanNames.SERVICE_NAME_WEBLANDING)
@Scope("prototype")
public class WebLandingServiceImpl implements WebLandingService {
	private static Logger log = Logger.getLogger(WebLandingServiceImpl.class);
	@Autowired
	SequenceService sequenceService;
	@Autowired
	private TemplateService templateMGService;
	@Autowired
	private LanguageService languageMGService;
	@Override
	public int createLandingPage(int fo100, String ov101, int fc800) {
		// TODO Auto-generated method stub
		try {
			L400Service l400Service = (L400Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_L400);
			int kq = 0;
			try {
				/*
				 * 1) create L900
				 */
				log.info("---insertTabL400:" + 0 + "," + null + ","
						+ null + "," + null + "," + null + "," + null + ","
						+ fo100 + "," + fc800 + "," + ov101);
				int fl400 = l400Service
						.inserttabL400(0, null, null, null, null, null, fo100, fc800, 0, 0, ov101);
				if (fl400 > 0) {
					L920Service l920Service = (L920Service) ApplicationHelper
							.findBean(SpringBeanNames.SERVICE_NAME_L920);
					String json = l920Service.getElemTabL920(fl400, ov101);
					log.info(json);
					if (templateMGService.insertWebStructure(fo100, "{" + json + "}", QbMongoCollectionsName.L900) > 0) {
						/*
						 * 2) get web now
						 */
						C400MG c400mg = templateMGService
								.findDocument(fo100, C400MG.class, new QbCriteria(
										QbMongoFiledsName.FO100, fo100));
						/*
						 * 3) get list language now
						 */
						List<LanguageMG> listCrLanguages = c400mg
								.getListC500mg();
						/*
						 * 4) create one language for L900
						 */
						L400MG l400mg = templateMGService
								.findDocumentById(fo100, fl400, L400MG.class);
						// create first language
						LanguageMG firstLaguage = listCrLanguages.get(0);
						languageMGService
								.createLanguage(l400mg.getId(), fo100, l400mg
										.getListC920mg(), firstLaguage
										.getCode(), firstLaguage.getText(), QbMongoCollectionsName.L900);
						kq = fl400;
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
	public int createLandingPageByA900(int fo100, String ov101, int templateId) {
		// TODO Auto-generated method stub
		try{
			/*
			 * 1) get web now
			 */
			C400MG c400mg = templateMGService.findDocument(fo100, C400MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			/*
			 * 2) get list language now
			 */
			List<LanguageMG> listCrLanguages = c400mg.getListC500mg();
			/*
			 * 3) create one language for L900
			 */
			LanguageMG firstLaguage = listCrLanguages.get(0);
			/*
			 * 4) find template
			 */
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			A400MG a400mg = templateService.findDocumentById(0, templateId, A400MG.class);
			A100MG a100mg = templateService.findDocument(0, A100MG.class, new QbCriteria("WEBID", templateId));
			/*
			 * 5) create new data
			 */
			SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
			int newId = (int) sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.L900);
			//create new
			L400MG l400mg = new L400MG(a400mg);
			l400mg.setCv805(ApplicationConstant.OHHAY_NORMAL_LANDING_CV805);
			l400mg.setFa900(a400mg.getId());
			l400mg.setId(newId);
			l400mg.setFo100(fo100);
			l400mg.setCv808(a400mg.getAv403());
			L100MG l100mg = new L100MG(a100mg);
			l100mg.setFo100(fo100);
			l100mg.setWebID(newId);
			l100mg.setLanguageID(ApplicationHelper.convertToMD5(newId+"en"));
			l100mg.setCv101("en");
			/*
			 * 6) save to mongo
			 */
			templateService.saveDocument(fo100, l400mg, QbMongoCollectionsName.L900);
			templateService.saveDocument(fo100, l100mg, QbMongoCollectionsName.L100);
			return 1;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<ComtabItem> loadListLandingTemplate(int fa950) {
		// TODO Auto-generated method stub
		List<A400MG> listA400s = templateMGService.findDocuments(0, A400MG.class,QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.AN402, 1),
																new QbCriteria(QbMongoFiledsName.FA950,fa950));
		List<ComtabItem> listComTabItems = new ArrayList<ComtabItem>();
		for(A400MG a400mg: listA400s){
			ComtabItem comtabItem = new ComtabItem();
			comtabItem.setA900Thumbnail(a400mg.getScreenShot());
			comtabItem.setVal(a400mg.getId());
			comtabItem.setLabel(a400mg.getAv403());
			listComTabItems.add(comtabItem);
		}
		return listComTabItems;
	}
	@Override
	public int copyWebLanding(int fl400, int fo100, String ov101) {
		// TODO Auto-generated method stub
		try{
			L400MG l400MG = templateMGService.findDocument(fo100, L400MG.class, new QbCriteria(QbMongoFiledsName.ID, fl400),
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if(l400MG != null){
				List<L100MG> listL100cmgs = templateMGService.findDocuments(fo100, L100MG.class, new QbCriteria(QbMongoFiledsName.WEBID, l400MG.getId()));
				int fl400New = (int) sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.L900);
				log.info("---new fl400New:"+fl400New);
				//copy current languages
				for(L100MG l100cmg: listL100cmgs){
					L100MG l100cmgNew = new L100MG(l100cmg);
					String languageIDNew = ApplicationHelper.convertToMD5(fl400New + l100cmg.getCv101());
					l100cmgNew.setLanguageID(languageIDNew);
					l100cmgNew.setWebID(fl400New);
					templateMGService.saveDocument(fo100, l100cmgNew,QbMongoCollectionsName.L100);
				}
				//copy current web page
				L400MG l400mgNew = new L400MG(l400MG);
				l400mgNew.setId(fl400New);
				templateMGService.saveDocument(fo100, l400mgNew, QbMongoCollectionsName.L900);
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
