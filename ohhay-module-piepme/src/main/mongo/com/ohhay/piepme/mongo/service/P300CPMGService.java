package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300CPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;

/**
 * @author TuNt
 * create date Nov 7, 2016
 * ohhay-module-piepme
 */
public interface P300CPMGService {
	/**
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param loc pieper location
	 * @return pieper id created
	 */
	int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, List<Integer> listFG100S, List<Integer> listFO100S);
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
	P300CPMG getPieperDetail(int fo100,int pp200);
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
	/**
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param loc pieper location
	 * @return pieper id created
	 */
	int createPieperV2(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, List<Integer> listFG100S, List<Integer> listFO100S);
}
