package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300VMG;
import com.ohhay.piepme.mongo.nestedentities.P300VConInfo;
import com.ohhay.piepme.mongo.nestedentities.P300VDetailInfo;
import com.ohhay.piepme.mongo.nestedentities.VoucherInfo;

/**
 * @author TuNt
 * create date Jun 30, 2017
 * ohhay-module-piepme
 */
public interface P300VPMGDao {
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<P300VConInfo> getListPieperOwner(int fo100, String search, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	P300VDetailInfo getPieperDetail(int fo100, int pp300);
	/**
	 * @param fo100
	 * @param pv300
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<VoucherInfo> listAllVoucher(int fo100, int pv300, int offset, int limit);
	/**
	 * @param fo100
	 * @param pv300
	 * @return
	 */
	int getTotalVoucher(int fo100, int pv300);
}