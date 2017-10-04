package com.ohhay.web.lego.service;

import java.util.Map;

import org.json.JSONObject;

/**
 * @author ThoaiNH
 * create Nov 6, 2015
 * process json data to save component data
 */
public interface ComponentUtilsService {
	/**
	 * @param fo100
	 * @param webId
	 * @param boxId
	 * @param component
	 * @return
	 */
	int updateComponent(int fo100, long webId, long boxId, JSONObject component, Map<String, Long> returnData, int extend);
}
