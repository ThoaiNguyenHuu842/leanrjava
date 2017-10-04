package com.ohhay.piepme.mongo.dao;

/**
 * @author TuNt
 * create date Mar 6, 2017
 * ohhay-module-piepme
 */
public interface P150PMGDao {
	/**
	 * @param fo100
	 * @param pv152
	 * @return
	 */
	String insertTabP150(int fo100, String pv152);

	/**
	 * @param fo100
	 * @param otpCode1
	 * @param otpCode2
	 * @param pv152
	 * @return
	 */
	String confirmTabP150(int fo100, String otpCode, String pv152);
}
