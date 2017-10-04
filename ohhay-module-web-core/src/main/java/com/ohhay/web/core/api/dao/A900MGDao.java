package com.ohhay.web.core.api.dao;

import java.util.List;

import com.ohhay.web.core.entities.mongo.web.A400MG;

/**
 * @author THOAIVT
 * create 29/8/2015
 */
public interface A900MGDao {
	List<A400MG> getListTemplate(int pnFA950,int pnAN402,int offset,int limit);
}
