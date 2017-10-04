package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.K100SummaryInfo;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author TuNt
 * create date Jun 15, 2017
 * ohhay-module-piepme
 */
public interface N100BusPMGDao {
	/**
	 * @param search
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<N100PMG> listOfTabN100Sug(String pvSearch, int offset, int limit);
	/**
	 * @param fo00
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<K100SummaryInfo> listSuggestedEnterprise(int fo100, String pvSearch, String pvSearchStem, int offset, int limit);
}
