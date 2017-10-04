package com.ohhay.other.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.R800MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.other.mongo.dao.R800MGDao;
import com.ohhay.other.mongo.service.R800MGService;


@Service(value = SpringBeanNames.SERVICE_NAME_R800MG)
@Scope("prototype")
public class R800MGServiceImpl implements R800MGService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_R800MG)
	R800MGDao r800mgDao;
	@Autowired
	TemplateService templateService;
	
	@Override
	public int insertTabR800(int fo100, int fm150,int fo100r,String rv801){
		// TODO Auto-generated method stub
		return r800mgDao.insertTabR800(fo100, fm150, fo100r, rv801);
	}
	@Override
	public List<R800MG> getListOfTabR800(int fo100, int offset, int limit){
		return r800mgDao.getListOfTabR800(fo100, offset, limit);
	}
}
