package com.ohhay.core.mongo.dao;

/**
 * @author ThoaiNH
 * create Apr 27, 2014
 */
public interface SequenceDao {
	long getNextSequenceId(int fo100, String key) throws Exception;
	long getNextSequenceIdTopic(int fo100, String key) throws Exception;
	long getNextSequenceIdShop(int fo100, String key) throws Exception;
	long getNextSequenceIdPiepMe(int fo100, String key) throws Exception;
}
