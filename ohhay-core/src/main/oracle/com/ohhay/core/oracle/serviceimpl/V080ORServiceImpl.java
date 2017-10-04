package com.ohhay.core.oracle.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.V080OR;
import com.ohhay.core.oracle.dao.V080ORDao;
import com.ohhay.core.oracle.service.V080ORService;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_V080OR)
@Scope("prototype")
public class V080ORServiceImpl implements V080ORService{

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V080OR)
	private V080ORDao v080orDao;
	@Override
	public int createTabV080O(int vn081, String pvLogin) {
		// TODO Auto-generated method stub
		return v080orDao.createTabV080O(vn081, pvLogin);
	}

	@Override
	public int confirmTabV080O(int vn081, String pvLogin) {
		// TODO Auto-generated method stub
		return v080orDao.confirmTabV080O(vn081, pvLogin);
	}

	@Override
	public List<V080OR> listOfTabV080(int vn081, int fo100,int offset, int limit,  String pvLogin) {
		// TODO Auto-generated method stub
		return v080orDao.listOfTabV080(vn081, fo100, offset,  limit,  pvLogin);
	}
	
}
