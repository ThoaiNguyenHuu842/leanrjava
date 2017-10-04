package com.ohhay.web.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.entities.C900;
import com.ohhay.web.core.mysql.dao.C900Dao;
import com.ohhay.web.core.mysql.service.C900Service;

@Service(value = SpringBeanNames.SERVICE_NAME_C900)
@Scope("prototype")
public class C900ServiceImpl implements C900Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_C900)
	C900Dao c900Dao;
	@Override
	public List<C900> chayListOfTabC900(int pnFC900, int pnFC850, int pnFC920,
			String pvLOGIN) {
		// TODO Auto-generated method stub
		return c900Dao.chayListOfTabC900(pnFC900, pnFC850, pnFC920, pvLOGIN);
	}
	

}
