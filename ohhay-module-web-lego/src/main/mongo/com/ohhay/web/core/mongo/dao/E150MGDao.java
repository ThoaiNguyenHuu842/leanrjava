package com.ohhay.web.core.mongo.dao;

import java.util.List;

import com.ohhay.web.lego.entities.mongo.web.E150MG;

/**
 * @author TuNt
 * create date Nov 18, 2016
 * ohhay-module-web-lego
 */
public interface E150MGDao {
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<E150MG> listOfTabCus(int fo100, String pvSearch, int offset, int limit);
	/**
	 * @param fo100d
	 * @param fo100c
	 * @param pvSearch
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<E150MG> listOfTabSitesOfCus(int fo100d, int fo100c, int offset, int limit);
	/**
	 * @param fo100
	 * @param pe150
	 * @return
	 */
	public int storNoTab(int fo100,int pe150);
	/**
	 * @param fo100d
	 * @param pe150
	 * @return
	 */
	public String confirmTabE150(int fo100d, int pe150, int pe400); 
	/**
	 * @param fo100
	 * @param pe150
	 * @return
	 */
	public E150MG listOfTabSitesOfCusDetail(int fo100,int pe150);
}
