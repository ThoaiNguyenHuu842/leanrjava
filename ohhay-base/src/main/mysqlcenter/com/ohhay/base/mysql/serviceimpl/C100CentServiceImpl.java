package com.ohhay.base.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.mysql.dao.C100CentDao;
import com.ohhay.base.mysql.service.C100CentService;
import com.ohhay.base.util.AESCenterUtil;

@Service(value = SpringBeanNames.SERVICE_NAME_C100CENT)
@Scope("prototype")
public class C100CentServiceImpl implements C100CentService{
	@Autowired
	C100CentDao c100CentDao;
	@Override
	public String getValTabC100Con(String pvLogin) {
		// TODO Auto-generated method stub
		return c100CentDao.getValTabC100Con(pvLogin);
	}
	@Override
	public String getValTabC100MySQL(String pvLogin) {
		// TODO Auto-generated method stub
		return AESCenterUtil.decrypt(c100CentDao.getValTabC100MySQL(pvLogin), 72);
	}
	@Override
	public String getValTabC100Web(String pvLogin) {
		// TODO Auto-generated method stub
		return AESCenterUtil.decrypt(c100CentDao.getValTabC100Web(pvLogin), 72);
	}
	@Override
	public String getValTabC100Piepme(String cv101, String pvLogin) {
		// TODO Auto-generated method stub
		return AESCenterUtil.decrypt(c100CentDao.getValTabC100Piepme(cv101, pvLogin), 72);
	}
	@Override
	public String getValTabC100PiepCent(String cv101, String pvLogin) {
		// TODO Auto-generated method stub
		return null;
	}
}
