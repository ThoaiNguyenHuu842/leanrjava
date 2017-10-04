package com.ohhay.other.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.N100;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.api.dao.M350OrelDao;
import com.ohhay.other.api.service.M350OrelService;
import com.ohhay.other.mysql.service.N100Service;
@Service(value = SpringBeanNames.SERVICE_NAME_M350OREL)
@Scope("prototype")
public class M350OrelServiceImpl implements M350OrelService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_M350OREL)
	private M350OrelDao m350OrelDao;
	@Override
	public int sendMailTabM350Shop(int pnFO100, String ov102, String pvMV367, String pvMV375, String pvMESSA, String pvLogin) {
		// TODO Auto-generated method stub
		return m350OrelDao.sendMailTabM350Shop(pnFO100, ov102, pvMV367, pvMV375, pvMESSA, pvLogin);
	}

	@Override
	public int sendMailTabM350Topic(int pnFO100, String ov102, String pvMV366, String pvMV367, String pvMV368, String pvMV369, String pvMV370, String pvMV371, String pvMV375, String pvMESSA, String pvLogin) {
		// TODO Auto-generated method stub
		return m350OrelDao.sendMailTabM350Topic(pnFO100, ov102, pvMV366, pvMV367, pvMV368, pvMV369, pvMV370, pvMV371, pvMV375, pvMESSA, pvLogin);
	}

	@Override
	public int checkEmail(String email, String pass, String host, String style,
			String post) {
		// TODO Auto-generated method stub
		return m350OrelDao.checkEmail(email, pass, host, style, post);
	}
}
