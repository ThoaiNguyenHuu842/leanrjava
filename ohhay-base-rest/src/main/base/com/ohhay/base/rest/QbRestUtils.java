package com.ohhay.base.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author ThoaiNH
 * create 12/08/2015
 * util convert data to json string
 */
public class QbRestUtils {
	public static final String REQUEST_PARAM = "param";
	private static final String JSON_ELEMENTS_NAME = "elements";
	private static final String JSON_ELEMENT_NAME = "element";
	public static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	private static final QbJsonStatus STATUS = new QbJsonStatus();
	public static String convertToJsonStringForProc(Object object) {
		String result = "[]";
		if (object != null)
			result = gson.toJson(object);
		return "{"
				+ STATUS.getSuccessStatus().replace("{", "").replace("}", "")
				+ ",\"" + JSON_ELEMENTS_NAME + "\":" + result + "}";
	}

	public static String convertToJsonStringForProc(Object object, Object list) {
		String resultObject = "";
		if (resultObject != null)
			resultObject = gson.toJson(object);

		String resultList = "[]";
		if (resultList != null)
			resultList = gson.toJson(list);
		return "{"
				+ STATUS.getSuccessStatus().replace("{", "").replace("}", "")
				+ ",\""  + JSON_ELEMENT_NAME + "\":" + resultObject
				+ ",\"" + JSON_ELEMENTS_NAME + "\":" + resultList + "}";
	}

	public static String convertToJsonStringForFunc(Object object) {
		return "{"
				+ STATUS.getSuccessStatus().replace("{", "").replace("}", "")
				+ ",\"" + JSON_ELEMENTS_NAME + "\":"
				+ STATUS.getFuctionResult(object) + "}";
	}
	
	public static String convertToJsonStringForFunc(Object object2, Object object) {
		return "{"
				+ STATUS.getSuccessStatus().replace("{", "").replace("}", "")
				+ ",\""  + JSON_ELEMENT_NAME + "\":" + object2
				+ ",\"" + JSON_ELEMENTS_NAME + "\":"
				+ STATUS.getFuctionResult(object) + "}";
	}
	
	public static String getSuccessStatus() {
		return "{"
				+ STATUS.getSuccessStatus().replace("{", "").replace("}", "")
				+ ",\"" + JSON_ELEMENTS_NAME + "\":" + "[]" + "}";
	}

	public static String getErrorStatus() {
		return "{" + STATUS.getErrorStatus().replace("{", "").replace("}", "")
				+ ",\"" + JSON_ELEMENTS_NAME + "\":" + "[]" + "}";
	}

}
