package com.ohhay.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mysql.dao.F100Dao;
import com.ohhay.other.mysql.dao.G100Dao;
import com.ohhay.other.mysql.service.G100Service;
@Service(value = SpringBeanNames.SERVICE_NAME_G100)
@Scope("prototype")
public class G100ServiceImpl implements G100Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_G100)
	private G100Dao g100Dao;
	@Override
	public int gbonInsertTabG100(int pnFQ100, String pvGV101, String pvGV102, String pvLogin) {
		// TODO Auto-generated method stub
		return g100Dao.gbonInsertTabG100(pnFQ100, pvGV101, pvGV102, pvLogin);
	}

	@Override
	public int gbonStornoTabG100(int fo100, int fq100, String pvLogin) {
		// TODO Auto-generated method stub
		return g100Dao.gbonStornoTabG100(fo100, fq100, pvLogin);
	}
	
}
