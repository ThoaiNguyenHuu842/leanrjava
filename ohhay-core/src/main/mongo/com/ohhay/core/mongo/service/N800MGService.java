package com.ohhay.core.mongo.service;

import java.util.List;

import com.ohhay.core.entities.N800MG;
import com.ohhay.core.mongo.dao.N800MGDao;

/**
 * @author TuNt
 * create date Dec 9, 2016
 * ohhay-core
 */
public interface N800MGService {
	/**
	 * @param typeDb
	 * @param fo100
	 * @param fo100n
	 * @param nv801
	 * @param nn802
	 * @param nv803
	 * @param nv804
	 * @return
	 */
	int insertTabN800(int typeDb, int fo100,int fo100n, String nv801, int nn802, String nv803, String nv804);
	/**
	 * @param typeDb
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<N800MG> listOfTabN800(int typeDb, int fo100, int offset, int limit, String nv804);
	/**
	 * @param typeDb
	 * @param fo100
	 * @param pieperType
	 * @param piperId
	 * @param nv801
	 * @param nn802
	 * @param nv803
	 * @param nv804
	 * @return
	 */
	int insertTabN800PieperComment(int typeDb, int fo100,String pieperType, int piperId, String nv801, int nn802, String nv803, String nv804);
}
