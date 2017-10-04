package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300BRePMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
/**
 * @author ThoaiNH
 * create May 4, 2017
 */
public interface P300BRePMGDao {
	/**
	 * @param pnLa
	 * @param pnLong
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param sort
	 * @param ft310
	 * @param ft320
	 * @param ft330
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieper(double pnLa, double pnLong,int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort, int ft310, int ft320, int ft330, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pp100
	 * @return
	 */
	P300BRePMG getPieperDetail(int fo100, int pp100);
}
