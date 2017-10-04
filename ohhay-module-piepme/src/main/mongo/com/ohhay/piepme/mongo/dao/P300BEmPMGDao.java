package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300BEmPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
/**
 * @author ThoaiNH
 * create May 4, 2017
 */
public interface P300BEmPMGDao {
	/**
	 * @param pnLa
	 * @param pnLong
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param pvSearchAd
	 * @param pvSearchStemAd
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieper(double pnLa, double pnLong,int fo100, int fo100f, String pvSearch, 
			String pvSearchStem, String pvSearchAd, String pvSearchStemAd, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pp100
	 * @return
	 */
	P300BEmPMG getPieperDetailEm(int fo100,int pp100);
}
