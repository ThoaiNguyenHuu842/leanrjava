package com.ohhay.piepme.mongo.dao;

import java.util.List;

import com.ohhay.piepme.mongo.entities.other.R100VAMG;
import com.ohhay.piepme.mongo.entities.other.R100VMG;

/**
 * @author ThoaiVt
 * @date 07-07-2017
 */
public interface R100VMGDao {
	/**
	 * @param fo100
	 * @param pv300
	 * @return
	 */
	R100VAMG listOfTabR100V01(int fo100, int pv300);

	/**
	 * @param fo100
	 * @param pv300
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<R100VMG> listOfTabR100V02(int fo100, int pv300, int offset, int limit);

	/**
	 * @param fo100
	 * @param pv300
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<R100VMG> listOfTabR100V03(int fo100, int pv300, int offset, int limit);

	/**
	 * @param fo100
	 * @param pv300
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<R100VMG> listOfTabR100V04(int fo100, int pv300, int offset, int limit);
}
