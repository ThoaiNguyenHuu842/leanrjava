package com.ohhay.core.mongo.service;

import java.util.List;

import com.ohhay.core.entities.mongo.other.P500MG;

public interface P500MGService {
	List<P500MG> listOfTabP500(int offset, int limit);
}
