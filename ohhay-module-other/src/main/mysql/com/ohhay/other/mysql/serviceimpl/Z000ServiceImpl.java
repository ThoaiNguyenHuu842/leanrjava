package com.ohhay.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mysql.dao.Z000Dao;
import com.ohhay.other.mysql.service.Z000Service;

@Service(value = SpringBeanNames.SERVICE_NAME_Z000)
@Scope("prototype")
public class Z000ServiceImpl implements Z000Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_Z000)
	private Z000Dao z000Dao;

	@Override
	public int zhayInsertTabZ000(int pz000, String zv001, String zv002, String zv003, String zv004, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return z000Dao.zhayInsertTabZ000(pz000, zv001, zv002, zv003, zv004, fo100, pvLogin);
	}

	
	

}
