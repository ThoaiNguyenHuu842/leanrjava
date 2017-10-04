package com.ohhay.web.core.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.api.dao.Q100OrelDao;
import com.ohhay.web.core.api.service.Q100OrelService;
@Service(value = SpringBeanNames.SERVICE_NAME_Q100OREL)
@Scope("prototype")
public class Q100OrelServiceImpl implements Q100OrelService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_Q100OREL)
	Q100OrelDao q100OrelDao;

	@Override
	public int qrelChecktabQ100code(String pvQV101,  String pvQV106, String pvQV109,String pvLOGIN){
		return q100OrelDao.qrelChecktabQ100code(pvQV101, pvQV106, pvQV109, pvLOGIN);
	}
}
