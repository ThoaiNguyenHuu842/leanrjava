package com.ohhay.core.oracle.dao;

import java.util.List;

import com.ohhay.core.entities.oracle.T900OR;

/**
 * @author ThoaiVt
 * date 28/03/2017
 */
public interface T900ORDao {
	List<T900OR> listOfTabT900OPEN(int fo100,int offset, int limit, String pvLOGIN);
	List<T900OR> listOfTabT900CONF(int fo100,int offset, int limit, String pvLOGIN);
	List<T900OR> listOfTabT900APP(int fo100,int offset, int limit, String pvLOGIN);
	List<T900OR> listOfTabT900AFF(int fo100,int offset, int limit, String pvLOGIN);
}
