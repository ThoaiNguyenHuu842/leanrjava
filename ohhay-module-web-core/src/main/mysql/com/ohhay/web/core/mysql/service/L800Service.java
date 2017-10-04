package com.ohhay.web.core.mysql.service;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

public interface L800Service {
	List<ComtabItem> chayCombtabL800(int fd000, String lv808, String pvLOGIN);
	List<ComtabItem> chayCombtabL800Video(String pvLOGIN);
}
