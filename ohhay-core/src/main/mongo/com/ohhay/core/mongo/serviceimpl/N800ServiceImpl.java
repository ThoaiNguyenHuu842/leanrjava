package com.ohhay.core.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.N800MG;
import com.ohhay.core.mongo.dao.N800MGDao;
import com.ohhay.core.mongo.service.N800MGService;

/**
 * @author TuNt
 * create date Dec 9, 2016
 * ohhay-core
 */
@Service(value=SpringBeanNames.SERVICE_NAME_N800MG)
@Scope("prototype")
public class N800ServiceImpl implements N800MGService{
	
	@Autowired
	N800MGDao n800mgDao;
	
	/* (non-Javadoc)
	 * @see com.ohhay.core.mongo.service.N800MGService#insertTabN800(int, int, int, java.lang.String, int, java.lang.String)
	 */
	@Override
	public int insertTabN800(int typeDb, int fo100, int fo100n, String nv801, int nn802, String nv803, String nv804) {
		// TODO Auto-generated method stub
		return n800mgDao.insertTabN800(typeDb, fo100, fo100n, nv801, nn802, nv803, nv804);
	}

	/* (non-Javadoc)
	 * @see com.ohhay.core.mongo.service.N800MGService#listOfTabN800(int, int, int, int)
	 */
	@Override
	public List<N800MG> listOfTabN800(int typeDb, int fo100, int offset, int limit, String nv804) {
		// TODO Auto-generated method stub
		return n800mgDao.listOfTabN800(typeDb, fo100, offset, limit, nv804);
	}

	@Override
	public int insertTabN800PieperComment(int typeDb, int fo100, String pieperType, int piperId, String nv801, int nn802,
			String nv803, String nv804) {
		// TODO Auto-generated method stub
		return n800mgDao.insertTabN800PieperComment(typeDb, fo100, pieperType, piperId, nv801, nn802, nv803, nv804);
	}

}
