package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.R100CSta1;

/**
 * @author ThoaiNH
 * create Mar 25, 2017
 */
public interface R100CPMGService {
	/**
	 * @param fo100
	 * @param fp300
	 * @return
	 */
	List<R100CSta1> listOfTabR100C01(int fo100, int fp300);
}
