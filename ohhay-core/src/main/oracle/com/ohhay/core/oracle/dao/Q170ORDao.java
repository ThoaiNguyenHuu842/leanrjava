package com.ohhay.core.oracle.dao;

import java.util.List;

import com.ohhay.core.entities.oracle.Q170OR;

/**
 * @author ThoaiNH
 * create Aug 30, 2017
 */
public interface Q170ORDao {
	/**
	 * @param fq400
	 * @param fo100U
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int insertTabQ170(int fq400, int fo100U, int fo100, String pvLogin);
	/**
	 * @param fq400
	 * @param fo100U
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int stornoTabQ170(int fq400, int fo100U, int fo100, String pvLogin);
	/**
	 * @param fo100u
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<Q170OR> listOfTabQ170(int fo100u, int fo100, String pvLogin);
}
