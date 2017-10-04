package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create Aug 3, 2017
 */
public interface T120PMGService {
	/**
	 * @param pnFO100
	 * @param pnFT110
	 * @param pnTV129
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<N100SummaryInfo> listOfTabT120Users(int pnFO100, int pnFT110, String pnTV129, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pt120
	 * @return
	 */
	int comfirmT120(int fo100, int pt120);
	/**
	 * @param fo100
	 * @param pt120
	 * @return
	 */
	int rejectT120(int fo100, int pt120);
}
