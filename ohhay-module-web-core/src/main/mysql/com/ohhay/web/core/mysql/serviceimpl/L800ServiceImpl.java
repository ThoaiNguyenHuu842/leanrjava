package com.ohhay.web.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.web.core.mysql.dao.L800Dao;
import com.ohhay.web.core.mysql.service.L800Service;

@Service(value = SpringBeanNames.SERVICE_NAME_L800)
@Scope("prototype")
public class L800ServiceImpl implements L800Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_L800)
	private L800Dao l800Dao;

	@Override
	public List<ComtabItem> chayCombtabL800(int fd000, String lv808, String pvLOGIN) {
		// TODO Auto-generated method stub
		return l800Dao.chayCombtabL800(fd000, lv808, pvLOGIN);
	}

	@Override
	public List<ComtabItem> chayCombtabL800Video(String pvLOGIN) {
		// TODO Auto-generated method stub
		return l800Dao.chayCombtabL800Video(pvLOGIN);
	}
	

}
