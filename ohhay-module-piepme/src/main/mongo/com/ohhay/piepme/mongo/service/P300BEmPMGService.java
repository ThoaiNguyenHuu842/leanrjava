package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300BEmPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;

/**
 * @author ThoaiNH
 * create Jun 22, 2017
 */
public interface P300BEmPMGService {
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
	 * @param otagAds
	 * @param latitude
	 * @param longitude
	 * @param addressFullName
	 * @return
	 */
	int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, String otagAds, double latitude, double longitude, String addressFullName);
	/**
	 * @param pnLa
	 * @param pnLong
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param pvSearchAd
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieper(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, String pvSearchAd, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pp100
	 * @return
	 */
	P300BEmPMG getPieperDetailEm(int fo100,int pp100);
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
	 * @param otagAds
	 * @param latitude
	 * @param longitude
	 * @param addressFullName
	 * @return
	 */
	int createPieperV2(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, String otagAds, double latitude, double longitude, String addressFullName);
}
