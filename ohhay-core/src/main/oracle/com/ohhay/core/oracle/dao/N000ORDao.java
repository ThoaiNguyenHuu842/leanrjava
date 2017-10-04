package com.ohhay.core.oracle.dao;

import java.util.List;

import com.ohhay.core.entities.oracle.N000OR;

/**
 * @author TuNt create date Mar 7, 2017 ohhay-core
 */
public interface N000ORDao {

	/**
	 * @param uuid
	 * @param nv002
	 * @param fo100
	 * @param login
	 * @return
	 */
	int insertTabN000Pie(String uuid, String nv002, int fo100, String login);
	/**
	 * @param uuid
	 * @param login
	 * @return
	 */
	int checkitTabN000Pie(String uuid, String login);

	/**
	 * @param uuid
	 * @param nv002
	 * @param fo100
	 * @param login
	 * @return
	 */
	int updateTabN000Pie(String nv002, int fo100, String login);

	/**
	 * @param uuid
	 * @param login
	 * @return
	 */
	List<N000OR> listOfTabN000Pie(String uuid, String login);
	
	/**
	 * @param uuid
	 * @param login
	 * @return
	 */
	List<N000OR> listOfTabN000PieCA(String uuid, String login);
	
	/**
	 * @param uuid
	 * @param login
	 * @return
	 */
	List<N000OR> listOfTabN000PieDN(String uuid, String login);
}
