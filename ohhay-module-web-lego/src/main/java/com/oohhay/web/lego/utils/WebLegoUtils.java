package com.oohhay.web.lego.utils;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ohhay.web.lego.entities.mongo.base.web.BgWebBase;
import com.ohhay.web.lego.entities.mongo.web.E920MG;

/**
 * @author ThoaiNH
 * create Nov 6, 2015
 */
public class WebLegoUtils {
	private static Logger log = Logger.getLogger(WebLegoUtils.class);
	/**
	 * @param jsonObject
	 * @return
	 */
	public static BgWebBase getBgOgObject(JSONObject jsonObject, String boxProperty){
		try {
			if(jsonObject.has(boxProperty)){
				JSONObject bg = jsonObject.getJSONObject(boxProperty);
				String type = bg.getString(WebRule.WEB_PRO_TYPE);
				String data = bg.getString(WebRule.WEB_PRO_DATA);
				BgWebBase bgWebBase = new BgWebBase();
				bgWebBase.setData(data);
				bgWebBase.setType(type);
				return bgWebBase;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			log.info("--- web element do'nt have bg object");
		}
		return null;
	}
}
