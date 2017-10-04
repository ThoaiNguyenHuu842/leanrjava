package com.ohhay.other.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.entities.O200;
import com.ohhay.other.mysql.dao.O200Dao;
import com.ohhay.other.mysql.service.O200Service;

@Service(value = SpringBeanNames.SERVICE_NAME_O200)
@Scope("prototype")
public class O200ServiceImpl implements O200Service {
	@Autowired
	@Qualifier(SpringBeanNames.REPOSITORY_NAME_O200)
	private O200Dao o200Dao;

	@Override
	public String insertTabO200(int fo100, String ov201, String ov205, String pvLogin) {
		// TODO Auto-generated method stub
		return o200Dao.insertTabO200(fo100, ov201, ov205, pvLogin);
	}

	@Override
	public int updateTabO200(int fo200, String pvLogin) {
		// TODO Auto-generated method stub
		return o200Dao.updateTabO200(fo200, pvLogin);
	}

	@Override
	public List<O200> listOfTabO200(String pvLogin) {
		// TODO Auto-generated method stub
		return o200Dao.listOfTabO200(pvLogin);
	}

	@Override
	public int stornoTabO200(String ov202, String pvLogin) {
		// TODO Auto-generated method stub
		return o200Dao.stornoTabO200(ov202, pvLogin);
	}
	
	
}
