package com.ohhay.web.core.mysql.service;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

public interface T500Service {
	List<ComtabItem> listOfTabT500(int fo100, int ft400, String pvLogin);
}
