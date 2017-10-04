package com.ohhay.base.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.mysql.dao.R000CentDao;
import com.ohhay.base.mysql.service.R000CentService;

@Service(value = SpringBeanNames.SERVICE_NAME_R000CENT)
@Scope("prototype")
public class R000CentServiceImpl implements R000CentService{
	@Autowired
	@Qualifier(SpringBeanNames.REPOSITORY_NAME_R000CENT)
	private R000CentDao r000CentDao;

	@Override
	public int getValTabR000(String ip) {
		// TODO Auto-generated method stub
		return r000CentDao.getValTabR000(ip);
	}
}
