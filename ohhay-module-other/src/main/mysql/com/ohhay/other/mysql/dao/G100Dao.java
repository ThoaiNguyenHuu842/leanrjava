package com.ohhay.other.mysql.dao;

/**
 * @author ThoaiNH
 * create Apr 1, 2016
 */
public interface G100Dao {
	/**
	 * @param pnFQ100
	 * @param pvGV101
	 * @param pvGV102
	 * @param pvLogin
	 * @return
	 */
	int gbonInsertTabG100(int pnFQ100, String pvGV101, String pvGV102, String pvLogin);
	/**
	 * @param fo100
	 * @param fq100
	 * @param pvLogin
	 * @return
	 */
	int gbonStornoTabG100(int fo100, int fq100, String pvLogin);
}
