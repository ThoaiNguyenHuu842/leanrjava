package com.ohhay.web.lego.service;

import java.util.List;

import com.ohhay.core.criteria.EvoAddLibCriteria;
import com.ohhay.web.lego.entities.mongo.web.E920MG;

/**
 * @author ThoaiNH
 * create Jan 12, 2016
 * EVO library service
 */
public interface EvoLibraryService {
	/**
	 * add box(section) to lib
	 * @param fo100
	 * @param pe920
	 * @param libType
	 * @param listOtags
	 * @return
	 */
	int addToLib(int fo100, EvoAddLibCriteria evoAddLibCriteria, String region);
	/**
	 * @param fo100
	 * @param pe920
	 * @param webId
	 * @return E920MG: box that has been added
	 */
	E920MG addLibToWeb(int fo100, int pe920, int webId);
	/**
	 * load lib
	 * @param fo100
	 * @param libType: 1 mylib, 2 publiclib
	 * @param textSearch
	 * @return
	 */
	List<E920MG> load(int fo100, int libType, String textSearch, int offset, int limit);
	/**
	 * admin approve
	 * @param fo100
	 * @param pe920
	 * @return
	 */
	int approve(int fo100, int pe920);
	/**
	 * remove from lib
	 * @param fo100
	 * @param pe920
	 * @param itemtype
	 * @return
	 */
	int remove(int fo100, int pe920, String itemtype);
	/**
	 * @param fo100
	 * @param pe920
	 * @return
	 */
	int getToMyLib(int fo100, int pe920);
}
