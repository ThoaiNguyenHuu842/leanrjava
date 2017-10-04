package com.ohhay.web.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.entities.C920;
import com.ohhay.web.core.mysql.dao.C920Dao;
import com.ohhay.web.core.mysql.service.C920Service;

@Service(value = SpringBeanNames.SERVICE_NAME_C920)
@Scope("prototype")
public class C920ServiceImpl implements C920Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_C920)
	private C920Dao c920Dao;
	@Override
	public List<C920> chayListOfTabC920(String pvHv101, String pvLOGIN) {
		// TODO Auto-generated method stub
		return c920Dao.chayListOfTabC920(pvHv101, pvLOGIN);
	}

	@Override
	public List<C920> chayListOfTabC920View(String pvHv101, String pvLOGIN) {
		// TODO Auto-generated method stub
		return c920Dao.chayListOfTabC920View(pvHv101, pvLOGIN);
	}

	@Override
	public String chayGetelemTabC920(String ov101, String pvLogin) {
		// TODO Auto-generated method stub
		return c920Dao.chayGetelemTabC920(ov101, pvLogin);
	}

	@Override
	public String chayGetelemTabC920Lp(String ov101, int fc400, String pvLogin) {
		// TODO Auto-generated method stub
		return c920Dao.chayGetelemTabC920Lp(ov101, fc400, pvLogin);
	}

	@Override
	public String chayGetelemTabC920Child(int fc500, String pvLogin) {
		// TODO Auto-generated method stub
		return c920Dao.chayGetelemTabC920Child(fc500, pvLogin);
	}
}
