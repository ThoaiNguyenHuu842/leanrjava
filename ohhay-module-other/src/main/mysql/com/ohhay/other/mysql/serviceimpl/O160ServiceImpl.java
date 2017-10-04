package com.ohhay.other.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.entities.O160;
import com.ohhay.other.mysql.dao.O160Dao;
import com.ohhay.other.mysql.service.O160Service;

@Service(value = SpringBeanNames.SERVICE_NAME_O160)
@Scope("prototype")
public class O160ServiceImpl implements O160Service {
	@Autowired
	@Qualifier(SpringBeanNames.REPOSITORY_NAME_O160)
	private O160Dao o160Dao;

	@Override
	public List<O160> listOfTabO160(String pvLogin) {
		// TODO Auto-generated method stub
		return o160Dao.listOfTabO160(pvLogin);
	}
	
	
	
}
