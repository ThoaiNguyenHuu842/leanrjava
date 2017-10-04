package com.ohhay.base.mysql.dao;

/**
 * @author ThoaiNH
 * create Jul 11, 2015
 */
public interface C100CentDao {
	String getValTabC100Con(String pvLogin);
	String getValTabC100MySQL(String pvLogin);
	String getValTabC100Web(String pvLogin);
	String getValTabC100Piepme(String cv101, String pvLogin);
}
