package com.ohhay.piepme.mongo.service;

import java.util.Map;

/**
 * @author ThoaiNH
 * create Jun 20, 2017
 */
public interface N900PMGService {
	/**
	 * @param fo100
	 * @param key
	 * @param status
	 * @return
	 */
	int updateNotification(int fo100, String key, int status);
	/**
	 * @param fo100
	 * @param object
	 * @return
	 */
	int updateNotificationMulti(int fo100, Map<String, Integer> objects);
	/**
	 * @param fo100
	 * @param fo100f
	 * @param objectName
	 * @param status
	 * @return
	 */
	int updateNotificationV2(int fo100, int fo100f, String objectName, int status);
}
