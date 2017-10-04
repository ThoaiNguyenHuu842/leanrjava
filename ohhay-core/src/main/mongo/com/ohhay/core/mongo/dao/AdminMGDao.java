package com.ohhay.core.mongo.dao;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.entities.mongo.profile.LanguageMG;

public interface AdminMGDao {
	int getNewWebDraftChildCn806(int fo100);
	int getNewWebChildCn806(int fo100);
	String getThumbnailOfWeb(int fo100, int webId, String type);
	String getUserColor(int fo100);
	int checkWebShortExists(int fo100, String webType, String refId);
	List<LanguageMG> getUserLanguage(int fo100);
	List<ComtabItem> getAllCoponent(int fc800);
	int insertLog(String type, String message);
}
