package com.ohhay.web.core.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.webbase.C920MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.mongo.dao.C900MGDao;
import com.ohhay.web.core.mongo.dao.C920MGDao;
import com.ohhay.web.core.utils.WebTemplateRule;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_C920MG)
@Scope("prototype")
public  class C920MGDaoImpl extends QbMongoDaoSupport implements C920MGDao {

	@Override
	public int stornoTabC920(int fo100, int webID, int fc920, int extend) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.WEB_TOOLS_STORNOPART);
			setParameterNumber(fo100);
			setParameterNumber(webID);
			setParameterNumber(fc920);
			String collectionName = WebTemplateRule.getWebMongoColectionFromExtend(extend);
			setParameterString(collectionName);
			setParameterString(WebTemplateRule.getWebLanguageCollection(collectionName));
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> OhhayWebBase findPartbyFC920(int fo100, int webId, int fc920, int extend) {
		// TODO Auto-generated method stub
		Query query = Query.query(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
		query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(webId));
		query.fields().elemMatch(QbMongoFiledsName.PART, Criteria.where(QbMongoFiledsName.FC920).is(fc920));
		try {
			return (OhhayWebBase) getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).findOne(query, WebTemplateRule.getWebClassFromExtend(extend));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int pushBoxToWeb(int fo100, int webID, C920MG c920mg, int extend, int fo100i) {
		// TODO Auto-generated method stub
		Query query = Query.query(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
		query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(webID));
		try {
			return  getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100i).updateFirst(query, new Update().push(QbMongoFiledsName.PART,c920mg),WebTemplateRule.getWebClassFromExtend(extend)).getN();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateBoxCn924(int fo100, int webID, int fc920, int cn924, int extend) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.WEB_TOOLS_UPDATEPART_CN924);
			setParameterNumber(fo100);
			setParameterNumber(webID);
			setParameterNumber(fc920);
			setParameterNumber(cn924);
			String collectionName = WebTemplateRule.getWebMongoColectionFromExtend(extend);
			setParameterString(collectionName);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY,fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int stornoTabC920Only(int fo100, int webID, int fc920, int extend) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.WEB_TOOLS_STORNOPART_ONLY);
			setParameterNumber(fo100);
			setParameterNumber(webID);
			setParameterNumber(fc920);
			String collectionName = WebTemplateRule.getWebMongoColectionFromExtend(extend);
			setParameterString(collectionName);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY,fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
