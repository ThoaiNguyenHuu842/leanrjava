package com.ohhay.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mysql.dao.Q950Dao;
import com.ohhay.other.mysql.service.Q950Service;

@Service(value = SpringBeanNames.SERVICE_NAME_Q950)
@Scope("prototype")
public class Q950ServiceImpl implements Q950Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_Q950)
	private Q950Dao q950Dao;

	@Override
	public String orgToolGetIdParam(String pvFQ800, String pvFQ850,
			String pvFQ900, String pvLOGIN) {
		// TODO Auto-generated method stub
		return q950Dao.org_tools_getiparam(pvFQ800, pvFQ850, pvFQ900, pvLOGIN);
	}
}
