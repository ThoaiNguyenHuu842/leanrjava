/*package com.ohhay.other.mongo.daoimpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.entities.mongo.showtime.W150MG;
import com.ohhay.other.mongo.dao.W150MGDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_W150MG)
@Scope("prototype")
public class W150MGDaoImpl extends QbMongoDaoSupport implements W150MGDao {

	@Override
	public int insertW150(W150MG w150) {
		// TODO Auto-generated method stub
		try {
			SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
			w150.setId(sequenceService.getNextSequenceId(QbMongoCollectionsName.W150));
			getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY,0).save(w150);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public W150MG findOne(String phoneNumber) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.HV101).is(phoneNumber));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			W150MG w150mg = getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY,0).findOne(query2, W150MG.class);
			return w150mg;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateW150(W150MG w150) {
		// TODO Auto-generated method stub
		try {
			getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY,0).save(w150);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
}
*/