package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300MMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.P300ConInfo;

/**
 * @author ThoaiNH
 * create Sep 21, 2016
 */
public interface P300MPMGDao {
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	P300MMG getPieperDetail(int fo100, int pp300, int pnSeen);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<P300ConInfo> listOfTabP300MCon(int fo100, String pvSearch, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param fo100F
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<P300MMG> listOfTabP300M(int fo100, int fo100F, String pvSearch, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param sort
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<P300MMG> listOfTabP300MArchived(int fo100, String pvSearch, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param fo100f
	 * @return
	 */
	int archiveTabP300(int fo100, int fo100f);
	/**
	 * @param fo100
	 * @param fo100f
	 * @return
	 */
	int storNoTabP300MCon(int fo100, int fo100f);
	/**
	 * @param fo100
	 * @param fo100DN
	 * @param voucherCode
	 * @return
	 */
	int updateTabPD310(int fo100, int fo100DN, String voucherCode);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<P300ConInfo> listOfTabP300MConV2(int fo100, String pvSearch, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param fo100F
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<P300MMG> listOfTabP300MV2(int fo100, int fo100F, String pvSearch, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	int pinTabP300M(int fo100, int pp300);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	int unpinTabP300M(int fo100, int pp300);
}
