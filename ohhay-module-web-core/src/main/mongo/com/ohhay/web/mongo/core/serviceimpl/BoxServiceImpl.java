/*package com.ohhay.web.mongo.core.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.mongo.dao.BoxDao;
import com.ohhay.web.core.mongo.service.BoxService;

@Service(value = SpringBeanNames.SERVICE_NAME_BOXMG)
@Scope("prototype")
public class BoxServiceImpl implements BoxService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_BOXDAO)
	BoxDao boxDao;
	@Override
	public C400MG findboxbyFc920(String phone, int fc920) {
		// TODO Auto-generated method stub
		return boxDao.findboxbyFc920(phone, fc920);
	}

	@Override
	public Query selectqueryStringFc920(String phone, int fc920) {
		// TODO Auto-generated method stub
		return boxDao.selectqueryStringFc920(phone, fc920);
	}

	@Override
	public int cloneboxbyFc920(Query query, Update update) {
		// TODO Auto-generated method stub
		return boxDao.cloneboxbyFc920(query,update);
	}

	@Override
	public C400MG findboxbyphone(String phone) {
		// TODO Auto-generated method stub
		return boxDao.findboxbyphone(phone);
	}

	@Override
	public Query selectfindboxbyphone(String phone) {
		// TODO Auto-generated method stub
		return boxDao.selectfindboxbyphone(phone);
	}

	@Override
	public int copyboxbyFC920(C400MG c400mg) throws Exception {
		// TODO Auto-generated method stub
		return boxDao.copyboxbyFC920(c400mg);
	}

	@Override
	public <T> int deletePartbyFc920(int fo100, String phone, int webId, int fc920, Class<T> mClass) {
		// TODO Auto-generated method stub
		return boxDao.deletePartbyFC920(fo100, phone, webId, fc920, mClass);
	}

	@Override
	public <T> int createPartbyFc920(OhhayWebBase ohhayWebBase, String collectionName) {
		// TODO Auto-generated method stub
		return boxDao.createPartbyFC920(ohhayWebBase, collectionName);
	}

	@Override
	public <T> OhhayWebBase findPartbyFc920(int fo100, int webId, int fc920,
			Class<T> mClass, String collectionName) {
		// TODO Auto-generated method stub
		return boxDao.findPartbyFC920(fo100,webId, fc920, mClass, collectionName);
	}

	@Override
	public <T> Query selectfindPartbyFc920(int fo100, int webId, int fc920,
			Class<T> mClass) {
		// TODO Auto-generated method stub
		return boxDao.selectfindPartbyFC920(fo100, webId, fc920, mClass);
	}
}
*/