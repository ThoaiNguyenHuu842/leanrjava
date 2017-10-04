package com.ohhay.other.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.ChartItemInfo2;
/**
 * @author ThoaiNH
 * create 03/04/2015
 * tracking service
 */
public interface R900Dao {
	int insertTabR900(int fo100, String pvRV902, String pvRV904, String pvRV907, String pvRV908, String pvRV958, int pnREFID, int pnFO100T, String pvLogin);
	int insertTabR900a(int fo100, String pvRV901, String pvRV902, String pvRV904, int pnRN905, String pvRV907, String pvRV908, String pvRV913, String pvRV914, String pvRV915, String pvLOGIN);
	int insertTabR900V1(int fo100, String pvRV902, String pvRV904, String pvRV907, String pvRV908, String pvRV958, String pvRV959, String pvRV960, int pnREFID, int pnFO100T, String pvLogin);
	List<ChartItemInfo2> reportTabR9001(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9002(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9003(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9004(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9005(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9006(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9007(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9008(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9009(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9010(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9011(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9012(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9013(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9014(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9015(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin);
	List<ChartItemInfo2> reportTabR9001(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9002(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9003(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9004(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9005(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9006(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9007(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9008(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9009(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9010(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9011(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9012(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9013(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9014(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
	List<ChartItemInfo2> reportTabR9015(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin);
}
