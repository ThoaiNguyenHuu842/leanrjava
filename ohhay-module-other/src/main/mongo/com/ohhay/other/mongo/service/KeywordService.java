package com.ohhay.other.mongo.service;

public interface KeywordService {
	/**
	 * @param keyword
	 * @param fo100: truyen 0 khi chay web, khac 0 khi web service
	 * @return
	 */
	int addNewKeyWord(String keyword, int fo100);
	/**
	 * @param keyword
	 * @param fo100: truyen 0 khi chay web, khac 0 khi web service
	 * @return
	 */
	int removeKeyWord(String keyword, int fo100);
}
