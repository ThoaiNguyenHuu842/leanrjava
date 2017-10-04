package com.ohhay.other.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.entities.W000;
import com.ohhay.other.mysql.dao.W000Dao;
import com.ohhay.other.mysql.service.W000Service;

@Service(value = SpringBeanNames.SERVICE_NAME_W000)
@Scope("prototype")
public class W000ServiceImpl implements W000Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_W000)
	private W000Dao w000Dao;

	@Override
	public List<W000> listOfTabW000(String pvWV004, String pvLogin) {
		// TODO Auto-generated method stub
		return w000Dao.listOfTabW000(pvWV004, pvLogin);
	}
	
	
}
