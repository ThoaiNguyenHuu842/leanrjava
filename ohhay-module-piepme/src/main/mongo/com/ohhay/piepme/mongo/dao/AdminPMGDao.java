package com.ohhay.piepme.mongo.dao;

/**
 * @author ThoaiNH
 * create May 5, 2017
 */
public interface AdminPMGDao {
	/**
	 * @param fo100
	 * @return
	 */
	int createDBUser(int fo100);
	/**
	 * @param fo100
	 * @return
	 */
	int removeDBUser(int fo100);
	/**
	 * @return
	 */
	long getCurrentTime();
}
