package com.ohhay.other.mysql.service;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.M300;
import com.ohhay.core.entities.N100;

/**
 * @author PhongDt
 * 
 */
public interface M300Service {
	/**
	 * @param email
	 * @return
	 */
	List<M300> autoFillProfile(String qv101, String email);	
}
