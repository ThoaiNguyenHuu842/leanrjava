package com.ohhay.web.lego.service;

import java.util.List;

import com.ohhay.core.entities.E100D;
import com.ohhay.core.entities.E150D;

/**
 * @author ThoaiNH
 * create Feb 25, 2016
 * all function of designer packet
 */
public interface DesignerService {
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int updateTabO100D(int fo100, String pvLogin);
	/**
	 * @param pnFO100
	 * @param pvLogin
	 * @return
	 */
	List<E100D> listOfTabE100D(int pnFO100, String pvLogin);
	/**
	 * @param pnFE100
	 * @param pvLogin
	 * @return
	 */
	List<E150D> listOfTabE150D(int pnFE100, String pvLogin);
	
	/**
	 * @param fo100Des
	 * @param pe150
	 * @param fe400Cus
	 * @param fe400Backup
	 * @param pvLogin
	 * @return
	 */
	int finishTabE150(int fo100Des, int pe150, int fe400Cus, int fe400Backup, String pvLogin);
	/**
	 * @param fo100
	 * @param fe400c : web id of customer
	 * @return
	 */
	int updateVersionSite(int fo100, int fe400c);
	/**
	 * @param fo100
	 * @param fe400s : idWeb designer send to customer
	 * @param ev202  : note 
	 * @return
	 */
	String submitToCustomer(int fo100,int fe400s, String ev202, int fe150);
}
