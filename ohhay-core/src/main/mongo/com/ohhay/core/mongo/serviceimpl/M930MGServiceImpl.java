package com.ohhay.core.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.M150MG;
import com.ohhay.core.entities.mongo.other.UserOtag;
import com.ohhay.core.mongo.dao.M150MGDao;
import com.ohhay.core.mongo.dao.M930MGDao;
import com.ohhay.core.mongo.service.M150MGService;
import com.ohhay.core.mongo.service.M930MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_M930MG)
@Scope("prototype")
public class M930MGServiceImpl implements M930MGService {
	@Autowired
	private M930MGDao m930mgDao;
	@Override	
	public int insertTabM930(int fo100, String pnKey) {
		// TODO Auto-generated method stub
		return m930mgDao.insertTabM930(fo100, pnKey);
	}

}
