package com.ohhay.core.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.Q250MG;
import com.ohhay.core.mongo.dao.SequenceDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_SEQUENCEDAO)
@Scope("prototype")
public class SequenceDaoImpl extends QbMongoDaoSupport implements SequenceDao {
	@Override
	public long getNextSequenceId(int fo100, String key) throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(key));
		Update update = new Update();
		update.inc("seq", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		//must add fo100
		Q250MG seqId =  getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).findAndModify(query, update,options, Q250MG.class);
		if (seqId == null)
			throw new Exception("Unable to get sequence id for key : " + key);
		return seqId.getSeq();
	}

	@Override
	public long getNextSequenceIdTopic(int fo100, String key) throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(key));
		Update update = new Update();
		update.inc("seq", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		//must add fo100
		Q250MG seqId =  getMongoOperations(ApplicationConstant.DB_TYPE_TOPIC, fo100).findAndModify(query, update,options, Q250MG.class);
		if (seqId == null)
			throw new Exception("Unable to get sequence id for key : " + key);
		return seqId.getSeq();
	}

	@Override
	public long getNextSequenceIdShop(int fo100, String key) throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(key));
		Update update = new Update();
		update.inc("seq", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		//must add fo100
		Q250MG seqId =  getMongoOperations(ApplicationConstant.DB_TYPE_SHOP, fo100).findAndModify(query, update,options, Q250MG.class);
		if (seqId == null)
			throw new Exception("Unable to get sequence id for key : " + key);
		return seqId.getSeq();
	}

	@Override
	public long getNextSequenceIdPiepMe(int fo100, String key)
			throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(key));
		Update update = new Update();
		update.inc("seq", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		//must add fo100
		Q250MG seqId =  getMongoOperations(ApplicationConstant.DB_TYPE_PIEPME, fo100).findAndModify(query, update,options, Q250MG.class);
		if (seqId == null)
			throw new Exception("Unable to get sequence id for key : " + key);
		return seqId.getSeq();
	}
}
