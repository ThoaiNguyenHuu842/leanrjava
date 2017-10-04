package com.ohhay.web.core.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.mysql.dao.L920Dao;
import com.ohhay.web.core.mysql.service.L920Service;

@Service(value = SpringBeanNames.SERVICE_NAME_L920)
@Scope("prototype")
public class L920ServiceImpl implements L920Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_L920)
	private L920Dao l920Dao;

	@Override
	public String getElemTabL920(int fl400, String pvLogin) {
		// TODO Auto-generated method stub
		return l920Dao.getElemTabL920(fl400, pvLogin);
	}
}
