package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.N100CAFMG;
import com.ohhay.piepme.mongo.entities.pieper.N100Status2;
import com.ohhay.piepme.mongo.entities.pieper.N100Status3;
import com.ohhay.piepme.mongo.nestedentities.DeviceToken;
import com.ohhay.piepme.mongo.nestedentities.K100DetailPMG;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH
 * create Oct 31, 2016
 */
public interface N100PMGDao {
	/**
	 * @param fo100
	 * @param userNameStemmed
	 * @return
	 */
	List<N100PMG> listOfTabN100(int fo100, String userNameStemmed);
	/**
	 * @param fo100S fo100s  array 1723##qb##1724
	 * @return
	 */
	List<DeviceToken> listOfTabN100Token(String fo100S);
	

	/**
	 * @param search
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<N100PMG> listOfTabN100Sug(String search, int offset, int limit);

	/**
	 * @param fo100
	 * @param fo100b
	 * @return
	 */
	K100DetailPMG getBusinessInfo(int fo100, int fo100b);
	/**
	 * @param fo100
	 * @param nv106Steam
	 * @param nv107
	 * @return
	 */
	List<N100PMG> listOfTabN100V2(int fo100, String nv106Steam, String nv107);
	/**
	 * @param fo100
	 * @param pnLat
	 * @param pnLong
	 * @param pnRadius
	 * @return
	 */
	N100Status3 listOfTabN100LocV2(int fo100, double latitude, double longitude, int radius);
	/**
	 * @param fo100
	 * @param pnRadius
	 * @return
	 */
	N100Status3 listOfTabN100LocRV2(int fo100, int radius);
	/**
	 * @param fo100
	 * @param radius
	 * @return
	 */
	N100Status2 listOfTabN100LocR(int fo100, int radius);
	/**
	 * @param fo100
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return
	 */
	N100Status2 listOfTabN100Loc(int fo100, double latitude, double longitude, int radius);

	/**
	 * @param fo100
	 * @return
	 */
	List<N100CAFMG> listOfTabN100OCaf(int fo100);
	/**
	 * @param fo100
	 * @param fo100e
	 * @return
	 */
	double getDistanceFromEnterprise(int fo100, int fo100e);
	/**
	 * @param listFO100
	 * @return
	 */
	List<N100SummaryInfo> listOfTabN100Summary(List<Integer> listFO100);
}
