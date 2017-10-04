package com.ohhay.core.oracle.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.V150OR;
import com.ohhay.core.oracle.dao.V150ORDao;
import com.ohhay.core.oracle.service.V150ORService;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_V150OR)
@Scope("prototype")
public class V150ORServiceImpl implements V150ORService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V150OR)
	private V150ORDao v150orDao;
	@Override
	public int createTabV150O(int vn154, String pvLogin) {
		// TODO Auto-generated method stub
		return v150orDao.createTabV150O(vn154, pvLogin);
	}

	@Override
	public int confirmTabV150O(int fv150, int vn152, int vn155, int vn156, int vn157, int vn158, String pvLogin) {
		// TODO Auto-generated method stub
		return v150orDao.confirmTabV150O(fv150, vn152, vn155, vn156, vn157, vn158, pvLogin);
	}

	@Override
	public List<V150OR> listOfTabV150O(int vn154, int fo100,int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return v150orDao.listOfTabV150O(vn154, fo100, offset,  limit, pvLogin);
	}

	@Override
	public int checkiTabV150O(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return v150orDao.checkiTabV150O(fo100, pvLogin);
	}
	
}
