package com.ohhay.core.oracle.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.V000OR;
import com.ohhay.core.oracle.dao.V000ORDao;
import com.ohhay.core.oracle.service.V000ORService;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_V000OR)
@Scope("prototype")
public class V000ORServiceImpl implements V000ORService{

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V000OR)
	private V000ORDao v000orDao;
	
	@Override
	public int insertTabV000O(int fv000, int vn001, int vn002, int vn003, String vv004, String vv005, String pvLogin) {
		// TODO Auto-generated method stub
		return v000orDao.insertTabV000O(fv000, vn001, vn002, vn003, vv004, vv005, pvLogin);
	}

	@Override
	public int confirmTabV000(int fv000, String pvLogin) {
		// TODO Auto-generated method stub
		return v000orDao.confirmTabV000(fv000, pvLogin);
	}

	@Override
	public List<V000OR> listOfTabV000(String pvLogin) {
		// TODO Auto-generated method stub
		return v000orDao.listOfTabV000(pvLogin);
	}
	
}
