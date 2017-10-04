package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300PPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;

/**
 * @author TuNt create date Nov 7, 2016 ohhay-module-piepme
 */
public interface P300PPMGService {
	/**
	 * @deprecated
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param pn306 is draft pieper
	 * @param pn309 rotation of image
	 * @return pieper id created
	 */
	int createPieper(int fo100, int pp300,  String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags);

	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<String> listSuggestedOtag(int fo100, String pvSearch, String pvSearchStem, int offset, int limit);

	/**
	 * @param pp100
	 * @return
	 */
	P300PPMG getPieperDetail(int fo100, int pp100);

	/**
	 * @deprecated
	 * @param fo100
	 * @return
	 */
	List<Pieper> getListPieperPublic(int fo100, int fo100f, String pvSearch, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	int storNoTabP300P(int fo100, int pp300);
	/**
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperFollow(int fo100, String pvSearch, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	List<Pieper> getListPieperPublicOne(int fo100, int pp300);
	/**
	 * @param pp100
	 * @return
	 */
	P300PPMG getPieperDetailV2(int fo100,int pp100);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperPublicV2(int fo100, int fo100f, String pvSearch, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pp300
	 * @param stt
	 * @return
	 */
	int confirmTabPA317(int fo100, int pp300, String stt);
	/**
	 * @param pp300
	 * @param pa317
	 * @return
	 */
	int updateTabPA317V2(int pp300, List<Double> pa317);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperOwner(int fo100, String pvSearch, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param pn306 is draft pieper
	 * @param pn309 rotation of image
	 * @return pieper id created
	 */
	int createPieperV2(int fo100, int pp300,  String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags);
}
