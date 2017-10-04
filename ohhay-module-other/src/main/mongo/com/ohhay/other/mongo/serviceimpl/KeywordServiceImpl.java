package com.ohhay.other.mongo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.KeyWord;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.other.mongo.service.KeywordService;
@Service(value = SpringBeanNames.SERVICE_NAME_KEYWORD)
@Scope("prototype")
public class KeywordServiceImpl implements KeywordService {
	@Autowired
	TemplateService templateService;
	@Override
	public int addNewKeyWord(String keyword, int fo100) {
		// TODO Auto-generated method stub
		try {
			int kq = 0;
			M900MG m900mg = null;
			if(fo100 == 0)
			{
				Q100 q100 = AuthenticationHelper.getUserLogin();
				if (q100 != null) 
					m900mg = q100.getM900mg();
			}
			else
				m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
			// check key word is not available
			if (m900mg.getListKeyWord() != null) {
				for (KeyWord keyWord2 : m900mg.getListKeyWord()) {
					if (keyword.equals(keyWord2.getKeyword())) {
						kq = -1;
						break;
					}
				}
			}
			// insert if keyword is not available
			if (kq != -1) {
				m900mg.getListKeyWord().add(new KeyWord(keyword));
				// re-index this profile
				m900mg.setMn909(1);
				kq = templateService.saveDocument(fo100, m900mg, QbMongoCollectionsName.M900);
			}
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeKeyWord(String keyword, int fo100) {
		// TODO Auto-generated method stub
		try{
			int kq = 0;
			M900MG m900mg = null;
			if(fo100 == 0)
			{
				Q100 q100 = AuthenticationHelper
						.getUserLogin();
				if (q100 != null) 
						m900mg = q100.getM900mg();
			}
			else
				m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
			for (int i = 0; i < m900mg.getListKeyWord().size(); i++) {
				KeyWord keyWord2 = m900mg.getListKeyWord().get(i);
				if (keyword.equals(keyWord2.getKeyword()))
					m900mg.getListKeyWord().remove(i);
			}
			// re-index this profile
			m900mg.setMn909(1);
			kq = templateService.saveDocument(fo100, m900mg, QbMongoCollectionsName.M900);
			return kq;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}

}
