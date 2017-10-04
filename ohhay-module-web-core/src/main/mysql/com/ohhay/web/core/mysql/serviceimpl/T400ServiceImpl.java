package com.ohhay.web.core.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.mysql.dao.T400Dao;
import com.ohhay.web.core.mysql.service.T400Service;
@Service(value = SpringBeanNames.SERVICE_NAME_T400)
@Scope("prototype")
public class T400ServiceImpl implements T400Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_T400)
	private T400Dao t400Dao;

	@Override
	public int insertTabT400(int pt400, String tv401, String tv402, String tv403, String tv404, String tv405, int fo100, int fc800, int fd000, String pvLogin) {
		// TODO Auto-generated method stub
		return t400Dao.insertTabT400(pt400, tv401, tv402, tv403, tv404, tv405, fo100, fc800, fd000, pvLogin);
	}

	@Override
	public int applyNewTemplate(int fo100, int pt400, String pvLogin) {
		// TODO Auto-generated method stub
		return t400Dao.applyNewTemplate(fo100, pt400, pvLogin);
	}
	

}
