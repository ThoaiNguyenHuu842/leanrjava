package com.ohhay.web.core.api.serviceimpl;

import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.AdminMGService;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.AdminService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.dao.WebChildDao;
import com.ohhay.web.core.api.service.WebChildService;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.C100CMG;

@Service(value = SpringBeanNames.SERVICE_NAME_WEBCHILD)
@Scope("prototype")
public class WebChildServiceImpl implements WebChildService {
	private static Logger log = Logger.getLogger(WebChildServiceImpl.class);
	@Autowired
	SequenceService sequenceService;
	@Autowired
	WebChildDao webChildMGDao;
	@Autowired
	TemplateService templateService;
	@Autowired
	AdminMGService adminMGService;
	@Override
	public <T> T findOneWebChild(Class<T> mClass, int id, int fo100) {
		// TODO Auto-generated method stub
		return webChildMGDao.findOneWebChild(mClass, id, fo100);
	}

	@Override
	public <T> List<T> getListLitteWeb(Class<T> mClass, int parentId, String extendType, int visible, int fo100) {
		// TODO Auto-generated method stub
		return webChildMGDao.getListLitteWeb(mClass, parentId, extendType, visible, fo100);
	}

	@Override
	public int copyWebChild(int fc500, int fo100, String ov101) {
		// TODO Auto-generated method stub
		try{
			C500MG c500mg = templateService.findDocument(fo100, C500MG.class, new QbCriteria(QbMongoFiledsName.ID, fc500),
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if(c500mg != null){
				List<C100CMG> listC100cmgs = templateService.findDocuments(fo100, C100CMG.class, new QbCriteria(QbMongoFiledsName.WEBID, c500mg.getId()));
				int fc500New = (int) sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.C500);
				log.info("--new fc550:"+fc500New);
				//copy current languages
				for(C100CMG c100cmg: listC100cmgs){
					C100CMG c100cmgNew = new C100CMG(c100cmg);
					String languageIDNew = ApplicationHelper.convertToMD5(fc500New + c100cmg.getCv101());
					c100cmgNew.setLanguageID(languageIDNew);
					c100cmgNew.setWebID(fc500New);
					templateService.saveDocument(fo100, c100cmgNew,QbMongoCollectionsName.C100C);
				}
				//copy current web page
				C500MG c500mgNew = new C500MG(c500mg);
				c500mgNew.setId(fc500New);
				c500mgNew.setOrginal(-1);
				int cn806New = adminMGService.getNewWebChildCn806(fo100);
				c500mgNew.setCn806(cn806New);
				templateService.saveDocument(fo100, c500mgNew, QbMongoCollectionsName.C500);
				return 1;
			}
			return -1;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteWebChild(int fc500, int fo100, String ov101) {
		// TODO Auto-generated method stub
		try{
			templateService.removeDocuments(fo100, C100CMG.class, new QbCriteria(QbMongoFiledsName.WEBID,fc500));
			templateService.removeDocumentById(fo100, fc500, C500MG.class);
			return 1;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}

	

	

	
	
	
}
