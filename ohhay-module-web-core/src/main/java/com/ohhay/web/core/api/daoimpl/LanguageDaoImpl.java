package com.ohhay.web.core.api.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.dao.LanguageDao;
import com.ohhay.web.core.entities.mongo.web.A400MG;
import com.ohhay.web.core.entities.mongo.web.B400MG;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.web.L400MG;
import com.ohhay.web.core.entities.mongo.web.T400MG;
import com.ohhay.web.core.entities.mongo.web.W400MG;
import com.ohhay.web.core.entities.mongo.webbase.C110MG;
import com.ohhay.web.core.entities.mongo.webchild.A500MG;
import com.ohhay.web.core.entities.mongo.webchild.B500MG;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.webchild.T500MG;
import com.ohhay.web.core.entities.mongo.webchild.W500MG;
import com.ohhay.web.core.entities.mongo.weblanguage.A100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.B100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.C100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.L100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.T100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.W100MG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.A100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.B100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.C100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.T100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.W100CMG;
import com.ohhay.web.core.utils.WebTemplateRule;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_LANGUAGE)
@Scope("prototype")
public class LanguageDaoImpl extends QbMongoDaoSupport implements LanguageDao {
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
		try {
			setFunctionName(QbMongoFunctionNames.CREATE_LANGUAGE);
			setParameterNumber(webId);
			setParameterString(colectionName);
			setParameterString(languageCode);
			setParameterString(languageName);
			//must add fo100
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY,fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.dao.LanguageMGDao#cloneLanguage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String) clone ra ngon ngu
	 * khac tu ngon ngu hien tai, dung cho khi add ngon ngu
	 */

	@Override
	public int deleteLanguageC450(int fo100, int webId, String languageCode, String colectionName) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.DELETE_LANGUAGE);
			setParameterNumber(webId);
			setParameterString(colectionName);
			setParameterString(languageCode);
			//must add fo100
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY,fo100).toString());
			return kq;
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
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(QbMongoFiledsName.LANGUAGEID)
					.is(ApplicationHelper.convertToMD5(webId + languageCode)));
			Update update = new Update();
			update.push(QbMongoFiledsName.PROPERTIES, c110mg);
			//must add fo100
			getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,fo100).updateFirst(query, update, WebTemplateRule.getWebLanguageClassFromCollectionClass(mClass));
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int stornoC110Data(int fo100, String languageID, String cv111, int extend) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.WEB_TOOLS_STORNOLANGUAGEDATA);
			setParameterNumber(fo100);
			setParameterString(languageID);
			setParameterString(cv111);
			setParameterString(WebTemplateRule.getWebLanguageCollection(WebTemplateRule.getWebMongoColectionFromExtend(extend)));
			//must add fo100
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY,fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
