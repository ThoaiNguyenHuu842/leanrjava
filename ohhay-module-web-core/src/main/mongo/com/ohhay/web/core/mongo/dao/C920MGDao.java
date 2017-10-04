package com.ohhay.web.core.mongo.dao;

import com.ohhay.web.core.entities.mongo.webbase.C920MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;

/**
 * @author ThoaiNH
 * create: 18/08/2015
 * dao for box function
 */
public interface C920MGDao {
	/**
	 * @param fo100
	 * @param webID
	 * @param fc920
	 * @param collectionName
	 * @param languageCollectionName
	 * @return
	 */
	int stornoTabC920(int fo100, int webID, int fc920, int extend);
	/**
	 * @param fo100
	 * @param webId
	 * @param fc920
	 * @param extend
	 * @return
	 */
	<T> OhhayWebBase findPartbyFC920(int fo100, int webId, int fc920, int extend);
	/**
	 * @param fo100
	 * @param webID
	 * @param c920mg
	 * @param extend
	 * @return
	 */
	int pushBoxToWeb(int fo100, int webID, C920MG c920mg, int extend, int fo100i);
	int updateBoxCn924(int fo100, int webID, int fc920, int cn924, int extend);
	/**
	 * xoa box, khong xoa du lieu ngon ngu, dung khi copy element
	 * @param fo100
	 * @param webID
	 * @param fc920
	 * @param extend
	 * @return
	 */
	int stornoTabC920Only(int fo100, int webID, int fc920, int extend);
}
