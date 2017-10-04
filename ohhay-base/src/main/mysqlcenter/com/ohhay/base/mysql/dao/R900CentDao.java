package com.ohhay.base.mysql.dao;

import java.sql.Date;
import java.util.List;

/**
 * @author ThoaiVt date 11/08/2016
 */
public interface R900CentDao {
	/**
	 * @param pnRN901
	 * @param pvBEZEI
	 * @param pnVALUE
	 * @param pvREART
	 * @param pvLogin
	 * @return
	 */
	int insertTabR900(int pnRN901, Object pvBEZEI, Object pnVALUE, String pvREART, String pvLogin);
}
