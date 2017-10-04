package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300BRePMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;

/**
 * @author ThoaiNH
 * create Jun 22, 2017
 */
public interface P300BRePMGService {
	/**
	 * @deprecated
	 * @param fo100
	 * @param pp300
	 * @param pv301
	 * @param pn303
	 * @param pv304
	 * @param pv304Thumb
	 * @param pv305
	 * @param pn306
	 * @param pn309
	 * @param pv314
	 * @param otags
	 * @param ft330
	 * @param latitude
	 * @param longitude
	 * @param addressFullName
	 * @return
	 */
	int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, int ft330, double latitude, double longitude, String addressFullName);
	/**
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param sort
	 * @param ft310
	 * @param ft320
	 * @param ft330
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieper(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, int sort, int ft310, int ft320, int ft330, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pp100
	 * @return
	 */
	P300BRePMG getPieperDetail(int fo100, int pp100);
	/**
	 * @param fo100
	 * @param pp300
	 * @param pv301
	 * @param pn303
	 * @param pv304
	 * @param pv304Thumb
	 * @param pv305
	 * @param pn306
	 * @param pn309
	 * @param pv314
	 * @param otags
	 * @param ft330
	 * @param latitude
	 * @param longitude
	 * @param addressFullName
	 * @return
	 */
	int createPieperV2(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, int ft330, double latitude, double longitude, String addressFullName);
}
