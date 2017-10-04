package com.ohhay.core.oracle.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.T900OR;
import com.ohhay.core.oracle.dao.T900ORDao;
import com.ohhay.core.oracle.service.T900ORService;

/**
 * @author ThoaiVt
 * Mar 29, 2017
 */
@Service(value=SpringBeanNames.SERVICE_NAME_T900OR)
@Scope("prototype")
public class T900ORServiceImpl implements T900ORService{

	@Qualifier(value=SpringBeanNames.REPOSITORY_NAME_T900OR)
	@Autowired
	T900ORDao t900Dao;
	
	@Override
	public List<T900OR> listOfTabT900OPEN(int fo100,int offset, int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		return t900Dao.listOfTabT900OPEN(fo100,offset,limit, pvLOGIN);
	}

	@Override
	public List<T900OR> listOfTabT900CONF(int fo100,int offset, int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		return t900Dao.listOfTabT900CONF(fo100,offset,limit, pvLOGIN);
	}

	@Override
	public List<T900OR> listOfTabT900APP(int fo100,int offset, int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		return t900Dao.listOfTabT900APP(fo100,offset,limit, pvLOGIN);
	}

	@Override
	public List<T900OR> listOfTabT900AFF(int fo100,int offset, int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		return t900Dao.listOfTabT900AFF(fo100,offset,limit, pvLOGIN);
	}

}
