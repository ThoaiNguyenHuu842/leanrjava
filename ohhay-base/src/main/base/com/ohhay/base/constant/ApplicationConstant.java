package com.ohhay.base.constant;

/**
 * @author ThoaiNH create 09/08/2014 constant use for all application
 */
public class ApplicationConstant {
	static {
		PropertiesLoader prop2 = new PropertiesLoader(PropertiesLoader.CONFIG);
		ApplicationConstant.BONEVO_VERSION = prop2.getProperty("BONEVO_VERSION");
		// lucene index path
		ApplicationConstant.INDEXPATH_M900 = prop2.getProperty("INDEXPATH_M900");
		ApplicationConstant.INDEXPATH_M150 = prop2.getProperty("INDEXPATH_M150");
		ApplicationConstant.INDEXPATH_V250 = prop2.getProperty("INDEXPATH_V250");
		// beyu account
		ApplicationConstant.BEYU_USERNAME = prop2.getProperty("BEYU_USERNAME");
		ApplicationConstant.BEYU_PASSWORD = prop2.getProperty("BEYU_PASSWORD");
		// key sync template
		ApplicationConstant.OHHAY_SYNC_TEMPLATE_KEY = prop2.getProperty("OHHAY_SYNC_TEMPLATE_KEY");
		// AES info
		ApplicationConstant.AES_KEY_1 = prop2.getProperty("AES_KEY_1");
		ApplicationConstant.AES_KEY_2 = prop2.getProperty("AES_KEY_2");
		// AWS info
		ApplicationConstant.AWS_KEY = prop2.getProperty("AWS_KEY");
		ApplicationConstant.AWS_KEY_SECRECT = prop2.getProperty("AWS_KEY_SECRECT");
		ApplicationConstant.AWS_MAIL_SENDDER = prop2.getProperty("AWS_MAIL_SENDDER");
		// nextmo
		ApplicationConstant.NEXMO_API_KEY = prop2.getProperty("NEXMO_API_KEY");
		ApplicationConstant.NEXMO_SECRET_KEY = prop2.getProperty("NEXMO_SECRET_KEY");
		// smtp mail
		ApplicationConstant.SMTP_OHAY_USERNAME = prop2.getProperty("SMTP_OHAY_USERNAME");
		ApplicationConstant.SMTP_OHAY_PASSWORD = prop2.getProperty("SMTP_OHAY_PASSWORD");
		// send gird mail
		ApplicationConstant.SENDGIRD_KEY = prop2.getProperty("SENDGIRD_KEY");
		ApplicationConstant.SENDGIRD_SENDER_NAME = prop2.getProperty("SENDGIRD_SENDER_NAME");

	}
	//public static final String HOST = "localhost:8080";
	public static final String HOST = "bonevo.net";
	public static String BONEVO_VERSION;
	public static final int FO100_SUPER_ADMIN = 598;
	public static final int FO100_SUPER_ADMIN_PIEPME = 1414;
	public static final String MV907_SUPER_ADMIN = "09033873686";
	public static final int FO100_ADMIN_TEMPLATE = 809;// dev 805, prod 809
	public static final String BACKGROUND_TOPIC_ROOT_PATH = "https://images.oohhay.com/bgtopic/";
	public static final String BACKGROUND_TOPIC_PATH = "/html/images/bg/";
	public static final String BACKGROUND_OHAY_ROOT_PATH = "https://images.oohhay.com/bgohay/";
	public static final String MVC_LANGUAGE_PARAM = "language";
	public static final String COOKIE_LOGIN_INFO_PATTERN = "##qb##";
	public static String OHHAY_SYNC_TEMPLATE_KEY;
	public static final String KEYWORD_PATTERN = ";";
	public static final String OTAGS_PATTERN = " ##qb## ";
	public static final int ROLE_VIEWER = 1;
	public static final int ROLE_OWNER = 2;
	public static final String DIEMTIN_DOMAIN = "diemtin.it";
	public static final String CONTEXT_PATH = "https://bonevo.net/";
	public static final String TOPIC_CONTEXT_PATH = "https://topic.bonevo.net/";
	public static final String SHOP_CONTEXT_PATH = "https://shop.oohhay.com/";
	public static final String OHHAY_PROFILE_DEFAULT_AVARTA = "html/images/44.png";
	public static final String OHHAY_AVARTA_CONSTANT = "avartaoohhay";
	/*
	 * INDEX PATH
	 */
	public static String INDEXPATH_M900;
	public static String INDEXPATH_M150;
	public static String INDEXPATH_V250;
	/*
	 * SPECIAL TEMPLATE FC800
	 */
	public static final int OHHAY_TEMPLATE_VIDEO_MAKERTING_1 = 22;
	public static final int OHHAY_TEMPLATE_VIDEO_MAKERTING_2 = 20;
	public static final String OHHAY_VIDEOMARKETING_CV805 = "VIDEO";
	public static final String OHHAY_SHOP_CV805 = "SHOP";
	public static final String OHHAY_NORMAL_LANDING_CV805 = "C400";
	/*
	 * AWS
	 */
	public static String AWS_KEY;
	public static String AWS_KEY_SECRECT;
	public static String AWS_MAIL_SENDDER;
	/*
	 * AES
	 */
	public static String AES_KEY_1;
	public static String AES_KEY_2;
	/*
	 * BEYU SMS
	 */
	public static String BEYU_USERNAME;
	public static String BEYU_PASSWORD;
	/*
	 * OHHAY CONFIG
	 */
	public static String PVLOGIN_ANONYMOUS = "phongvt";
	/*
	 * BASE DAO
	 */
	public static final String IN_PARAM = "inparam";
	public static final String OUT_PARAM_VALUE = "outvl";
	public static final String OUT_PARAM_REFCUR = "outref";
	/*
	 * DB TYPE ID
	 */
	public static final int DB_TYPE_OHHAY = 2;
	public static final int DB_TYPE_TOPIC = 3;
	public static final int DB_TYPE_SHOP = 4;
	public static final int DB_TYPE_PIEPME = 5;
	/*
	 * NEXTMO SMS
	 */
	public static String NEXMO_API_KEY;
	public static String NEXMO_SECRET_KEY;
	public static String NEXMO_FROM_NAME = "OHHAY";
	/*
	 * PACKAGE KEY
	 */
	public static String PACKET_FREE = "9f6c6fb421d3e04dbecca3c46e70034c";
	public static String PACKET_OPTIMAL = "9f2baa4ad4feba2c4c0c723a8d547d53";
	public static String PACKET_EXPERT = "3bb01c604e0f98f566d50a54f0825761";
	public static String PACKET_PRO = "5ae306f19da737ecf28feea61c6fa1e4";
	public static String PACKET_DESIGNER = "a13bcf1ecf060c929a4a87c945d209bc";
	/*
	 * RIGHT RETURN
	 */
	public static String RE_MUST_UPGRADE = "RE_MUST_UPGRADE";
	public static String RE_MUST_UPGRADE_TO_USE_MORE = "RE_MUST_UPGRADE_TO_USE_MORE";
	public static String RE_VAILD_RIGHT = "RE_VAILD_RIGHT";
	/*
	 * OHAY SMTP EMAIL
	 */
	public static String SMTP_OHAY_USERNAME;
	public static String SMTP_OHAY_PASSWORD;
	/*
	 * DEVICE TYPE CODE
	 */
	public static final int REQUEST_IS_NORMAL = 1;
	public static final int REQUEST_IS_TABLET = 2;
	public static final int REQUEST_IS_MOBILE = 3;
	/*
	 * SEND GIRD EMAIL
	 */
	public static String SENDGIRD_KEY;
	public static String SENDGIRD_SENDER_NAME;
	/*
	 * SYSTEM ADMIN ROLE
	 */
	public static final String SYSTEM_ROLE_ADMIN = "ADMIN";
	public static final String SYSTEM_ROLE_MOD = "MOD";
	/*
	 * PAYMENT WALL
	 */
	public static final String PAYMENTWALL_APP_KEY = "71246d57acaa6f0f0eb05d0fbc12ace3";
	public static final String PAYMENTWALL_SECRET_KEY = "d4dedc6567473af67fe048f509705b46";
	public static final String IP_SERVER = "169.53.141.72";
	/*
	 * FLICKR API
	 */
	public static final String FLICKR_RESOURCE_URL = "https://api.flickr.com/services/rest/";
	public static final String FLICKR_APP_KEY = "fb2c8d6300345ae4899394944d5cabaf";
	public static final String FLICKR_APP_SECRET = "58e9967df60c97f9";
	public static final String FLICKR_REQUEST_TOKEN = "FLICK_REQUEST_TOKEN";
	public static final String FLICKR_ACCESS_TOKEN = "FLICK_ACCESS_TOKEN";
	public static final String FLICKR_USER_ID = "FLICKR_USER_ID";
	public static final String INSTAGRAM_RESOURCE_URL = "https://api.instagram.com/v1/";
	public static final String INSTAGRAM_APP_KEY = "20d7da4391a24c63b4e24a12f14a51fd";
	public static final String INSTAGRAM_APP_SECRET = "c4c677b17ce94c29943b5a1201814fb7";
	public static final String INSTAGRAM_ACCESS_TOKEN = "INSTAGRAM_ACCESS_TOKEN";
	public static final String INSTAGRAM_USER_ID = "INSTAGRAM_USER_ID";
	/*
	 * DIGISTORE PATH
	 */
	public static final String DIGISTORE_IPN_PASSPHRASE = "bonevo";
	/*
	 * DIGISTORE API KEY
	 */
	public static final String DIGISTORE_API_KEY = "22927-qPuXyurc7SFwoh3ZZVG1lFX7HuftQno0TZdNhWHm";

	/*
	 * key for Tracking By ...
	 */
	public static final String TRACKING_BY_VIEWS = "V";
	public static final String TRACKING_BY_BROWSER = "B";
	public static final String TRACKING_BY_DEVICES = "D";
	public static final String TRACKING_BY_SYSTEMS = "S";
	public static final String TRACKING_BY_COUNTRY = "L";
	public static final String TRACKING_BY_OBJECTNAME = "J";
	public static final String TRACKING_BY_URL = "U";
}
