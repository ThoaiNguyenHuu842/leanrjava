package com.ohhay.web.other.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.web.other.mysql.dao.W500Dao;
import com.ohhay.web.other.mysql.service.W500Service;

@Service(value = SpringBeanNames.SERVICE_NAME_W500)
@Scope("prototype")
public class W500ServiceImpl implements W500Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_W500)
	private W500Dao w500Dao;

	@Override
	public List<ComtabItem> listOfTabW500(int fo100, int fw400, String pvLogin) {
		// TODO Auto-generated method stub
		return w500Dao.listOfTabW500(fo100, fw400, pvLogin);
	}

	
}
