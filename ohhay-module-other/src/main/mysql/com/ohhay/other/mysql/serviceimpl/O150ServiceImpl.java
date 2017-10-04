package com.ohhay.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mysql.dao.O150Dao;
import com.ohhay.other.mysql.service.O150Service;

@Service(value = SpringBeanNames.SERVICE_NAME_O150)
@Scope("prototype")
public class O150ServiceImpl implements O150Service {
	@Autowired
	@Qualifier(SpringBeanNames.REPOSITORY_NAME_O150)
	private O150Dao o150Dao;

	@Override
	public int insertTabO150(int fo100, String ov151, String pvLogin) {
		// TODO Auto-generated method stub
		return o150Dao.insertTabO150(fo100, ov151, pvLogin);
	}
	
}
