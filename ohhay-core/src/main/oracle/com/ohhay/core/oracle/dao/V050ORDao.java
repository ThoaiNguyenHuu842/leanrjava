package com.ohhay.core.oracle.dao;

import java.util.List;

import com.ohhay.core.entities.oracle.V050OR;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
public interface V050ORDao {
	/**
	 * @param fv050
	 * @param vn051
	 * @param vn052
	 * @param vn053
	 * @param vv054
	 * @param vv055
	 * @param fv000
	 * @param pvLogin
	 * @return
	 */
	int insertTabV050O(int fv050, int vn051, int vn052, int vn053, String vv054, String vv055, int fv000, String pvLogin);
	/**
	 * @param fv000
	 * @param pvLogin
	 * @return
	 */
	int confirmTabV050(int fv050, String pvLogin);
	/**
	 * @param fv000
	 * @param pvLogin
	 * @return
	 */
	List<V050OR> listOfTabV050(int fv050, int offset, int limit,String pvLogin);
}
