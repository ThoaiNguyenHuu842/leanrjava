package com.ohhay.other.mysql.dao;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.ChartItemInfo2;

/**
 * @author ThoaiNH create 03/04/2015 tracking service
 */
public interface R900DaoNew {
	List<ChartItemInfo2> reportTabR950V(int pnFo100, int pnRN905, int pnRN906, int pnINTER, Date pdFRDAT, Date pdTODAT, String pvLogin);

	List<ChartItemInfo2> reportTabR950B(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin);

	List<ChartItemInfo2> reportTabR950D(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin);

	List<ChartItemInfo2> reportTabR950L(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin);

	List<ChartItemInfo2> reportTabR950S(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin);

	List<ChartItemInfo2> reportTabR950J(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin);

	List<ChartItemInfo2> reportTabR950U(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin);

	/*
	 * cronjob
	 */

	/**
	 * @param pvLogin
	 * @return
	 */
	int statGetCountTabR950(String pvLogin);

	/**
	 * @param pvLogin
	 * @return
	 */
	<T> List<T> cronjReportTabR900B(Class<T> mapping, String pvLogin);

	/**
	 * @param pvLogin
	 * @return
	 */
	<T> List<T> cronjReportTabR900D(Class<T> mapping, String pvLogin);

	/**
	 * @param pvLogin
	 * @return
	 */
	<T> List<T> cronjReportTabR900L(Class<T> mapping, String pvLogin);

	/**
	 * @param pvLogin
	 * @return
	 */
	<T> List<T> cronjReportTabR900S(Class<T> mapping, String pvLogin);

	/**
	 * @param pvLogin
	 * @return
	 */
	<T> List<T> cronjReportTabR900U(Class<T> mapping, String pvLogin);

	/**
	 * @param pvLogin
	 * @return
	 */
	<T> List<T> cronjReportTabR900J(Class<T> mapping, String pvLogin);

}
