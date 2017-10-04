package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.other.T300PMG;

/**
 * @author ThoaiNH
 * create Apr 8, 2017
 * categories of pieper
 */
public interface T300PMGService {
	/**
	 * @param pt300
	 * @param fo100
	 * @param tv301
	 * @param tv302
	 * @param otags
	 * @return
	 */
	int insertT300(int pt300, int fo100, String tv301, String tv302, String otags);
	/**
	 * @param fo100
	 * @return
	 */
	List<T300PMG> listOfTabT300(int fo100);
}
