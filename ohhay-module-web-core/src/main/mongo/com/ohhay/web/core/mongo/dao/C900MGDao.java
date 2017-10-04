package com.ohhay.web.core.mongo.dao;

public interface C900MGDao {
	int deleteC400(int fo100);
	<T> int updateEleme(int id, int indexPart, int indexEleme, String fieldUpdate, Object value, Class<T> mClass, int fo100, int fo100i);
	<T> int updatePart(int id, int indexPart,String fieldUpdate, Object value, Class<T> mClass, int fo100, int fo100i);
	<T> int updateEleme100(String webId, String languageCode, int indexProperties, String fieldName, Object value, Class<T> mClass, int fo100, int fo100i);
}
