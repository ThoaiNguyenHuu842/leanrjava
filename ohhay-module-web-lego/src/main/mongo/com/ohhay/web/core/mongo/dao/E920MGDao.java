package com.ohhay.web.core.mongo.dao;

import java.util.List;

import com.ohhay.web.lego.entities.mongo.web.E920MG;

/**
 * @author ThoaiNH create Jan 15, 2016
 */
public interface E920MGDao {
	/**
	 * @param fo100
	 * @param libType
	 * @param pvSearch
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<E920MG> listOfTabLibrary(int fo100, int libType, String pvSearch, int offset, int limit);

	/**
	 * @param pe920
	 * @return
	 */
	int incTotalAdd(int pe920, int fo100);

	/**
	 * @param pe920
	 * @param fo100
	 * @param star
	 * @return
	 */
	String e920UpdateVote(int pe920, int fo100, int star);
	/**
	 * @param pe920
	 * @param fo100
	 * @return
	 */
	int e920ListOfTabVote(int pe920, int fo100);

}
