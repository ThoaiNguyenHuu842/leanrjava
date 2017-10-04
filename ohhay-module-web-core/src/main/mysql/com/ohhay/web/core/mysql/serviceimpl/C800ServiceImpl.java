package com.ohhay.web.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.web.core.mysql.dao.C800Dao;
import com.ohhay.web.core.mysql.service.C800Service;

@Service(value = SpringBeanNames.SERVICE_NAME_C800)
@Scope("prototype")
public class C800ServiceImpl implements C800Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_C800)
	private C800Dao c800Dao;

	@Override
	public List<ComtabItem> chayCombtabc800(int fd000, int fk100, String src, String pvLOGIN) {
		// TODO Auto-generated method stub
		return c800Dao.chayCombtabc800(fd000, fk100, src, pvLOGIN);
	}

	@Override
	public String chayGetElemTabC800(int fc800, String pvLogin) {
		// TODO Auto-generated method stub
		return c800Dao.chayGetElemTabC800(fc800, pvLogin);
	}

	@Override
	public List<ComtabItem> chayCombtabc800LgPages(int fd000, int fk100, String src, String pvLOGIN) {
		// TODO Auto-generated method stub
		return c800Dao.chayCombtabc800LgPages(fd000, fk100, src, pvLOGIN);
	}

}
