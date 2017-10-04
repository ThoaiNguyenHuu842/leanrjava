package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.R100CSta1;


/**
 * @author ThoaiNH
 * create Mar 25, 2017
 */
public interface R100CPMGDao {
	/**
	 * @param fo100
	 * @param fp300
	 * @return
	 */
	List<R100CSta1> listOfTabR100C01(int fo100, int fp300);
	/**
	 * @param fo100
	 * @param fp300
	 * @param fo100t
	 * @param rn101
	 * @param rv102
	 * @return
	 */
	int insertTabR100C(int fo100, int fp300, int fo100t, int rn101, String rv102);
}
