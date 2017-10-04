package com.ohhay.core.mongo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.N700MG;
import com.ohhay.core.mongo.dao.N700MGDao;
import com.ohhay.core.mongo.service.N700MGService;

@Service(value=SpringBeanNames.SERVICE_NAME_N700MG)
@Scope("prototype")
public class N700ServiceImpl implements N700MGService{

	@Autowired
	N700MGDao n700mgDao;
	 
	/* (non-Javadoc)
	 * @see com.ohhay.core.mongo.service.N700MGService#updateTabN700(int, int, java.lang.String)
	 */
	@Override
	public int updateTabN700(int typeDb, int fo100, String objectName) {
		// TODO Auto-generated method stub
		return n700mgDao.updateTabN700(typeDb, fo100, objectName);
	}

	/* (non-Javadoc)
	 * @see com.ohhay.core.mongo.service.N700MGService#getValueOfKey(int, int, java.lang.String)
	 */
	@Override
	public int getValueOfKey(int typeDb, int fo100, String objectName) {
		// TODO Auto-generated method stub
		return n700mgDao.getValueOfKey(typeDb, fo100, objectName);
	}

	/* (non-Javadoc)
	 * @see com.ohhay.core.mongo.service.N700MGService#listOfTabN700(int, int)
	 */
	@Override
	public N700MG listOfTabN700(int typeDb, int fo100) {
		// TODO Auto-generated method stub
		return n700mgDao.listOfTabN700(typeDb, fo100);
	}

}
