package com.ohhay.piepme.mongo.dao;

/**
 * @author TuNt
 * create date Jun 28, 2017
 * ohhay-module-piepme
 */
public interface N900PMGDao {
	/**
	 * @param fo100
	 * @param fo100f
	 * @param objectName
	 * @param status
	 * @return
	 */
	int updateNotification(int fo100, int fo100f, String objectName, int status);
}
