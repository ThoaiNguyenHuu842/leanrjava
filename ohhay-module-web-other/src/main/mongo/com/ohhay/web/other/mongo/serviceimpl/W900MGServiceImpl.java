package com.ohhay.web.other.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.entities.mongo.web.W400MG;
import com.ohhay.web.other.mongo.dao.W900MGDao;
import com.ohhay.web.other.mongo.service.W900MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_W900MG)
@Scope("prototype")
public class W900MGServiceImpl implements W900MGService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_W900MG)
	W900MGDao w900mgDao;

	@Override
	public W400MG findWebinaris(int fo100) {
		// TODO Auto-generated method stub
		return w900mgDao.findWebinaris(fo100);
	}

	@Override
	public List<W400MG> findWebinarRoom(int fo100) {
		// TODO Auto-generated method stub
		return w900mgDao.findWebinarRoom(fo100);
	}
}
