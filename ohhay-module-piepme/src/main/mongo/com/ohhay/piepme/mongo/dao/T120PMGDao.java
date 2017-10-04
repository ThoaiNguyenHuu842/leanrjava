package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create Aug 3, 2017
 */
public interface T120PMGDao {
	List<N100SummaryInfo> listOfTabT120Users(int pnFO100, int pnFT110, String pnTV129, int pnOffset, int pnLimit);
}
