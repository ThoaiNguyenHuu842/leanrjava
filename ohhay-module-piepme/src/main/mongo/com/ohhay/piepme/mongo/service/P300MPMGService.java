package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300MMG;
import com.ohhay.piepme.mongo.nestedentities.P300ConInfo;
import com.ohhay.piepme.mongo.nestedentities.PieperImg;

/**
 * @author TuNt
 * create date Nov 7, 2016
 * ohhay-module-piepme
 */
public interface P300MPMGService {
	/**
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param loc pieper location
	 * @return pieper id created
	 */
	int createPieper(int fo100, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, List<Integer> listFO100R, List<PieperImg> listImgs);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	P300MMG getPieperDetail(int fo100, int pp300, int pnSeen);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<P300ConInfo> listOfTabP300MCon(int fo100, String pvSearch, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param fo100F
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<P300MMG> listOfTabP300M(int fo100, int fo100F, String pvSearch, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param sort
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<P300MMG> listOfTabP300MArchived(int fo100, String pvSearch, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param fo100f
	 * @return
	 */
	int archiveTabP300(int fo100, int fo100f);
	/**
	 * @param fo100
	 * @param fo100f
	 * @return
	 */
	int storNoTabP300MCon(int fo100, int fo100f);
	/**
	 * @deprecated {replace by @link #sendOTPCodeV2(int, String, String)}
	 * @param fo100
	 * @param otpCode
	 * @return
	 */
	int sendOTPCode(int fo100, String nickName, String otpCode);
	/**
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param loc pieper location
	 * @return pieper id created
	 */
	int createPieperV2(int fo100, String pv301, String pv302, int pn303, String pv304, String pv304Thumb, String pv305, int pn306, float pn309, String pv314, String otags, List<Integer> listFO100R, List<PieperImg> listImgs);
	/**
	 * @param fo100
	 * @param otpCode
	 * @return
	 */
	int sendOTPCodeV2(int fo100, String nickName, String otpCode);
	/**
	 * @param fo100
	 * @param message
	 * @return
	 */
	int messageByAdmin(int fo100, String message);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<P300ConInfo> listOfTabP300MConV2(int fo100, String pvSearch, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param fo100F
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<P300MMG> listOfTabP300MV2(int fo100, int fo100F, String pvSearch, int sort, int offset, int limit);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	int pinTabP300M(int fo100, int pp300);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	int unpinTabP300M(int fo100, int pp300);
}
