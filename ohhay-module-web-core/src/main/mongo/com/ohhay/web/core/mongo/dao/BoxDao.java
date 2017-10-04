/*package com.ohhay.web.core.mongo.dao;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;

public interface BoxDao {
	C400MG findboxbyFc920(String phone, int fc920);
	C400MG findboxbyphone(String phone);
	Query selectfindboxbyphone(String phone);
	Query selectqueryStringFc920(String phone, int fc920);
	int cloneboxbyFc920(Query query, Update update);
	int copyboxbyFC920(C400MG c400mg) throws Exception;//copy tungns sua lai
	
	//tungns: delbox
	<T> int deletePartbyFC920(int fo100, String phone, int webId, int fc920, Class<T> mClass);
	//tungns: createbox
	<T> int createPartbyFC920(OhhayWebBase ohhayWebBase, String collectionName);
	//tungns: find box by FC920
	<T> OhhayWebBase findPartbyFC920(int fo100, int webId, int fc920, Class<T> mClass, String collectionName);
	<T> Query selectfindPartbyFC920(int fo100, int webId, int fc920, Class<T> mClass);
}
*/