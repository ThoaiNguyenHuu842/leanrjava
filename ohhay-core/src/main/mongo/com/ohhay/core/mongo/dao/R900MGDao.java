package com.ohhay.core.mongo.dao;

import java.util.List;

import com.ohhay.core.entities.mongo.history.R900MG;

public interface R900MGDao {
	int insertTabR900Vote(int fo100, int fo100Voted);
	int insertTabR900Bookmark(int fo100, int fo100Voted, String rv921, String rv922);
	int stornoTabR900Bookmark(int fo100, int fo100Bookmark);
	int insertTabR900Share(int fo100, int fo100s, int fo100f, String content);
	String getAllHisotry(int fo100);
	List<R900MG> findPeopleBookmarkMe(int fo100, int limit);
}
