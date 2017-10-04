package com.ohhay.other.mongo.dao;

import java.util.List;

import com.ohhay.other.entities.mongo.domain.U920MG;

/**
 * dao for domain management function
 * @author ThoaiNH
 * create Apr 21, 2016
 */
public interface U920MGDao {
	/**
	 * @param offset
	 * @param limit
	 * @param un923
	 * @return
	 */
	List<U920MG>  listOfTabU920(int offset, int limit, int un923);
}
