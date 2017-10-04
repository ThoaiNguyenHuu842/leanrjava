package com.ohhay.piepme.mongo.utils;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.piepme.mongo.entities.reportpieper.B200BPMG;
import com.ohhay.piepme.mongo.entities.reportpieper.B200PPMG;

/**
 * @author ThoaiNH
 * create May 11, 2017
 */
public class PiepmeTemplateRule {
	public static String getReportCollectionByPieperType(String pieperType){
		switch (pieperType) {
		case QbMongoCollectionsName.P300B:
			return QbMongoCollectionsName.B200B;
		case QbMongoCollectionsName.P300P:
			return QbMongoCollectionsName.B200P;
		default:
			break;
		}
		return null;
	}
	public static String getBlockPieperCollectionByPieperType(String pieperType){
		switch (pieperType) {
		case QbMongoCollectionsName.P300B:
			return QbMongoCollectionsName.B300B;
		case QbMongoCollectionsName.P300P:
			return QbMongoCollectionsName.B300P;
		default:
			break;
		}
		return null;
	}
	public static String getBlockAccountCollectionByPieperType(String pieperType){
		switch (pieperType) {
		case QbMongoCollectionsName.P300B:
			return QbMongoCollectionsName.B100B;
		case QbMongoCollectionsName.P300P:
			return QbMongoCollectionsName.B100P;
		default:
			break;
		}
		return null;
	}
	public static <T> Class<T> getReportCollectionByCollection(String collectionName) {
		switch (collectionName) {
		case  QbMongoCollectionsName.B200B:
			return (Class<T>) B200BPMG.class;
		case QbMongoCollectionsName.C100C:
			return (Class<T>) B200PPMG.class;
		default:
			break;
		}
		return null;
	}
}
