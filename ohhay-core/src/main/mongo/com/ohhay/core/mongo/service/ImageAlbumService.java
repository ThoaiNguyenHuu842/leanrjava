package com.ohhay.core.mongo.service;

import com.ohhay.core.entities.mongo.other.P950MG;

/**
 * @author ThoaiNH
 * create Oct 28, 2015
 * manage image of user by fo100 and web id
 */
public interface ImageAlbumService {
	/**
	 * create Oct 28, 2015
	 * using for web evo function
	 * upload img to image album
	 * if webid = 0 -> upload to fo100/fileName
	 * else upload to fo100/webid/filename
	 * @param fo100
	 * @param webId: id of web evo
	 * @param fileName
	 * @param bs
	 * @return null if upload fail, return full url of image uploaded
	 */
	P950MG upload(int fo100, String region, int webId, String imgBase64, String src, String ext);
	/**
	 * @param fo100
	 * @param webId
	 * @param fileName
	 * @return
	 */
	int remove(int pp950, int fo100, String region);
}
