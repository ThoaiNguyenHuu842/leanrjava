package com.ohhay.base.mysql.dao;

import java.sql.Date;

/**
 * @author ThoaiNH
 * create Jul 11, 2016
 */
public interface O100CentDao {
	/**
	 * @param pnFO100
	 * @param pvOV101
	 * @param pvOV102
	 * @param pvOV103
	 * @param pvOV104
	 * @param pvLogin
	 * @return
	 */
	int insertTabO100(int pnFO100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvLogin);
	/**
	 * @param pnFO100
	 * @param pvOV101
	 * @param pvOV102
	 * @param pvOV103
	 * @param pvOV104
	 * @param pvOV105
	 * @param pvLogin
	 * @return
	 */
	int insertTabO100EVO(int pnFO100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvOV105, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	String getValTabO100Web(int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	String getValTabO100Topic(int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	String getValTabO100MySql(int fo100, String pvLogin);
	/**
	 * @param ov101
	 * @param ov061
	 * @param pvLogin
	 * @return
	 */
	String getMoTabO100MySql(String ov101, String ov061, String pvLogin);
	/**
	 * @param po100
	 * @param pvOV101
	 * @param pvOV102
	 * @param pvOV103
	 * @param pvOV104
	 * @param pvOV105
	 * @param pdOD114
	 * @param pnON115
	 * @param pvOV116
	 * @param pvOV117
	 * @param pvOV118
	 * @param pdOD119
	 * @param pvOV130
	 * @param pvOV131
	 * @param pdOD132
	 * @param pvLOGIN
	 * @return
	 */
	String ohayInsertTabO100Bon(int po100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvOV105,  Date pdOD114,
			double pnON115, String pvOV116, String pvOV117, String pvOV118, Date pdOD119, String pvOV130, String pvOV131, Date pdOD132, String pvLOGIN);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	String getValTabO100Shop(int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	String getValTabO100Piepme(int fo100, String pvLogin);
	/**
	 * @param pnFO100
	 * @param pvOV101
	 * @param pvOV102
	 * @param pvOV103
	 * @param pvOV104
	 * @param pvOV110
	 * @param pvLOGIN
	 * @return
	 */
	int insertTabO100Piep(int pnFO100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvOV110, String pvLOGIN);
	/**
	 * @param pnFO100
	 * @param pvOV126
	 * @param pvLOGIN
	 * @return
	 */
	int updateTabO100Piepme(int pnFO100, String pvOV126, String pvLOGIN);
}
