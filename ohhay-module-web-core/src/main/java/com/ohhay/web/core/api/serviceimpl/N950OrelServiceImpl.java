package com.ohhay.web.core.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.api.dao.N950OrelDao;
import com.ohhay.web.core.api.service.N950OrelService;

@Service(value = SpringBeanNames.SERVICE_NAME_N950OREL)
@Scope("prototype")
public class N950OrelServiceImpl implements N950OrelService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_N950OREL)
	N950OrelDao n950OrelDao;

	@Override
	public int inserTabN950(int pnPN950, String pvNV951, String pvNV952, String pvNV953, String pvNV954, String pvNV955, String pvNV956, String pvNV957, String pvNV958, String pvNV959, int pnFN100, String pvLogin) {
		// TODO Auto-generated method stub
		return n950OrelDao
				.inserTabN950(pnPN950, pvNV951, pvNV952, pvNV953, pvNV954, pvNV955, pvNV956, pvNV957, pvNV958, pvNV959, pnFN100, pvLogin);
	}
}
