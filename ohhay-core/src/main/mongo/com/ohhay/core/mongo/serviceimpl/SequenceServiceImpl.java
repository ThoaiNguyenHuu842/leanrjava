package com.ohhay.core.mongo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.dao.SequenceDao;
import com.ohhay.core.mongo.service.SequenceService;
@Service(value = SpringBeanNames.SERVICE_NAME_SEQUENCE)
@Scope("prototype")
public class SequenceServiceImpl implements SequenceService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_SEQUENCEDAO)
	SequenceDao sequenceDao;
	@Override
	public long getNextSequenceId(int fo100, String key) throws Exception {
		// TODO Auto-generated method stub
		return sequenceDao.getNextSequenceId(fo100, key);
	}
	@Override
	public long getNextSequenceIdTopic(int fo100, String key) throws Exception {
		// TODO Auto-generated method stub
		return sequenceDao.getNextSequenceIdTopic(fo100, key);
	}
	@Override
	public long getNextSequenceIdShop(int fo100, String key) throws Exception {
		// TODO Auto-generated method stub
		return sequenceDao.getNextSequenceIdShop(fo100, key);
	}
	@Override
	public long getNextSequenceIdPiepMe(int fo100, String key)
			throws Exception {
		// TODO Auto-generated method stub
		return sequenceDao.getNextSequenceIdPiepMe(fo100, key);
	}
}
