package com.ohhay.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.M350;
import com.ohhay.core.mysql.dao.M350Dao;
import com.ohhay.core.mysql.service.M350Service;

@Service(value = SpringBeanNames.SERVICE_NAME_M350)
@Scope("prototype")
public class M350ServiceImpl implements M350Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_M350)
	private M350Dao m350Dao;

	@Override
	public int sendMailTabM350Confirm(int pnFO100,String ov102,String pvMV367,  String pvMV375, String pvMESSA, String pvLogin) {
		// TODO Auto-generated method stub
		return m350Dao.sendMailTabM350Confirm(pnFO100, ov102,pvMV367, pvMV375, pvMESSA, pvLogin);
	}

	@Override
	public List<M350> listOfTabM350Send(String pvLogin) {
		// TODO Auto-generated method stub
		return m350Dao.listOfTabM350Send(pvLogin);
	}

	@Override
	public int sendMailTabM350Check(String email, String pvLogin) {
		// TODO Auto-generated method stub
		return m350Dao.sendMailTabM350Check(email, pvLogin);
	}
	
}
