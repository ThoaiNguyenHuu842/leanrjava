package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.reportpieper.B200BPMG;

/**
 * @author TuNt
 * create date May 12, 2017
 * ohhay-module-piepme
 */
public interface B200BPMGDao {
	/**
	 *lay danh sach user dang bi report theo pieper (commercial)
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<B200BPMG> getListB200B(int fo100, int offset, int limit);

}
