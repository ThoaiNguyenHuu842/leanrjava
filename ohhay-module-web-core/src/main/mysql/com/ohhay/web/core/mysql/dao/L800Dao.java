package com.ohhay.web.core.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

public interface L800Dao {
	List<ComtabItem> chayCombtabL800(int fd000, String lv808, String pvLOGIN);
	List<ComtabItem> chayCombtabL800Video(String pvLOGIN);
}
