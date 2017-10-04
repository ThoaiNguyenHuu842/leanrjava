package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.K100SummaryInfo;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author TuNt
 * create date Jun 15, 2017
 * ohhay-module-piepme
 */
public interface N100BusPMGService {
	/**
	 * @param uuid
	 * @return
	 */
	N100PMG getN100ByUUID(String uuid);
	/**
	 * @param nickName
	 * @param securityNumber
	 * @return
	 */
	N100PMG getN100ByNV101(String nickName, String securityNumber);
	/**
	 * @param search
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<N100PMG> listOfTabN100Sug(String search, int offset, int limit);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<K100SummaryInfo> listSuggestedEnterprise(int fo100, String pvSearch, int offset, int limit);
	/**
	 * @param fo100
	 * @param kv101
	 * @param kv102
	 * @param kv103
	 * @param kv106
	 * @param kv107
	 * @return
	 */
	int update(int fo100, String nv106, String nv107, String nv108, String kv101, String kv102, String kv107,String nv201, String nv204, String nv207, String nv208, String nv209, String nv212,
			String login);
}
