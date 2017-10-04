package com.ohhay.web.mongo.core.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.mongo.dao.C800MGDao;
import com.ohhay.web.core.mongo.service.C800MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_C800MG)
@Scope("prototype")
public class C800MGServiceImpl implements C800MGService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_C800MG)
	C800MGDao c800mgDao;

	@Override
	public String getCv802Template(int fc800) {
		// TODO Auto-generated method stub
		return c800mgDao.getCv802Template(fc800);
	}
	
}
