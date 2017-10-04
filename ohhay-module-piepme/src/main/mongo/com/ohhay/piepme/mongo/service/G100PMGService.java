package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.interaction.C100PMG;
import com.ohhay.piepme.mongo.entities.interaction.G100PMG;
import com.ohhay.piepme.mongo.entities.pieper.G100Status;
import com.ohhay.piepme.mongo.entities.pieper.N100Status;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH
 * create Aug 9, 2016
 * PiepMe service for group module
 */
public interface G100PMGService {
	/**
	 * @param fo100
	 * @param groupName
	 * @param groupIcon
	 * @return
	 */
	int createGroup(int fo100, String groupName, String groupIcon);
	/**
	 * @param fo100
	 * @param fg100
	 * @param fo100String list fo100 ex: "775,1208,268"
	 * @return
	 */
	int addFriendToGroup(int fo100, int fg100, String fo100String);
	/**
	 * @param fo100
	 * @param fg100
	 * @param fo100r
	 * @return
	 */
	int removeFriendFromGroup(int fo100, int fg100, int fo100r);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<G100PMG> listOfTabG100(int fo100, int offset, int limit, String type);
	/**
	 * @param fo100
	 * @param pg100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<N100PMG> listOfTabG100Friends(int fo100, int pg100, int offset, int limit, String kv105);
	/**
	 * @param fo100
	 * @param fg100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<C100PMG> listOfTabFriendsToAdd(int fo100,int pg100, int offset, int limit);
	/**
	 * @param fo100
	 * @param fg100String
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<N100PMG> listOfTabG100FriendsDis(int fo100, String fg100String, int offset, int limit, String kv105);
	
	/**
	 * @param fo100
	 * @param fg100
	 * @param fo100Del
	 * @return
	 */
	int removeFriendFromGroup(int fo100, int fg100, List<Integer> fo100Del);
	/**
	 * @param fo100
	 * @return
	 */
	N100Status getFO100OfLoyaltyCustomer(int fo100);
	/**
	 * @param fo100
	 * @return
	 */
	G100Status listOfTabG100Ids(int fo100);
}
