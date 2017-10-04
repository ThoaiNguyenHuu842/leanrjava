package com.ohhay.core.oracle.service;

import java.util.List;

import com.ohhay.core.entities.oracle.V100OR;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
public interface V100ORService {
	/**
	 * @param vn103
	 * @param vn104
	 * @param pvLogin
	 * @return
	 */
	int createTabV100O(int vn103, int vn104, String pvLogin);
	/**
	 * @param vn103
	 * @param vn104
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<V100OR> listOfTabV100(int vn103, int vn104, int fo100, int offset, int limit,String pvLogin);
}
