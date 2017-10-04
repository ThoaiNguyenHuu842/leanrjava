package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.other.T300PMG;

/**
 * @author ThoaiNH
 * create Apr 10, 2017
 */
public interface T300PMGDao {
	/**
	 * @param fo100
	 * @return
	 */
	List<T300PMG> listOfTabT300(int fo100);
	
	/*
	 * test mapping auto
	 */
	List<T300PMG> listOfTabT300Test(int fo100);
}
