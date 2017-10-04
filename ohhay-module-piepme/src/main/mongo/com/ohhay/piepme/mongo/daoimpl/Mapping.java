/*package com.ohhay.piepme.mongo.daoimpl;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

*//**
 * @author: htmz
 * @create: 26-04-2017 01:01 AM
 *//*
public class Mapping {
	static Logger log = Logger.getLogger(Mapping.class);

	
	 * mongodb helper auto mapping with class
	 
	private <T> T objectAutoMapingClassEach(JSONObject object, Class<T> obj) {
		try {
			Iterator<?> keys = object.keys();
			T objCast = obj.newInstance();
			while (keys.hasNext()) {
				try {
					String key = (String) keys.next();
					log.info("Map key : " + key);
					Method[] methods = obj.getMethods();
					Method methodGet = null;
					String nameField = key.toLowerCase().replaceAll("_", "");
					String setter = "set" + upperCharFrist(nameField);
					for (Method method : methods) {
						if (method.getName().equalsIgnoreCase(setter)) {
							methodGet = method;
							break;
						}
					}
					// log.info(methodGet);
					// get list param for method
					Object[] listParam = new Object[methodGet.getParameterCount()];
					if (methodGet != null) {
						int i = 0;
						for (Type param : methodGet.getGenericParameterTypes()) {
							try {
								listParam[i] = switchType(param.toString(), object.get(key));
							} catch (Exception e) {
								// TODO: handle exception
								log.info(e);
								listParam[i] = null;
							}
							i++;
						}
					}
					// for (Object object2 : listParam) {
					// log.info(object2);
					// }
					methodGet.invoke(objCast, listParam);
				} catch (Exception e) {
					// TODO: handle exception
					log.info("Not field");
				}
			}
			return objCast;
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
		}
		return null;
	}

	*//**
	 * @author: htmz
	 * @throws JSONException
	 * @create: 26-04-2017 01:13 AM
	 * @category Catch class special
	 *//*
	private Object switchType(String type, Object name) throws Exception {
		Object reClass = name;
		// case java 7 :
		type = type.replaceAll("class", "").trim();
		switch (type) {
		case "java.util.Date":
			// Mongo ISODate
			try {
				reClass = ISODateTimeFormat.dateTime().parseDateTime(name.toString()).toDate();
			} catch (Exception e) {
				// TODO: handle exception
				log.info("Try map field $date");
				JSONObject jsonObject = new JSONObject(name.toString());
				String dateTry = jsonObject.get("$date").toString();
				reClass = ISODateTimeFormat.dateTime().parseDateTime(dateTry).toDate();
			}
			break;
		case "[Ljava.lang.String;":
			// String reClassTp = name.toString();
			JSONArray jsonArray = new JSONArray(name.toString());
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
				list.add(jsonArray.getString(i));
			}
			String[] stringArray = list.toArray(new String[list.size()]);
			reClass = stringArray;
			break;
		}

		return reClass;
	}

	private static String upperCharFrist(String text) {
		String reVal = text;
		if (text != null && !text.equals("")) {
			reVal = text.substring(0, 1).toUpperCase() + text.substring(1);
		}
		return reVal;
	}

	*//**
	 * @author: htmz
	 * @create: 27-04-2017 11:13 AM
	 * @category main mapping
	 *//*
	public <T> T mappingAuto(String jsonObject, Class<T> obj) {
		log.info("Data : " + jsonObject);
		try {
			JSONObject object = new JSONObject(jsonObject);
			return objectAutoMapingClassEach(object, obj);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error with json object try with JSONARRAY !");
			try {
				List<T> list = new ArrayList<T>();
				JSONArray jsonArray = new JSONArray(jsonObject);
				for (int i = 0, max = jsonArray.length(); i < max; i++) {
					JSONObject object = jsonArray.getJSONObject(i);
					list.add(objectAutoMapingClassEach(object, obj));
				}
				return (T) list;
			} catch (Exception e2) {
				// TODO: handle exception
				log.info(e2);
			}
		}
		return null;
	}

	
	 * end support mapping
	 
}
*/