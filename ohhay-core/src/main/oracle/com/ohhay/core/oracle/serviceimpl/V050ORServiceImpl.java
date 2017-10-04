package com.ohhay.core.oracle.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.V050OR;
import com.ohhay.core.oracle.dao.V050ORDao;
import com.ohhay.core.oracle.service.V050ORService;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_V050OR)
@Scope("prototype")
public class V050ORServiceImpl implements V050ORService{

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V050OR)
	private V050ORDao v050orDao;
	
	@Override
	public int insertTabV050O(int fv050, int vn051, int vn052, int vn053, String vv054, String vv055, int fv000, String pvLogin) {
		// TODO Auto-generated method stub
		return v050orDao.insertTabV050O(fv050, vn051, vn052, vn053, vv054, vv055, fv000, pvLogin);
	}

	@Override
	public int confirmTabV050(int fv050, String pvLogin) {
		// TODO Auto-generated method stub
		return v050orDao.confirmTabV050(fv050, pvLogin);
	}

	@Override
	public List<V050OR> listOfTabV050(int fv050,int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return v050orDao.listOfTabV050(fv050, offset, limit, pvLogin);
	}
	
}
