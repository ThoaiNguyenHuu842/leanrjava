package com.ohhay.web.core.utils;

import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.web.A400MG;
import com.ohhay.web.core.entities.mongo.web.B400MG;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.web.L400MG;
import com.ohhay.web.core.entities.mongo.web.T400MG;
import com.ohhay.web.core.entities.mongo.web.W400MG;
import com.ohhay.web.core.entities.mongo.webchild.A500MG;
import com.ohhay.web.core.entities.mongo.webchild.B500MG;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.webchild.T500MG;
import com.ohhay.web.core.entities.mongo.webchild.W500MG;
import com.ohhay.web.core.entities.mongo.weblanguage.A100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.B100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.C100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.L100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.T100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.V100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.W100MG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.A100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.B100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.C100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.T100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.W100CMG;
import com.ohhay.web.core.entities.mongo.webshort.C550MG;
import com.ohhay.web.core.entities.mongo.webshort.C950MG;
import com.ohhay.web.core.entities.mongo.webshort.L950MG;
import com.ohhay.web.core.entities.mongo.webshort.T550MG;
import com.ohhay.web.core.entities.mongo.webshort.T950MG;

/**
 * @author ThoaiNH
 * create 20/04/2015
 * rule of all web o!hay
 * ** VERY IMPORTANT CLASS
 */
public class WebTemplateRule {
	/*
	 * get collection name by extend
	 */
	public static String getWebMongoColectionFromExtend(int extend) {
		switch (extend) {
		case OhhayWebType.WEBTYPE_OHHAY_WEBHOME:
			return QbMongoCollectionsName.C900;
		case OhhayWebType.WEBTYPE_OHHAY_WEBHOME_CHILD:
			return QbMongoCollectionsName.C500;
		case OhhayWebType.WEBTYPE_OHHAY_WEBDRAFT:
			return QbMongoCollectionsName.T900;
		case OhhayWebType.WEBTYPE_OHHAY_WEBDRAFT_CHILD:
			return QbMongoCollectionsName.T500;
		case OhhayWebType.WEBTYPE_OHHAY_WEBLANDING:
			return QbMongoCollectionsName.L900;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINAR:
			return QbMongoCollectionsName.W900;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINARCHILD:
			// TODO soon
			break;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINAROOM:
			return QbMongoCollectionsName.W900;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINAROOM_CHILD:
			return QbMongoCollectionsName.W500;
		case OhhayWebType.WEBTYPE_OHHAY_BSELL:
			return QbMongoCollectionsName.B900;
		case OhhayWebType.WEBTYPE_OHHAY_BSELL_CHILD:
			return QbMongoCollectionsName.B500;
		case OhhayWebType.WEBTYPE_OHHAY_VIDEOMARKETING:
			return QbMongoCollectionsName.L900;
		case OhhayWebType.WEBTYPE_OHHAY_TEMPLATE:
			return QbMongoCollectionsName.A900;
		case OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD:
			return QbMongoCollectionsName.A500;
		default:
			break;
		}
		return null;
	}

	/*
	 * get web class by extend
	 */
	public static <T> Class<T> getWebClassFromExtend(int extend) {
		switch (extend) {
		case OhhayWebType.WEBTYPE_OHHAY_WEBHOME:
			return (Class<T>) C400MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBHOME_CHILD:
			return (Class<T>) C500MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBDRAFT:
			return (Class<T>) T400MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBDRAFT_CHILD:
			return (Class<T>) T500MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBLANDING:
			return (Class<T>) L400MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINAR:
			return (Class<T>) W400MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINARCHILD:
			// TODO soon
			break;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINAROOM:
			return (Class<T>) W400MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINAROOM_CHILD:
			return (Class<T>) W500MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_BSELL:
			return (Class<T>) B400MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_BSELL_CHILD:
			return (Class<T>) B500MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_VIDEOMARKETING:
			return (Class<T>) L400MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_TEMPLATE:
			return (Class<T>) A400MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD:
			return (Class<T>) A500MG.class;
		default:
			break;
		}
		return null;
	}

	/*
	 * get language class by extent
	 */
	public static <T> Class<T> getWebLanguageClassFromExtend(int extend) {
		switch (extend) {
		case OhhayWebType.WEBTYPE_OHHAY_WEBHOME:
			return (Class<T>) C100MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBHOME_CHILD:
			return (Class<T>) C100CMG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBDRAFT:
			return (Class<T>) T100MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBDRAFT_CHILD:
			return (Class<T>) T100CMG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBLANDING:
			return (Class<T>) L100MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINAR:
			return (Class<T>) W500MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINARCHILD:
			// TODO soon
			break;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINAROOM:
			return (Class<T>) W100MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_WEBINAROOM_CHILD:
			return (Class<T>) W100CMG.class;
		case OhhayWebType.WEBTYPE_OHHAY_BSELL:
			return (Class<T>) B100MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_BSELL_CHILD:
			return (Class<T>) B100CMG.class;
		case OhhayWebType.WEBTYPE_OHHAY_VIDEOMARKETING:
			return (Class<T>) L100MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_TEMPLATE:
			return (Class<T>) A100MG.class;
		case OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD:
			return (Class<T>) A100CMG.class;
		default:
			break;
		}
		return null;
	}
	/*
	 * get language class by collection name
	 */
	public static <T> Class<T> getWebLanguageClassFromCollectionClass(Class<T> mClass) {
		if (mClass.equals(C400MG.class))
			return (Class<T>) C100MG.class;
		else if (mClass.equals(C500MG.class))
			return (Class<T>) C100CMG.class;
		else if (mClass.equals(W500MG.class))
			return (Class<T>) W100CMG.class;
		else if (mClass.equals(W400MG.class))
			return (Class<T>) W100MG.class;
		else if (mClass.equals(B400MG.class))
			return (Class<T>)  B100MG.class;
		else if (mClass.equals(B500MG.class))
			return (Class<T>)  B100MG.class;
		else if (mClass.equals(T400MG.class))
			return (Class<T>) T100MG.class;
		else if (mClass.equals(T500MG.class))
			return (Class<T>) T100CMG.class;
		else if (mClass.equals(L400MG.class))
			return (Class<T>) L100MG.class;
		else if (mClass.equals(A400MG.class))
			return (Class<T>) A100MG.class;
		else if (mClass.equals(A500MG.class))
			return (Class<T>) A100CMG.class;
		else
			return null;
	}
	/*
	 * get wen short class by collection name
	 */
	public static <T> Class<T> getWebShortClassFromCollectionClass(Class<T> mClass) {
		if (mClass.equals(C400MG.class))
			return (Class<T>) C950MG.class;
		else if (mClass.equals(C500MG.class))
			return (Class<T>) C550MG.class;
		else if (mClass.equals(T400MG.class))
			return (Class<T>) T950MG.class;
		else if (mClass.equals(T500MG.class))
			return (Class<T>) T550MG.class;
		else if (mClass.equals(L400MG.class))
			return (Class<T>) L950MG.class;
		else
			return null;
	}
	/*
	 * get wen short name by collection name
	 */
	public static <T> String getWebShortNameFromCollectionClass(Class<T> mClass) {
		if (mClass.equals(C400MG.class))
			return QbMongoCollectionsName.C950;
		else if (mClass.equals(C500MG.class))
			return QbMongoCollectionsName.C550;
		else if (mClass.equals(T400MG.class))
			return QbMongoCollectionsName.T950;
		else if (mClass.equals(T500MG.class))
			return QbMongoCollectionsName.T550;
		else if (mClass.equals(L400MG.class))
			return QbMongoCollectionsName.L950;
		else
			return null;
	}
	/*
	 * get language class by collection name
	 */
	public static <T> Class<T> getWebLanguageClassFromCollectionName(String collectionName) {
		switch (collectionName) {
		case  QbMongoCollectionsName.C100:
			return (Class<T>) C100MG.class;
		case QbMongoCollectionsName.C100C:
			return (Class<T>) C100CMG.class;
		case QbMongoCollectionsName.T100:
			return (Class<T>) T100MG.class;
		case QbMongoCollectionsName.T100C:
			return (Class<T>) T100CMG.class;
		case QbMongoCollectionsName.L100:
			return (Class<T>) L100MG.class;
		case QbMongoCollectionsName.C100H:
			// TODO soon
			break;
		case QbMongoCollectionsName.W100:
			return (Class<T>) W100MG.class;
		case QbMongoCollectionsName.W100C:
			return (Class<T>) W100CMG.class;
		case QbMongoCollectionsName.B100:
			return (Class<T>) B100MG.class;
		case QbMongoCollectionsName.B100C:
			return (Class<T>) B100CMG.class;
		case QbMongoCollectionsName.V100:
			return (Class<T>) V100MG.class;
		case QbMongoCollectionsName.A100:
			return (Class<T>) A100MG.class;
		case QbMongoCollectionsName.A100C:
			return (Class<T>) A100CMG.class;			
		default:
			break;
		}
		return null;
	}
	/*
	 * get name of language collection from web collection
	 */
	public static String getWebLanguageCollection(String webMongoCollection) {
		switch (webMongoCollection) {
		case QbMongoCollectionsName.C900:
			return QbMongoCollectionsName.C100;
		case QbMongoCollectionsName.C500:
			return QbMongoCollectionsName.C100C;
		case QbMongoCollectionsName.B900:
			return QbMongoCollectionsName.B100;
		case QbMongoCollectionsName.W500:
			return QbMongoCollectionsName.W100C;
		case QbMongoCollectionsName.W900:
			return QbMongoCollectionsName.W100;
		case QbMongoCollectionsName.V900:
			return QbMongoCollectionsName.V100;
		case QbMongoCollectionsName.L900:
			return QbMongoCollectionsName.L100;
		case QbMongoCollectionsName.T900:
			return QbMongoCollectionsName.T100;
		case QbMongoCollectionsName.T500:
			return QbMongoCollectionsName.T100C;
		case QbMongoCollectionsName.A900:
			return QbMongoCollectionsName.A100;
		case QbMongoCollectionsName.A500:
			return QbMongoCollectionsName.A100C;			
		default:
			break;
		}
		return null;
	}
}
