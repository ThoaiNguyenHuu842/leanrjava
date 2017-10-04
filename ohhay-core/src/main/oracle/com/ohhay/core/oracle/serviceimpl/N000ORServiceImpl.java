package com.ohhay.core.oracle.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.N000OR;
import com.ohhay.core.oracle.dao.N000ORDao;
import com.ohhay.core.oracle.service.N000ORService;

/**
 * @author TuNt
 * create date Mar 7, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_N000OR)
@Scope("prototype")
public class N000ORServiceImpl implements N000ORService{
	
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_N000OR)
	private N000ORDao n000orDao;
	
	@Override
	public int insertTabN000Pie(String uuid, String nv002, int fo100, String login) {
		// TODO Auto-generated method stub
		return n000orDao.insertTabN000Pie(uuid, nv002, fo100, login);
	}

	@Override
	public int checkitTabN000Pie(String uuid, String login) {
		// TODO Auto-generated method stub
		return n000orDao.checkitTabN000Pie(uuid, login);
	}

	@Override
	public int updateTabN000Pie(String nv002, int fo100, String login) {
		// TODO Auto-generated method stub
		return n000orDao.updateTabN000Pie(nv002, fo100, login);
	}

	@Override
	public List<N000OR> listOfTabN100Pie(String uuid, String login) {
		// TODO Auto-generated method stub
		return n000orDao.listOfTabN000Pie(uuid, login);
	}

	@Override
	public List<N000OR> listOfTabN000PieCA(String uuid, String login) {
		// TODO Auto-generated method stub
		return n000orDao.listOfTabN000PieCA(uuid, login);
	}

	@Override
	public List<N000OR> listOfTabN000PieDN(String uuid, String login) {
		// TODO Auto-generated method stub
		return n000orDao.listOfTabN000PieDN(uuid, login);
	}

}
