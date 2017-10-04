package com.ohhay.web.core.mongo.service;

import com.ohhay.core.criteria.GMapCriteria;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;

/**
 * @author ThoaiNH
 * create 22/12/2014
 * service for c900 element of web
 */
public interface C900MGService {
	int deleteC400(int fo100);
	<T> int updateEleme(int id, int indexPart, int indexEleme, String fieldUpdate, Object value, Class<T> mClass, int fo100, int fo100i);
	<T> int updateEleme100(String webId, String languageCode, int indexProperties, String fieldName, Object value, Class<T> mClass, int fo100, int fo100i);
	<T> int updatePart(int id, int indexPart, String fieldUpdate, Object value, Class<T> mClass, int fo100, int fo100i);
	 int updateWebColorByUrl(String url, int fo100, String newColor);
	 OhhayWebBase getWebByUrl(String url, int fo100);
	 <T> OhhayWebBase saveGmap(GMapCriteria gMapCriteria,Class<T> mClass, int fo100, int fo100i);
}
