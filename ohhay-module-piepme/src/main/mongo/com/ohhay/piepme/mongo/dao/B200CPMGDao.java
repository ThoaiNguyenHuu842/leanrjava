package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.reportpieper.B200CPMG;



/**
 * @author TuNt
 * create date May 12, 2017
 * ohhay-module-piepme
 */
public interface B200CPMGDao {
	/**
	 *lay danh sach user dang bi report theo pieper (Circle)
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<B200CPMG> getListB200C(int fo100, int offset, int limit);

}
