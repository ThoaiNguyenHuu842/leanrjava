package com.ohhay.web.other.mongo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.other.mongo.dao.C900HMGDao;
import com.ohhay.web.other.mongo.service.C900MGHService;

@Service(value = SpringBeanNames.SERVICE_NAME_C900MGH)
@Scope("prototype")
public class C900MGHServiceImpl implements C900MGHService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_C900MGH)
	C900HMGDao c900hmgDao;

}
