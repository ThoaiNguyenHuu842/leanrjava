package com.ohhay.other.mysql.dao;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.M300;
import com.ohhay.core.entities.N100;

public interface M300Dao {
	List<M300> autoFillProfile(String qv101, String email);
}
