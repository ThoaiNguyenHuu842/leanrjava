package com.ohhay.piepme.mongo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.M930PMGDao;
import com.ohhay.piepme.mongo.service.M930PMGService;
@Service(value = SpringBeanNames.SERVICE_NAME_M930P)
@Scope("prototype")
public class M930PMGServiceImpl implements M930PMGService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_M930P)
	private M930PMGDao m930p;
	@Override
	public int insertTabM930(int fo100, String key) {
		// TODO Auto-generated method stub
		return m930p.insertTabM930(fo100, key);
	}

}
