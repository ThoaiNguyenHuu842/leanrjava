package com.ohhay.base.mysql.service;

/**
 * @author ThoaiNH
 * create 23/09/2015
 */
public interface C100CentService {
	String getValTabC100Con(String pvLogin);
	String getValTabC100MySQL(String pvLogin);
	String getValTabC100Web(String pvLogin);
	String getValTabC100Piepme(String cv101, String pvLogin);
	String getValTabC100PiepCent(String cv101, String pvLogin);
}
