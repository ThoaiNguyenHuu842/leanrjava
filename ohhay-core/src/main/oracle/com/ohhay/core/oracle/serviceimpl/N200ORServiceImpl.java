package com.ohhay.core.oracle.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.N200OR;
import com.ohhay.core.oracle.dao.N200ORDao;
import com.ohhay.core.oracle.service.N200ORService;

/**
 * @author ThoaiVt
 * Mar 28, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_N200OR)
@Scope("prototype")
public class N200ORServiceImpl implements N200ORService{

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_N200OR)
	private N200ORDao n200orDao;
	
	@Override
	public int insertTabN200VND(String pvNV201, String pvNV204, String pvNV207, String pvNV208, String pvNV209, String pnNV212,
			int pnFO100, String pvLOGIN) {
		// TODO Auto-generated method stub
		return n200orDao.insertTabN200VND(pvNV201, pvNV204, pvNV207, pvNV208, pvNV209, pnNV212, pnFO100, pvLOGIN);
	}

	@Override
	public List<N200OR> listOfTabN200O(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return n200orDao.listOfTabN200O(fo100, pvLogin);
	}

}
