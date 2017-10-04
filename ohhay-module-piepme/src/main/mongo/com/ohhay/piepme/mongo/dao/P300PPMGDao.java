package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300PPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;

/**
 * @author ThoaiNH
 * create Sep 23, 2016
 */
public interface P300PPMGDao {
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
	P300PPMG getPieperDetail(int fo100,int pp100);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperPublic(int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit);
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
	List<Pieper> getListPieperPublicV2(int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param pp300
	 * @param stt
	 * @return
	 */
	int confirmTabPA317(int fo100, int pp300, String stt);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	int updateTabPA317(int fo100, int pp300);
	/**
	 * @param pp300
	 * @param pa317
	 * @return
	 */
	int updateTabPA317V2(int pp300, List<Double> pa317);
	/**
	 * @param fo100
	 * @return
	 */
	int checkRoleOnCreate(int fo100);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperOwner(int fo100, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit);
}
