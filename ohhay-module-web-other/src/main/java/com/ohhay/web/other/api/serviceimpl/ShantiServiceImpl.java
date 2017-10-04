package com.ohhay.web.other.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.entities.B050Shanti;
import com.ohhay.web.core.entities.C150Shanti;
import com.ohhay.web.other.api.dao.ShantiDao;
import com.ohhay.web.other.api.service.ShantiService;

@Service(value = SpringBeanNames.SERVICE_NAME_SHANTI)
@Scope("prototype")
public class ShantiServiceImpl implements ShantiService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_SHANTIDAO)
	ShantiDao shantiDao;

	@Override
	public List<B050Shanti> listOfTabB050(String key, String pvLogin) {
		// TODO Auto-generated method stub
		return shantiDao.listOfTabB050(key, pvLogin);
	}

	@Override
	public List<C150Shanti> listOfTabC150(String key, int fb050, String pvLogin) {
		// TODO Auto-generated method stub
		return shantiDao.listOfTabC150(key, fb050, pvLogin);
	}
	
}
