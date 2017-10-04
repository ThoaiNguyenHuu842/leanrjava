package com.ohhay.core.mongo.util;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 *
 */
public class QbMongoUtils {
	private static SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);

	public static String addId(String jsonString, String colectionName, int fo100) {
		try {
			String subId = "\"_id\":"
					+ sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.M900)
					+ ",";
			StringBuilder builder = new StringBuilder("{");
			builder.append(subId);
			builder.append(jsonString.substring(1, jsonString.length()));
			return builder.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
