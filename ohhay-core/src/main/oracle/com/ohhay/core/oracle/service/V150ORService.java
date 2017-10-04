package com.ohhay.core.oracle.service;

import java.util.List;

import com.ohhay.core.entities.oracle.V150OR;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
public interface V150ORService {
	/**
	 * @param vn154
	 * @param pvLogin
	 * @return
	 */
	int createTabV150O(int vn154, String pvLogin);
	/**
	 * @param fv150
	 * @param vn152
	 * @param vn155
	 * @param vn156
	 * @param vn157
	 * @param vn158
	 * @param pvLogin
	 * @return
	 */
	int confirmTabV150O(int fv150, int vn152, int vn155, int vn156, int vn157, int vn158, String pvLogin);
	/**
	 * @param vn154
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<V150OR> listOfTabV150O(int vn154, int fo100,int offset, int limit, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int checkiTabV150O(int fo100, String pvLogin);
}
