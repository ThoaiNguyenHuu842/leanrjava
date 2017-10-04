package com.ohhay.web.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.web.core.mysql.dao.C500Dao;
import com.ohhay.web.core.mysql.service.C500Service;

@Service(value = SpringBeanNames.SERVICE_NAME_C500)
@Scope("prototype")
public class C500ServiceImpl implements C500Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_C500)
	private C500Dao c500Dao;

	@Override
	public List<ComtabItem> listOfTabC500(int fo100, int fc400, String pvLogin) {
		// TODO Auto-generated method stub
		return c500Dao.listOfTabC500(fo100, fc400, pvLogin);
	}

	

	
}
