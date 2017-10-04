package com.ohhay.web.core.mysql.service;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;
import com.ohhay.web.core.entities.C400;

public interface C400Service {
	List<C400> chayListOfTabC400(int pnFC400, int pnFO100, int pnFC800, String pvLOGIN);
	List<C400> chayListOfTabC400View(String qv101,String pvLOGIN);
	List<ComtabItem> ahayCombtabc400(int fo100, String pvLogin);
	String chayUpdatetabc400(int fo100, int fc400, String pvLogin);
	String insertTabC400(int pnPC400, String pvCV401, String pvCV402, String pvCV403, String pvCV404, String pvCV405, int pnFO100, int pnFC800, int pnFD000, int pnFH020, String pvLogin);
}
