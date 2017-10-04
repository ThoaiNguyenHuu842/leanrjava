package com.ohhay.piepme.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.R100CPMGDao;
import com.ohhay.piepme.mongo.nestedentities.R100CSta1;
import com.ohhay.piepme.mongo.service.R100CPMGService;


/**
 * @author ThoaiNH
 * create Mar 25, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_R100C)
@Scope("prototype")
public class R100CPMGServiceImpl implements R100CPMGService{
	@Autowired
	private R100CPMGDao r100cpmgDao;
	@Override
	public List<R100CSta1> listOfTabR100C01(int fo100, int fp300) {
		// TODO Auto-generated method stub
		return r100cpmgDao.listOfTabR100C01(fo100, fp300);
	}
	
}
