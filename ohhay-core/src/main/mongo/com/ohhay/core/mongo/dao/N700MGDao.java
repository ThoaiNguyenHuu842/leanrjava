package com.ohhay.core.mongo.dao;

import com.ohhay.core.entities.N700MG;

/**
 * @author TuNt
 * create date Dec 9, 2016
 * ohhay-core
 */
public interface N700MGDao {
	/**
	 * @param fo100
	 * @param objectName
	 * @return
	 */
	int updateTabN700(int typeDb, int fo100, String objectName);

	/**
	 * @param fo100
	 * @param objectName
	 * @return
	 */
	int getValueOfKey(int typeDb, int fo100, String objectName);
	
	/**
	 * @param typeDb
	 * @param fo100
	 * @return
	 */
	N700MG listOfTabN700(int typeDb, int fo100); 
}
