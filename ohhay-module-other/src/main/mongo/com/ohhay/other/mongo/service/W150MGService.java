package com.ohhay.other.mongo.service;

import com.ohhay.other.entities.mongo.showtime.W150MG;

public interface W150MGService {
	int insertW150(W150MG w150);
	W150MG findOne(String phoneNumber);
	int updateW150(W150MG w150);
}
