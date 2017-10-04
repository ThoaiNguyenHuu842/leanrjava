package com.ohhay.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mysql.dao.F100Dao;
import com.ohhay.other.mysql.dao.M300Dao;
import com.ohhay.other.mysql.service.F100Service;
@Service(value = SpringBeanNames.SERVICE_NAME_F100)
@Scope("prototype")
public class F100ServiceImpl implements F100Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_F100)
	private F100Dao f100Dao;
	@Override
	public int fbonInsertTabF100(int pnFQ100, String pvFV101, String pvFV102, String pvLogin) {
		// TODO Auto-generated method stub
		return f100Dao.fbonInsertTabF100(pnFQ100, pvFV101, pvFV102, pvLogin);
	}

	@Override
	public int fbonStornoTabF100(int fo100, int fq100, String pvLogin) {
		// TODO Auto-generated method stub
		return f100Dao.fbonStornoTabF100(fo100, fq100, pvLogin);
	}

}
