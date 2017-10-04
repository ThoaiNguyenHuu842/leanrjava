package com.ohhay.web.lego.service;

import java.util.Map;

import com.ohhay.core.authentication.AuthenticationRightInfo;
import com.ohhay.web.lego.entities.mongo.base.web.OhhayLegoWebBase;

/**
 * @author ThoaiNH
 * create Oct 13, 2015
 * service for web evo
 */
public interface WebLegoService {
	/**
	 * create blank web evo
	 * @param fo100
	 * @param hv101 
	 * @param isTopicContent true only create web data for topic content, false web data will save to MongoDB
	 * @param webId id of original template
	 * @return
	 */
	<T> OhhayLegoWebBase createWebEvo(int fo100, String hv101, boolean isTopicContent, long webId);
	/**
	 * @param fo100
	 * @param webId id of original template
	 * @param html HTML to save view version
	 * @param jsonData model web data
	 * @param apiCompSelector selector of all API component that can't store in HTML
	 * @param editToolSelector model web data
	 * @param fm150 id topic, if = 0 is save web evo
	 * @param siteName name of site
	 * @return map (rawId, dbId) to set id new component and new box 
	 */
	Map<String, Long> save(int fo100, long webId, String html, String jsonData, String apiCompSelector, String editToolSelector, int extend);
	/**
	 * @param fo100
	 * @param webId
	 * @param html HTML to save view version
	 * @param jsonData model web data
	 * @param apiCompSelector selector of all API component that can't store in HTML
	 * @param editToolSelector model web data
	 * @param fm150 id topic, if = 0 is save web evo
	 * @param siteName name of site
	 * @return map (rawId, dbId) to set id new component and new box 
	 */
	int saveMobileVersion(int fo100, long webId, String html, String jsonData, String apiCompSelector, String editToolSelector, int extend);
	/**
	 * get web data create by user
	 * @param fo100
	 * @return
	 */
	OhhayLegoWebBase getWebEvoData(int fo100, int pe400);
	/**
	 * @param fo100
	 * @param fm150
	 * @return
	 */
	OhhayLegoWebBase getWebTopicData(int fo100, int fm150);
	/**
	 * delete a website
	 * @param fo100
	 * @param pe400
	 * @param extend
	 * @return
	 */
	int remove(int fo100, int pe400, int extend);
	/**
	 * @param fo100
	 * @param webId
	 * @param extend
	 * @param siteName
	 * @return
	 */
	int updateSiteName(int fo100, int webId, int extend, String siteName);
	/**
	 * @param fo100
	 * @param hv101
	 * @param html
	 * @param jsonData
	 * @param apiCompSelector
	 * @param editToolSelector
	 * @param extend
	 * @return
	 */
	Map<String, Long> saveTrial(int fo100, long tempateId, String hv101, String html, String jsonData, String apiCompSelector, String editToolSelector, int extend);
	/**
	 * @param fo100
	 * @param webId
	 * @return
	 */
	int duplicate(int fo100, int webId);
	/**
	 * @param pe400
	 * @return
	 */
	OhhayLegoWebBase getWebEvoDataTrial(int pe400);
	/**
	 * @param fo100
	 * @param templateId
	 * @param checkDB true kiem tra dieu kien web phai luu vet la tempalte, false bo qua check dieu kien
	 * @return
	 */
	int createBytemplate(int fo100, int templateId, boolean checkDB);
	/**
	 * @param fo100
	 * @param pe400
	 * @param title
	 * @param description
	 * @param imageBase64
	 * @return
	 */
	int updateSEOinfo(int fo100, int pe400, String title, String description, String imageBase64, String imageBase64Fav, String region, String googleTrackingScript);
	/**
	 * @param fo100
	 * @param templateId
	 * @param kubvideo
	 * @return
	 */
	int createKubLandingPage(int fo100, int templateId, String kubvideo);
	/**
	 * @param fo100
	 * @param templateId
	 * @param cardContent
	 * @param imgBase64
	 * @return
	 */
	int createEVOCard(int fo100, int templateId, String cardContent, String imgBase64, String nv100, String region);
	/**
	 * check right add domain
	 * @param authenticationRightInfo
	 * @param fo100
	 * @return
	 */
	String checkRightCreateWeb(AuthenticationRightInfo authenticationRightInfo,int fo100);
	/**
	 * @param fo100
	 * @param fo100Temp
	 * @param currentWebId
	 * @param templateId
	 * @return
	 */
	int replaceWebToWeb(int fo100, int fo100Temp, int currentWebId, int templateId);
	/**
	 * @param fo100
	 * @param isBusinessAccount
	 * @return
	 */
	int createPiepmeSite(int fo100, boolean isBusinessAccount);
}
