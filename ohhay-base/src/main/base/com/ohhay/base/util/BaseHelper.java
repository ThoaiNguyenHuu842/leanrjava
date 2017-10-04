package com.ohhay.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.DB;

/**
 * @author ThoaiNH
 * create 12/12/2014
 * spring xml config class
 */
public class BaseHelper {
	public static ApplicationContext context = new ClassPathXmlApplicationContext("SpringOhhayContext.xml");
}
