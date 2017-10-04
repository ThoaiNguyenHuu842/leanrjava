package com.ohhay.web.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.other.mysql.dao.B920Dao;
import com.ohhay.web.other.mysql.service.B920Service;

@Service(value = SpringBeanNames.SERVICE_NAME_B920)
@Scope("prototype")
public class B920ServiceImpl implements B920Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_B920)
	private B920Dao b920dao;

	@Override
	public String chayGetelemTabB920(String ov101, String pvLogin) {
		// TODO Auto-generated method stub
		return b920dao.chayGetelemTabB920(ov101, pvLogin);
	}

	
}
