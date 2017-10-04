package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.R100PSta01;
/**
 * @author ThoaiNH
 * create Feb 24, 2017
 */
public interface R100PMGDao {
	/**
	 * @param fo100
	 * @param fp300
	 * @return
	 */
	List<R100PSta01> listOfTabR10001(int fo100, int fp300);
	
}
