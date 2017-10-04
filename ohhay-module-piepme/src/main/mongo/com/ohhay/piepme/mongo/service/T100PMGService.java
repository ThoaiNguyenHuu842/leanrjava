package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.nestedentities.K100PMG;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH create Mar 27, 2017
 */
public interface T100PMGService {
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<T100PMG> getListT100(int fo100, int offset, int limit);

	/**
	 * @deprecated
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<T100PMG> getListT100Def(int fo100, int offset, int limit);

	/**
	 * @param fo100
	 * @param piepmeId
	 * @return
	 */
	N100PMG checkPiepmeId(int fo100, int fo100f);

	/**
	 * @param fo100
	 * @param piepmeId
	 * @return
	 */
	int registerLoyaltyCustomer(int fo100, int fo100f);
	/**
	 * @param fo100
	 * @param tv101
	 * @param tv102
	 * @param tv103
	 * @return
	 */
	int createT100(int fo100, String tv101, String tv102, String tv103);
	/**
	 * @param fo100
	 * @param tv106
	 * @return
	 */
	int updateCOMBanner(int fo100, String tv106);
	/**
	 * @param fo100
	 * @param pt100
	 * @return
	 */
	int removeT100(int fo100, int pt100);
}
