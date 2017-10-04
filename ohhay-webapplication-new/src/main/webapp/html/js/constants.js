function isTable() {
                var isiPad = /ipad|iphone|ipod/i.test(navigator.userAgent
                        .toLowerCase())
                        || /android/i.test(navigator.userAgent.toLowerCase())
                        || /blackberry/i
                        .test(navigator.userAgent.toLowerCase())
                        || /windows phone/i.test(navigator.userAgent
                                .toLowerCase());
                return isiPad;
};

function isIphone() {
    var isiPad = /iphone|ipod/i.test(navigator.userAgent
            .toLowerCase())
            || /android/i.test(navigator.userAgent.toLowerCase())
            || /blackberry/i
            .test(navigator.userAgent.toLowerCase())
            || /windows phone/i.test(navigator.userAgent
                    .toLowerCase());
    return isiPad;
};
/*
 * ALL RULE CLASS
 */
var OHHAY_EDIT_CLASS_NOT_PREVENT_A_CLICK = "a4c1924054bd11c635e419ec1573a6f4";
var OHHAY_EDIT_CLASS = "8e4c66f4049862712ac582aa60c22204";
var OHHAY_EDIT_TEXT_NULL_CLASS = "a2c559d12e9a27b6f1b6b7e3021a233e";
var OHHAY_EDIT_TEXT_IFRAME_CLASS = "a38b6f9c329acaae6764bde0d03ff8c7";
var OHHAY_EDIT_TEXT_RSS_CLASS = "947d71bcb88fdf1b40de294f48d2480e";
var OHHAY_EDIT_TEXT_IFRAME_GALLERY_CLASS = "gn3h6f9c329acaae6764bde0d03ffh37";
var OHHAY_EDIT_TEXT_CLASS = "72f30f8c4761a5232c8a2dc9dab439b7";
var OHHAY_EDIT_IMAGE_CLASS = "e3aa7d94d77a83523e7918fbf4fe7784";
var OHHAY_EDIT_LINK_CLASS = "9243114030f414bac2735c9d7af063aa";
var OHHAY_EDIT_BG_COLOR = "1b4eb004cde87c4a385331accb140d17";
var OHHAY_EDIT_PERCENT_CLASS = "17fa1489c71f1f17f365166c54770a79";
var OHHAY_EDIT_GALLERY_ITEM_CLASS = "c72a4ce6b268210711712328f00d6550";
var OHHAY_EDIT_GALLERY_ITEM_LINK_IMAGE_CLASS = "614c02e5abb83adac755f32f720866e9";
var OHHAY_EDIT_GALLERY_ITEM_FIELD_CLASS = "52256d023661c2b1a782405db92f5040";
var OHHAY_EDIT_GROUP_LINK_TEXT = "e67ba24389ddb46993916f49a907e3fc";
var OHHAY_EDIT_GROUP_LINK_IMAGE = "e31861cc9717be488755a240aa4e49ae";
var OHHAY_EDIT_GROUP_LINK_TEXT_IMAGE = "e6956120631eba30b1d649bb1401aa2b";
var OHHAY_NO_EDIT_CLASS = "f1a1cdab6deb1967d0981daece50e0a4";
var OHHAY_EDIT_TEXT_IFRAME_CLASS = "a38b6f9c329acaae6764bde0d03ff8c7";
var OHHAY_EDIT_TEXT_IFRAME_GALLERY_CLASS = "gn3h6f9c329acaae6764bde0d03ffh37";
var OHHAY_EDIT_BOX_CLASS = "b07d74b5207c62dee3282169abc3e619";
var OHHAY_EDIT_CLASS = "8e4c66f4049862712ac582aa60c22204";
var OHHAY_EDIT_TEXT_CLASS = "72f30f8c4761a5232c8a2dc9dab439b7";
var OHHAY_EDIT_IMAGE_CLASS = "e3aa7d94d77a83523e7918fbf4fe7784";
var OHHAY_EDIT_LINK_CLASS = "9243114030f414bac2735c9d7af063aa";
var OHHAY_EDIT_GALLERY_ITEM_LINK_IMAGE_CLASS = "614c02e5abb83adac755f32f720866e9";
var OHHAY_EDIT_GALLERY_ITEM_FIELD_CLASS = "52256d023661c2b1a782405db92f5040";
var OHHAY_EDIT_GROUP_LINK_TEXT = "e67ba24389ddb46993916f49a907e3fc";
var OHHAY_EDIT_GROUP_LINK_IMAGE = "e31861cc9717be488755a240aa4e49ae";
var OHHAY_EDIT_GROUP_LINK_TEXT_IMAGE = "e6956120631eba30b1d649bb1401aa2b";
var OHHAY_NO_EDIT_CLASS = "f1a1cdab6deb1967d0981daece50e0a4";
var OHHAY_IFRAME_WIDTH = "c9f573ad2754765e1a11e2e8b53af09f";
var OHHAY_IFRAME_HEIGHT =  "c459e6d376ee5487e7c944d39fb88239";
var OHHAY_IFRAME_SRC_ATTR = "f14d1af1e1684e0baa5c2655aa79e38b";
var OHHAY_QB_LINK_REGIST_CLASS = "22AB10746793741A23FB3287BF02F906";
var OHHAY_QB_ACCOUNT_SHORT_FILED_CLASS = "4230E18AB7817E873BC74A08F5DF4CDC";
var OHHAY_QB_ERROR_CLASS = "1D6F130E42DAC64B0C78D7E682F03E12";
var OHHAY_QB_NO_LOGIN_CLASS = "0E6F448CDD9626FB9A70A3C0987BBB26";
var  OHHAY_SUPER_ID = "d3430ec37159a1bf274b42e0ba9d75c2";
var OHHAY_BOX_ID = "51dadada648c507eb9c6431fe7d36af4";
var OHHAY_EDIT_BOX_CLASS = "b07d74b5207c62dee3282169abc3e619";
var  OHHAY_EDIT_BOX_TYPE= "4aa61af40a4fd53bbd095395bf9a06b1";
var OHHAY_EDIT_BOX_REAL_INDEX = "ce4cbe37980210f7fc453587fdb5a7c6";
var OHHAY_EDIT_BOX_ORIGINAL = "5a8d16aa1ef298e178eb590e444c44cb";
var OHHAY_EDIT_BOX_VISIBLE = "d844e9be67f4ae4ebb619fd3e9aefd30";
var OHHAY_WEB_PROFILE_CLASS = "b4bf06605a7ac8dc7a8d55a65df8f91e";
var OHHAY_WEB_PROFILE_CLASS_ADDRESS = "169cc335fd0561182db81eebccfefbfc";
var OHHAY_WEB_PROFILE_CLASS_PHONE = "4f051f10d0dd1031e01e4d907104cda0";
var OHHAY_WEB_PROFILE_CLASS_EMAIL = "d5e7242a7e7f794e601ce7943a028e74";
/*
 * chỉnh sửa background
 */
var isiPad = isTable();
var OHHAY_EDIT_BACKGROUND = ".qc92008dd51836efef00607b6e7fb45c";
/*
 * Events
 */
var EVENT_MOUSE_DOWN = "mousedown touchstart";
var EVENT_MOUSE_CLICK = "click touched";
var EVENT_MOUSE_MOVE = "mousemove touchmove";
var EVENT_MOUSE_UP = "mouseup mouseleave touchend touchleave touchcancel";
var EVENT_WHEEL = "wheel mousewheel DOMMouseScroll";
var EVENT_RESIZE = "resize";
var EVENT_DBLCLICK = "dblclick";
var EVENT_DRAG_START = "dragstart";
var EVENT_DRAG_MOVE = "dragmove";
var EVENT_DRAG_END = "dragend";
/*
 * cac bien khac viet cho nay
 */
var QB_IMG_WIDTH = "8bcb93e7f5aff1fb72f30f434a1c2223";
var QB_IMG_HEIGHT = "ca12e439b4d487d218bad3fbd1752384";
var AJAX_SUCESS = "SUCCESS";
var AJAX_ERROR = "ERROR";
var DATA_QUEENB_TEXT_TYPE = "dcd4abb34169fdf236b158afd1ec6d5c";
var DATA_QUEENB_TEXT_TYPE_ALL = "all";
// data-queenb-image-type
// var DATA_QUEENB_IMAGE_TYPE = "data-queenb-image-type";
var DATA_QUEENB_IMAGE_TYPE = "75985ecd2d44dcbd3727ffb4cbf83e79";
var DATA_QUEENB_IMAGE_TYPE_BGFULL = "BgFull";
var DATA_QUEENB_IMAGE_TYPE_BGNOFULL = "BgNoFull";
var DATA_QUEENB_IMAGE_TYPE_BGREPEAT = "BgRepeat";
var DATA_QUEENB_IMAGE_TYPE_AVARTA = "avarta";
var DATA_QUEENB_IMAGE_TYPE_LOGO = "logo";
var DATA_QUEENB_IMAGE_TYPE_NORMAL = "normal";
var DATA_QUEENB_IMAGE_TYPE_BG_NORMAL = "BgNormal";
var DATA_QUEENB_IMAGE_HISTORY = "<img alt='Picture' class='cropper-edit-img imageBox' onerror='this.onerror=null;this.src='/Ohhay/html/images/60.png';' style='max-width: 200px; max-height: 200px;' src='http://oohhay.s3.amazonaws.com/357/ecef38db554f60cb6b8a6f8acc66699b'>";
var DATA_QUEENB_IMAGE_BACKGROUND_OBJECT = "<img alt='Picture' class='cropper-edit-img imageBox' onerror='this.onerror=null;this.src='/Ohhay/html/images/60.png';' style='max-width: 200px; max-height: 200px;' src='http://oohhay.s3.amazonaws.com/357/ecef38db554f60cb6b8a6f8acc66699b'>";
var DATA_QUEENB_IMAGE_BACKGROUND_CLSS = "span#bg-info";
var QB_IMG_WIDTH_WINDOW = 600;
var QB_IMG_HEIGHT_WINDOW = 600;
var OHHAY_ROOT_PATH = "http://dev.oohhay.com";
var SHOP_ROOT_PATH = "https://shop.oohhay.com";
var TOPIC_ROOT_PATH = "https://topic.bonevo.net";
var MERIAN_ROOT_PATH = "https://task.bonevo.net";
var OREL_ROOT_PATH = "https://crm.oohhay.com";
var OHHAY_RULE_GMAP_MARKER = "marker";
var OHHAY_RULE_GMAP_LA = "la";
var OHHAY_RULE_GMAP_LOG = "log";
var OHHAY_RULE_GMAP_INFOR = "infor";
var DATA_QUEENB_IMAGE_BG_EDIT = "";
var MAP_GLOBAL;
var ARRAY_MARKERS = [];
/*
 * parameter value
 */
var OHHAY_PARAM_DRAFT = "draft";
var OHHAY_PARAM_CHILD = "child";
var OHHAY_PARAM_WEBINARROOM = "webinarroom";
var OHHAY_PARAM_BSELL = "bsell";
var OHHAY_PARAM_WEBINAR = "webinar";
var OHHAY_PARAM_LANDING = "landing";
var OHHAY_PARAM_SHOP = "shop";
var OHHAY_PARAM_VIDEO_MARKETING = "videomarketing";
var OHHAY_PARAM_EDITMODE = "editmode";
var OHHAY_PARAM_ELEMENT = "element";
if($(window).width() <= 1024){
	var DATA_QB_WH_TIMES = 1;
}else{
	var DATA_QB_WH_TIMES = 1;
};
var DATA_QB_WH_MOBILE = 0;
if(isiPad){
	DATA_QB_WH_MOBILE = 20;
};
var OHHAY_BOX_ID = "51dadada648c507eb9c6431fe7d36af4";
var objBackground2 = {
		w: 0,
		h: 0,
		data:{}
};
var objLogo = {
		w: 0,
		h: 0,
		data:{}
};
var EDIT_IMAGE_TYPE = "";
/*
 * TRACKING RULE
 */
var OHHAY_TRACKING_CLASS = "trackingObClass";
var OHHAY_TRACKING_ATTR_OB = "ob";
var OHHAY_TRACKING_ATTR_STT = "trackstt";
var OHHAY_CHANGEPOSITION_CLASS = "ohhay-changed-positon";
var QB_FORMSENDMAIL_CLASS = "qb-form-send-mail";
//topic
var PAGE_TOPIC_CONTANTS = (function(){
	var location = window.location;
	var href = location.href;
	if(href.indexOf('etopic') != -1){
		return 678;
	}
	return 0;
})();