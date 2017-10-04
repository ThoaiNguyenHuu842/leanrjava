package com.ohhay.core.mongo.dao;

import java.util.List;

import com.ohhay.core.entities.mongo.other.P500MG;

/**
 * @author TuNt
 * create date Jul 13, 2016
 * ohhay-core
 */
public interface P500MGDao {
	List<P500MG> listOfTabP500(int offset, int limit);
}
