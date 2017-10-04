package com.ohhay.core.oracle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.oracle.dao.V330ORDao;
import com.ohhay.core.oracle.service.V330ORService;

/**
 * @author TuNt
 * create date Jun 30, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_V330OR)
@Scope("prototype")
public class V330ORServiceImpl implements V330ORService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V330OR)
	private V330ORDao v330orDao;
	
	/* (non-Javadoc)
	 * @see com.ohhay.core.oracle.service.V330ORService#insertTabV330(int, java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public String insertTabV330(int pv330, String vv334, int fo100, int fv300, String login) {
		// TODO Auto-generated method stub
		return v330orDao.insertTabV330(pv330, vv334, fo100, fv300, login);
	}

	/* (non-Javadoc)
	 * @see com.ohhay.core.oracle.service.V330ORService#usedItTabV330(java.lang.String, int, java.lang.String)
	 */
	@Override
	public int usedItTabV330(String vv334, int fo100, String login) {
		// TODO Auto-generated method stub
		return v330orDao.usedItTabV330(vv334, fo100, login);
	}

}
