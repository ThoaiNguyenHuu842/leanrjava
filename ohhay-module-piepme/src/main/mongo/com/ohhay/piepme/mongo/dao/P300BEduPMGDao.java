package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300BEduPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;

/**
 * @author ThoaiNH
 * create Jul 29, 2017
 */
public interface P300BEduPMGDao {
	/**
	 * @param pnFO100
	 * @param pnFT110
	 * @param pnTV119
	 * @param pvSearch
	 * @param pvSearchSteam
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieper(int pnFO100, int pnFT110, String pnTV119, String pvSearch, String pvSearchSteam, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pp100
	 * @return
	 */
	P300BEduPMG getPieperDetail(int fo100, int pp100);
}
