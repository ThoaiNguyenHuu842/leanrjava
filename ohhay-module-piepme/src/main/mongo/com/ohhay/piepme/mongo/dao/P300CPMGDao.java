package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300CPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;

/**
 * @author TuNt
 * create date Nov 5, 2016
 * ohhay-module-piepme
 */
public interface P300CPMGDao {
	/**
	 * @param fo100
	 * @param fo100t
	 * @param pvSearch
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListCirclePieper(int fo100, int fo100t, String pvSearch, int sort, int pnOffset, int pnLimit);
	/**
	 * @param pp200
	 * @return
	 */
	P300CPMG getPieperDetail(int fo100, int pp200);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<String> listSuggestedOtag(int fo100, String pvSearch, String pvSearchStem, int offset, int limit);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	public int storNoTabP300C(int fo100, int pp300);
}
