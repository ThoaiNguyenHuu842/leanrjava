package com.ohhay.web.core.mongo.service;

import java.util.List;

/**
 * @author ThoaiNH
 * create 08/09/2015
 */
public interface WebToolService {
	/**
	 * get all web url user can set to link element (not web draft and web draft child)
	 * @param fo100
	 * @return
	 */
	List<String> getAllWebUrls(int fo100);
	
	/**
	 * get all web of user
	 * @param fo100
	 * @return
	 */
	List<String> getAllWebLink(int fo100);
	/**
	 * create 14/09/2015
	 * get total web of user
	 * @param fo100
	 * @return
	 */
	int getTotalWebLink(int fo100);
}
