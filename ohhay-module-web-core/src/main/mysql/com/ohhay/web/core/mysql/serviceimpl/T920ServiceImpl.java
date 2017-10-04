package com.ohhay.web.core.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.mysql.dao.T920Dao;
import com.ohhay.web.core.mysql.service.T920Service;
@Service(value = SpringBeanNames.SERVICE_NAME_T920)
@Scope("prototype")
public class T920ServiceImpl implements T920Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_T920)
	private T920Dao t920Dao;

	@Override
	public String getelemTabT920(int ft400, String pvLogin) {
		// TODO Auto-generated method stub
		return t920Dao.getelemTabT920(ft400, pvLogin);
	}

	@Override
	public String getelemTabT920Child(int ft500, String pvLogin) {
		// TODO Auto-generated method stub
		return t920Dao.getelemTabT920Child(ft500, pvLogin);
	}

	


}
