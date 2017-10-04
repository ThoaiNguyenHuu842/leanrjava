package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.blockaccount.B100PPMG;

/**
 * @author ThoaiNH
 * create May 11, 2017
 */
public interface B100PPMGService {
	/**
	 * @param fo100
	 * @param fo100p
	 * @return
	 */
	int insertTabB100(int fo100, int fo100p);
	/**
	 * lay danh sach user dang bi block theo pnFO100 (public)
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<B100PPMG> getListB100P(int fo100, int offset, int limit);
	/**
	 * @param fo100
	 * @param fo100p
	 * @return
	 */
	int storNoTabB100(int fo100, int fo100p);
}
