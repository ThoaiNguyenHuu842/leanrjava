package com.ohhay.core.oracle.service;

import java.util.List;

import com.ohhay.core.entities.oracle.V000OR;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
public interface V000ORService {
	/**
	 * @param fv000
	 * @param vn001
	 * @param vn002
	 * @param vn003
	 * @param vv004
	 * @param vv005
	 * @param pvLogin
	 * @return
	 */
	int insertTabV000O(int fv000, int vn001, int vn002, int vn003, String vv004, String vv005, String pvLogin);
	/**
	 * @param fv000
	 * @param pvLogin
	 * @return
	 */
	int confirmTabV000(int fv000, String pvLogin);
	/**
	 * @param pvLogin
	 * @return
	 */
	List<V000OR> listOfTabV000(String pvLogin);
}
