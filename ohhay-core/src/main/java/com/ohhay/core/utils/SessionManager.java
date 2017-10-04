package com.ohhay.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;

/**
 * @author ThoaiNH
 * create 22/12/2014
 * application session manager
 */
public class SessionManager {
	private static Logger log = Logger.getLogger(SessionManager.class);
	private static Map<String, Integer> mapAllUser = new HashMap<String, Integer>();
	public static Map<String, Integer> getMapAllUser(){
		return mapAllUser;
	}
	public static void put(String key, Integer value){
		log.info("---put session user:"+value);
		TemplateService  templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		if(value != null && value > 0)
		templateService.updateOneField(0, M900MG.class, value, QbMongoFiledsName.IS_ONLINE, "Y", null);
		mapAllUser.put(key, value);
	}
	public static void remove(String key){
		log.info("---remove session user:"+key);
		TemplateService  templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		Integer po100 = mapAllUser.get(key);
		if(po100 != null && po100 > 0)
			templateService.updateOneField(0, M900MG.class, po100, QbMongoFiledsName.IS_ONLINE, "N", null);
		mapAllUser.remove(key);
	}
}
