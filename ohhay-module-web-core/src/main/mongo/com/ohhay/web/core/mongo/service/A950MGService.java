package com.ohhay.web.core.mongo.service;

import java.util.List;

import com.ohhay.web.core.entities.mongo.web.A950MG;

/**
 * @author Phongdt
 * create 29/7/2015 - 
 * file nay dung o thuc hien cac tac vu vs Category gom them va` giam tong so luong Category
 */
public interface A950MGService {
	/**
	 * @param id
	 * @param av101
	 * @param an102
	 * @param av103
	 * @param av104
	 * @param an105
	 * @return
	 */
	int incCategory(int id);
	/**
	 * @param id
	 * @param an105
	 * @return
	 */
	int decCategory(int id);
	/**
	 * author ThoaiNH
	 * create 30/10/2015
	 * @param id
	 * @param an105
	 * @return
	 */
	List<A950MG> loadListA950(String type);
}
