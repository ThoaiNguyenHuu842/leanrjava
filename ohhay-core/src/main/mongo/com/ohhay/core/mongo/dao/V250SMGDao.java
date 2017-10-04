package com.ohhay.core.mongo.dao;

import java.util.List;

import com.ohhay.core.entities.mongo.shop.V250SMG;

/**
 * @author ThoaiNH
 * create Jun 17, 2016
 */
public interface V250SMGDao {
	List<V250SMG> findV250Index(int limit);
}
