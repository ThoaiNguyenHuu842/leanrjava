package com.ohhay.core.oracle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.oracle.dao.V220ORDao;
import com.ohhay.core.oracle.service.V220ORService;

/**
 * @author ThoaiNH
 * create May 6, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_V220OR)
@Scope("prototype")
public class V220ORServiceImpl implements V220ORService{

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V220OR)
	private V220ORDao v220orDao;

	@Override
	public String checkedTabV220OVN(int fo100, String uuid, String pvLogin) {
		// TODO Auto-generated method stub
		String uuidEncrypted = AESUtils.encrypt(uuid.substring(0, 16));
		return v220orDao.checkedTabV220OVN(fo100, uuidEncrypted, pvLogin);
	}

	@Override
	public int activateTabV220O(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return v220orDao.activateTabV220O(fo100, pvLogin);
	}

	
}
