package com.ohhay.other.mongo.service;

import com.ohhay.core.entities.mongo.profile.M900MG;


/**
 * @author Phongdt
 * - create 19/8/2015 -  - file nay dung o thuc hien xu ly info cua topic
 */
public interface M960MGService {
	/**
	 * @param save info for topic
	 * @param result
	 * @return
	 */
	int saveInfoTopic(M900MG m900mg,int po100);
	
	/**
	 * @param using web service save info for topic
	 * @param po100
	 * @return
	 */
	int saveInfoTopicWithoutSession(M900MG m900mg, int po100, String region);
	
	/**
	 * @param save coverImage for topic
	 * @param result
	 * @return
	 */
	int saveCoverImage(String image,int po100);
	
	/**
	 * @param save coverDescription for topic
	 * @param result
	 * @return
	 */
	int saveCoverDescription(String descript,int po100);
	
	String checkRegion(int po100);
}
