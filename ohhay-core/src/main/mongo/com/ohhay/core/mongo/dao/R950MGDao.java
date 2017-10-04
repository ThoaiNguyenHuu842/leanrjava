package com.ohhay.core.mongo.dao;

import java.util.List;

import com.ohhay.core.entities.mongo.other.R950MG;

/**
 * @author TuNt
 * create date Mar 8, 2017
 * ohhay-core
 */
public interface R950MGDao {
	/**
	 * @param fo100
	 * @param fo100t
	 * @param webId
	 * @param rv951
	 * @param rv952
	 * @param rv953
	 * @param rv954
	 * @param rv955
	 * @param rv957
	 * @param rv958
	 * @param rv959
	 * @return
	 */
	int insertTabR950(int fo100, int fo100t, int webId, String rv951, String rv952, String rv953,String rv954, String rv955,String rv957, String rv958, String rv959, String rv961, String rv962, String rv963);
	/**
	 * @param fo100
	 * @param webId
	 * @param dateCod
	 * @param dateFromString
	 * @param dateToString
	 * @return
	 */
	List<R950MG> reportTab001(int fo100, int webId, int dateCod, String dateFromString, String dateToString);
	/**
	 * @param fo100
	 * @param webId
	 * @param dateCod
	 * @param dateFromString
	 * @param dateToString
	 * @return
	 */
	List<R950MG> reportTab002(int fo100, int webId, int dateCod, String dateFromString, String dateToString);
	/**
	 * @param fo100
	 * @param webId
	 * @param dateCod
	 * @param dateFromString
	 * @param dateToString
	 * @return
	 */
	List<R950MG> reportTab003(int fo100, int webId, int dateCod, String dateFromString, String dateToString);
	/**
	 * @param fo100
	 * @param webId
	 * @param dateCod
	 * @param dateFromString
	 * @param dateToString
	 * @return
	 */
	List<R950MG> reportTab004(int fo100, int webId, int dateCod, String dateFromString, String dateToString);
	/**
	 * @param fo100
	 * @param webId
	 * @param dateCod
	 * @param dateFromString
	 * @param dateToString
	 * @return
	 */
	List<R950MG> reportTab005(int fo100, int webId, int dateCod, String dateFromString, String dateToString);
	/**
	 * @param fo100
	 * @param webId
	 * @param dateCod
	 * @param dateFromString
	 * @param dateToString
	 * @return
	 */
	List<R950MG> reportTab006(int fo100, int webId, int dateCod, String dateFromString, String dateToString);

}
