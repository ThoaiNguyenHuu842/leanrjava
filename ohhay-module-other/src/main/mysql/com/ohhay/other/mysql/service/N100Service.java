package com.ohhay.other.mysql.service;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.N100;

/**
 * @author ThoaiNH
 * create 12/09/2014
 * user info service
 */
public interface N100Service {
	int nhayUpdateTabN100(int pnFO100, String pvNV101, String pvNV102, String pvNV103, String pvNV104, 
			String pvNV105, String pvNV106, String pvNV107, Date pdND108, String pvNV109, int pnNN110, int pnNN111, int pnFD000, String pvLOGIN);
	/**
	 * @param pvNV119
	 * @param pvNV120
	 * @param pnFO100
	 * @param pvLOGIN
	 * @return -1 if email inviald, > 0 if success, orther is error
	 */
	int nhayUpdateTabN100Smtp(String pvNV116,String pvNV117,String pvNV118,String pvNV119,String pvNV120, int pnFO100, String pvLOGIN);
	int getValTabN100(int po100, String pvLogin);
	/**- date create - 28/07/2015
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<N100> nhayListOfTabN100Smtp(int fo100, String pvLogin);
	/**
	 * kiem tra user co setup smtp email hay k
	 * @param fo100
	 * @return 1: da setup 0: chua setup
	 */
	int checkUserHaveSmtpEmail(int fo100);
	
}
