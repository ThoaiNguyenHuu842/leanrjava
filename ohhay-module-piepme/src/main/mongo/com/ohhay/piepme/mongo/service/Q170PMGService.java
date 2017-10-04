package com.ohhay.piepme.mongo.service;

import java.util.List;
import java.util.Map;

import com.ohhay.core.entities.oracle.Q170OR;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create Aug 29, 2017
 */
public interface Q170PMGService {
	/**
	 * @param fo100
	 * @param fo100U
	 * @param listFQ400
	 * @return
	 */
	int createQ170(int fo100, int fo100U, List<Integer> listFQ400);
	/**
	 * @param fq400
	 * @param fo100U
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int stornoTabQ170(int fq400, int fo100U, int fo100, String pvLogin);
	/**
	 * @param pnFO100
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<N100SummaryInfo> listOfTabQ170Users(int pnFO100, int pnOffset, int pnLimit);
	/**
	 * @param fo100u
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<Q170OR> listOfTabQ170(int fo100u, int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param listFO100U
	 * @return
	 */
	int createQ170Multi(int fo100, Map<Integer, List<Integer>> mapDecentral);
}
