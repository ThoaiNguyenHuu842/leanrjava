package com.ohhay.web.other.api.service;

import java.util.List;

import com.ohhay.web.core.entities.B050Shanti;
import com.ohhay.web.core.entities.C150Shanti;

public interface ShantiService {
	//danh sach chuong trinh hoc
	List<B050Shanti> listOfTabB050(String key, String pvLogin);
	//danh sach khoa hoc
	List<C150Shanti> listOfTabC150(String key, int fb050, String pvLogin);
}
