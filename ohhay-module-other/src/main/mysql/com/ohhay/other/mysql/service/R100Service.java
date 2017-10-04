package com.ohhay.other.mysql.service;

import java.util.List;

import com.ohhay.core.entities.ChartItemInfo;

/**
 * @author ThoaiNH
 * all report function
 * date create: 10/07/2014
 */
public interface R100Service {
	/**
	 * @param fo100
	 * @param colno: report type
	 * @param login
	 * @return
	 */
	int rhayInsertTabR100(int fo100, int colno, String login);
	/**
	 * @param fo100v
	 * @param fo100
	 * @param rv121
	 * @param pvLogin
	 * @return
	 */
	int rhayUpdateTabR100Vote(int fo100v, int fo100,String rv121 ,String pvLogin);
	/**
	 * @param fo100
	 * @param rn120
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo> rhayReportTabR1001(int fo100, int rn120, String pvLogin);
	/**
	 * @param fo100
	 * @param day
	 * @param month
	 * @param year
	 * @param total
	 * @param pvLogin
	 * @return
	 */
	int rhayUpdateTabR100Call(int fo100, int day, int month, int year, int total, String pvLogin);
	/**
	 * @param fo100s
	 * @param hoten
	 * @param fo100
	 * @param link
	 * @param pvLogin
	 * @return
	 */
	int updateTabR100Sha(int fo100s, String hoten, int fo100, String link, String pvLogin);
}
