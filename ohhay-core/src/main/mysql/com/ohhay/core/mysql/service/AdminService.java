package com.ohhay.core.mysql.service;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.BonEvoAccount;
import com.ohhay.core.entities.ChartItemInfo2;

/**
 * @author ThoaiNH
 * create 18/06/2015
 * mysql admin service
 */
public interface AdminService {
	/**
	 * set draft web to home web
	 * @param pnFID00
	 * @param pvHERKU
	 * @param pnFC400
	 * @param pvLOGIN
	 * @return
	 */
	int adminSetNewTemplate(int pnFID00, String pvHERKU, int pnFC400, String pvLOGIN);
	/**
	 * get location of ip address
	 * @param ip
	 * @return
	 */
	String getIpOfLocation(String ip);
	/**
	 * report web o!hay
	 * @param pdDATEF
	 * @param pdDATET
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportWebDaily(Date pdDATEF, Date pdDATET, String pvLogin);
	/**
	 * @param po100
	 * @param fc800
	 * @param fd000
	 * @param pvLogin
	 * @return
	 */
	int adminUpdateTabO100(int po100, int fc800, int fd000, String pvLogin);
	/**
	 * @param fo100
	 * @param qv101
	 * @param pvLogin
	 * @return
	 */
	List<BonEvoAccount> listOfTabAccounts(int fo100, String qv101, String pvLogin);
}
