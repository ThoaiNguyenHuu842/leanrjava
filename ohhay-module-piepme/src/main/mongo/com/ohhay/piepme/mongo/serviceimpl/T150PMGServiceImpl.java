package com.ohhay.piepme.mongo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.T150PMGDao;
import com.ohhay.piepme.mongo.service.T150PMGService;

/**
 * @author ThoaiVt
 * date 21/09/2016
 */
@Service(value = SpringBeanNames.SERVICE_NAME_T150P)
@Scope("prototype")
public class T150PMGServiceImpl implements T150PMGService{
	@Autowired
	private T150PMGDao t150PmgDao;
	@Override
	public int insertTabT150(int pnFO100, int pnTV151) {
		// TODO Auto-generated method stub
		return t150PmgDao.insertTabT150(pnFO100, pnTV151);
	}

}
