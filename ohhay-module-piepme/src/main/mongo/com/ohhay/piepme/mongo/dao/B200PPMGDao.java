package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.reportpieper.B200PPMG;

/**
 * @author TuNt
 * create date May 12, 2017
 * ohhay-module-piepme
 */
public interface B200PPMGDao {
	/**
	 * lay danh sach user dang bi block theo pieper (public)
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<B200PPMG> getListB200P(int fo100, int offset, int limit);
}
