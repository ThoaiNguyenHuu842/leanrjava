package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BSummaryInfo;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;

/**
 * @author ThoaiNH
 * create Jun 22, 2017
 */
public interface P300BPMGService {
	/**
	 * @deprecated
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param pn306 is draft pieper
	 * @param pn309 rotation of image
	 * @param pv314 preview content
	 * @param otags otags
	 * @param ft300s categories of pieper
	 * @return pieper id created
	 */
	int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, int ft300);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<String> listSuggestedOtag(int fo100, String pvSearch, String pvSearchStem, int offset, int limit);
	/**
	 * @param fo100
	 * @param pp100
	 * @return
	 */
	P300BPMG getPieperDetail(int fo100, int pp100);
	/**
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
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
	 * @param fo100
	 * @param ft300
	 * @param pvSearch
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperPublicByCategory(int fo100, int fo100f, int ft300, String pvSearch, int sort, int pnOffset, int pnLimit);
	/**
	 * @param pnLa
	 * @param pnLong
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperPublicV2(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param ft300
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperOwner(int fo100, String pvSearch, int ft300, int sort, int pnOffset, int pnLimit);
	/**
	 * @param pnLa
	 * @param pnLong
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperPublicV3(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, int sort, int pnOffset, int pnLimit);
	/**
	 * @param pnLa
	 * @param pnLong
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param sort
	 * @return
	 */
	P300BSummaryInfo getSummaryInfoV3(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, int sort);
	/**
	 * @param pnLa
	 * @param pnLong
	 * @param fo100
	 * @param fo100f
	 * @param ft300
	 * @param pvSearch
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperPublicByCategoryV2(double pnLa, double pnLong, int fo100, int fo100f, int ft300, String pvSearch, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperPublicEOM(int fo100, int fo100f, String pvSearch, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param pn306 is draft pieper
	 * @param pn309 rotation of image
	 * @param pv314 preview content
	 * @param otags otags
	 * @param ft300s categories of pieper
	 * @return pieper id created
	 */
	int createPieperV2(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, int ft300);
}
