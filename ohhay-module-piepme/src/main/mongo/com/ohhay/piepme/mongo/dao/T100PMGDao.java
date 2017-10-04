package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.core.entities.oracle.Q170OR;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create Mar 27, 2017
 */
public interface T100PMGDao {
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<T100PMG> getListT100(int fo100, int offset, int limit);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<T100PMG> getListT100Def(int fo100, int offset, int limit);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Integer> getListFO100EOM(int fo100, String vv308);
	/**
	 * @param pnFO100
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<N100SummaryInfo> listOfTabUserADM(int pnFO100, int pnOffset, int pnLimit);
}
