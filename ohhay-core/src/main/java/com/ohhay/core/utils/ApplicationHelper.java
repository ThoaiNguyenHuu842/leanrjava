package com.ohhay.core.utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.mobile.device.site.SitePreferenceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.BaseHelper;
import com.ohhay.core.constant.OhhayDefaultData;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.OhhayViewer;
import com.ohhay.core.entities.Q100;

import net.sourceforge.vietpad.utilities.VietUtilities;

/**
 * @author ThoaiNH 
 * create 12/07/2014 
 * application helper
 */
public class ApplicationHelper {
	private static Logger log = Logger.getLogger(ApplicationHelper.class);

	/**
	 * find application bean
	 * 
	 * @param reposityName
	 * @return
	 */
	public static Object findBean(String reposityName) {
		return BaseHelper.context.getBean(reposityName);
	}

	/**
	 * random mot so kieu int
	 * 
	 * @return
	 */
	public static int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(500) + rand.nextInt(100);
	}

	/**
	 * check exist object in jSon
	 * 
	 * @param jsonObject
	 * @param object
	 * @return
	 * @throws JSONException
	 */
	public static String checkObjectJson(JSONObject jsonObject, String object) throws JSONException {
		String str = null;
		if (!jsonObject.isNull(object)) {
			str = jsonObject.getString(object);
		}
		return str;
	}

	/**
	 * function get element for array
	 * 
	 * @param superID
	 * @param pos
	 * @return
	 */
	public static String getIdOfSuperId(String superID, int pos) {
		if (superID != null) {
			String[] getsub = superID.split("#");
			if (getsub != null) {
				return getsub[pos];
			} else {
				throw new IllegalArgumentException("String " + superID + " does not contain #");
			}
		} else {
			throw new IllegalArgumentException("String " + superID + " does not contain #");
		}

	}

	/**
	 * convert mutilpartfile to file
	 * 
	 * @param multipart
	 * @return
	 * @throws IOException
	 */
	public static File convertMultipartFiletoFile(MultipartFile multipart) throws IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	/**
	 * convert string to md5
	 * 
	 * @param arg
	 * @return
	 */
	public static String convertToMD5(String arg) {
		String hashed_key = "";

		try {
			byte[] intext = arg.getBytes();
			StringBuffer sb = new StringBuffer();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md5rslt = md5.digest(intext);
			for (int i = 0; i < md5rslt.length; i++) {
				sb.append(String.format("%02x", 0xff & md5rslt[i]));
			}
			hashed_key = sb.toString();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return hashed_key;
	}

	/**
	 * hash string to sha 512
	 * 
	 * @param arg
	 * @return hashed key
	 */
	public static String convertToSHA512(String arg) {
		String hashed_key = "";
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-512");
			sh.update(arg.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			hashed_key = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return hashed_key;
	}

	/**
	 * return redirect in MVC
	 * 
	 * @param url
	 * @return
	 */
	public static String getRedirectString(String url) {
		return "redirect:" + url;
	}

	/**
	 * validate http url is exist
	 * 
	 * @param text
	 * @return
	 */
	public static boolean validateUrl(String text) {
		boolean vaild = false;
		Connection.Response response = null;
		try {
			response = Jsoup.connect(text)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5")
					.timeout(100000).ignoreHttpErrors(true).execute();
			if (response != null) {
				if (response.statusCode() == 200) {
					vaild = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vaild;
	}

	/**
	 * validate file type is image file
	 * 
	 * @param text
	 * @return
	 */
	public static boolean validateImageExtendtion(String text) {
		String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(IMAGE_PATTERN);
		matcher = pattern.matcher(text.replaceAll("\\s+", ""));
		return matcher.matches();
	}

	/**
	 * check percent js text
	 * 
	 * @param text
	 * @return
	 */
	public static boolean validatePercent(String text) {
		String IMAGE_PATTERN = "^[\\d*]+[%]$";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(IMAGE_PATTERN);
		matcher = pattern.matcher(text);
		return matcher.matches();
	}

	/**
	 * validate text is phone number or hv101 type
	 * 
	 * @param text
	 * @return
	 */
	public static boolean validatePhoneNumber(String text) {
		String IMAGE_PATTERN = "\\d{8}|\\d{9}|\\d{10}|\\d{11}|\\d{12}|\\d{13}|\\d{14}|\\d{15}|\\d{16}|^[a-f0-9]{32}$";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(IMAGE_PATTERN);
		matcher = pattern.matcher(text);
		return matcher.matches();
	}

	/**
	 * @param text
	 * @return
	 */
	public static boolean validatePhoneNumberReal(String text) {
		String PATTERN = "\\d{8}|\\d{9}|\\d{10}|\\d{11}|\\d{12}|\\d{13}|\\d{14}|\\d{15}|\\d{16}$";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(PATTERN);
		matcher = pattern.matcher(text);
		return matcher.matches();
	}

	/**
	 * get http servlet request
	 * 
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest();
	}

	/**
	 * get http servlet response
	 * 
	 * @return
	 */
	public static HttpServletResponse getHttpServletResponse() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getResponse();
	}

	/**
	 * get http servlet session
	 */
	public static HttpSession getHttpSession() {
		return getHttpServletRequest().getSession(true); // true == allow create
	}

	/**
	 * get session from http servlet request
	 */
	public static Object getSession(String sessionName) {
		return getHttpSession().getAttribute(sessionName);
	}

	/**
	 * set a session
	 */
	public static void setSession(String sessionName, Object object) {
		getHttpSession().setAttribute(sessionName, object);
	}

	/**
	 * remove a session
	 */
	public static void removeSession(String key) {
		getHttpSession().removeAttribute(key);
	}

	/**
	 * remove all session
	 */
	public static void removeAllSession() {
		getHttpSession().removeAttribute(SessionConstant.USER_LOGIN);
		getHttpSession().removeAttribute(SessionConstant.CURRENT_M900);
		getHttpSession().removeAttribute(SessionConstant.DOMAINT_POINT_YES);
		getHttpSession().removeAttribute(SessionConstant.TIME_END_SEARCH);
		getHttpSession().removeAttribute(SessionConstant.USER_WEB_LANGUAGE_HISTORY);
		getHttpSession().removeAttribute(SessionConstant.WEB_EDIT_HISTORY);
	}

	/**
	 * generate file name MD5
	 */
	public static String generateFileName() {
		Random rand = new Random();
		// radomize file name
		int n = 100000;
		int i = rand.nextInt(n + 1);
		StringBuilder builder = new StringBuilder("");
		// time now
		java.util.Date date = new java.util.Date();
		builder.append(date.getTime());
		builder.append(i);
		return ApplicationHelper.convertToMD5(builder.toString());
	}

	/**
	 * generate file name MD5 with id
	 */
	public static String generateFileName(String id) {
		StringBuilder builder = new StringBuilder("");
		// time now
		java.util.Date date = new java.util.Date();
		builder.append(date.getTime());
		builder.append(id);
		return ApplicationHelper.convertToMD5(builder.toString());
	}

	/**
	 * remove accent
	 */
	public static String removeAccent(String s) {
		return VietUtilities.stripDiacritics(s);
	}

	/**
	 * validate regex
	 * 
	 * @return true if string match pattern
	 */
	public static boolean validateRegex(String value, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * get aws key for img file
	 */
	public static String getAWSKeyFromUrlImg(String imgUrl) {
		try {
			int index = imgUrl.lastIndexOf("/");
			return imgUrl.substring(index + 1, imgUrl.length());
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * get aws key from base link
	 */
	public static String getAWSKeyFromBaseLink(String imgUrl) {
		try {
			return imgUrl.replace(TemplateRule.OHHAY_SERVER_LINK, "");
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * @param s
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static String replaceString(String s, String oldString, String newString) {
		StringBuilder builder2 = new StringBuilder(s);
		int index = 0;
		int lenght = oldString.length();
		log.info(builder2.indexOf(oldString));
		while ((index = builder2.indexOf(oldString)) >= 0) {
			builder2.replace(index, index + lenght, newString);
		}
		return builder2.toString();
	}

	/**
	 * random color
	 */
	public static String randomColor() {
		int R = (int) (Math.random() * 256);
		int G = (int) (Math.random() * 256);
		int B = (int) (Math.random() * 256);
		Color color = new Color(R, G, B); // random color, but can be bright or
											// dull

		// to get rainbow, pastel colors
		Random random = new Random();
		final float hue = random.nextFloat();
		final float saturation = 0.9f;// 1.0 for brilliant, 0.0 for dull
		final float luminance = 1.0f; // 1.0 for brighter, 0.0 for black
		color = Color.getHSBColor(hue, saturation, luminance);
		String hexColor = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
		log.info("--random color:" + hexColor);
		return hexColor;
	}

	/**
	 * encode url link encodeURI in js
	 */
	public static String encodeURI(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!")
					.replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return s;
		}
	}

	/**
	 * get ip address of http request
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (ipAddress.indexOf("0:0:0:0:0:0:0") >= 0)
			ipAddress = "222.255.167.122";
		return ipAddress;
	}

	/**
	 * process dummies phone
	 */
	public static String processDummiesPhones(String phone, String countryCode) {
		// +84(0)903387368
		if (phone.substring(1, countryCode.length() + 1).equals(countryCode) && phone.charAt(0) == '+')
			phone = phone.substring(countryCode.length() + 4, phone.length());
		// 0084(0)903387368
		else if (phone.substring(2, countryCode.length() + 2).equals(countryCode) && phone.substring(0, 2).equals("00"))
			phone = phone.substring(5 + countryCode.length(), phone.length());
		// 84903387368
		else if (phone.substring(0, countryCode.length()).equals(countryCode))
			phone = "0" + phone.substring(countryCode.length(), phone.length());
		if (phone.charAt(0) != '0')
			phone = "0" + phone;
		return phone;
	}

	/**
	 * decode topic string
	 */
	public static String decodeTopicString(String s) {
		s = StringEscapeUtils.unescapeHtml(s);
		s = s.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
		s = s.replaceAll("\\+", "%2B");
		try {
			return URLDecoder.decode(org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3(s), "UTF-8")
					.replace("21dauphay12", "'").replace("21champhay12", ";").replaceAll("&lt;", "<")
					.replaceAll("&gt;", ">");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * get device type of request
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	public static int getDeviceFromRequest(HttpServletRequest httpServletRequest) {
		SitePreference currentSitePreference = SitePreferenceUtils.getCurrentSitePreference(httpServletRequest);
		if (currentSitePreference.isMobile())
			return ApplicationConstant.REQUEST_IS_MOBILE;
		else if (currentSitePreference.isTablet())
			return ApplicationConstant.REQUEST_IS_TABLET;
		else
			return ApplicationConstant.REQUEST_IS_NORMAL;
	}

	/**
	 * @param httpServletRequest
	 * @return
	 */
	public static String getDeviceNameFromRequest(HttpServletRequest httpServletRequest) {
		String device = "PC";
		int deviceCode = ApplicationHelper.getDeviceFromRequest(httpServletRequest);
		if (deviceCode == ApplicationConstant.REQUEST_IS_MOBILE)
			device = "MOBILE";
		else if (deviceCode == ApplicationConstant.REQUEST_IS_TABLET)
			device = "TABLET";
		return device;
	}

	/**
	 * @param subject:
	 *            email orginal subject
	 * @return emal after process
	 */
	public static String processSubjectEmail(String subject) {
		if (subject.trim().length() > 79)
			return subject.trim().substring(0, 70) + "...";
		else
			return subject.trim();
	}

	/**
	 * create 31/07/2015 - lay thong tin tu http request
	 * 
	 * @param request
	 * @return
	 */
	public static OhhayViewer getInfoFromRequest(HttpServletRequest request) {
		OhhayViewer ohhayViewer = new OhhayViewer();
		String device = "PC";
		int deviceCode = ApplicationHelper.getDeviceFromRequest(request);
		if (deviceCode == ApplicationConstant.REQUEST_IS_MOBILE)
			device = "MOBILE";
		else if (deviceCode == ApplicationConstant.REQUEST_IS_TABLET)
			device = "TABLET";
		// os, browser
		String browsers = request.getHeader("User-Agent");
		log.info("---browsers:" + browsers);
		// System.out browser of user
		String browser = "Other";
		if (browsers.toLowerCase().contains("opera") || browsers.toLowerCase().contains("opr")) {
			browser = "Opera";
		} else if (browsers.toLowerCase().contains("chrome")) {
			browser = "Chrome";
		} else if (browsers.toLowerCase().contains("firefox")) {
			browser = "Firefox";
		} else if (browsers.toLowerCase().contains("safari") && browsers.toLowerCase().contains("version")) {
			browser = "Safari";
		} else if (browsers.toLowerCase().contains("rv")) {
			browser = "IE";
		}
		// System.out opera system of user
		String os = "Other";
		if (browsers.toLowerCase().indexOf("iphone") >= 0) {
			os = "IOS";
		} else if (browsers.toLowerCase().indexOf("mac") >= 0) {
			os = "MacOS";
		} else if (browsers.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (browsers.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		}
		ohhayViewer.setDevice(device);
		ohhayViewer.setIp(ApplicationHelper.getIpAddress(request));
		ohhayViewer.setOs(os);
		ohhayViewer.setBrowser(browser);
		return ohhayViewer;
	}

	/**
	 * @author ThoaiNH create: 06/08/2015 get domain of http request
	 * @param request
	 * @return
	 */
	public static String getDomainFromRequest(HttpServletRequest request) {
		String requestUrl = request.getServerName();
		// for test
		if (request.getServerName().equals("localhost") || request.getServerName().indexOf("192.168") >= 0)
			requestUrl = ApplicationConstant.HOST;
		if (requestUrl.indexOf("www.") == 0)
			requestUrl = requestUrl.substring(4, requestUrl.length());
		return requestUrl;
	}

	public static String proccessFriendlyKey(String name) {
		return ApplicationHelper.removeAccent(name).trim().toLowerCase().replaceAll("-", " ").replaceAll("\\s+", "-");
	}

	public static String proccessFriendlyKeyTopicName(String name) {
		return ApplicationHelper.removeAccent(name).trim().toLowerCase().replaceAll("-", " ").replaceAll("-", "%20")
				.replaceAll("\\s+", "-");
	}

	public static String proccessFriendlyKeyTopicTitle(String name) {
		String s = ApplicationHelper.removeAccent(name).trim().toLowerCase().replace("?", "")
				.replaceAll("-", "\\&nbsp;").replace(".", "").replaceAll("-", " ").replaceAll("-", "%20")
				.replaceAll("\\s+", "-");
		log.info("+++++++++++++++++++++++++++++++++++++++++url++++++++++++++++++++++++++++++++++++");
		log.info("ss:" + s);
		return s;
	}

	/**
	 * check user is admin
	 * 
	 * @param q100
	 * @return
	 */
	public static boolean isAdmin(Q100 q100) {
		if (q100 != null && q100.getM900mg() != null && q100.getM900mg().getMv922() != null
				&& q100.getM900mg().getMv922().equals("ADMIN"))
			return true;
		return false;
	}

	/**
	 * check user is moderator
	 * 
	 * @param q100
	 * @return
	 */
	public static boolean isModerator(Q100 q100) {
		if (q100 != null && q100.getM900mg() != null && q100.getM900mg().getMv922() != null
				&& q100.getM900mg().getMv922().equals("MOD"))
			return true;
		return false;
	}

	/**
	 * @param document
	 * @param attr
	 * @return
	 */
	public static String getMetaTag(Document document, String attr) {
		Elements elements = document.select("meta[name=" + attr + "]");
		for (Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		elements = document.select("meta[property=" + attr + "]");
		for (Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		return null;
	}

	/**
	 * @param password
	 *            length
	 * @return randomized password
	 */
	public static String fastPasswordRandomizer(int length) {
		return passwordRandomizer(length, false);
	}

	/**
	 * @param password
	 *            length
	 * @param include
	 *            special characters
	 * @return randomized password
	 */
	public static String passwordRandomizer(int length, Boolean withSpecialChar) {
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String number = "0123456789";
		String specialChar = "!@#$%^&*";
		String characters = number + upperCase + lowerCase;
		if (withSpecialChar)
			characters += specialChar;
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(characters.charAt(random.nextInt(characters.length())));
		return sb.toString();
	}

	public static String convertDateALTiengViet(String str) {
		return str.replaceAll("GIAP", "GiÃ¡p").replaceAll("AT", "áº¤t").replaceAll("BINH", "BÃ­nh")
				.replaceAll("DINH", "Ä�inh").replaceAll("MAU", "Máº­u ").replaceAll("KY", "Ká»·").replaceAll("CANH", "Canh")
				.replaceAll("TAN", "TÃ¢n").replaceAll("NHAM", "NhÃ¢m").replaceAll("QUI", "QuÃ­").replaceAll("TI", "TÃ­ ")
				.replaceAll("SUU", "Sá»­u").replaceAll("DAN", "Dáº§n").replaceAll("MAO", "MÃ£o").replaceAll("THIN", "ThÃ¬n")
				.replaceAll("TY", "Tá»µ").replaceAll("NGO", "Ngá»�").replaceAll("MUI", "MÃ¹i").replaceAll("THAN", "ThÃ¢n")
				.replaceAll("DAU", "Dáº­u").replaceAll("TUAT", "Tuáº¥t").replaceAll("HOI", "Há»£i");
	}

	public static String dateNumberToChar(String str) {
		return str.replaceAll("01", "MÃ¹ng Má»™t").replaceAll("02", "MÃ¹ng Hai").replaceAll("03", "MÃ¹ng Ba")
				.replaceAll("04", "MÃ¹ng Bá»‘n").replaceAll("05", "MÃ¹ng NÄƒm").replaceAll("06", "MÃ¹ng SÃ¡u")
				.replaceAll("07", "MÃ¹ng Báº£y").replaceAll("08", "MÃ¹ng TÃ¡m").replaceAll("09", "MÃ¹ng ChÃ­n")
				.replaceAll("10", "MÃ¹ng MÆ°á»�i").replaceAll("15", "Ráº±m (15)");
	}

	public static String monthNumberToChar(String str) {
		return str.replaceAll("01", "GiÃªng").replaceAll("02", "Hai").replaceAll("03", "Ba").replaceAll("04", "TÆ°")
				.replaceAll("05", "NÄƒm").replaceAll("06", "SÃ¡u").replaceAll("07", "Báº£y").replaceAll("08", "TÃ¡m")
				.replaceAll("09", "ChÃ­n").replaceAll("10", "MÆ°á»�i").replaceAll("11", "MÆ°á»�i Má»™t")
				.replaceAll("12", "Cháº¡p");
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getFullURL(HttpServletRequest request) {
		StringBuffer requestURL = request.getRequestURL();
		String queryString = request.getQueryString();

		if (queryString == null) {
			return requestURL.toString();
		} else {
			return requestURL.append('?').append(queryString).toString();
		}
	}

	/**
	 * @param request
	 * @return
	 */
	public static String redirectLoginForTopic(HttpServletRequest request) {
		return ApplicationHelper
				.getRedirectString("https://bonevo.net/login?re=" + ApplicationHelper.getFullURL(request));
	}

	/**
	 * @param ohhayViewer
	 * @return
	 */
	public static boolean checkUnsupportedBrowser(OhhayViewer ohhayViewer) {
		if (ohhayViewer.getBrowser().equals("IE") || ohhayViewer.getBrowser().equals("Other"))
			return true;
		return false;
	}

	/**
	 * @param url
	 * @return
	 */
	public static long getFileSize(String url) {
		HttpURLConnection conn = null;
		try {
			URL urlNet = new URL(url);
			conn = (HttpURLConnection) urlNet.openConnection();
			conn.setRequestMethod("HEAD");
			conn.getInputStream();
			return conn.getContentLength();
		} catch (IOException e) {
			return -1;
		} finally {
			conn.disconnect();
		}
	}
	/**
	 * create 22/09/2016
	 * @param url
	 * @return
	 */
	public static String getInfoFromURL(String url) {
		try {
			String title = "";
			String image = "";
			Document document = Jsoup.connect(url).get();
			Elements titles = document.select("title");
			if(titles != null && titles.size() > 0)
				title = titles.get(0).html();
			Elements featureImgs = document.select("meta[property=og:image]");
			if(featureImgs != null && featureImgs.size() > 0)
				image = featureImgs.get(0).attr("content");
			else {
				featureImgs = document.select("meta[property=og:image:url]");
				if(featureImgs != null && featureImgs.size() > 0)
					image = featureImgs.get(0).attr("content");
				else {
					featureImgs = document.select("link[rel=shortcut icon]");
					if(featureImgs != null && featureImgs.size() > 0)
						image = featureImgs.get(0).attr("href");
				}
			}
			URI uri2 = new URI("sss");
			log.info("---getHost"+uri2.getHost());
			log.info("--image:"+image);
			//process for relative image
			if(image.indexOf("http") < 0 && image.indexOf("//") != 0){
				URI uri = new URI(url);
			    String domain = uri.getHost();
			    String domainName = domain.startsWith("www.") ? domain.substring(4) : domain;
			    //add protocol to link
			    log.info("--domainName:"+domainName);
			    log.info("--image:"+image);
			    log.info("--domainName.charAt(domainName.length() -1 ):"+domainName.charAt(domainName.length() -1 ));
			    log.info("--image.charAt(0):"+image.charAt(0));
			    if(url.indexOf("https") == 0)
			    {
			    	if(domainName.charAt(domainName.length() -1 ) != '/' && image.charAt(0) != '/')
			    		image = "https://www."+domainName + "/"  +image;
			    	else
			    		image = "https://www."+domainName + image;
			    }
			    else
			    {
			    	if(domainName.charAt(domainName.length() -1 ) != '/' && image.charAt(0) != '/')
			    		image = "http://www."+domainName + "/"  +image;
			    	else
			    		image = "http://www."+domainName + image;
			    }
			}
			return org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(title) + ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN + image;
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * stemming word: remove all space, accent
	 * @param srcString
	 * @return
	 */
	public static String getStemString(String srcString){
		return ApplicationHelper.removeAccent(srcString).replaceAll(" ", "").toLowerCase();
	}
	
	/**
	 * @param otag
	 * @return
	 */
	public static String getStemOtag(String otag){
		return ApplicationHelper.removeAccent(otag.toLowerCase()).replaceAll(" |-|,|;", "");
	}
	/**
	 * @param region
	 * @param fo100
	 * @return
	 */
	public static String getImgLink(String region, int fo100)
	{
		if(region != null)
			return "https://"+region.toLowerCase()+"-oohhay.s3.amazonaws.com/"+fo100+"/";
		else
			return "https://oohhay.s3.amazonaws.com/"+fo100+"/";
	}
	/**
	 * @param fo100
	 * @param mv908
	 * @param mv905
	 * @param region
	 * @return
	 */
	public static String getUrlAvarta(int fo100, String mv908, String mv905, String region){
		String urlAvarta = null;
		if (mv908 != null && mv908.length() > 0)
			urlAvarta = ApplicationHelper.getImgLink(region, fo100) + mv908;
		else
		{
			if(mv905.toLowerCase().equals("m"))
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_M;
			else if(mv905.toLowerCase().equals("f"))
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_F;
			else
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_N;
		}
		return urlAvarta;
	}
	/**
	 * @return
	 */
	public static String getRandomOTP(){
		return String.valueOf((int) (Math.random() * (99999 - 10000)) + 10000);
	}
	
	/**
	 * @param pvSearch
	 * @return
	 */
	public static String createPVSearchStem(String pvSearch){
		if(pvSearch != null){
			String tokens[] = pvSearch.split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
			StringBuilder builderStem = new StringBuilder();
			for(int i = 0; i < tokens.length; i++){
				builderStem.append(ApplicationHelper.getStemOtag(tokens[i]));
				if(i < tokens.length - 1)
					builderStem.append(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
			}
			return builderStem.toString();
		}
		return null;
	}
	/**
	 * validate a JSONObject String
	 * @param s
	 * @return
	 */
	public static boolean isJSONObject(String s){
		try {
			new JSONObject(s);
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	/**
	 * validate a JSONArray String
	 * @param s
	 * @return
	 */
	public static boolean isJSONArray(String s){
		try {
			new JSONArray(s);
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	/**
	 * validate a JSON String
	 * @param s
	 * @return
	 */
	public static boolean isJSON(String s){
		try {
	        new JSONObject(s);
	    } catch (JSONException ex) {
	        try {
	            new JSONArray(s);
	        } catch (JSONException ex1) {
	            return false;
	        }
	    }
	    return true;
	}
	
	/**
	 * @param uiid
	 * @return
	 */
	public static String preProcessUIID(String uiid){
		StringBuilder stringBuilder = new StringBuilder(uiid.trim());
		if(stringBuilder.length() < 16){
			/*
			 * update 28/06/2017 truong hop uiid < 16 ky tu
			 */
			int k = 16 - stringBuilder.toString().length();
			for(int i = 0; i< k; i++)
				stringBuilder.append("0");
		}
		return stringBuilder.toString();
	}
	
	/**
	 * @param nv102
	 * @param fo100
	 * @return
	 */
	public static String createPiepmeKey02(String nv102, int fo100){
		String tempKey = nv102.substring(0, 16);
		int fo100Length = String.valueOf(fo100).length();
		StringBuilder key2 = new StringBuilder();
		for (int i = 0; i < 16 - fo100Length; i++)
			key2.append("0");
		key2.append(fo100);
		AESUtilsPiepme aesUtilsPiepme = new AESUtilsPiepme(key2.toString(), key2.toString());
		return aesUtilsPiepme.encrypt(tempKey);
	}
}
