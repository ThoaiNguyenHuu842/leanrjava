package com.ohhay.web.core.mysql.service;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

public interface C500Service {
	List<ComtabItem> listOfTabC500(int fo100, int fc400, String pvLogin);
}
