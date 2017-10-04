package com.ohhay.web.lego.service;

import java.util.List;

import com.ohhay.core.entities.E150D;
import com.ohhay.other.entities.O100;

/**
 * @author ThoaiNH create Feb 25, 2016 all function of designer packet
 */
public interface CustomerService {
	/**
	 * @param fo100:
	 *            user login fo100
	 * @param ev151:
	 *            id web current
	 * @param pvSEARC
	 * @param pnOFFSET
	 * @param pnLIMIT
	 * @param pvLogin
	 * @return
	 */
	List<O100> ohayListTabO100D(int fo100, String ev151, String pvSEARC, int pnOFFSET, int pnLIMIT, String pvLogin);

	/**
	 * @param pnFO100C
	 * @param pnFO100D
	 * @param pvEV144
	 * @param pvLogin
	 * @return
	 */
	int insertTabE100(int pnFO100C, int pnFO100D, String pvLogin);

	/**
	 * @param pnPE150
	 * @param pvEV151
	 * @param pvEV152
	 * @param pvEV153
	 * @param pvEV154
	 * @param pnFE100
	 * @param pvLOGIN
	 * @return
	 */
	int insertTabE150(int pnPE150, String pvEV151, String pvEV152, String pvEV153, String pvEV154, int pnFE100,
			String pvLOGIN);

	/**
	 * @param fo100c
	 * @param fo100d
	 * @param webID
	 * @param boxIDPattern
	 * @param note
	 * @param pvLogin
	 * @return
	 */
	int shareForDes(int fo100c, int fo100d, long webID, String boxIDPattern, String note, String pvLogin);

	/**
	 * @param fo100
	 * @param ev151
	 * @param pvLogin
	 * @return
	 */
	List<E150D> listOfTabE150C(int fo100, String ev151, String pvLogin);

	/**
	 * @param fe150
	 * @param pvLogin
	 * @return
	 */
	int stornoTabE150(int fe150, String pvLogin);

	/**
	 * @param pe150
	 * @param pvLogin
	 * @return
	 */
	int confirmTabE150(int fo100Cus, int fo100Des, int pe150, int fe400Cus, String pvLogin);

	/**
	 * @param fo100Cus
	 * @param fo100Des
	 * @param fe400Cus
	 * @param fe400Des
	 * @return
	 */
	int applySiteOfDes(int fo100Cus, int fo100Des, int fe400Cus, int fe400Des, int fe150);
	/**
	 * @param fo100
	 * @param fe150
	 * @param ev161
	 * @return
	 */
	int rejectSiteOfDes(int fo100, int fe150, String ev161);
}
