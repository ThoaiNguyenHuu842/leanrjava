package com.ohhay.core.mongo.dao;

import java.util.List;

import com.ohhay.core.entities.mongo.profile.M910MG;


public interface M910MGDao {
	List<M910MG> listOfShare(int fo100, int offset, int limit, String pvSearch);
}
