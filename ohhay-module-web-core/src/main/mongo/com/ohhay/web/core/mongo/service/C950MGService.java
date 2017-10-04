package com.ohhay.web.core.mongo.service;

import com.ohhay.web.core.entities.mongo.webbase.N950MG;
import com.ohhay.web.core.load.util.WebOhhay;

public interface C950MGService {
	/**
	 * save to web short
	 * @param webOhhay: web ohhay
	 * @param n950mg: web profile
	 * @param fo100
	 * @param languageCode
	 * @param fc800
	 * @param qbMongoColectionName: collection name of web short, EX: C950
	 * @return
	 */
	int insertC950MG(WebOhhay webOhhay, N950MG n950mg, int fo100, String languageCode,int fc800, String qbMongoColectionName);
}
