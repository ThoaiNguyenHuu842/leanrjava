package com.ohhay.web.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.other.mysql.dao.W920Dao;
import com.ohhay.web.other.mysql.service.W920Service;

@Service(value = SpringBeanNames.SERVICE_NAME_W920)
@Scope("prototype")
public class W920ServiceImpl implements W920Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_W920)
	private W920Dao w920dao;

	@Override
	public String chayGetelemTabW920(int fw400, String pvLogin) {
		// TODO Auto-generated method stub
		return w920dao.chayGetelemTabW920(fw400, pvLogin);
	}

	@Override
	public String chayGetelemTabW920Child(int fw400, String pvLogin) {
		// TODO Auto-generated method stub
		return w920dao.chayGetelemTabW920Child(fw400, pvLogin);
	}

	
}
