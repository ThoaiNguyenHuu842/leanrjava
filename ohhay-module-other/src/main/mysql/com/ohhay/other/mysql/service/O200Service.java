package com.ohhay.other.mysql.service;

import java.util.List;

import com.ohhay.other.entities.O200;

/**
 * @author ThienND
 * created Feb 17, 2016
 * user domain name redirect
 */
public interface O200Service {
	String insertTabO200(int fo100, String ov201, String ov205, String pvLogin);
	int updateTabO200(int fo200, String pvLogin);
	int stornoTabO200(String ov202, String pvLogin);
	List<O200> listOfTabO200(String pvLogin);
}
