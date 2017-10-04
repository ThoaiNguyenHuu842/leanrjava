package com.ohhay.web.core.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.mongo.dao.C900MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_C900MG)
@Scope("prototype")
public class C900MGDaoImpl extends QbMongoDaoSupport implements C900MGDao {
	@Override
	public int deleteC400(int fo100) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
			getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,fo100).remove(query2, C400MG.class);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> int updateEleme(int id, int indexPart, int indexEleme, String fieldUpdate, Object value, Class<T> mClass, int fo100, int fo100i) {
		// TODO Auto-generated method stub
		try {
			if (value != null) {
				Query query = new Query();
				query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
				query.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
				Update update = new Update();
				update.set(QbMongoFiledsName.PART+"." + indexPart + "."+QbMongoFiledsName.ELEME+"." + indexEleme + "."+ fieldUpdate, value);
				getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,fo100i).updateFirst(query, update, mClass);
			}
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> int updateEleme100(String webId, String languageCode, int indexProperties, String fieldName, Object value, Class<T> mClass, int fo100, int fo100i) {
		// TODO Auto-generated method stub
		try {
			if (value != null) {
				Query query = new Query();
				query.addCriteria(Criteria.where(QbMongoFiledsName.LANGUAGEID).is(ApplicationHelper.convertToMD5(webId+languageCode)));
				query.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
				Update update = new Update();
				update.set(QbMongoFiledsName.PROPERTIES+"." + indexProperties + "."+fieldName, value);
				getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,fo100i).updateFirst(query, update, mClass);
			}
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> int updatePart(int id, int indexPart, String fieldUpdate, Object value, Class<T> mClass, int fo100, int fo100i) {
		// TODO Auto-generated method stub
		try {
			if (value != null) {
				Query query = new Query();
				query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
				query.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
				Update update = new Update();
				update.set(QbMongoFiledsName.PART+"." + indexPart + "."
						+ fieldUpdate, value);
				getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,fo100i).updateFirst(query, update, mClass);
			}
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	



}
