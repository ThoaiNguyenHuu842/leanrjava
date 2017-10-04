package com.ohhay.core.mongo.service;

import java.util.List;

import com.ohhay.core.entities.O180;
import com.ohhay.core.entities.mongo.profile.M900MG;

/**
 * @author ThoaiNH
 * create 20/12/2014
 * user history service
 */
public interface R900MGService {
	int insertTabR900Vote(int fo100, int fo100Voted);
	int insertTabR900Bookmark(int fo100, int fo100Voted, String rv921, String rv922);
	int stornoTabR900Bookmark(int fo100, int fo100Bookmark);
	int insertTabR900Share(int fo100, int fo100s, int fo100f, String content);
	String getAllHisotry(int fo100);
	List<M900MG> loadBookmark(int fo100, int limit);
	List<O180> findPeopleBookmarkMe(int fo100, int limit);
	List<O180> findPeopleBookmarkMeNew(int fo100);
}
