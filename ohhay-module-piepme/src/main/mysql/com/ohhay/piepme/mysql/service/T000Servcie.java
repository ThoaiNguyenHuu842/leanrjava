package com.ohhay.piepme.mysql.service;

/**
 * @author ThoaiNH
 * create Jul 25, 2016
 */
public interface T000Servcie {
	/**
	 * @param fo100
	 * @return
	 */
	String uploadTanListPDFToAWS(int fo100);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int insertTabT000(int fo100, String pvLogin);
}
