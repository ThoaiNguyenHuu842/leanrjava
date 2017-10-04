package com.ohhay.web.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.web.core.entities.C400;
import com.ohhay.web.core.mysql.dao.C400Dao;
import com.ohhay.web.core.mysql.service.C400Service;

@Service(value = SpringBeanNames.SERVICE_NAME_C400)
@Scope("prototype")
public class C400ServiceImpl implements C400Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_C400)
	private C400Dao c400Dao;
	@Override
	public List<C400> chayListOfTabC400(int pnFC400, int pnFO100, int pnFC800,
			String pvLOGIN) {
		// TODO Auto-generated method stub
		return c400Dao.chayListOfTabC400(pnFC400, pnFO100, pnFC800, pvLOGIN);
	}

	@Override
	public List<C400> chayListOfTabC400View(String qv101, String pvLOGIN) {
		// TODO Auto-generated method stub
		return c400Dao.chayListOfTabC400View(qv101, pvLOGIN);
	}

	@Override
	public List<ComtabItem> ahayCombtabc400(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return c400Dao.ahayCombtabc400(fo100, pvLogin);
	}

	@Override
	public String chayUpdatetabc400(int fo100, int fc400, String pvLogin) {
		// TODO Auto-generated method stub
		return c400Dao.chayUpdatetabc400(fo100, fc400, pvLogin);
	}

	@Override
	public String insertTabC400(int pnPC400, String pvCV401, String pvCV402, String pvCV403, String pvCV404, String pvCV405, int pnFO100, int pnFC800, int pnFD000, int pnFH020, String pvLogin) {
		// TODO Auto-generated method stub
		return c400Dao.insertTabC400(pnPC400, pvCV401, pvCV402, pvCV403, pvCV404, pvCV405, pnFO100, pnFC800, pnFD000, pnFH020, pvLogin);
	}

	
	

}
