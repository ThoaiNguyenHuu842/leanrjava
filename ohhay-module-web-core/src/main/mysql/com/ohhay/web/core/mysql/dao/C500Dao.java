package com.ohhay.web.core.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

public interface C500Dao {
	List<ComtabItem> listOfTabC500(int fo100, int fc400, String pvLogin);
}
