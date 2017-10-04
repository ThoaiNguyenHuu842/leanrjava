package com.ohhay.base.mysql.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.mysql.dao.R000CentDao;
import com.ohhay.base.mysql.dao.R900CentDao;
import com.ohhay.base.mysql.service.R900CentService;


/**
 * @author ThoaiVt
 * date 11/08/2016
 */

@Service(value = SpringBeanNames.SERVICE_NAME_R900CENT)
@Scope("prototype")
public class R900CentServiceImpl implements R900CentService{

	@Autowired
	@Qualifier(SpringBeanNames.REPOSITORY_NAME_R900CENT)
	private R900CentDao r900CentDao;

	@Override
	public int insertTabR900(int pnRN901, Object pvBEZEI, Object pnVALUE, String pvREART, String pvLogin) {
		// TODO Auto-generated method stub
		return r900CentDao.insertTabR900(pnRN901, pvBEZEI, pnVALUE, pvREART, pvLogin);
	}


}
