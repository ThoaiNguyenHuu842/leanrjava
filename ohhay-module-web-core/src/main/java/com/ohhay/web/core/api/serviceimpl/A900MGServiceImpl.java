package com.ohhay.web.core.api.serviceimpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.TemplateChildsPublishCriteria;
import com.ohhay.core.criteria.TemplatePublishCriteria;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.filesutil.AWSFileUtils;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.AdminService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.core.api.dao.A900MGDao;
import com.ohhay.web.core.api.service.A900MGService;
import com.ohhay.web.core.api.service.LanguageService;
import com.ohhay.web.core.entities.mongo.web.A000MG;
import com.ohhay.web.core.entities.mongo.web.A400MG;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.webchild.A500MG;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.weblanguage.A100MG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.A100CMG;
import com.ohhay.web.core.mysql.service.C500Service;
import com.ohhay.web.core.mysql.service.C920Service;

/**
 * @author ThoaiNH  
 * create: 08/04/2015
 */
@Service(value = SpringBeanNames.SERVICE_NAME_A900MG)
@Scope("prototype")
public class A900MGServiceImpl implements A900MGService {
	private static Logger log = Logger.getLogger(A900MGServiceImpl.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	private A900MGDao a900MGDao;
	public int publishTemplate(TemplatePublishCriteria templatePublishCriteria) {
		int kq = 0;
		try {
			log.info("--upload template img:"
					+ templatePublishCriteria.getImgBase64());
			// upload screenshot web
			try {
				String imgContent = templatePublishCriteria.getImgBase64()
						.split("\\,")[1];
				byte[] btDataFile;
				btDataFile = new sun.misc.BASE64Decoder()
						.decodeBuffer(imgContent);
				AWSFileUtils awsFileUtils = new AWSFileUtils();
				awsFileUtils.uploadObjectMutilPartA900(templatePublishCriteria
						.getFa900(), "screenshot", btDataFile);
			} catch (Exception ex) {

			}
			// upload screenshot mobile
			try {
				String imgMBContent = templatePublishCriteria.getImgMobiBase64()
						.split("\\,")[1];
				byte[] btDataFileMobile;
				btDataFileMobile = new sun.misc.BASE64Decoder()
						.decodeBuffer(imgMBContent);
				AWSFileUtils awsFileUtils = new AWSFileUtils();
				awsFileUtils.uploadObjectMutilPartA900(templatePublishCriteria
						.getFa900(), "screenshotMobile", btDataFileMobile);
			} catch (Exception ex) {

			}
			templateService.updateOneField(0, A400MG.class, templatePublishCriteria
					.getFa900(), QbMongoFiledsName.AV403, templatePublishCriteria
							.getAv403(), QbMongoFiledsName.CL948);
			templateService.updateOneField(0, A400MG.class, templatePublishCriteria
					.getFa900(), QbMongoFiledsName.FA950, templatePublishCriteria
							.getFa950(), QbMongoFiledsName.CL948);
			templateService.updateOneField(0, A400MG.class, templatePublishCriteria
					.getFa900(), QbMongoFiledsName.AN402, 1, QbMongoFiledsName.CL948);
			kq = 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kq;

	}

	@Override
	public int createA900(int fo100, String ov101, int fc800) {
		// TODO Auto-generated method stub
		TemplateService templateMGService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		LanguageService languageMGService = (LanguageService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
		SequenceService sequenceService = (SequenceService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
		int kq = 1;
		try {
			/*
			 * 1) create A900
			 */
			log.info("---adminUpdateTabO100:"+ApplicationConstant.FO100_ADMIN_TEMPLATE+","+fc800+","+2+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
			AdminService adminService = (AdminService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_ADMIN);
			int fc400 = adminService.adminUpdateTabO100(ApplicationConstant.FO100_ADMIN_TEMPLATE, fc800, 2, ApplicationConstant.PVLOGIN_ANONYMOUS);
			if (fc400 > 0) {
				C920Service c920Service = (C920Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C920);
				String json = c920Service.chayGetelemTabC920("02903387368", ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * 2) delete old c900, create new c900
				 */
				templateMGService.removeDocuments(0, A000MG.class, new QbCriteria(QbMongoFiledsName.FO100, ApplicationConstant.FO100_ADMIN_TEMPLATE));
				log.info(json);
				templateMGService.insertWebStructure(0, "{" + json + "}", QbMongoCollectionsName.A000); 
				/*
				 * 3) copy a000 to a900
				 */
				int newId = (int) sequenceService.getNextSequenceId(0, "A900");
				A000MG a000mg = templateMGService.findDocument(0, A000MG.class, new QbCriteria(QbMongoFiledsName.FO100, ApplicationConstant.FO100_ADMIN_TEMPLATE));
				A400MG a400mg = new A400MG(a000mg);
				a400mg.setId(newId);
				a400mg.setFo100(fo100);
				a400mg.setCl946(new Date());
			    a400mg.setCl948(new Date());
				a400mg.setHv101(ApplicationHelper.convertToMD5(ov101));
				if (templateMGService.saveDocument(0, a400mg, QbMongoCollectionsName.A900) > 0)
					languageMGService.createLanguage(a400mg.getId(), fo100, a400mg.getListC920mg(), "en", "English", QbMongoCollectionsName.A900);
				/*
				 * 4) insert all child page
				 */
				C500Service c500Service = (C500Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C500);
				log.info("---listOfTabC500:" + fo100 + "," + a000mg.getId()+ "," + ov101);
				List<ComtabItem> listComtabItems = c500Service.listOfTabC500(ApplicationConstant.FO100_ADMIN_TEMPLATE, a000mg.getId(), ov101);
				for (ComtabItem comtabItem : listComtabItems) {
					C920Service c920Service2 = (C920Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C920);
					LanguageService languageMGService2 = (LanguageService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
					TemplateService templateMGService2 = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
					log.info("---chayGetelemTabC920Child:"+ comtabItem.getVal() + "," + ov101);
					String elemStringChild = c920Service2.chayGetelemTabC920Child(comtabItem.getVal(), ov101);
					log.info("---:" + elemStringChild);
					int kq2 = templateMGService2.insertWebStructure(0, "{"+ elemStringChild + "}", QbMongoCollectionsName.A500);
					if (kq2 > 0) {
						/*
						 * ---- 2.6) insert language  C100CMG
						 */
						A500MG a500mg = templateMGService2.findDocumentById(0, comtabItem.getVal(), A500MG.class);
						templateMGService.updateOneField(0, A500MG.class, comtabItem.getVal(), QbMongoFiledsName.FO100,fo100, null);
						templateMGService.updateOneField(0, A500MG.class, comtabItem.getVal(), QbMongoFiledsName.PARID,a400mg.getId(), null);
						templateMGService.updateOneField(0, A500MG.class, comtabItem.getVal(), QbMongoFiledsName.AN402,0, null);
						templateMGService.updateOneField(0, A500MG.class, comtabItem.getVal(), QbMongoFiledsName.FA950,0, null);
						templateMGService.updateOneField(0, A500MG.class, comtabItem.getVal(), QbMongoFiledsName.CL946,new Date(), null);
						templateMGService.updateOneField(0, A500MG.class, comtabItem.getVal(), QbMongoFiledsName.CL948,new Date(), null);
						templateMGService.updateOneField(0, A500MG.class, comtabItem.getVal(), QbMongoFiledsName.HV101,ApplicationHelper.convertToMD5(ov101), null);
						languageMGService2.createLanguage(a500mg.getId(), fo100, a500mg.getListC920mg(),"en", "English", QbMongoCollectionsName.A500);
					}
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
			kq = 0;
		}
		return kq;
	}

	@Override
	public int removeA900(int fa900) {
		// TODO Auto-generated method stub
		templateService.removeDocuments(0, A400MG.class, new QbCriteria(QbMongoFiledsName.ID, fa900));
		templateService.removeDocuments(0, A100MG.class, new QbCriteria(QbMongoFiledsName.WEBID, fa900));
		//remove web child
		List<A500MG> list = templateService.findDocuments(0, A500MG.class, new QbCriteria(QbMongoFiledsName.PARID, fa900));
		if(list != null){
			for(A500MG a500mg: list)
				templateService.removeDocuments(0, A100CMG.class,new QbCriteria(QbMongoFiledsName.WEBID, a500mg.getId()));
		}
		templateService.removeDocuments(0, A500MG.class, new QbCriteria(QbMongoFiledsName.PARID, fa900));
		return 1;
	}
	@Override
	public List<A400MG> getListTemplate(int pnFA950,int pnAN402,int offset,int limit) {
		// TODO Auto-generated method stub
		return a900MGDao.getListTemplate(pnFA950, pnAN402, offset, limit);
	}

	/*
	 * author : ThoaiVT
	 * create 1/9/2015
	 * publish Template Childs
	 */
	@Override
	public int publishTemplateChilds(TemplateChildsPublishCriteria templateChildsPublishCriteria) {
		int kq = 0;
		try {
			log.info("--upload template img:"
					+ templateChildsPublishCriteria.getImgBase64());
			// upload screenshot web
			try {
				String imgContent = templateChildsPublishCriteria.getImgBase64()
						.split("\\,")[1];
				byte[] btDataFile;
				btDataFile = new sun.misc.BASE64Decoder()
						.decodeBuffer(imgContent);
				AWSFileUtils awsFileUtils = new AWSFileUtils();
				awsFileUtils.uploadObjectMutilPartA500(templateChildsPublishCriteria
						.getFa500(), "screenshot", btDataFile);
			} catch (Exception ex) {

			}
			// upload screenshot mobile
			try {
				String imgMBContent = templateChildsPublishCriteria.getImgMobiBase64()
						.split("\\,")[1];
				byte[] btDataFileMobile;
				btDataFileMobile = new sun.misc.BASE64Decoder()
						.decodeBuffer(imgMBContent);
				AWSFileUtils awsFileUtils = new AWSFileUtils();
				awsFileUtils.uploadObjectMutilPartA500(templateChildsPublishCriteria
						.getFa500(), "screenshotMobile", btDataFileMobile);
			} catch (Exception ex) {

			}
			kq = 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}
}
