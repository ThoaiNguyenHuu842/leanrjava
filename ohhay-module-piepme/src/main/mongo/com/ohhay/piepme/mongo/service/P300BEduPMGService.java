package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300BEduPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;

/**
 * @author ThoaiNH
 * create Jul 29, 2017
 */
public interface P300BEduPMGService {
	/**
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param pn309 rotation of image
	 * @param pv314 preview content
	 * @param otags otags
	 * @param ft110 id COM
	 * @param tv119 'EOM', 'EOM_PH', 'EOM_HS'
	 * @return pieper id created
	 */
	int createPieperEdu(int fo100, int pp300, String pv301, int pn303, String pv304, String pv304Thumb, String pv305, float pn309, String pv314, String otags, int ft110, String tv119);
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
	List<Pieper> getListPieper(int pnFO100, int pnFT110, String pnTV119, String pvSearch, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pp100
	 * @return
	 */
	P300BEduPMG getPieperDetail(int fo100, int pp100);
}
