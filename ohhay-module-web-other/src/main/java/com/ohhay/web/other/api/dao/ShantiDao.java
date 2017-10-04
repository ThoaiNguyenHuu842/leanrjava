package com.ohhay.web.other.api.dao;

import java.util.List;

import com.ohhay.web.core.entities.B050Shanti;
import com.ohhay.web.core.entities.C150Shanti;

public interface ShantiDao {
	List<B050Shanti> listOfTabB050(String key, String pvLogin);
	List<C150Shanti> listOfTabC150(String key, int fb050, String pvLogin);
}
