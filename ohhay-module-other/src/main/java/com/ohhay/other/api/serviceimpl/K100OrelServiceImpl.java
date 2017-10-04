package com.ohhay.other.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.api.dao.K100OrelDao;
import com.ohhay.other.api.service.K100OrelService;
@Service(value = SpringBeanNames.SERVICE_NAME_K100OREL)
@Scope("prototype")
public class K100OrelServiceImpl implements K100OrelService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_K100OREL)
	K100OrelDao k100OrelDao;

	@Override
	public String insertTabK100Orel(int pnPK100, String pvKV101, String pvKV102, String pvKV103, int pnKN104, int pnFO100, String pnOV101, String pvLogin) {
		// TODO Auto-generated method stub
		return k100OrelDao.insertTabK100Orel(pnPK100, pvKV101, pvKV102, pvKV103, pnKN104, pnFO100, pnOV101, pvLogin);
	}

}
