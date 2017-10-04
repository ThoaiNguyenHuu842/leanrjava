package com.ohhay.core.oracle.service;

import java.util.List;

import com.ohhay.core.entities.oracle.V080OR;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
public interface V080ORService {
	/**
	 * @param vn081
	 * @param pvLogin
	 * @return
	 */
	int createTabV080O(int vn081, String pvLogin);
	/**
	 * @param vn081
	 * @param pvLogin
	 * @return
	 */
	int confirmTabV080O(int vn081, String pvLogin);
	/**
	 * @param vn081
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<V080OR> listOfTabV080(int vn081, int fo100,int offset, int limit,  String pvLogin);
}
