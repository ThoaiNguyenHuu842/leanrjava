package com.ohhay.web.core.mongo.service;

/**
 * @author ThoaiNH
 * create: 18/08/2015
 * dao for box function
 */
public interface C920MGService {
	/**
	 * @param fo100
	 * @param webID
	 * @param fc920
	 * @param extend
	 * @return
	 */
	int stornoTabC920(int fo100, int webID, int fc920, int extend);
	/**
	 * swap cn924 between fc920 and fc920t
	 * @param fo100
	 * @param webId
	 * @param fc920
	 * @param fc920t
	 * @param extend
	 * @return
	 */
	<T> int swapCn924(int fo100,  int webId, int fc920, int fc920t, int extend);
	/**
	 * @param fo100
	 * @param webId
	 * @param fc920
	 * @param extend
	 * @return
	 */
	<T> int copyBox(int fo100, int webId, int fc920, int extend);
	/**
	 * @param fo100
	 * @param webId
	 * @param part
	 * @param elem
	 * @param extend
	 * @return
	 */
	<T> int copyElem(int fo100, int webId, int part, int elem, int extend);
	/**
	 * @param fo100
	 * @param webId
	 * @param part
	 * @param elem
	 * @param extend
	 * @return
	 */
	<T> int deleteElem(int fo100, int webId, int part, int elem, int extend);
	/**
	 * create 02/11/2015
	 * @param fo100
	 * @param webId
	 * @param fc820
	 * @param extend
	 * @return
	 */
	<T> int copyBoxByFC820(int fo100, int webId, int fc820, int extend);
}
