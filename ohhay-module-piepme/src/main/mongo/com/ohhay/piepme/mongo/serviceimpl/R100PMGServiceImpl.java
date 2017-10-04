package com.ohhay.piepme.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.R100PMGDao;
import com.ohhay.piepme.mongo.dao.T150PMGDao;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01;
import com.ohhay.piepme.mongo.service.R100PMGService;
import com.ohhay.piepme.mongo.service.T150PMGService;

/**
 * @author ThoaiVt
 * date 21/09/2016
 */
@Service(value = SpringBeanNames.SERVICE_NAME_R100P)
@Scope("prototype")
public class R100PMGServiceImpl implements R100PMGService{
	@Autowired
	private R100PMGDao r100pmgDao;

	@Override
	public List<R100PSta01> listOfTabR10001(int fo100, int fp300) {
		// TODO Auto-generated method stub
		return r100pmgDao.listOfTabR10001(fo100, fp300);
	}
	
}
