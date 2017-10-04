package com.ohhay.core.mongo.service;

public interface SequenceService {
	long getNextSequenceId(int fo100, String key) throws Exception;
	long getNextSequenceIdTopic(int fo100, String key) throws Exception;
	long getNextSequenceIdShop(int fo100, String key) throws Exception;
	long getNextSequenceIdPiepMe(int fo100, String key) throws Exception;
}
