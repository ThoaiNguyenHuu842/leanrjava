package com.ohhay.other.mysql.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.RegexConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.N100;
import com.ohhay.core.mysql.daoimpl.Q100DaoImpl;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mysql.dao.N100Dao;
import com.ohhay.other.mysql.service.N100Service;
@Service(value = SpringBeanNames.SERVICE_NAME_N100)
@Scope("prototype")
public class N100ServiceImpl implements N100Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_N100)
	private N100Dao  n100Dao;
	@Override
	public int nhayUpdateTabN100(int pnFO100, String pvNV101, String pvNV102, String pvNV103, 
			String pvNV104, String pvNV105, String pvNV106, String pvNV107, Date pdND108, String pvNV109,int pnNN110, int pnNN111, int pnFD000, String pvLOGIN) {
		// TODO Auto-generated method stub
		return n100Dao.nhayUpdateTabN100(pnFO100, pvNV101, pvNV102, pvNV103, pvNV104, pvNV105, pvNV106, pvNV107, pdND108, pvNV109,pnNN110,pnNN111,pnFD000, pvLOGIN);
	}
	@Override
	public int getValTabN100(int po100, String pvLogin) {
		// TODO Auto-generated method stub
		return n100Dao.getValTabN100(po100,pvLogin);
	}
	@Override
	public int nhayUpdateTabN100Smtp(String pvNV116,String pvNV117,String pvNV118,String pvNV119,String pvNV120, int pnFO100, String pvLOGIN){
		// TODO Auto-generated method stub
		 if (ApplicationHelper.validateRegex(pvNV120, RegexConstant.EMAIL_PATTERN) == false)
			 return -1;
		return n100Dao.nhayUpdateTabN100Smtp(pvNV116,pvNV117,pvNV118,AESUtils.encrypt(pvNV119), pvNV120, pnFO100, pvLOGIN);
	}
	@Override
	public List<N100> nhayListOfTabN100Smtp(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return n100Dao.nhayListOfTabN100Smtp(fo100, pvLogin);
	}
	@Override
	public int checkUserHaveSmtpEmail(int fo100) {
		// TODO Auto-generated method stub
		N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
		N100 n100 = null;
		try{
			n100 = n100Service.nhayListOfTabN100Smtp(fo100,ApplicationConstant.PVLOGIN_ANONYMOUS).get(0);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		if(n100 != null && n100.getNv120() != null && n100.getNv120().isEmpty() == false)
			return 1;
		return 0;
	}
}
