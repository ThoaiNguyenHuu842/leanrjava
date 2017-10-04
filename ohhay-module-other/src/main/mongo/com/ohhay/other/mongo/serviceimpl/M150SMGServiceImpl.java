package com.ohhay.other.mongo.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mongo.dao.M150SMGDao;
import com.ohhay.other.mongo.service.M150SMGService;

@Service(value = SpringBeanNames.SERVICE_NAME_M150SMG)
@Scope("prototype")
public class M150SMGServiceImpl implements M150SMGService {
	private static Logger log = Logger.getLogger(M150SMGServiceImpl.class);

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_M150SMG)
	M150SMGDao m150smgDao;
	@Override
	public int updateTabMn211(int pnFO100, int pnFM150, int pnMN211) {
		// TODO Auto-generated method stub
		return m150smgDao.updateTabMn211(pnFO100, pnFM150, pnMN211);
	}
	
}
