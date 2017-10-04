package com.ohhay.core.constant;

import com.ohhay.base.constant.PropertiesLoader;


/**
 * @author ThoaiNH
 * create 03/03/2015
 * all constant (html class, id, attribute...) to load and edit web o!hay
 */
public class TemplateRule{
	static {
		PropertiesLoader prop = new PropertiesLoader(PropertiesLoader.TEMPLATE);
		TemplateRule.TEMPLATE_ROOT_DOMAIN_PATH = prop
				.getProperty("TEMPLATE_ROOT_DOMAIN_PATH");
		TemplateRule.OHHAY_DOMAIN_LINK = prop
				.getProperty("OHHAY_DOMAIN_LINK");
		TemplateRule.TEMPLATE_ROOT_PATH = prop
				.getProperty("TEMPLATE_ROOT_PATH");
		TemplateRule.OHHAY_STYLE_LINK = prop
				.getProperty("OHHAY_STYLE_LINK");
		TemplateRule.TEMPLATE_INDEX_PAGE = prop
				.getProperty("TEMPLATE_INDEX_PAGE");
		TemplateRule.OHHAY_QB_DATA_CONTENT = prop
				.getProperty("OHHAY_QB_DATA_CONTENT");
		TemplateRule.OHHAY_QB_DATA_CONTAINER = prop
				.getProperty("OHHAY_QB_DATA_CONTAINER");
		TemplateRule.OHHAY_SERVER_LINK = prop
				.getProperty("OHHAY_SERVER_LINK");
		TemplateRule.OHHAY_SERVER_LINK_VALUE = prop
				.getProperty("OHHAY_SERVER_LINK_VALUE");
		TemplateRule.OHHAY_BASE_LINK = prop
				.getProperty("OHHAY_BASE_LINK");
		TemplateRule.OHHAY_SUPER_ID = prop.getProperty("OHHAY_SUPER_ID");
		TemplateRule.OHHAY_BOX_ID = prop.getProperty("OHHAY_BOX_ID");
		TemplateRule.OHHAY_EDIT_BOX_REAL_INDEX = prop.getProperty("OHHAY_EDIT_BOX_REAL_INDEX");
		TemplateRule.OHHAY_EDIT_BOX_VISIBLE = prop.getProperty("OHHAY_EDIT_BOX_VISIBLE");
		TemplateRule.OHHAY_2CHAM = prop.getProperty("OHHAY_2CHAM");
		TemplateRule.OHHAY_2CHAM_VALUE = prop.getProperty("OHHAY_2CHAM_VALUE");
		/*
		 * edit type
		 */
		TemplateRule.OHHAY_EDIT_CLASS_NOT_PREVENT_A_CLICK = prop.getProperty("OHHAY_EDIT_CLASS_NOT_PREVENT_A_CLICK");
		TemplateRule.OHHAY_EDIT_BOX_CLASS = prop
				.getProperty("OHHAY_EDIT_BOX_CLASS");
		TemplateRule.OHHAY_EDIT_BOX_TYPE = prop
				.getProperty("OHHAY_EDIT_BOX_TYPE");
		TemplateRule.OHHAY_EDIT_BOX_ORIGINAL = prop.getProperty("OHHAY_EDIT_BOX_ORIGINAL");
		TemplateRule.OHHAY_EDIT_CLASS = prop
				.getProperty("OHHAY_EDIT_CLASS");
		TemplateRule.OHHAY_EDIT_TEXT_NULL_CLASS = prop
				.getProperty("OHHAY_EDIT_TEXT_NULL_CLASS");
		TemplateRule.OHHAY_EDIT_TEXT_IFRAME_GALLERY_CLASS = prop
				.getProperty("OHHAY_EDIT_TEXT_IFRAME_GALLERY_CLASS");
		TemplateRule.OHHAY_EDIT_TEXT_IFRAME_CLASS = prop
				.getProperty("OHHAY_EDIT_TEXT_IFRAME_CLASS");
		TemplateRule.OHHAY_IFRAME_WIDTH = prop
				.getProperty("OHHAY_IFRAME_WIDTH");
		TemplateRule.OHHAY_IFRAME_HEIGHT = prop
				.getProperty("OHHAY_IFRAME_HEIGHT");
		TemplateRule.OHHAY_IFRAME_SRC_ATTR = prop
				.getProperty("OHHAY_IFRAME_SRC_ATTR");
		TemplateRule.OHHAY_EDIT_TEXT_CLASS = prop
				.getProperty("OHHAY_EDIT_TEXT_CLASS");
		TemplateRule.OHHAY_EDIT_TEXT_RSS_CLASS = prop
				.getProperty("OHHAY_EDIT_TEXT_RSS_CLASS");
		TemplateRule.OHHAY_EDIT_IMAGE_CLASS = prop
				.getProperty("OHHAY_EDIT_IMAGE_CLASS");
		TemplateRule.OHHAY_EDIT_LINK_CLASS = prop
				.getProperty("OHHAY_EDIT_LINK_CLASS");
		TemplateRule.OHHAY_EDIT_PERCENT_CLASS = prop
				.getProperty("OHHAY_EDIT_PERCENT_CLASS");
		TemplateRule.OHHAY_QB_ACCOUNT_SHORT_FILED_CLASS = prop
				.getProperty("OHHAY_QB_ACCOUNT_SHORT_FILED_CLASS");
		TemplateRule.OHHAY_QB_ERROR_CLASS = prop
				.getProperty("OHHAY_QB_ERROR_CLASS");
		TemplateRule.OHHAY_QB_NO_LOGIN_CLASS = prop
				.getProperty("OHHAY_QB_NO_LOGIN_CLASS");
		/*
		 * data type
		 */
		TemplateRule.OHHAY_DATA_QB_TYPE = prop
				.getProperty("OHHAY_DATA_QB_TYPE");
		TemplateRule.OHHAY_DATA_QB_TYPE_PERCENT = prop
				.getProperty("OHHAY_DATA_QB_TYPE_PERCENT");
		TemplateRule.OHHAY_DATA_QB_TYPE_TEXT = prop
				.getProperty("OHHAY_DATA_QB_TYPE_TEXT");
		TemplateRule.OHHAY_DATA_QB_TYPE_IMAGE = prop
				.getProperty("OHHAY_DATA_QB_TYPE_IMAGE");
		TemplateRule.OHHAY_DATA_QB_TYPE_LINK = prop
				.getProperty("OHHAY_DATA_QB_TYPE_LINK");
		TemplateRule.OHHAY_DATA_QB_SUB_CONTAINER = prop
				.getProperty("OHHAY_DATA_QB_SUB_CONTAINER");
		TemplateRule.OHHAY_DATA_QB_TYPE_GALLERY_ITEM_LINK_IMAGE = prop
				.getProperty("OHHAY_DATA_QB_TYPE_GALLERY_ITEM_LINK_IMAGE");
		TemplateRule.OHHAY_EDIT_GALLERY_ITEM_CLASS = prop
				.getProperty("OHHAY_EDIT_GALLERY_ITEM_CLASS");
		TemplateRule.OHHAY_EDIT_FC850 = prop
				.getProperty("OHHAY_EDIT_FC850");
		TemplateRule.OHHAY_DATA_QB_TYPE_BG_COLOR = prop
				.getProperty("OHHAY_DATA_QB_TYPE_BG_COLOR");
		TemplateRule.OHHAY_EDIT_BG_COLOR = prop
				.getProperty("OHHAY_EDIT_BG_COLOR");
		TemplateRule.OHHAY_DATA_QB_STYLE_TYPE = prop
				.getProperty("OHHAY_DATA_QB_STYLE_TYPE");
		TemplateRule.OHHAY_DATA_QB_STYLE = prop
				.getProperty("OHHAY_DATA_QB_STYLE");
		TemplateRule.OHHAY_DATA_QB_TYPE_GROUP = prop
				.getProperty("OHHAY_DATA_QB_TYPE_GROUP");
		TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT = prop
				.getProperty("OHHAY_DATA_QB_ACCEPT_EDIT");
		TemplateRule.OHHAY_DATA_QB_TYPE_GALLERY_ITEM = prop
				.getProperty("OHHAY_DATA_QB_TYPE_GALLERY_ITEM");
		TemplateRule.OHHAY_EDIT_GALLERY_ITEM_LINK_IMAGE_CLASS = prop
				.getProperty("OHHAY_EDIT_GALLERY_ITEM_LINK_IMAGE_CLASS");
		TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM = prop
				.getProperty("OHHAY_DATA_QB_ACCEPT_EDIT_ITEM");
		TemplateRule.OHHAY_DATA_QB_API_NAME = prop
				.getProperty("OHHAY_DATA_QB_API_NAME");
		TemplateRule.OHHAY_EDIT_GALLERY_ITEM_FIELD_CLASS = prop
				.getProperty("OHHAY_EDIT_GALLERY_ITEM_FIELD_CLASS");
		TemplateRule.OHHAY_NO_EDIT_CLASS = prop
				.getProperty("OHHAY_NO_EDIT_CLASS");
		TemplateRule.OHHAY_EDIT_GROUP_LINK_TEXT = prop
				.getProperty("OHHAY_EDIT_GROUP_LINK_TEXT");
		TemplateRule.OHHAY_EDIT_GROUP_LINK_IMAGE = prop
				.getProperty("OHHAY_EDIT_GROUP_LINK_IMAGE");
		TemplateRule.OHHAY_EDIT_GROUP_LINK_TEXT_IMAGE = prop
				.getProperty("OHHAY_EDIT_GROUP_LINK_TEXT_IMAGE");
		TemplateRule.OHHAY_QB_LINK_REGIST_CLASS = prop
				.getProperty("OHHAY_QB_LINK_REGIST_CLASS");
		TemplateRule.OHHAY_QB_ACCOUNT_SHORT_FILED_CLASS = prop
				.getProperty("OHHAY_QB_ACCOUNT_SHORT_FILED_CLASS");
		TemplateRule.OHHAY_QB_ERROR_CLASS = prop
				.getProperty("OHHAY_QB_ERROR_CLASS");
		TemplateRule.OHHAY_QB_NO_LOGIN_CLASS = prop
				.getProperty("OHHAY_QB_NO_LOGIN_CLASS");
		TemplateRule.OHHAY_DATA_QB_VIDEO_SUBCONTAINER = prop.getProperty("OHHAY_DATA_QB_VIDEO_SUBCONTAINER");
		TemplateRule.OHHAY_TEMPLATE_LINK = prop.getProperty("OHHAY_TEMPLATE_LINK");
	}
	public static final String TEMPLATE_ROOT_PATH_A900 = "https://oohhay.s3.amazonaws.com/imgtemplates/";
	public static String TEMPLATE_ROOT_PATH;
	public static String TEMPLATE_ROOT_DOMAIN_PATH;
	public static String TEMPLATE_TEMPLATE_SCREENSHOT = "screenshot.png";
	public static String TEMPLATE_INDEX_PAGE;
	public static String OHHAY_QB_DATA_CONTENT;
	public static String OHHAY_QB_DATA_CONTAINER;
	public static String OHHAY_DATA_QB_SUB_CONTAINER;
	public static String OHHAY_SERVER_LINK;
	public static String OHHAY_TEMPLATE_LINK;
	public static String OHHAY_SERVER_LINK_VALUE;//link hinh cua trang web dang xem
	public static String OHHAY_BASE_LINK;
	public static String OHHAY_STYLE_LINK;
	public static String OHHAY_DOMAIN_LINK;
	public static String OHHAY_SUPER_ID;
	public static String OHHAY_BOX_ID;
	public static String OHHAY_2CHAM;
	public static String OHHAY_2CHAM_VALUE;
	public static String OHHAY_DATA_QB_STYLE;
	public static String OHHAY_DATA_QB_STYLE_TYPE;
	public static String OHHAY_DATA_QB_VIDEO_SUBCONTAINER;
	public static String OHHAY_EDIT_BOX_CLASS;
	public static String OHHAY_EDIT_BOX_TYPE;
	public static String OHHAY_EDIT_BOX_ORIGINAL;
	public static String OHHAY_EDIT_BOX_REAL_INDEX;
	public static String OHHAY_EDIT_BOX_VISIBLE;
	public static String OHHAY_EDIT_CLASS;
	public static String OHHAY_EDIT_GALLERY_ITEM_CLASS;
	public static String OHHAY_EDIT_FC850;
	public static String OHHAY_EDIT_TEXT_CLASS;
	public static String OHHAY_EDIT_TEXT_RSS_CLASS;
	public static String OHHAY_EDIT_TEXT_NULL_CLASS;
	public static String OHHAY_EDIT_TEXT_IFRAME_CLASS;
	public static String OHHAY_EDIT_IMAGE_CLASS;
	public static String OHHAY_EDIT_LINK_CLASS;
	public static String OHHAY_EDIT_PERCENT_CLASS;
	public static String OHHAY_EDIT_GALLERY_ITEM_FIELD_CLASS;
	public static String OHHAY_NO_EDIT_CLASS;
	public static String OHHAY_EDIT_CLASS_NOT_PREVENT_A_CLICK;
	public static String OHHAY_EDIT_GROUP_LINK_TEXT;
	public static String OHHAY_EDIT_GROUP_LINK_IMAGE;
	public static String OHHAY_EDIT_GROUP_LINK_TEXT_IMAGE;
	public static String OHHAY_IFRAME_WIDTH;
	public static String OHHAY_IFRAME_HEIGHT;
	public static String OHHAY_IFRAME_SRC_ATTR;
	public static String OHHAY_EDIT_TEXT_IFRAME_GALLERY_CLASS;
	public static String OHHAY_QB_LINK_REGIST_CLASS;
	public static String OHHAY_QB_ACCOUNT_SHORT_FILED_CLASS;
	public static String OHHAY_QB_ERROR_CLASS;
	public static String OHHAY_QB_NO_LOGIN_CLASS;
	public static String OHHAY_DATA_QB_TYPE;
	public static String OHHAY_DATA_QB_TYPE_PERCENT;
	public static String OHHAY_DATA_QB_TYPE_TEXT;
	public static final String OHHAY_DATA_QB_IFRAME_TYPE_RSS = "RSS";
	public static final String OHHAY_DATA_QB_IFRAME_TYPE = "type";
	public static String OHHAY_DATA_QB_TYPE_IMAGE;
	public static String OHHAY_DATA_QB_TYPE_GALLERY_ITEM_LINK_IMAGE;
	public static String OHHAY_DATA_QB_TYPE_GALLERY_ITEM;
	public static String OHHAY_DATA_QB_TYPE_LINK;
	public static String OHHAY_DATA_QB_TYPE_BG_COLOR;
	public static String OHHAY_EDIT_BG_COLOR;
	public static String OHHAY_EDIT_GALLERY_ITEM_LINK_IMAGE_CLASS;
	public static String OHHAY_DATA_QB_TYPE_GROUP;
	public static String OHHAY_DATA_QB_ACCEPT_EDIT;
	public static String OHHAY_DATA_QB_ACCEPT_EDIT_ITEM;
	public static String OHHAY_DATA_QB_API_NAME;
	/*
	 * OHHAY INNER RULE
	 */
	public static String OHHAY_RULE_GMAP_MARKER = "marker";
	public static String OHHAY_RULE_GMAP_LA = "la";
	public static String OHHAY_RULE_GMAP_LOG = "log";
	public static String OHHAY_RULE_GMAP_INFOR = "infor";
	public static String OHHAY_RULE_GMAP_ADDRESS = "address";
	public static String OHHAY_RULE_GMAP_COLOR = "color";
	public static String OHHAY_CHANGEPOSITION_CLASS = "ohhay-changed-positon";//add this class to element has moved position
	public static String OHHAY_ADDED_INFO = "ohhay-added-info";
	public static String OHHAY_IMGS_NO_LAZY = "ohhay-img-no-lazy";//img which has this class will not use lazy load
	public static String OHHAY_IMGS_LINK = "oohhay-img-link";//attribute link of image
	/*
	 * TRACKING RULE
	 */
	public static String OHHAY_TRACKING_CLASS = "trackingObClass";
	public static String OHHAY_TRACKING_ATTR_OB = "ob";
	public static String OHHAY_TRACKING_ATTR_STT = "trackstt";
	/*
	 * WEB PROFILE CLASS
	 */
	public static String OHHAY_WEB_PROFILE_CLASS = "b4bf06605a7ac8dc7a8d55a65df8f91e";
	public static String OHHAY_WEB_PROFILE_CLASS_ADDRESS = "169cc335fd0561182db81eebccfefbfc";
	public static String OHHAY_WEB_PROFILE_CLASS_PHONE = "4f051f10d0dd1031e01e4d907104cda0";
	public static String OHHAY_WEB_PROFILE_CLASS_EMAIL = "d5e7242a7e7f794e601ce7943a028e74";
	/*
	 * OPACITY CLASS, ATTR
	 */
	public static String QB_OPACITY_WRAPPER_CLASS = "qb-wrapper-opacity";
	public static String QB_OPACITY_VALUE_ATTR = "opacity-value";
}