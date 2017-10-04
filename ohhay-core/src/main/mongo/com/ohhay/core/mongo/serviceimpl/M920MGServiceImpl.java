package com.ohhay.core.mongo.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M920MG;
import com.ohhay.core.mongo.dao.M920MGDao;
import com.ohhay.core.mongo.service.M920MGService;
import com.ohhay.core.mongo.service.TemplateService;

@Service(value = SpringBeanNames.SERVICE_NAME_M920MG)
@Scope("prototype")
public class M920MGServiceImpl implements M920MGService {
	private static Logger log = Logger.getLogger(M920MGServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_M920MG)
	M920MGDao m920mgDao;
	
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	TemplateService templateMGService;

	/*
	 * checkFriendStatus
	 * */
	public String checkFriendStatus(int fo100, int fo100c){
		return m920mgDao.checkFriendStatus(fo100, fo100c);
	}
	/*
	 * checkFollowStatus
	 * */
	public String checkFollowStatus(int fo100, int fo100f){
		return m920mgDao.checkFollowStatus(fo100, fo100f);
	}
	
	public List<M920MG> listOfTabM920Wait(int fo100c, int offset, int limit){
		return m920mgDao.listOfTabM920Wait(fo100c, offset, limit);
	}
	
	public int stornoTabM920(int fo100, int pm920){
		return m920mgDao.stornoTabM920(fo100, pm920);
	}
	
	public int stornoTabM920F(int fo100, int fo100c){
		return m920mgDao.stornoTabM920F(fo100, fo100c);
	}
	
}
