package com.ohhay.core.mongo.service;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.entities.mongo.profile.LanguageMG;

/**
 * @author ThoaiNH
 * create 02/03/2015
 * admin mongo function
 */
public interface AdminMGService {
	/**
	 * use when copy web draft
	 * create 11/09/2015
	 * @param fo100
	 * @return
	 */
	int getNewWebDraftChildCn806(int fo100);
	/**
	 * @param fo100
	 * @return
	 */
	int getNewWebChildCn806(int fo100);
	/**
	 * ThoaiNH
	 * Date create: 11:13 am 06/04/2015
	 */
	String getThumbnailOfWeb(int fo100, int webId, String type);
	/**
	 * get color of C900
	 * @param fo100
	 * @return
	 */
	String getUserColor(int fo100);
	/**
	 * check short web is exists
	 * @param webType: Ex: "C900"
	 * @param refId: md5(webid + languageCod)
	 * @return: 1 = exists, 0 = not exists
	 */
	int checkWebShortExists(int fo100, String webType, String refId);
	/**
	 * @param fo100: user id
	 * @return: list language of web home (C400)
	 */
	List<LanguageMG> getUserLanguage(int fo100);
	List<ComtabItem> getAllCoponent(int fc800);
}
