package com.ohhay.core.mysql.service;

import java.util.List;

import com.ohhay.core.entities.M350;

/**
 * @author ThoaiNH
 * create 28/12/2014
 * email service
 */
public interface M350Service {
	/**
	 * @param pnFO100: fo100 gui
	 * @param ov102: ten nguoi gui
	 * @param pvMV367: 
	 * @param pvMV375: subject
	 * @param pvMESSA: content
	 * @param pvLogin:
	 * @return
	 */
	int sendMailTabM350Confirm(int pnFO100,String ov102,String pvMV367,  String pvMV375, String pvMESSA, String pvLogin);
	/**
	 * @param pvLogin
	 * @return
	 */
	List<M350> listOfTabM350Send(String pvLogin);
	/**
	 * @param email
	 * @param pvLogin
	 * @return
	 */
	int sendMailTabM350Check(String email, String pvLogin);
}
