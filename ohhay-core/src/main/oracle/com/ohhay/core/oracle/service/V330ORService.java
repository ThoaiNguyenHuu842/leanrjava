package com.ohhay.core.oracle.service;

/**
 * @author TuNt 
 * create date Jun 30, 2017 
 * ohhay-core
 */
public interface V330ORService {
	/**
	 * @param pv330
	 * @param vv334
	 * @param fo100
	 * @param fv300
	 * @param login
	 * @return
	 */
	String insertTabV330(int pv330, String vv334, int fo100, int fv300, String pvLogin);

	/**
	 * @param vv334
	 * @param fo100
	 * @param login
	 * @return
	 */
	int usedItTabV330(String vv334, int fo100, String pvLogin);
}
