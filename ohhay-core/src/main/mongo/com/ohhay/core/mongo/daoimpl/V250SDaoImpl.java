package com.ohhay.core.mongo.daoimpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.shop.V250SMG;
import com.ohhay.core.mongo.dao.V250SMGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_V250SDAO)
@Scope("prototype")
public class V250SDaoImpl extends QbMongoDaoSupport implements V250SMGDao{

	@Override
	public List<V250SMG> findV250Index(int limit) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where("VN270").is(1));
			query2.with(new Sort(Sort.Direction.ASC, QbMongoFiledsName.ID));
			query2.limit(limit);
			List<V250SMG> list = getMongoOperations(ApplicationConstant.DB_TYPE_SHOP, ApplicationConstant.FO100_SUPER_ADMIN).find(query2, V250SMG.class);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
