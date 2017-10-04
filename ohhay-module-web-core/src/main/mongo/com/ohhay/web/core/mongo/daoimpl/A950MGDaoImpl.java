package com.ohhay.web.core.mongo.daoimpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.web.core.entities.mongo.web.A950MG;
import com.ohhay.web.core.mongo.dao.A950MGDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_A950MG)
@Scope("prototype")
public class A950MGDaoImpl extends QbMongoDaoSupport implements A950MGDao {

	@Override
	public int incCategory(int id) {
		// TODO Auto-generated method stub
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
			Update update = new Update();
			// tang so luong len 1 dv
			update.inc("AN105", 1);
			return getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,0).upsert(query, update, A950MG.class).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int decCategory(int id) {
		// TODO Auto-generated method stub
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
			Update update = new Update();
			// tang so luong len 1 dv
			update.inc("AN105", -1);
			return getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,0).upsert(query, update, A950MG.class).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<A950MG> loadListA950(String type) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where("AA906").in(type));
			return getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,0).find(query2,A950MG.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
