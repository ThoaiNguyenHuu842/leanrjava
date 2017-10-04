package com.ohhay.core.oracle.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.V100OR;
import com.ohhay.core.oracle.dao.V080ORDao;
import com.ohhay.core.oracle.dao.V100ORDao;
import com.ohhay.core.oracle.service.V100ORService;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_V100OR)
@Scope("prototype")
public class V100ORServiceImpl implements V100ORService{

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V100OR)
	private V100ORDao v100orDao;
	@Override
	public int createTabV100O(int vn103, int vn104, String pvLogin) {
		// TODO Auto-generated method stub
		return v100orDao.createTabV100O(vn103, vn104, pvLogin);
	}
	@Override
	public List<V100OR> listOfTabV100(int vn103, int vn104, int fo100,int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return v100orDao.listOfTabV100(vn103, vn104, fo100,  offset,  limit,pvLogin);
	}
	
}
