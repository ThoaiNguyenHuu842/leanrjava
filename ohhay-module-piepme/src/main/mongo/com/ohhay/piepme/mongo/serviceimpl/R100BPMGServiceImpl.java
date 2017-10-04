package com.ohhay.piepme.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.R100BPMGDao;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01;
import com.ohhay.piepme.mongo.service.R100BPMGService;

/**
 * @author TuNt
 * create date Mar 15, 2017
 * ohhay-module-piepme
 */
@Service(value = SpringBeanNames.SERVICE_NAME_R100BP)
@Scope("prototype")
public class R100BPMGServiceImpl implements R100BPMGService{

	@Autowired
	private R100BPMGDao r100bpmgDao;
	@Override
	public List<R100PSta01> listOfTabR100B01(int fo100, int fp300) {
		// TODO Auto-generated method stub
		return r100bpmgDao.listOfTabR100B01(fo100, fp300);
	}
	@Override
	public int insertTabR100B(int fo100, int fp300, int fo100t, int rn101, String rv102) {
		// TODO Auto-generated method stub
		return r100bpmgDao.insertTabR100B(fo100, fp300, fo100t, rn101, rv102);
	}

}
