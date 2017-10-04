package com.ohhay.web.core.mysql.dao;

import java.util.List;

import com.ohhay.web.core.entities.C920;

/**
 * @author ThoaiNH
 * create 09/06/201
 */
public interface C920Dao {
	List<C920> chayListOfTabC920(String pvHv101, String pvLOGIN);
	List<C920> chayListOfTabC920View(String pvHv101, String pvLOGIN);
	String chayGetelemTabC920(String ov101, String pvLogin);
	String chayGetelemTabC920Lp(String ov101, int fc400,  String pvLogin);
	String chayGetelemTabC920Child(int fc500, String pvLogin);
}
