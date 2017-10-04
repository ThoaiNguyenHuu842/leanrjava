package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.interaction.C100PMG;
import com.ohhay.piepme.mongo.nestedentities.C100ACPMG;

/**
 * @author ThoaiNH
 * create May 30, 2016
 * friendship piepme info
 */
public interface C100PMGService {
	 /**
	 * @param fo100
	 * @param fo100f
	 * @return
	 */
	int sendRequest(int fo100, int fo100f);
	 /**
	 * @param fo100f
	 * @param fo100
	 * @return
	 */
	int confirmRequest(int fo100f, int fc100);
	 /**
	 * @param fo100f
	 * @param fo100
	 * @return
	 */
	int cancelRequest(int fo100f, int fc100);
	/**
	 * @param fo100A
	 * @param fb100B
	 * @return 0: chua gui lien he, 1: fo100A da gui yeu cau ket ban, 2: fo100B da gui yc ket ban, 3: da la ban
	 */
	int checkFriendStatus(int fo100A, int fb100B);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<C100PMG> listOfTabC100Friend(int fo100, int offset, int limit);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<C100PMG> listOfTabC100Request(int fo100, int offset, int limit);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<C100PMG> listOfTabC100SentRequest(int fo100, int offset, int limit);
	/**
	 * @param fo100 user login
	 * @param fo100F fo100 of friend
	 * @param cv103 nickname
	 * @return
	 */
	int updateTabC100Nick(int fo100, int fo100F, String cv103);
	/**
	 * @param fo100A
	 * @param fo100B
	 * @return
	 */
	int unfriend(int fo100A, int fo100B);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<C100ACPMG> listOfTabC100RecentAc(int fo100, int offset, int limit);
	/**
	 * @param fo100
	 * @param fc100s
	 * @return
	 */
	int updateTabSecureContact(int fo100, List<Double> fc100s);
	/**
	 * @param fo100
	 * @return
	 */
	List<C100PMG> listOfTabC100Secure(int fo100);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<C100PMG> listOfTabC100FriendExcludeSecure(int fo100, int offset, int limit);
	/**
	 * @param fo100
	 * @param code
	 * @return
	 */
	int insertReferrerCode(int fo100, String code);
	/**
	 * @param fo100
	 * @param fo100f
	 * @param timeStamp
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<C100PMG> listOfTabC100FriendHis(int fo100, int fo100f, long timeStamp, int offset, int limit);
	/**
	 * @param fo100
	 * @param time
	 * @return
	 */
	List<Integer> listOfTabC100FriendDel(int fo100,long time);
	/**
	 * @param fo100
	 * @param ft100
	 * @param ft110
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<C100PMG> listOfTabC100FriendEdu(int fo100, int ft100, int ft110, int offset, int limit);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<C100PMG> listOfTabC100FriendAdm(int fo100, int offset, int limit);
}
