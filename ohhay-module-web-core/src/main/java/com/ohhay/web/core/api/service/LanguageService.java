package com.ohhay.web.core.api.service;

import java.util.List;
import java.util.Map;

import com.ohhay.core.authentication.AuthenticationRightInfo;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.web.core.entities.mongo.webbase.C110MG;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.entities.mongo.webbase.C920MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;
import com.ohhay.web.core.load.util.PropertyValue;

/**
 * @author ThoaiNH
 * create: 12/11/2014
 * service for create, load web language
 */
public interface LanguageService {
	/**
	 * tao ngon ngu cho mot web ohhay
	 * @author ThoaiNH
	 * @param webId: id web
	 * @param fo100: fo100 user
	 * @param languageCode: lang code muon tao
	 * @param languageName: lang name muon tao
	 * @param colectionName: colection name (C900, W900, B900, L900)
	 * @return
	 */
	int cloneLanguage(int webId, int fo100,String languageCode,String languageName, String colectionName);
	/**
	 * tao ngon ngu dau tien khi dang ky web ohhay
	 * @param webId: id web
	 * @param fo100: fo100 user
	 * @param listC920mgs: danh sach box
	 * @param languageCode: lang code muon tao
	 * @param languageName: lang name muon tao
	 * @param colectionName: colection name (C900, W900, B900, L900)
	 * @return
	 */
	int createLanguage(int webId, int fo100, List<C920MG> listC920mgs, String languageCode,String languageName, String colectionName);
	/**
	 * tao ngon ngu c500 trong web ohhay, hien thi hinh flag select ngon ngu
	 * @param webId: id web
	 * @param colectionName: colection name (C900, W900, B900, L900)
	 * @param languageCode: lang code muon tao
	 * @param languageName: lang name muon tao
	 * @return
	 */
	int createLanguageC450(int fo100, int webId,String colectionName, String languageCode,String languageName);
	/**
	 * @param webId:id web
	 * @param colectionName: colection name (C900, W900, B900, L900)
	 * @param languageCode: lang code muon xoa
	 * @return
	 */
	int deleteLanguage(int fo100, int webId,String colectionName, String languageCode);
	/**
	 * @param webId: id web
	 * @param languageCode: lang code muon xoa
	 * @param colectionName: colection name (C900, W900, B900, L900)
	 * @return
	 */
	int deleteLanguageC450(int fo100, int webId,String languageCode,String colectionName);
	/** dung de copy ngon ngu khi copy box
	 * @param webId: id web hien tai
	 * @param fo100: fo100 
	 * @param c920mg: c920 muon copy
	 * @param mClass: web class
	 * @param c920mgOld: c920 tao tu c920 muon copy
	 * @param appendID: append id moi co c920, c900
	 * @param collectionName: web collection name
	 * @return
	 */
	<T> int pushAllLanguage(int webId, int fo100, C920MG c920mg, Class<T> mClass, C920MG c920mgOld, String appendID, String collectionName);
	/**
	 * @param webId
	 * @param languageCode
	 * @param c110mg
	 * @return
	 */
	<T> int pushOneLanguage(int fo100, int webId, String languageCode, C110MG c110mg, Class<T> mClass);
	/**
	 * @param ohhayWebLanguageBase
	 * @return
	 */
	public Map<String, PropertyValue> getProperties(OhhayWebLanguageBase ohhayWebLanguageBase);
	
	/**
	 * update 07/07/2015 check right before save
	 * @param c500
	 * @param fo100
	 * @param authenticationRightInfo
	 * @param appCall: true if call on app -> not confirm right 
	 * @return
	 */
	int addLanguage(LanguageMG c500, int fo100, AuthenticationRightInfo authenticationRightInfo, boolean appCall);
	/**
	 * @param c500
	 * @param fo100
	 * @return
	 */
	int removeLanguage(LanguageMG c500, int fo100);
	/**
	 * @param c900mg
	 * @param fc920
	 * @param fc850
	 * @return
	 */
	C110MG createC110ForElement(C900MG c900mg, int fc920, int fc850);
	/**
	 * date create: 07/07/2015
	 * check right before add language
	 * @param authenticationRightInfo
	 * @return -2222: using free packet -> can't add language; -1111: using basic packet and created 3 language -> can't add language, 1111: can create new language
	 */
	int checkRightAddLanguage(AuthenticationRightInfo authenticationRightInfo, int fo100);
	int stornoC110Data(int fo100, String languageID, String cv111, int extend);
}
