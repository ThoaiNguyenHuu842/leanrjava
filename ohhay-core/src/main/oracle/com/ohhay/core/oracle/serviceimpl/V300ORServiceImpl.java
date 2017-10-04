package com.ohhay.core.oracle.serviceimpl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.oracle.dao.V300ORDao;
import com.ohhay.core.oracle.service.V300ORService;

/**
 * @author TuNt
 * create date Jun 30, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_V300OR)
@Scope("prototype")
public class V300ORServiceImpl implements V300ORService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V300OR)
	private V300ORDao v300orDao;

	@Override
	public int checkedTabV300(int fo100, String vv308, String login) {
		// TODO Auto-generated method stub
		return v300orDao.checkedTabV300(fo100, vv308, login);
	}
	@Override
	public int insertTabV300(int pv300, String vv301, String vv302, Date vd303, Date vd304, String vv305, String vv306, String vv308, int vn309, int fo100,
			String login) {
		// TODO Auto-generated method stub
		return v300orDao.insertTabV300(pv300, vv301, vv302, vd303, vd304, vv305, vv306, vv308, vn309, fo100, login);
	}
	
	
}
