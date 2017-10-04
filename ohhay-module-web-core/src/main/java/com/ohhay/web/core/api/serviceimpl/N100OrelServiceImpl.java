package com.ohhay.web.core.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.api.dao.N100OrelDao;
import com.ohhay.web.core.api.service.N100OrelService;
@Service(value = SpringBeanNames.SERVICE_NAME_N100OREL)
@Scope("prototype")
public class N100OrelServiceImpl implements N100OrelService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_N100OREL)
	N100OrelDao n100OrelDao;

	@Override
	public int insertTabN100(String nv101, String nv102, String nv103, String nv104, String nv105, String nv106, String nv107, String qv106, String mv102) {
		// TODO Auto-generated method stub
		return n100OrelDao.insertTabN100(nv101, nv102,nv103, nv104, nv105, nv106, nv107, qv106, mv102);
	}
	
}
