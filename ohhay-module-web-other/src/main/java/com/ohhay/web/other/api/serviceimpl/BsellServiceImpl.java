/*package com.ohhay.web.other.api.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.LanguageService;
import com.ohhay.web.core.entities.V250;
import com.ohhay.web.core.entities.mongo.web.B400MG;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.other.api.dao.BsellDao;
import com.ohhay.web.other.api.service.BsellService;
import com.ohhay.web.other.mongo.service.B900MGService;
import com.ohhay.web.other.mysql.service.B400Service;
import com.ohhay.web.other.mysql.service.B920Service;
@Service(value = SpringBeanNames.SERVICE_NAME_BSELLSER)
@Scope("prototype")
public class BsellServiceImpl implements BsellService{
	private static Logger log = Logger.getLogger(BsellServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_BSELLDAO)
	BsellDao bsellDao;

	@Override
	public int checkTabV050Key(String key) {
		// TODO Auto-generated method stub
		return bsellDao.checkTabV050Key(key);
	}

	@Override
	public List<V250> listOfTabV250KEY(String key) {
		// TODO Auto-generated method stub
		return bsellDao.listOfTabV250KEY(key);
	}

	@Override
	public int createBsellpage(int po100, String ov101, String key) {
		// TODO Auto-generated method stub
		//tungns: kiem tra su ton tai cua bsell cho sdt nay
		int result = 0;// bang 1 thi dung, o thi sai
		B900MGService b900mgService = (B900MGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_B900MG);
		B400MG b400mg = b900mgService.findB400One(ApplicationHelper
				.convertToMD5(ov101));
		if(b400mg != null){
			//truong hop co roi, khoi tao moi
			log.info("co roi---");
		}else{
			//truong hop chua co thi tao moi
			B400Service b400Service = (B400Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_B400);
			LanguageService languageMGService = (LanguageService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
			int result_b400 = b400Service.inserttabb400(0,ov101 , "Bsell1", key, null, null, po100, 12, 0, ApplicationConstant.PVLOGIN_ANONYMOUS);
			if(result_b400 != 0){
				log.info("thanh cong----"+result_b400);
				//tungns: bat dau tao html trong mongo cho bsell
				B920Service b920Service = (B920Service) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_B920);
				String elemString = b920Service.chayGetelemTabB920(ov101, ApplicationConstant.PVLOGIN_ANONYMOUS);
				log.info("----------ELEMENT----------"+elemString);
				if(elemString != null){
					//tungns: insert html vao mongo
					log.info("co vao day khong----");
					TemplateService service = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
					if (service.insertWebStructure(po100, "{" + elemString + "}", QbMongoCollectionsName.B900) > 0){
						result = 1;// create new
						log.info("insert thanh cong B900 cho so dien thoai--"+ApplicationHelper
								.convertToMD5(ov101));
						//insert language
						TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						JSONObject jsonObject2;
						try {
							jsonObject2 = new JSONObject("{" + elemString + "}");
							int webId = Integer.parseInt(jsonObject2.get("_id").toString());
							B400MG b400mgNew = templateMGService.findDocumentById(po100, webId, B400MG.class);
							
							 * find default language
							 
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
							languageMGService.createLanguage(b400mgNew.getId(),b400mgNew.getFo100(), b400mgNew.getListC920mg(),languageCode,languageName,QbMongoCollectionsName.B900);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else{
					log.info("Khong co html de insert");
				}
				
			}else{
				log.info("that bai----"+result_b400);
			}
		}
		return result;
	}

	@Override
	public String printJavaScript() {
		// TODO Auto-generated method stub
		return bsellDao.printJavaScript();
	}

}
*/