package com.ohhay.other.mysql.dao;

import java.util.List;

import com.ohhay.other.entities.W000;

public interface W000Dao {
	List<W000> listOfTabW000(String pvWV004, String pvLogin);
}
