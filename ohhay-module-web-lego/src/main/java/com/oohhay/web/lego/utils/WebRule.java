package com.oohhay.web.lego.utils;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.lego.entities.mongo.topic.ET400MG;
import com.ohhay.web.lego.entities.mongo.topic.ET900MG;
import com.ohhay.web.lego.entities.mongo.topic.ET920MG;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.entities.mongo.web.E900MG;
import com.ohhay.web.lego.entities.mongo.web.E920MG;
import com.ohhay.web.lego.entities.mongo.web.E950MG;

/**
 * @author ThoaiNH
 * create Nov 6, 2015
 */
public class WebRule {
	/*
	 * object model property name
	 */
	public static final String WEB_PRO_INDEX = "index";
	public static final String WEB_PRO_LISTBOX = "listBox";
	public static final String WEB_PRO_LIBTYPE = "libType";
	public static final String WEB_PRO_LISTCOMP = "listComponent";
	public static final String WEB_PRO_WEBID = "webId";
	public static final String WEB_PRO_BOXID = "boxId";
	public static final String WEB_PRO_ID = "id";
	public static final String WEB_PRO_BG = "bg";
	public static final String WEB_PRO_GRID_BG = "gridBg";
	public static final String WEB_PRO_CONFIG = "config";
	public static final String WEB_PRO_CSS = "css";
	public static final String WEB_PRO_BGCSS = "bgCss";
	public static final String WEB_PRO_M_BGCSS = "mBgCss";
	public static final String WEB_PRO_GRID_CSS = "gridCss";
	public static final String WEB_PRO_GRID_BGCSS = "gridBgCss";
	public static final String WEB_PRO_X = "x";
	public static final String WEB_PRO_Y = "y";
	public static final String WEB_PRO_W = "w";
	public static final String WEB_PRO_H = "h";
	public static final String WEB_PRO_DATA = "data";
	public static final String WEB_PRO_FE920 = "fe920";
	public static final String WEB_PRO_TYPE = "type";
	public static final String WEB_PRO_RE_TYPE = "reType";
	public static final String WEB_PRO_STT = "stt";
	public static final String WEB_PRO_TEXT = "text";
	public static final String WEB_PRO_BG_VID = "bgVid";
	public static final String WEB_PRO_GRID_BG_VID = "gridBgVid";
	public static final String WEB_PRO_ELEME_CR_ID = "elementCrId";
	public static final String WEB_PRO_C_FORMID = "cFormId";
	public static final String WEB_PRO_R_FORMID= "rFormId";
	public static final String WEB_PRO_NAME = "name";
	public static final String WEB_PRO_MDATA = "mData";
	public static final String WEB_PRO_HIDE = "hide";
	public static final String WEB_PRO_ONLOAD_EFFECT = "onLoadEffect";
	public static final String WEB_PRO_WIGETNAME = "widgetName";
	public static final String WEB_PRO_WIGET_ELEMENT_ID = "widgetEleId";
	public static final String WEB_PRO_GRID_INDEX = "gridIndex";
	public static final String WEB_PRO_FE900 = "fe900";
	/*
	 * component type
	*/
	public static final String WEB_COMP_TYPE_TEXT = "normal-text";
	public static final String WEB_COMP_TYPE_IMG = "normal-image";
	public static final String WEB_COMP_TYPE_BTN = "normal-button";
	public static final String WEB_COMP_TYPE_GMAP = "normal-gmap";
	public static final String WEB_COMP_TYPE_IFRAME = "normal-iframe";
	/*
	 * propertype value constants
	 */
	public static final String WEB_PRO_STT_NEW = "NEW";
	public static final String WEB_PRO_STT_REMOVED = "REMOVE";
	public static final String WEB_PRO_STT_UPDATE = "UPDATED";
	public static final String WEB_PRO_STT_NO_CHANGE = "NO_CHANGE";
	/*
	 * id, class, attribute
	 */
	public static final String WEB_ATTR_QB_COMP_ID = "qb-component-id";
	/*
	 * get web class from extend
	 */
	public static <T> Class<T> getWebClassFromExtend(int extend) {
		switch (extend) {
			case EvoWebType.EVOTYPE_TOPIC:
				return (Class<T>) ET400MG.class;
			case EvoWebType.EVOTYPE_WEB:
				return (Class<T>) E400MG.class;
			default:
				break;
		}
		return null;
	}
	/*
	 * get box class from extend
	 */
	public static <T> Class<T> getBoxClassFromExtend(int extend) {
		switch (extend) {
			case EvoWebType.EVOTYPE_TOPIC:
				return (Class<T>) ET920MG.class;
			case EvoWebType.EVOTYPE_WEB:
				return (Class<T>) E920MG.class;
			default:
				break;
		}
		return null;
	}
	/*
	 * get component class from extend
	 */
	public static <T> Class<T> getComponentClassFromExtend(int extend) {
		switch (extend) {
			case EvoWebType.EVOTYPE_TOPIC:
				return (Class<T>) ET900MG.class;
			case EvoWebType.EVOTYPE_WEB:
				return (Class<T>) E900MG.class;
			default:
				break;
		}
		return null;
	}
	/*
	 * get collection name by extend
	 */
	public static String getWebMongoColectionFromExtend(int extend) {
		switch (extend) {
		case EvoWebType.EVOTYPE_TOPIC:
			return QbMongoCollectionsName.ET400;
		case EvoWebType.EVOTYPE_WEB:
			return QbMongoCollectionsName.E400;
		default:
			break;
		}
		return null;
	}
	/*
	 * get collection name by extend
	 */
	public static String getBoxMongoColectionFromExtend(int extend) {
		switch (extend) {
		case EvoWebType.EVOTYPE_TOPIC:
			return QbMongoCollectionsName.ET920;
		case EvoWebType.EVOTYPE_WEB:
			return QbMongoCollectionsName.E920;
		default:
			break;
		}
		return null;
	}
	/*
	 * get collection name by extend
	 */
	public static String getComponentMongoColectionFromExtend(int extend) {
		switch (extend) {
		case EvoWebType.EVOTYPE_TOPIC:
			return QbMongoCollectionsName.ET900;
		case EvoWebType.EVOTYPE_WEB:
			return QbMongoCollectionsName.E900;
		default:
			break;
		}
		return null;
	}
	/*
	 * get wen short name by collection name
	 */
	public static <T> String getWebShortMongoColectionFromExtend(int extend) {
		switch (extend) {
		case EvoWebType.EVOTYPE_WEB:
			return QbMongoCollectionsName.E950;
		default:
			break;
		}
		return null;
	}
	/*
	 * get wen short name by collection name
	 */
	public static <T> Class<T> getWebShortClassFromExtend(int extend) {
		switch (extend) {
		case EvoWebType.EVOTYPE_WEB:
			return (Class<T>) E950MG.class;
		default:
			break;
		}
		return null;
	}
}
