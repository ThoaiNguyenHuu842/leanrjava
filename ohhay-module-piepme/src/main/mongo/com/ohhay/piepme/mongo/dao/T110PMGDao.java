package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.channel.T110PMG;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create Jul 29, 2017
 */
public interface T110PMGDao {
	List<T110PMG> getListT110EOM(int fo100, int ft100);
	List<T110PMG> getNearestCOM1(int fo100, double latitude, double longitude, int ft100);
	List<N100SummaryInfo> listOfTabT110Users(int pnFO100, int pnFT110, String pnTV119, int pnOffset, int pnLimit);
}
