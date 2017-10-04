package com.ohhay.other.mysql.service;

import java.util.List;

import com.ohhay.core.entities.ChartItemInfo2;

/**
 * @author ThoaiNH
 * create 03/04/2015
 * tracking service
 */
public interface R900Service {
	/**
	 * insert tracking
	 * @param fo100
	 * @param pvRV902
	 * @param pvRV904
	 * @param pvRV907
	 * @param pvRV908
	 * @param pvRV958
	 * @param pvRV959
	 * @param pvRV960
	 * @param pnREFID
	 * @param pnFO100T
	 * @param pvLogin
	 * @return
	 */
	int insertTabR900(int fo100, String pvRV902, String pvRV904, String pvRV907, String pvRV908, String pvRV958, int pnREFID, int pnFO100T, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV902
	 * @param pvRV904
	 * @param pnRN905
	 * @param pvRV907
	 * @param pvRV908
	 * @param pvRV913
	 * @param pvRV914
	 * @param pvRV915
	 * @param pvLOGIN
	 * @return
	 */
	int insertTabR900a(int fo100, String pvRV901, String pvRV902, String pvRV904, int pnRN905, String pvRV907, String pvRV908, String pvRV913, String pvRV914, String pvRV915, String pvLOGIN);
	/**
	 * 
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9001(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9002(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9003(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9004(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9005(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9006(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9007(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9008(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9009(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	int insertTabR900V1(int fo100, String pvRV902, String pvRV904, String pvRV907, String pvRV908, String pvRV958,String pvRV959,String pvRV960, int pnREFID, int pnFO100T, String pvLogin);
	/**
	 * 
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9010(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9011(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9012(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9013(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9014(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9015(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	
	List<ChartItemInfo2> reportTabR9001(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9002(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9003(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9004(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9005(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9006(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9007(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9008(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9009(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9010(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9011(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9012(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9013(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9014(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	List<ChartItemInfo2> reportTabR9015(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	/**
	 * @param fo100
	 * @param pvRV901
	 * @param pvRV907
	 * @param pnINTER
	 * @param pvLogin
	 * @return
	 */
	
}
