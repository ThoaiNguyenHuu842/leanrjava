package com.ohhay.other.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.entities.ItemTabN750;
import com.ohhay.other.mysql.dao.N750Dao;
import com.ohhay.other.mysql.service.N750Service;

@Service(value = SpringBeanNames.SERVICE_NAME_N750)
@Scope("prototype")
public class N750ServiceImpl implements N750Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_N750)
	N750Dao n750Se;
	
	@Override
	public List<ItemTabN750> nhayCombTabN750(String pvLOGIN) {
		// TODO Auto-generated method stub
		return n750Se.nhayCombTabN750(pvLOGIN);
	}

	@Override
	public List<ItemTabN750> nhayCombTabN750Set(String pvLOGIN) {
		// TODO Auto-generated method stub
		return n750Se.nhayCombTabN750Set(pvLOGIN);
	}

	@Override
	public List<ItemTabN750> listOfTabN750(String inLocation) {
		// TODO Auto-generated method stub
		return n750Se.listOfTabN750(inLocation);
	}
}
