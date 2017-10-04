package com.ohhay.web.other.api.service;

import com.ohhay.core.entities.mongo.profile.M940MG;

public interface VideoMarketingService {
	int changeTemplate(int fo100, int fl400, int fv910, int fc800, String pvLogin);
	/**
	 * ThoaiNH
	 * Date create: 10:57 am 06/04/2015
	 * @param fo100
	 * @param fl800
	 * @param menuName
	 * @param menuIcon
	 * @param pvLogin
	 * @return
	 */
	int createWebVideoMarketing(int fo100, int fl800, String menuName, String menuIcon, String pvLogin);
	/*
	 * ThoaiNH
	 * Date create: 10:57 am 06/04/2015
	 */
	int updateVideoNew(int fo100, int pv910, M940MG m930mg);
	/*
	 * ThoaiNH
	 * Date create: 11:47 am 07/04/2015
	 */
	int deleteVideoNew(int fo100, int pv910 , int videoId);
	/*
	 * ThoaiNH
	 * Date create: 11:47 am 07/04/2015
	 */
	int changeIndexNew(int fo100, int pv910, int videoId1, int index1, int videoId2, int index2);
}
