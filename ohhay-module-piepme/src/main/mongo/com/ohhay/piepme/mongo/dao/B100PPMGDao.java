package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.blockaccount.B100PPMG;

/**
 * @author TuNt
 * create date May 12, 2017
 * ohhay-module-piepme
 */
public interface B100PPMGDao {
	/**
	 * lay danh sach user dang bi block theo pnFO100 (public)
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<B100PPMG> getListB100P(int fo100, int offset, int limit);
}
