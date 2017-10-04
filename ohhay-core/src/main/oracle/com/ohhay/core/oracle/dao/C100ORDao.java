package com.ohhay.core.oracle.dao;

import java.util.List;

import com.ohhay.core.entities.oracle.C100OR;

/**
 * @author ThoaiNH
 * create Jun 2, 2016
 */
public interface C100ORDao {
	/**
	 * @param pnPC100
	 * @param pvCV101
	 * @param pvCV102
	 * @param pvCV103
	 * @param pnFO100
	 * @param pnFO100C
	 * @param pvLOGIN
	 * @return
	 */
	int insertTabC100(int pnPC100, String pvCV101, String pvCV102, String pvCV103, int pnFO100, int pnFO100C, String pvLOGIN);
	/**
	 * @param pvCV102
	 * @param pnFO100C
	 * @param pvLogin
	 * @return
	 */
	List<C100OR> listOfTabC100(String pvCV102, int pnFO100C, String pvLogin);
}
