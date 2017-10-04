package com.ohhay.piepme.mysql.dao;

/**
 * @author ThoaiNH
 * create Jul 21, 2016
 */
public interface T000Dao {
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int insertTabT000(int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int getValTabT000(int fo100, String pvLogin);
	/**
	 * @param pnFO100
	 * @param pnSOTTU
	 * @param pvTV002
	 * @param pvLogin
	 * @return
	 */
	int checkTiTabT000(int pnFO100, int pnSOTTU, String pvTV002, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	String getTabTabT000(int fo100, String pvLogin);
}
