package com.ohhay.other.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.other.mysql.dao.D000Dao;
import com.ohhay.other.mysql.service.D000Service;

@Service(value = SpringBeanNames.SERVICE_NAME_D000)
@Scope("prototype")
public class D000ServiceImpl implements D000Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_D000)
	private D000Dao d000Dao;

	@Override
	public List<ComtabItem> combTabD000(String pvDv002, String pvLogin) {
		// TODO Auto-generated method stub
		return d000Dao.combTabD000(pvDv002, pvLogin);
	}

	@Override
	public List<ComtabItem> combTabD000Regis(String pvDv002, String pvLogin) {
		// TODO Auto-generated method stub
		return d000Dao.combTabD000Regis(pvDv002, pvLogin);
	}
	
	

}
