package com.ohhay.web.core.api.dao;

import com.ohhay.web.core.entities.mongo.webbase.C110MG;

public interface LanguageDao {
	/**
	 * @param webId: id web
	 * @param colectionName: colection name (C900, W900, B900, L900)
	 * @param languageCode: lang code muon tao
	 * @param languageName: lang name muon tao
	 * @return
	 */
	int createLanguageC450(int fo100, int webId,String colectionName, String languageCode,String languageName);
	/**
	 * @param webId:id web
	 * @param colectionName: colection name (C900, W900, B900, L900)
	 * @param languageCode: lang code muon xoa
	 * @return
	 */
	int deleteLanguageC450(int fo100, int webId,String languageCode,String colectionName);
	/**
	 * @param webId: id web
	 * @param fo100: f0100
	 * @param c900mg: c900 muon tao
	 * @param colectionName: colection name (C900, W900, B900, L900)
	 * @return
	 */
	<T> int pushOneLanguage(int fo100, int webId, String languageCode, C110MG c110mg, Class<T> mClass);
	/**
	 * @param <T>
	 * @param c400mg: web (C900,W900,B900,L900)
	 * @param languageCode: language code request
	 * @param map: map properties 
	 * @param extend: extend type
	 * @param fc400: fc400 
	 * @return
	 */
	int stornoC110Data(int fo100, String languageID, String cv111, int extend);
}
