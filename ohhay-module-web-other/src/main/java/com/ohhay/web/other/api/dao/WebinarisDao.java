package com.ohhay.web.other.api.dao;

import java.util.List;

import com.ohhay.web.core.entities.B050Wbn;

public interface WebinarisDao {
	List<B050Wbn> getListB050(String key);
	int confirmKey(String key);
	int insertTabW400(int fw400, String webniarKey);
	int stornoTabW400(int fw400);
}
