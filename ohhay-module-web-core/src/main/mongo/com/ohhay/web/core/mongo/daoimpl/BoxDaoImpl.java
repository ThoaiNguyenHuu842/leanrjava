/*package com.ohhay.web.core.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.mongo.dao.BoxDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_BOXDAO)
@Scope("prototype")
public class BoxDaoImpl extends QbMongoDaoSupport implements BoxDao{

	@Override
	public C400MG findboxbyFc920(String phone, int fc920) {
		// TODO Auto-generated method stub
		Query query = selectqueryStringFc920(phone,fc920);
		//must add fo100
		C400MG c400mg = null;
		try {
			c400mg = getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY, 0).findOne(query, C400MG.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(c400mg != null){
			return c400mg;
		}else{
			return null;
		}
		
	}

	@Override
	public Query selectqueryStringFc920(String phone, int fc920) {
		// TODO Auto-generated method stub
		Query query = Query.query(Criteria.where(QbMongoFiledsName.HV101).is(phone));
		query.fields().elemMatch(QbMongoFiledsName.PART, Criteria.where(QbMongoFiledsName.FC920).is(fc920));
		return query;
	}

	@Override
	public int cloneboxbyFc920(Query query, Update update) {
		// TODO Auto-generated method stub
		WriteResult result = null;
		try {
			result = getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY, 0).updateFirst(query, update, C400MG.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.getN();
	}

	@Override
	public C400MG findboxbyphone(String phone) {
		// TODO Auto-generated method stub
		Query query = Query.query(Criteria.where(QbMongoFiledsName.HV101).is(phone));
		C400MG c400mg = null;
		try {
			c400mg = getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY, 0).findOne(query, C400MG.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(c400mg != null){
			return c400mg;
		}else{
			return null;
		}
	}

	@Override
	public Query selectfindboxbyphone(String phone) {
		// TODO Auto-generated method stub
		Query query = Query.query(Criteria.where(QbMongoFiledsName.HV101).is(phone));
		return query;
	}

	@Override
	public int copyboxbyFC920(C400MG c400mg) throws Exception {
		// TODO Auto-generated method stub
		try {
			getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY, 0).save(c400mg);
			return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
		
	}

	@Override
	public <T> int deletePartbyFC920(int fo100, String phone,int webId, int fc920, Class<T> mClass) {
		// TODO Auto-generated method stub
		try {
			Query query = new Query(Criteria.where(QbMongoFiledsName.HV101).is(phone).and(QbMongoFiledsName.PART+"."+QbMongoFiledsName.FC920).is(fc920).and(QbMongoFiledsName.ID).is(webId));
			Update update = new Update().pull(QbMongoFiledsName.PART, new BasicDBObject(QbMongoFiledsName.FC920, fc920));
			WriteResult wr = getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY, 0).updateFirst(query, update, mClass);
			return wr.getN();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> int createPartbyFC920(OhhayWebBase ohhayWebBase, String collectionName) {
		// TODO Auto-generated method stub
		try {
			getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY, 0).save(ohhayWebBase, collectionName);
			return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
	}

	@Override
	public <T> OhhayWebBase findPartbyFC920(int fo100, int webId, int fc920,
			Class<T> mClass, String collectionName) {
		// TODO Auto-generated method stub
		try {
			Query query = selectfindPartbyFC920(fo100, webId, fc920, mClass);
			OhhayWebBase ohhayWebBase = (OhhayWebBase) getMongoOperations(ApplicationConstant.DB_TYPE_OOHHAY, 0).findOne(query, mClass, collectionName);
			if(ohhayWebBase != null){
				return ohhayWebBase;
			}else{
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> Query selectfindPartbyFC920(int fo100,int webId, int fc920,
			Class<T> mClass) {
		// TODO Auto-generated method stub
		try {
			Query query = Query.query(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
			query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(webId));
			query.fields().elemMatch(QbMongoFiledsName.PART, Criteria.where(QbMongoFiledsName.FC920).is(fc920));
			return query;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
*/