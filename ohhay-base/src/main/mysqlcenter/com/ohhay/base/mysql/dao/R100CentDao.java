package com.ohhay.base.mysql.dao;

import java.sql.Date;
import java.util.List;

import com.ohhay.base.entities.R100CentP;
import com.ohhay.base.entities.R100CentP2017Dash;
import com.ohhay.base.entities.R100CentP2017Eng;
import com.ohhay.base.entities.R100CentP2017Lifo;
import com.ohhay.base.entities.R100CentP2017Piep;
import com.ohhay.base.entities.R100CentP2017Revi;

/**
 * @author ThoaiNH
 * create Feb 15, 2016
 */
public interface R100CentDao {
	/**
	 * @param pvRV101
	 * @param pvRV103
	 * @param pvRV105
	 * @param pnFP100
	 * @param pnFO100V
	 * @param pnFO100K
	 * @param pvLOGIN
	 * @return
	 */
	int insertTabR100(String pvRV101, String pvRV103, String pvRV105, int pnFP100, int pnFO100V, int pnFO100K, String pvLOGIN);
	/**
	 * @param pnFP100
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP> listOfTabR100Pie(int pnFP100,String pvLogin);
	/**
	 * @param fo100k
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP> listOftabR100Dev(int fo100k,String pvLogin);
	/**
	 * @param fo100
	 * @param pdFRDAT
	 * @param pdTODAT
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP> listOfTabR100Wee(int fo100, Date pdFRDAT, Date pdTODAT, String pvLogin);
	/**
	 * @param fo100
	 * @param pdFRDAT
	 * @param pdTODAT
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP> listOfTabR100Hrs(int fo100, Date pdFRDAT, Date pdTODAT, String pvLogin);
	/**
	 * @param pdFRDAT
	 * @param pdTODAT
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP> listOfTabR100Vlf(Date pdFRDAT, Date pdTODAT, int limit, String pvLogin);
	/**
	 * @param fo100
	 * @param pdFRDAT
	 * @param pdTODAT
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP> listOfTabR100Det(int fo100, Date pdFRDAT, Date pdTODAT, String pvLogin);
	/**
	 * @param pdFRDAT
	 * @param pdTODAT
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	/**
	 * @param pdFRDAT
	 * @param pdTODAT
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP> listOfTabR100Sen(Date pdFRDAT, Date pdTODAT, int limit, String pvLogin);
	/**
	 * @param fo100
	 * @param pdFRDAT
	 * @param pdTODAT
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP> listOfTabR100Vie(int fo100, Date pdFRDAT, Date pdTODAT, int limit, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP2017Dash> listOfTabR100Dash(int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP2017Eng> listOfTabR100Eng(int fo100, int offset, int limit, String pvLogin);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP2017Revi> listOfTabR100Revi(int fo100, int offset, int limit, String pvLogin);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP2017Lifo> listOfTabR100Lifo(int fo100, int offset, int limit, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<R100CentP2017Piep> listOfTabR100Piep(int fo100, String pvLogin);
	/**
	 * @param pvRV101
	 * @param pvRV103
	 * @param pvRV105
	 * @param pvRV106
	 * @param pnRN107
	 * @param pnFP100
	 * @param pnFO100V
	 * @param pnFO100K
	 * @param pvLogin
	 * @return
	 */
	int insertTabR1002017(String pvRV101, String pvRV103, String pvRV105, String pvRV106, int pnRN107, int pnFP100, int pnFO100V, int pnFO100K, String pvLogin);
	/**
	 * @param pvRV101
	 * @param pvRV103
	 * @param pvRV105
	 * @param pvRV106
	 * @param pnRN107
	 * @param pnRN109
	 * @param pnFP100
	 * @param pnFO100V
	 * @param pnFO100K
	 * @param pvLogin
	 * @return
	 */
	int insertTabR1002017dis(String pvRV101, String pvRV103, String pvRV105, String pvRV106, int pnRN107, double pnRN109, int pnFP100, int pnFO100V, int pnFO100K, String pvLogin);
}
