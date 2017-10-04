package com.ohhay.core.mongo.dao;

import java.util.List;

import com.ohhay.core.entities.mongo.other.P950MG;

/**
 * @author TuNt
 * create date Jul 6, 2016
 * ohhay-core
 */
public interface P950MGDao {
	List<P950MG> listOfTabP950(int fo100, String src, int offset, int limit );
}
