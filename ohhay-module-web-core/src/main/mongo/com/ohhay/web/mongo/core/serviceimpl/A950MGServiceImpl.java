package com.ohhay.web.mongo.core.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationRightInfo;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.DomainCriteria;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.A950MG;
import com.ohhay.web.core.mongo.dao.A950MGDao;
import com.ohhay.web.core.mongo.service.A950MGService;
@Service(value = SpringBeanNames.SERVICE_NAME_A950MG)
@Scope("prototype")
public class A950MGServiceImpl implements A950MGService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_A950MG)
	A950MGDao dao;
	
	@Override
	public int incCategory(int id) {
		// TODO Auto-generated method stub
		return dao.incCategory(id);
	}
	@Override
	public int decCategory(int id) {
		// TODO Auto-generated method stub
		return dao.decCategory(id);
	}
	@Override
	public List<A950MG> loadListA950(String type) {
		// TODO Auto-generated method stub
		return dao.loadListA950(type);
	}
}
