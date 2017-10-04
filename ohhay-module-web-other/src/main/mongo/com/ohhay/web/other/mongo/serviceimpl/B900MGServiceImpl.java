/*package com.ohhay.web.other.mongo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.entities.mongo.web.B400MG;
import com.ohhay.web.other.mongo.dao.B900MGDao;
import com.ohhay.web.other.mongo.service.B900MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_B900MG)
@Scope("prototype")
public class B900MGServiceImpl implements B900MGService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_B900MG)
	B900MGDao b900mgDao;

	@Override
	public B400MG findB400One(String phoneNumber) {
		// TODO Auto-generated method stub
		return b900mgDao.findB400One(phoneNumber);
	}

	@Override
	public int updateB400(B400MG b400mg) {
		// TODO Auto-generated method stub
		return b900mgDao.updateB400(b400mg);
	}



}
*/