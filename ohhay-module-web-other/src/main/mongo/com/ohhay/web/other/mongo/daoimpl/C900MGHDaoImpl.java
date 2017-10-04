package com.ohhay.web.other.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.other.mongo.dao.C900HMGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_C900MGH)
@Scope("prototype")
public class C900MGHDaoImpl extends QbMongoDaoSupport implements C900HMGDao {

	
}
