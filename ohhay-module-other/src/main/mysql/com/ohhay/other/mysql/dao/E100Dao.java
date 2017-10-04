package com.ohhay.other.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.E100D;

/**
 * @author ThoaiNH
 * create Feb 24, 2016
 */
public interface E100Dao {
	int insertTabE100(int pnFO100C, int pnFO100D, String pvLogin);
	List<E100D> listOfTabE100D(int pnFO100, String pvLogin);
}
