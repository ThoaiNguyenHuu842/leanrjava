package com.ohhay.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.O180;
import com.ohhay.core.mysql.dao.O180Dao;
import com.ohhay.core.mysql.service.O180Service;
@Service(value = SpringBeanNames.SERVICE_NAME_O180)
@Scope("prototype")
public class O180ServiceImpl implements O180Service {
	@Autowired
	@Qualifier(SpringBeanNames.REPOSITORY_NAME_O180)
	private O180Dao o180Dao;

	@Override
	public int insertTabO180(int po180, String ov182, String ov183, String ov184, int fo100c, int fo100p, String pvLogin) {
		// TODO Auto-generated method stub
		return o180Dao.insertTabO180(po180, ov182, ov183, ov184, fo100c, fo100p, pvLogin);
	}

	@Override
	public int stornotabo180(int fo100p, int fo100c, String pvLogin) {
		// TODO Auto-generated method stub
		return o180Dao.stornotabo180(fo100p, fo100c, pvLogin);
	}

	@Override
	public List<O180> listOfTabO180C(int fo100, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return o180Dao.listOfTabO180C(fo100, offset, limit, pvLogin);
	}

	
	
	
}
