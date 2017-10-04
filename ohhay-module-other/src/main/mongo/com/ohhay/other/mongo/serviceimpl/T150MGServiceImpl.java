package com.ohhay.other.mongo.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mongo.dao.T150MGDao;
import com.ohhay.other.mongo.service.T150MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_T150SMG)
@Scope("prototype")
public class T150MGServiceImpl implements T150MGService {
	private static Logger log = Logger.getLogger(T150MGServiceImpl.class);

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_T150SMG)
	T150MGDao t150mgDao;

	@Override
	public int updateTabT150(int pnFO100, int pnFO100S, String pnTV151) {
		// TODO Auto-generated method stub
		return t150mgDao.updateTabT150(pnFO100, pnFO100S, pnTV151);
	}
}
