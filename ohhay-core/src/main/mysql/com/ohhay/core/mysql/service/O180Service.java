package com.ohhay.core.mysql.service;

import java.util.List;

import com.ohhay.core.entities.O180;

/**
 * @author ThoaiNH
 * bookmark Dao
 */
public interface O180Service {
	int insertTabO180(int po180, String ov182, String ov183, String ov184, int fo100c, int fo100p, String pvLogin);
	int stornotabo180(int fo100p, int fo100c, String pvLogin);
	List<O180> listOfTabO180C(int fo100, int offset, int limit, String pvLogin);
}
