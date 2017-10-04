package com.ohhay.other.mongo.service;

import java.util.List;

import com.ohhay.core.entities.mongo.other.R800MG;
/**
 * @author ThongQB
 * report service
 */
public interface R800MGService {
	/**
	 * insert report
	 * @param fo100
	 * @param pnFM150
	 * @param pnFO100R
	 * @param pvRV801
	 * @return
	 */
	int insertTabR800(int fo100, int fm150,int fo100r,String rv801);
	/**
	 * insert tracking
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<R800MG> getListOfTabR800(int fo100, int offset, int limit);
}
