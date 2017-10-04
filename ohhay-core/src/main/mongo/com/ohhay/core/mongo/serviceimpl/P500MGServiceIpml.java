package com.ohhay.core.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.P500MG;
import com.ohhay.core.mongo.dao.P500MGDao;
import com.ohhay.core.mongo.service.P500MGService;

/**
 * @author TuNt
 * create date Jul 13, 2016
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_P500MG)
@Scope("prototype")
public class P500MGServiceIpml implements P500MGService{

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_P500MG)
	P500MGDao p500mgDao;

	@Override
	public List<P500MG> listOfTabP500(int offset, int limit) {
		// TODO Auto-generated method stub
		return p500mgDao.listOfTabP500( offset, limit);
	}

}
