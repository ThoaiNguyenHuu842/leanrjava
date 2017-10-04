package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.R100PSta01;

/**
 * @author TuNt
 * create date Mar 15, 2017
 * ohhay-module-piepme
 */
public interface R100BPMGDao {
	/**
	 * @param fo100
	 * @param fp300
	 * @return
	 */
	List<R100PSta01> listOfTabR100B01(int fo100, int fp300);
	/**
	 * @param fo100
	 * @param fp300
	 * @param fo100t
	 * @param rn101
	 * @param rv102
	 * @return
	 */
	int insertTabR100B(int fo100, int fp300, int fo100t, int rn101, String rv102);
}
