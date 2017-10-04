package com.ohhay.core.oracle.dao;

import java.util.List;

import com.ohhay.core.entities.oracle.V130OR;

/**
 * @author TuNt
 * create date Mar 16, 2017
 * ohhay-core
 */
public interface V130ORDao {

	/**
	 * @param fp100
	 * @param fo100
	 * @param login
	 * @return
	 */
	int createTabV130(int fp100, int fo100, String login);
	/**
	 * @param vn133
	 * @param fp100
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<V130OR> listOfTabV130(int vn133, int fp100, int fo100,int offset, int limit, String pvLogin);
}
