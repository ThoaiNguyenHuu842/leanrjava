package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.interaction.C100PMG;
import com.ohhay.piepme.mongo.nestedentities.C100ACPMG;

/**
 * @author ThoaiNH
 * create Sep 21, 2016
 */
public interface C100PMGDao {
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
	int updateTabC100Nick(int fo100DB, int fo100, int fo100F, String cv103);
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
	 * @param fc100
	 * @return
	 */
	int updateTabSecureContactAuto(int fc100);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<C100PMG> listOfTabC100FriendExcludeSecure(int fo100, int offset, int limit);
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