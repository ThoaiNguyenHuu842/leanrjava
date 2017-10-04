package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.PieperComment;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH
 * create Nov 11, 2016
 */
public interface PieperDao {
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
	 * @param pnFO100
	 * @param pnPiperId
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
	List<N100PMG> pieperListOfTabUserView(String pnPiperType, int pnPiperId, int pnFO100, int offset, int limit);
}
