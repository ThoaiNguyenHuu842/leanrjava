package com.ohhay.web.other.api.service;

import java.util.List;

import com.ohhay.web.core.entities.B050Wbn;

public interface WebinarisService {
	List<B050Wbn> getListB050(String key);
	int confirmKey(String key);
	int createWebinaris(int po100, String ov101,int fc800, String key) ;
	int createWebinarRoom(int po100, String ov101, int fc800);
	int insertTabW400(int fw400, String webniarKey);
	int deleteWebinarRoom(int fw400, int po100);
}
