package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.PieperComment;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH
 * create May 30, 2016
 * pieper service
 */
public interface PieperService {
	/**
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param loc pieper location
	 * @return pieper id created
	 */
	int createPieper(int fo100, String pv301, int pn303, String pv304, String pv305, float pn309, String pv314, String otags, String collectionName);
	/**
	 * @param pnPiperType
	 * @param pnPiperId
	 * @param pnFO100
	 * @return
	 */
	int checkTabUserLike(String pnPiperType,int pnPiperId,int pnFO100);
	/**
	 * @param pnPiperType
	 * @param pnPiperId
	 * @param pnFO100
	 * @return
	 */
	int insertTabLike(String pnPiperType,int pnPiperId,int pnFO100);
	/**
	 * @param pnPiperType
	 * @param pnPiperId
	 * @param pnFO100
	 * @return
	 */
	int stornoTabLike(String pnPiperType,int pnPiperId,int pnFO100);
	/**
	 * @param pnPiperType
	 * @param pnCommentID
	 * @param pnPiperId
	 * @param pnFO100
	 * @param pnCOMMENT
	 * @return
	 */
	int insertTabComment(String pnPiperType,int pnCommentID,int pnPiperId,int pnFO100,String pnCOMMENT);
	/**
	 * @param pnPiperType
	 * @param pnPiperId
	 * @param pnComentId
	 * @param pnFO100
	 * @return
	 */
	int stornoTabComment(String pnPiperType,int pnPiperId,int pnComentId,int pnFO100);
	/**
	 * @param pnPiperType
	 * @param pnPiperId
	 * @param pnFO100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<PieperComment> pieperListOfTabComment(String pnPiperType,int pnPiperId,int pnFO100,int offset, int limit);
	/**
	 * @param pnPiperType
	 * @param pnPiperId
	 * @param pnFO100
	 * @param commentId
	 * @return
	 */
	PieperComment pieperListOfTabCommentDetail(String pnPiperType, int pnPiperId, int pnFO100, int commentId);
	/**
	 * @param pnPiperType
	 * @param pnPiperId
	 * @param pnFO100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<N100PMG> pieperListOfTabUserLike(String pnPiperType,int pnPiperId,int pnFO100,int offset, int limit);
	/**
	 * @param pnPiperType
	 * @param pnPiperId
	 * @param pnFO100
	 * @return
	 */
	int repiep(String pnPiperType, int pnPiperId, int pnFO100);
	/**
	 * @param pnPiperType
	 * @param pnPiperId
	 * @param pnFO100
	 * @param pn306
	 * @return
	 */
	int pieperUpdateTabPn306(String pnPiperType, int pnPiperId, int pnFO100, int pn306);
	/**
	 * @param pnPiperType
	 * @param pnPiperId
	 * @param pnFO100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<N100PMG> pieperListOfTabUserView(String pnPiperType,int pnPiperId,int pnFO100,int offset, int limit);
}
