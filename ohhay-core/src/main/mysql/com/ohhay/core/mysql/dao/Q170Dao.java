package com.ohhay.core.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.Q170;

/**
 * @author ThoaiNH
 * date create: 06/07/2015
 */ 
public interface Q170Dao {
	List<Q170> listOfTabQ170(int fo100, int fq300, int fq400, String pvLogin);
}
