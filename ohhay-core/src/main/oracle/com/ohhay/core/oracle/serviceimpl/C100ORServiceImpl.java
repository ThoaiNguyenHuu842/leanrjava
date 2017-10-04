package com.ohhay.core.oracle.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.C100OR;
import com.ohhay.core.entities.oracle.N100OR;
import com.ohhay.core.oracle.dao.C100ORDao;
import com.ohhay.core.oracle.dao.N100ORDao;
import com.ohhay.core.oracle.service.C100ORService;
import com.ohhay.core.oracle.service.N100ORService;
@Service(value = SpringBeanNames.SERVICE_NAME_C100OR)
@Scope("prototype")
public class C100ORServiceImpl implements C100ORService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_C100OR)
	private C100ORDao c100orDao;
	@Override
	public int insertTabC100(int pnPC100, String pvCV101, String pvCV102, String pvCV103, int pnFO100, int pnFO100C, String pvLOGIN) {
		// TODO Auto-generated method stub
		return c100orDao.insertTabC100(pnPC100, pvCV101, pvCV102, pvCV103, pnFO100, pnFO100C, pvLOGIN);
	}

	@Override
	public List<C100OR> listOfTabC100(String pvCV102, int pnFO100C, String pvLogin) {
		// TODO Auto-generated method stub
		return c100orDao.listOfTabC100(pvCV102, pnFO100C, pvLogin);
	}

}
