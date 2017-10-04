package com.ohhay.web.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.other.mysql.dao.V920Dao;
import com.ohhay.web.other.mysql.service.V920Service;
@Service(value = SpringBeanNames.SERVICE_NAME_V920)
@Scope("prototype")
public class V920ServiceImpl implements V920Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V920)
	private V920Dao v920Dao;
	@Override
	public String vhayGetelemTabV920(String ov101, String pvLogin) {
		// TODO Auto-generated method stub
		return v920Dao.vhayGetelemTabV920(ov101, pvLogin);
	}

}
