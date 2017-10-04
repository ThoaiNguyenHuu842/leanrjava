package com.ohhay.web.other.api.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.LanguageService;
import com.ohhay.web.core.entities.B050Wbn;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.web.W400MG;
import com.ohhay.web.core.entities.mongo.webchild.W500MG;
import com.ohhay.web.core.entities.mongo.weblanguage.W100MG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.W100CMG;
import com.ohhay.web.other.api.dao.WebinarisDao;
import com.ohhay.web.other.api.service.WebinarisService;
import com.ohhay.web.other.mongo.service.W900MGService;
import com.ohhay.web.other.mysql.service.W400Service;
import com.ohhay.web.other.mysql.service.W500Service;
import com.ohhay.web.other.mysql.service.W920Service;

@Service(value = SpringBeanNames.SERVICE_NAME_WEBINARIS)
@Scope("prototype")
public class WebinarisServiceImpl implements WebinarisService {
	private static Logger log = Logger.getLogger(WebinarisServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_WEBINARIS)
	WebinarisDao b050WbnDao;
	@Autowired
	TemplateService templateService;

	@Override
	public List<B050Wbn> getListB050(String key) {
		return b050WbnDao.getListB050(key);
	}

	@Override
	public int confirmKey(String key) {
		return b050WbnDao.confirmKey(key);
	}

	@Override
	public int createWebinaris(int po100, String ov101, int fc800, String key) {
		// TODO Auto-generated method stub
		log.info("---key:" + key + ", po100:" + po100 + ",ov101:"
				+ ov101);
		try {
			/*
			 * 1) confirm key
			 */
			int kq = confirmKey(key);
			if (kq > 0) {
				/*
				 * 2) insert to mongo
				 */
				TemplateService templateService = (TemplateService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				LanguageService languageMGService = (LanguageService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
				W900MGService w900mgService = (W900MGService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_W900MG);
				/*
				 * ---- 2.1) tim sdt nay da co webinar chua
				 */
				W400MG w400mg = w900mgService.findWebinaris(po100);
				/*
				 * ---- 2.2) chua co thi tao moi
				 */
				if (w400mg == null) {
					W400Service w400Service = (W400Service) ApplicationHelper
							.findBean(SpringBeanNames.SERVICE_NAME_W400);
					log.info("--inserttabw400:" + 0 + ","
							+ "webinaris" + "," + "webinaris" + "," + key + ","
							+ null + "," + null + "," + po100 + "," + 11 + ","
							+ 0 + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
					int pw400 = w400Service
							.inserttabw400(0, "webinaris", "webinaris", key, null, null, po100, fc800, 0, ApplicationConstant.PVLOGIN_ANONYMOUS);
					if (pw400 > 0) {
						W920Service w920Service = (W920Service) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_W920);
						log.info("---chayGetelemTabW920:" + pw400
								+ "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
						String elemString = w920Service
								.chayGetelemTabW920(pw400, ApplicationConstant.PVLOGIN_ANONYMOUS);
						if (templateService.insertWebStructure(po100, "{" + elemString
								+ "}", QbMongoCollectionsName.W900) > 0) {
							kq = 1;// create new
							/*
							 * ---- 2.3) insert language
							 */
							W400MG w400mgNew = templateService
									.findDocumentById(po100, pw400, W400MG.class);
							languageMGService
									.createLanguage(w400mgNew.getId(), w400mgNew
											.getFo100(), w400mgNew
											.getListC920mg(), "vn", "Vietnamese", QbMongoCollectionsName.W900);
							/*
							 * ---- 2.4) insert all child page
							 */

							W500Service w500Service = (W500Service) ApplicationHelper
									.findBean(SpringBeanNames.SERVICE_NAME_W500);
							log.info("---listOfTabW500:" + po100
									+ "," + pw400 + "," + ov101);
							List<ComtabItem> listComtabItems = w500Service
									.listOfTabW500(po100, pw400, ov101);
							for (ComtabItem comtabItem : listComtabItems) {
								TemplateService templateMGService = (TemplateService) ApplicationHelper
										.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
								W920Service w920Service2 = (W920Service) ApplicationHelper
										.findBean(SpringBeanNames.SERVICE_NAME_W920);
								System.out
										.println("---chayGetelemTabW920Child:"
												+ comtabItem.getVal() + ","
												+ ov101);
								String elemStringChild = w920Service2
										.chayGetelemTabW920Child(comtabItem
												.getVal(), ov101);
								log.info("---html string:"
										+ elemStringChild);
								int kq2 = templateMGService
										.insertWebStructure(po100, "{"
												+ elemStringChild + "}", QbMongoCollectionsName.W500);
								if (kq2 > 0) {
									/*
									 * ---- 2.6) insert language as W100CMG
									 */
									W500MG w500mg = templateMGService
											.findDocumentById(po100, comtabItem
													.getVal(), W500MG.class);
									/*
									 * ---- 2.7 create new language
									 */
									/*
									 * find default language
									 */
									C400MG c400mg =templateMGService.findDocument(po100, C400MG.class, new QbCriteria(QbMongoFiledsName.FO100,po100));
									List<LanguageMG> listCrLanguages = c400mg.getListC500mg();
									
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
									languageMGService
											.createLanguage(w500mg.getId(), w500mg
													.getFo100(), w500mg
													.getListC920mg(),languageCode ,languageName, QbMongoCollectionsName.W500);
								}
							}

						}
						else
							kq = 0;
					}
					else
						kq = 0;
				}
				/*
				 * ---- 2.3) co roi thi update lai key
				 */
				else {
					// update lai key
					if (templateService.updateOneField(po100, W400MG.class, w400mg
							.getId(), "WV403", key, "WL948") > 0)
						kq = 2; // update
					else
						kq = 0;
				}
			}
			else
				kq = -1;// key khong hop le
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createWebinarRoom(int po100, String ov101, int fc800) {
		// TODO Auto-generated method stub
		try {
			int kq = 0;
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			LanguageService languageMGService = (LanguageService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
			W400Service w400Service = (W400Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_W400);
			log.info("--inserttabw400:" + 0 + "," + "webinaris room"
					+ "," + "webinaris room" + "," + null + "," + null + ","
					+ null + "," + po100 + "," + fc800 + "," + 0 + "," + ov101);
			/*
			 * 0) insert to my sql
			 */
			int pw400 = w400Service
					.inserttabw400(0, "webinar room", "webinar room", null, null, null, po100, fc800, 0, ov101);
			if (pw400 > 0) {
				/*
				 * 1) insert to mongo
				 */
				W920Service w920Service = (W920Service) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_W920);
				log.info("---chayGetelemTabW920:" + pw400 + ","
						+ ov101);
				String elemString = w920Service
						.chayGetelemTabW920(pw400, ov101);
				log.info("--html String:" + elemString);
				if (templateService
						.insertWebStructure(po100, "{" + elemString + "}", QbMongoCollectionsName.W900) > 0) {
					kq = 1;
					/*
					 * 2) insert language
					 */
					JSONObject jsonObject = new JSONObject("{" + elemString
							+ "}");
					int webId = Integer.parseInt(jsonObject.get("_id")
							.toString());
					W400MG w400mgNew = templateService
							.findDocumentById(po100, webId, W400MG.class);
					languageMGService
							.createLanguage(w400mgNew.getId(), w400mgNew
									.getFo100(), w400mgNew.getListC920mg(), "vn", "Vietnamese", QbMongoCollectionsName.W900);
					/*
					 * 3) insert all litte page
					 */

					W500Service w500Service = (W500Service) ApplicationHelper
							.findBean(SpringBeanNames.SERVICE_NAME_W500);
					/*
					 * ---- 3.1) lay danh sach trang con insert mongo
					 */
					log.info("---listOfTabW500:" + po100 + ","
							+ pw400 + "," + ov101);
					List<ComtabItem> listComtabItems = w500Service
							.listOfTabW500(po100, pw400, ov101);
					for (ComtabItem comtabItem : listComtabItems) {
						TemplateService templateMGService = (TemplateService) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						W920Service w920Service2 = (W920Service) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_W920);
						log.info("---chayGetelemTabW920Child:"
								+ comtabItem.getVal() + "," + ov101);
						String elemStringChild = w920Service2
								.chayGetelemTabW920Child(comtabItem.getVal(), ov101);
						log.info("---:" + elemStringChild);
						int kq2 = templateMGService.insertWebStructure(po100, "{"
								+ elemStringChild + "}", QbMongoCollectionsName.W500);
						if (kq2 > 0) {
							/*
							 * ---- 3.2) insert language W100CMG
							 */
							JSONObject jsonObject2 = new JSONObject("{"
									+ elemStringChild + "}");
							int webIdChild = Integer.parseInt(jsonObject2
									.get("_id").toString());
							W500MG w500mg = templateMGService
									.findDocumentById(po100, webIdChild, W500MG.class);
							languageMGService
									.createLanguage(w500mg.getId(), w500mg
											.getFo100(), w500mg.getListC920mg(), "vn", "Vietnamese", QbMongoCollectionsName.W500);
						}
					}

					/*
					 * 4) call webinaris web service
					 */
					W900MGService w900mgService = (W900MGService) ApplicationHelper
							.findBean(SpringBeanNames.SERVICE_NAME_W900MG);
					String webinarKey = w900mgService.findWebinaris(po100)
							.getWv403();
					log.info("---insertTabW400:" + pw400 + ","
							+ webinarKey);
					insertTabW400(pw400, webinarKey);
				}
			}
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertTabW400(int fw400, String webniarKey) {
		return b050WbnDao.insertTabW400(fw400, webniarKey);
	}

	@Override
	public int deleteWebinarRoom(int fw400, int po100) {
		// TODO Auto-generated method stub
		try {
			/*
			 * 1) remove W400, W100, W500, W100C
			 */
			templateService.removeDocuments(po100, W400MG.class, new QbCriteria("_id",
					fw400), new QbCriteria(QbMongoFiledsName.FO100, po100));
			templateService.removeDocuments(po100, W100MG.class, new QbCriteria(
					"WEBID", fw400), new QbCriteria(QbMongoFiledsName.FO100, po100));
			templateService.removeDocuments(po100, W500MG.class, new QbCriteria(
					"PARID", fw400), new QbCriteria(QbMongoFiledsName.FO100, po100));
			templateService.removeDocuments(po100, W100CMG.class, new QbCriteria(
					"WEBID", fw400), new QbCriteria(QbMongoFiledsName.FO100, po100));
			/*
			 * 2) call webinaris web service
			 */
			b050WbnDao.stornoTabW400(fw400);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
