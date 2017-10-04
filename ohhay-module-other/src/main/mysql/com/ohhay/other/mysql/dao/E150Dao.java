package com.ohhay.other.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.E150D;

/**
 * @author ThoaiNH
 * create Feb 24, 2016
 */
public interface E150Dao {
	int insertTabE150(int pnPE150, String pvEV151, String pvEV152, String pvEV153, String pvEV154, int pnFE100, String pvLOGIN);
	List<E150D> listOfTabE150D(int pnFE100, String pvLogin);
	List<E150D> listOfTabE150C(int fo100, String ev151, String pvLogin);
	int stornoTabE150(int fe150, String pvLogin);
	int finishTabE150(int pe150,String ev155, String pvLogin);
	int confirmTabE150(int pe150, String pvLogin);
}
