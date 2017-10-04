package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BRePMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BSummaryInfo;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;


/**
 * @author ThoaiNH
 * create Mar 13, 2017
 */
/**
 * @author ThoaiNH
 * create May 4, 2017
 */
public interface P300BPMGDao {
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
	P300BPMG getPieperDetail(int fo100,int pp100);
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
	 * @param fo100
	 * @param ft300
	 * @param pvSearch
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperPublicByCategory(int fo100, int fo100f, int ft300, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit);
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
	List<Pieper> getListPieperPublicV2(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param ft300
	 * @return
	 */
	int checkRoleOnCreate(int fo100, int ft300);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param ft300
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperOwner(int fo100, String pvSearch, String pvSearchStem, int ft300, int sort, int pnOffset, int pnLimit);
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
	List<Pieper> getListPieperPublicV3(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit);
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
	P300BSummaryInfo getSummaryInfoV3(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort);
	/**
	 * @param pnLa
	 * @param pnLong
	 * @param fo100
	 * @param fo100f
	 * @param ft300
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<Pieper> getListPieperPublicByCategoryV2(double pnLa, double pnLong, int fo100, int fo100f, int ft300, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit);
	/**
	 * @param fo100
	 * @param fo100f
	 * @param pvSearch
	 * @param pvSearchStem
	 * @param sort
	 * @return
	 */
	List<Pieper> getListPieperPublicEOM(int fo100, int fo100f, String pvSearch, String pvSearchStem, int offset, int limit);
}
