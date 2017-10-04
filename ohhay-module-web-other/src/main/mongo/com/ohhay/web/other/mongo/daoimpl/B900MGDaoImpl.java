/*package com.ohhay.web.other.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.entities.mongo.web.B400MG;
import com.ohhay.web.other.mongo.dao.B900MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_B900MG)
@Scope("prototype")
public class B900MGDaoImpl extends QbMongoDaoSupport implements B900MGDao {

	@Override
	public B400MG findB400One(String phoneNumber) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.HV101).is(phoneNumber));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			//must add fo100
			B400MG b400MG = getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY,0).findOne(query2, B400MG.class);
			return b400MG;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateB400(B400MG b400mg) {
		// TODO Auto-generated method stub
		try {
			//must add fo100
			getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY,0).save(b400mg);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
*/