package com.ohhay.core.oracle.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.V130OR;
import com.ohhay.core.oracle.dao.V130ORDao;
import com.ohhay.core.oracle.service.V130ORService;

/**
 * @author TuNt
 * create date Mar 16, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_V130OR)
@Scope("prototype")
public class V130ORServiceImpl implements V130ORService{

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V130OR)
	private V130ORDao v130orDao;

	@Override
	public int createTabV130(int fp100, int fo100, String login) {
		// TODO Auto-generated method stub
		return v130orDao.createTabV130(fp100, fo100, login);
	}

	@Override
	public List<V130OR> listOfTabV130(int vn133, int fp100, int fo100,int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return v130orDao.listOfTabV130(vn133, fp100, fo100, offset,  limit, pvLogin);
	}
}
