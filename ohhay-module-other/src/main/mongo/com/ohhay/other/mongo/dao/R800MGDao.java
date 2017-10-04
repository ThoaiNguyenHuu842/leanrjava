package com.ohhay.other.mongo.dao;

import java.util.List;

import com.ohhay.core.entities.mongo.other.R800MG;

public interface R800MGDao {
	int insertTabR800(int fo100, int fm150,int fo100r,String rv801);
	List<R800MG> getListOfTabR800(int fo100, int offset, int limit);
}
