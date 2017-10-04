package com.ohhay.web.core.api.daoimpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.RequestParamRule;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.api.dao.WebChildDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_WEBCHILD)
@Scope("prototype")
public class WebChildDaoImpl extends QbMongoDaoSupport implements
		WebChildDao {
	@Override
	public <T> T findOneWebChild(Class<T> mClass, int id, int fo100) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			T t = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).findOne(query2, mClass);
			//co the tim theo Cn806 (index trang con)
			if(t == null){
				Query query = new Query();
				query.addCriteria(Criteria.where(QbMongoFiledsName.CN806).is(id));
				query.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
				query.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
				t = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).findOne(query, mClass);
			}
			return t;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	// moi load theo parent id 
	@Override
	public <T> List<T> getListLitteWeb(Class<T> mClass, int parentId, String extendType, int visible, int fo100) {
		// TODO Auto-generated method stub
		try {
			if (extendType != null) {
				switch (extendType) {
				case RequestParamRule.PARAM_VALUE_EXTEND_WEBINARIS_ROOM: {
					Query query2 = new Query();
					query2.addCriteria(Criteria.where(QbMongoFiledsName.CN806).exists(true));
					query2.addCriteria(Criteria.where(QbMongoFiledsName.PARID).is(parentId));
					query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
					List<T> list = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).find(query2, mClass);
					return list;
				}
				case RequestParamRule.PARAM_VALUE_EXTEND_WEBINARIS: {
					Query query2 = new Query();
					query2.addCriteria(Criteria.where(QbMongoFiledsName.PARID).is(parentId));
					query2.addCriteria(Criteria.where(QbMongoFiledsName.CN806).exists(false));
					query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
					List<T> list = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).find(query2, mClass);
					return list;
				}
				default:
					return null;
				}
			}
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.PARID).is(parentId));
			if(visible == 0 || visible == -1)
				query2.addCriteria(Criteria.where(QbMongoFiledsName.VISIBLE).is(visible));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			List<T> list = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).find(query2, mClass);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
