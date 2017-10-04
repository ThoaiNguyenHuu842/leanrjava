package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.entities.interaction.F150PMG;

/**
 * @author TuNt
 * create date Oct 28, 2016
 * ohhay-module-piepme
 */
public interface F150PMGService {
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<F150PMG> listOfTabF150(int fo100, int offset, int limit);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<F150PMG> listOfTabF150T(int fo100, int offset, int limit);
	/**
	 * @param fo100
	 * @param fo100t
	 * @return
	 */
	int insertTabF150(int fo100, int fo100t, int fb300, String pieperType, String uuid);
	/**
	 * @param fo100
	 * @param fo100t
	 * @return
	 */
	String checkFollowStatus(int fo100, int fo100t);
	/**
	 * @param fo100
	 * @param fo100t
	 * @return
	 */
	int storNoTabF150(int fo100, int fo100t);
	/**
	 * @param fo100
	 * @return
	 */
	int insertTabF150Admin(int fo100);
}
