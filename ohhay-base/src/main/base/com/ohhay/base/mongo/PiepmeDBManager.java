package com.ohhay.base.mongo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ThoaiNH
 * create Dec 27, 2016
 * store all PIEPME DB center for each area
 */
public class PiepmeDBManager implements Serializable{
	private static Map<String, String> mapDbByLocation = new HashMap<String, String>();
	static {
		mapDbByLocation.put("vn", "piepcentVN#localhost;4005;piepme;Xmaj82oka28dm;piepmecent");
		mapDbByLocation.put("de", "piepcentVN#localhost;4005;piepme;Xmaj82oka28dm;piepmecent");
	}
	public static String getURI(String countryCode){
		return mapDbByLocation.get(countryCode);
	}
}
