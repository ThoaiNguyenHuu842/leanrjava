package com.ohhay.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q170;
import com.ohhay.core.mysql.dao.Q170Dao;
import com.ohhay.core.mysql.service.Q170Service;

@Service(value = SpringBeanNames.SERVICE_NAME_Q170)
@Scope("prototype")
public class Q170ServiceImpl implements Q170Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_Q170)
	private Q170Dao q170Dao;

	@Override
	public List<Q170> listOfTabQ170(int fo100, int fq300, int fq400, String pvLogin) {
		// TODO Auto-generated method stub
		return q170Dao.listOfTabQ170(fo100, fq300, fq400, pvLogin);
	}

}
