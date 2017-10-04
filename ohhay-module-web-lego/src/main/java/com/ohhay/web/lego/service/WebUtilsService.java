package com.ohhay.web.lego.service;

import org.json.JSONObject;

/**
 * @author ThoaiNH
 * create Nov 6, 2015
 */
public interface WebUtilsService {
	/**
	 * @param fo100
	 * @param webId
	 * @param webData
	 * @return
	 */
	int saveBG(int fo100, long webId, JSONObject webData, int extend);
	/**
	 * @param fo100
	 * @param webId
	 * @param webData
	 * @return
	 */
	int saveConfig(int fo100, long webId, JSONObject webData, int extend);
	/**
	 * @param fo100
	 * @param webId
	 * @param webData
	 * @param extend
	 * @return
	 */
	int saveElemeCrId(int fo100, long webId, JSONObject webData, int extend);
}
