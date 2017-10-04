package com.ohhay.other.mysql.service;

import java.util.List;

import com.ohhay.other.entities.ItemTabN750;

/**
 * @author ThoaiNH
 * create 09/12/2014
 * list country, language ... support
 */
public interface N750Service {
	List<ItemTabN750> nhayCombTabN750(String pvLOGIN);
	List<ItemTabN750> nhayCombTabN750Set(String pvLOGIN);
	List<ItemTabN750> listOfTabN750(String inLocation);
}
