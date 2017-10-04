package com.ohhay.web.lego.service;

import java.util.Map;

import org.json.JSONObject;

import com.ohhay.web.lego.entities.mongo.base.box.Box;
import com.ohhay.web.lego.entities.mongo.web.E920MG;

/**
 * @author ThoaiNH
 * create Nov 6, 2015
 * process json data to save box data
 */
public interface BoxUtilsService {
	/**
	 * @param fo100
	 * @param webId
	 * @param box
	 * @param returnData
	 * @return
	 */
	int updateBox(int fo100, long webId, JSONObject box, Map<String, Long> returnData, int extend);
	/**
	 * cap nhat web publish cho chua nhung box refrence de boxId
	 * @param e920mg
	 * @return
	 */
	int updateBoxUsingLib(E920MG e920mg);
	/**
	 * cap nhat phien ban mobile
	 * cap nhat web publish cho chua nhung box refrence de boxId
	 * @param e920mg
	 * @return
	 */
	int updateBoxUsingLibMo(E920MG e920mg);
}
