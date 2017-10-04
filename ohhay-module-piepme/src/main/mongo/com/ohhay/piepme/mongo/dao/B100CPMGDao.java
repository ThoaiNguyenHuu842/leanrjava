package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.blockaccount.B100CPMG;

/**
 * @author TuNt
 * create date May 12, 2017
 * ohhay-module-piepme
 */
public interface B100CPMGDao {
	/**
	 *lay danh sach user dang bi block theo pnFO100 (Circle)
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<B100CPMG> getListB100C(int fo100, int offset, int limit);

}
