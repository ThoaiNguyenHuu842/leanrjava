package com.ohhay.other.mongo.dao;

import com.ohhay.other.entities.mongo.showtime.W150MG;

public interface W150MGDao {
	int insertW150(W150MG w150);
	int updateW150(W150MG w150);
	W150MG findOne(String phoneNumber);
}
