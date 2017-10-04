package com.ohhay.core.mongo.dao;

import java.util.List;

import com.ohhay.core.entities.mongo.profile.M900DesMG;
import com.ohhay.core.entities.mongo.profile.M900MG;

public interface M900MGDao {
	List<M900MG> findM900All(int limit);
	List<M900MG> findM900Index(int limit);
	int getMaxId();
	int checkTabPrivacy(String mv907);
	void loadHistory(M900MG m900mg);
	int getMaxIndexM940(int fo100);
	int getMaxIdM940(int fo100);
	int changeM940Index(int fo100, int id, int newIndex);
	M900MG loadUserProfile(String hv101);
	M900MG loadUserProfileMerian(int fo100);
	int storNotTabM900(int po100);
	int storNotTabM900Center(int po100);
	/*
	 * ThoaiVt 03/03/2016
	 */
	List<M900MG> getListAccount(String content,int fo100,int offset,int limit);
	/*
	 * Tunt 17/11/2016
	 * get list designer
	 */
	List<M900MG> listOfTabDesigner(int fo100, int fe400, String pvSearch, int offset, int limit);
	/*
	 * Tunt 25/11/2016
	 * get info designer
	 */
	M900DesMG listOfTabDesignerOne(int fo100);
}
