package com.ohhay.web.other.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

public interface W500Dao {
	List<ComtabItem> listOfTabW500(int fo100, int fw400, String pvLogin);
}
