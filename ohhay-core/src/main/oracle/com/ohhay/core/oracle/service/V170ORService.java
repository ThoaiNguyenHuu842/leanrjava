package com.ohhay.core.oracle.service;

import java.util.List;

import com.ohhay.core.entities.oracle.V170OR;

/**
 * @author TuNt
 * create date Mar 16, 2017
 * ohhay-core
 */
public interface V170ORService {
	/**
	 * @param fp100
	 * @param fo100
	 * @param login
	 * @return
	 */
	String checkedTabV170(int fp100, int fo100, int fo100d, String login);
	/**
	 * @param fv170
	 * @param vv172
	 * @param vn175
	 * @param fo100u
	 * @param pvLogin
	 * @return
	 */
	int usedItTabV170O(int fv170, String vv172, int vn175, int fo100u,String pvLogin);
	/**
	 * @param fo100
	 * @param fp100
	 * @param fo100d
	 * @param pvLogin
	 * @return
	 */
	List<V170OR> listOfTabV170(int fo100, int fp100, int fo100d,int offset, int limit, String pvLogin);
}
