package com.ohhay.web.core.mongo.daoimpl;

import org.apache.catalina.core.ApplicationPart;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.entities.mongo.webbase.C800MG;
import com.ohhay.web.core.mongo.dao.C800MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_C800MG)
@Scope("prototype")
public class C800MGDaoImpl extends QbMongoDaoSupport implements C800MGDao {

	@Override
	public String getCv802Template(int fc800) {
		try {
			// TODO Auto-generated method stub
			Query query2 = new Query();
			query2.fields().include(QbMongoFiledsName.CV802);
			query2.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(fc800));
			return getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,0).findOne(query2, C800MG.class).getCv802();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
