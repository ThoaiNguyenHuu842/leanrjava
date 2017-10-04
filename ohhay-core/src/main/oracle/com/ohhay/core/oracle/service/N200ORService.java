package com.ohhay.core.oracle.service;

import java.util.List;

import com.ohhay.core.entities.oracle.N200OR;

/**
 * @author ThoaiVt 
 * Mar 28, 2017
 */
public interface N200ORService {
	/**
	 * @param pvNV201
	 * @param pvNV204
	 * @param pvNV207
	 * @param pvNV208
	 * @param pvNV209
	 * @param pnFO100
	 * @param pvLOGIN
	 * @return
	 */
	int insertTabN200VND(String pvNV201, String pvNV204, String pvNV207, String pvNV208, String pvNV209, String pnNV212, int pnFO100,
			String pvLOGIN);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<N200OR> listOfTabN200O(int fo100, String pvLogin);
}
