package com.ohhay.web.core.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

public interface C800Dao {
	List<ComtabItem> chayCombtabc800(int fd000, int fk100, String src, String pvLOGIN);
	List<ComtabItem> chayCombtabc800LgPages(int fd000, int fk100, String src, String pvLOGIN);
	String chayGetElemTabC800(int fc800, String pvLogin);
}
