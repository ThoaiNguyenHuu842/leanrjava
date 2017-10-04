package com.ohhay.other.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.M300;
import com.ohhay.other.mysql.dao.M300Dao;
import com.ohhay.other.mysql.service.M300Service;
@Service(value = SpringBeanNames.SERVICE_NAME_M300)
@Scope("prototype")
public class M300ServiceImpl implements M300Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_M300)
	private M300Dao  m300Dao;

	@Override
	public List<M300> autoFillProfile(String qv101, String email) {
		// TODO Auto-generated method stub
		return m300Dao.autoFillProfile(qv101,email);
	}

}
