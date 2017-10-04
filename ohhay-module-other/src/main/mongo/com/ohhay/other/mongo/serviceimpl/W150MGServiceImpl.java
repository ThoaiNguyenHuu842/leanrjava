package com.ohhay.other.mongo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.entities.mongo.showtime.W150MG;
import com.ohhay.other.mongo.dao.W150MGDao;
import com.ohhay.other.mongo.service.W150MGService;


@Service(value = SpringBeanNames.SERVICE_NAME_W150MG)
@Scope("prototype")
public class W150MGServiceImpl implements W150MGService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_W150MG)
	W150MGDao w150mgDao;

	@Override
	public int insertW150(W150MG w150) {
		// TODO Auto-generated method stub
		return w150mgDao.insertW150(w150);
	}

	@Override
	public W150MG findOne(String phoneNumber) {
		// TODO Auto-generated method stub
		return w150mgDao.findOne(phoneNumber);
	}

	@Override
	public int updateW150(W150MG w150) {
		// TODO Auto-generated method stub
		return w150mgDao.updateW150(w150);
	}




}
