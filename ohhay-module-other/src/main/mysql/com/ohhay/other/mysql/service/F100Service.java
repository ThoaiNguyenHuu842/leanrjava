package com.ohhay.other.mysql.service;

/**
 * @author ThoaiNH
 * create Apr 1, 2016
 */
public interface F100Service {
	/**
	 * @param pnFQ100
	 * @param pvFV101
	 * @param pvFV102
	 * @param pvLogin
	 * @return
	 */
	int fbonInsertTabF100(int pnFQ100, String pvFV101, String pvFV102, String pvLogin);
	/**
	 * @param fo100
	 * @param fq100
	 * @param pvLogin
	 * @return
	 */
	int fbonStornoTabF100(int fo100, int fq100, String pvLogin);
}
