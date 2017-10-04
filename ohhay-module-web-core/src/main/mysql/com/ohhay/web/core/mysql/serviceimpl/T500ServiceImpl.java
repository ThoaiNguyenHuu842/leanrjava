package com.ohhay.web.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.web.core.mysql.dao.T500Dao;
import com.ohhay.web.core.mysql.service.T500Service;
@Service(value = SpringBeanNames.SERVICE_NAME_T500)
@Scope("prototype")
public class T500ServiceImpl implements T500Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_T500)
	private T500Dao t500Dao;

	@Override
	public List<ComtabItem> listOfTabT500(int fo100, int ft400, String pvLogin) {
		// TODO Auto-generated method stub
		return t500Dao.listOfTabT500(fo100, ft400, pvLogin);
	}


}
