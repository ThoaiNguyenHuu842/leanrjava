package com.ohhay.web.core.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

public interface L400Dao {
	int generateTabL400(int fo100, int fc800, String pvLogin);
	List<ComtabItem> combTabL400(int fo100, String pvLogin);
	int inserttabL400(int pnPL400, String pvLV401, String pvLV402, String pvLV403, String pvLV404, String pvLV405, int pnFO100, int pnFC800, int pnFD000, int fh020, String pvLogin);

}
