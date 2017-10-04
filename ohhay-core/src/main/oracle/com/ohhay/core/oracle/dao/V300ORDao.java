package com.ohhay.core.oracle.dao;

import java.sql.Date;

/**
 * @author TuNt 
 * create date Jun 30, 2017 
 * ohhay-core
 */
public interface V300ORDao {
	/**
	 * @param pv300
	 * @param vv301
	 * @param vv302
	 * @param vd303
	 * @param vd304
	 * @param vv305
	 * @param vv306
	 * @param vv308
	 * @param fo100
	 * @param login
	 * @return
	 */
	int insertTabV300(int pv300, String vv301, String vv302, Date vd303, Date vd304, String vv305, String vv306, String vv308,  int vn309, int fo100, String login);

	/**
	 * @param fo100
	 * @param vv308
	 * @param login
	 * @return FV300
	 */
	int checkedTabV300(int fo100, String vv308, String login);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	int stornoTabV300O(int fv300, String pvLogin);
	/**
	 * @param fv300
	 * @param vv307
	 * @param pvLogin
	 * @return
	 */
	int deActTabV300O(int fv300, String vv307, String pvLogin);
}
