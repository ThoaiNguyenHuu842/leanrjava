package com.ohhay.piepme.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.R100VMGDao;
import com.ohhay.piepme.mongo.entities.other.R100VAMG;
import com.ohhay.piepme.mongo.entities.other.R100VMG;
import com.ohhay.piepme.mongo.service.R100VMGService;

/**
 * @author ThoaiVt
 * @date  07-07-2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_R100V)
public class R100VMGServiceImpl implements R100VMGService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_R100V)
	private R100VMGDao dao;
	
	
	@Override
	public R100VAMG listOfTabR100V01(int fo100, int pv300) {
		// TODO Auto-generated method stub
		return dao.listOfTabR100V01(fo100, pv300);
	}

	@Override
	public List<R100VMG> listOfTabR100V02(int fo100, int pv300, int offset, int limit) {
		// TODO Auto-generated method stub
		return dao.listOfTabR100V02(fo100, pv300, offset, limit);
	}

	@Override
	public List<R100VMG> listOfTabR100V03(int fo100, int pv300, int offset, int limit) {
		// TODO Auto-generated method stub
		return dao.listOfTabR100V03(fo100, pv300, offset, limit);
	}

	@Override
	public List<R100VMG> listOfTabR100V04(int fo100, int pv300, int offset, int limit) {
		// TODO Auto-generated method stub
		return dao.listOfTabR100V04(fo100, pv300, offset, limit);
	}

}
