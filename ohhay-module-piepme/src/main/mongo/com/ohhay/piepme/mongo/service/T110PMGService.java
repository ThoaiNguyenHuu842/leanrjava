package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.channel.T110PMG;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create Jul 27, 2017
 */
public interface T110PMGService {	
	/**
	 * @param fo100
	 * @param pt110
	 * @param tv111
	 * @param latitude
	 * @param longitude
	 * @param addressFullName
	 * @param fo100s
	 * @return
	 */
	int createT110COM1(int fo100, int pt110, String tv111, String tv112, double latitude, double longitude, String addressFullName, List<Integer> fo100s);
	/**
	 * @param fo100
	 * @param pt110
	 * @param ft110
	 * @param tv111
	 * @param tv115
	 * @param fo100s
	 * @return
	 */
	int createT110COM2(int fo100, int pt110, int ft110, String tv111, String tv112, String tv115, List<Integer> fo100s);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<T110PMG> getListT110COM1(int fo100, int ft100, int offset, int limit);
	/**
	 * @param fo100
	 * @param ft110
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<T110PMG> getListT110COM2(int fo100, int ft110, int offset, int limit);
	/**
	 * @param fo100
	 * @param ft110
	 * @return
	 */
	int registerLoyaltyCustomerCOM1(int fo100, int ft110);
	/**
	 * @param fo100
	 * @param ft110
	 * @param regisCode
	 * @return
	 */
	int registerLoyaltyCustomerCOM2(int fo100, int ft110, String regisCode);
	/**
	 * @param fo100
	 * @param ft110
	 * @return
	 */
	T110PMG getT110Info(int fo100, int pt110);
	/**
	 * @param fo100
	 * @param fo100E
	 * @return
	 */
	List<T110PMG> getListT110(int fo100, int ft100);
	/**
	 * @param fo100
	 * @param ft100
	 * @return
	 */
	List<T110PMG> getNearestCOM1(int fo100, double latitude, double longitude, int ft100);
	/**
	 * @param pnFO100
	 * @param pnFT110
	 * @param pnTV119
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<N100SummaryInfo> listOfTabT110Users(int pnFO100, int pnFT110, String pnTV119, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pt110
	 * @param tv112
	 * @return
	 */
	int updateTv112(int fo100, int pt110, String tv112);
	/**
	 * @param fo100
	 * @param pt110
	 * @param tv120
	 * @return
	 */
	int updateTv120(int fo100, int pt110, String tv120);
}
