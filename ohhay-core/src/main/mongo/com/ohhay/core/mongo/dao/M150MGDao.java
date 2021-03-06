package com.ohhay.core.mongo.dao;

import java.util.List;

import org.json.JSONArray;

import com.ohhay.core.entities.mongo.other.M150CMG;
import com.ohhay.core.entities.mongo.other.M150MG;
import com.ohhay.core.entities.mongo.other.UserOtag;

public interface M150MGDao {
	String getNewTopic(int fo100);
	List<M150MG> findM150Index(int limit);
	List<UserOtag> getListUserOtags(int fo100);
	JSONArray listOfTabM150(int fo100Login, int fo100View, int offset, int limit, String langCode);
	JSONArray listOfTabM150one(int pnFO100, int pnFM150);
	List<M150CMG> findM150C(int pnFM150);
}
