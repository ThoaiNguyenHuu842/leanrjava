package com.ohhay.core.oracle.dao;

import java.util.List;

import com.ohhay.core.entities.oracle.N200OR;

/**
 * @author ThoaiVt 
 * @date 28/03/2017
 */
public interface N200ORDao {
	int insertTabN200VND(String pvNV201, String pvNV204, String pvNV207, String pvNV208, String pvNV209, String pnNV212, int pnFO100,String pvLOGIN);
	List<N200OR> listOfTabN200O(int fo100, String pvLogin);
}
