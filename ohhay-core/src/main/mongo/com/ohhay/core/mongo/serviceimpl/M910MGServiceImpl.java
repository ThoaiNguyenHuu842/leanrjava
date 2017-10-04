package com.ohhay.core.mongo.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M910MG;
import com.ohhay.core.mongo.dao.M910MGDao;
import com.ohhay.core.mongo.service.M910MGService;
import com.ohhay.core.mongo.service.TemplateService;

@Service(value = SpringBeanNames.SERVICE_NAME_M910MG)
@Scope("prototype")
public class M910MGServiceImpl implements M910MGService {
	private static Logger log = Logger.getLogger(M910MGServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_M910MG)
	M910MGDao m910mgDao;
	
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	TemplateService templateMGService;


	@Override
	public List<M910MG> listOfShare(int fo100, int offset, int limit, String pvSearch){
		// TODO Auto-generated method stub
		return m910mgDao.listOfShare(fo100, offset, limit, pvSearch);
	} 
}
