package com.ohhay.web.other.mongo.daoimpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.entities.mongo.web.W400MG;
import com.ohhay.web.other.mongo.dao.W900MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_W900MG)
@Scope("prototype")
public class W900MGDaoImpl extends QbMongoDaoSupport implements W900MGDao {

	@Override
	public W400MG findWebinaris(int fo100) {
		// TODO Auto-generated method stub
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
			query.addCriteria(Criteria.where(QbMongoFiledsName.WV403).exists(true));
			query.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			W400MG w400mg = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).findOne(query, W400MG.class);
			return w400mg;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<W400MG> findWebinarRoom(int fo100) {
		// TODO Auto-generated method stub
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
			query.addCriteria(Criteria.where(QbMongoFiledsName.WV403).exists(false));
			query.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			List<W400MG> list = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).find(query, W400MG.class);
			return list;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	

}
