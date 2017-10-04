package com.ohhay.core.oracle.service;

/**
 * @author ThoaiNH
 * create May 6, 2017
 */
public interface V220ORService {
	/**
	 * @param fo100
	 * @param uuid
	 * @param pvLogin
	 * @return
	 */
	String checkedTabV220OVN(int fo100, String uuid, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int activateTabV220O(int fo100, String pvLogin);
}
