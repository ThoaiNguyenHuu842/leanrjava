package com.ohhay.web.core.utils;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ohhay.web.core.entities.mongo.webbase.C920MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.load.util.PropertyValue;

/**
 * @author ThoaiNH
 * create 26/12/2014
 * parameter to load web
 */
public class WebCreateParam {
	private static Logger log = Logger.getLogger(WebCreateParam.class);
	private OhhayWebBase ohhayWebBase;// web structure in mongo
	private int role;// role
	private int extendPage;// extend page type
	private String key;// key extend
	private String editMode;// edit mode
	private Map<String, PropertyValue> mapProperties;// map language properties
	private String currentLanguage;// current language
	private int webchildID;// id of web child
	private int testMode;// 1 is test mode
	private int landingID;// id of landing page
	private int nCase;// case request
	private int boxVisible;// 1: show all box, 0: show only visible box
	public OhhayWebBase getOhhayWebBase() {
		return ohhayWebBase;
	}
	public void setOhhayWebBase(OhhayWebBase ohhayWebBase) {
		// create real index
		for (int i = 0; i < ohhayWebBase.getListC920mg().size(); i++) {
			C920MG c920mg = ohhayWebBase.getListC920mg().get(i);
			if(c920mg != null){
				log.info("----SET REAL INDEX FC920:" + c920mg.getFc920()
						+ ", realindex:" + i);
				c920mg.setRealIndex(i);
			}
		}
		// sort by cn904
		Collections.sort(ohhayWebBase.getListC920mg());
		this.ohhayWebBase = ohhayWebBase;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getExtendPage() {
		return extendPage;
	}

	public void setExtendPage(int extendPage) {
		this.extendPage = extendPage;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getEditMode() {
		return editMode;
	}

	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}

	public Map<String, PropertyValue> getMapProperties() {
		return mapProperties;
	}

	public void setMapProperties(Map<String, PropertyValue> mapProperties) {
		this.mapProperties = mapProperties;
	}

	public String getCurrentLanguage() {
		return currentLanguage;
	}

	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
	}

	public int getWebchildID() {
		return webchildID;
	}

	public void setWebchildID(int webchildID) {
		this.webchildID = webchildID;
	}

	public int getTestMode() {
		return testMode;
	}

	public void setTestMode(int testMode) {
		this.testMode = testMode;
	}

	public int getLandingID() {
		return landingID;
	}

	public void setLandingID(int landingID) {
		this.landingID = landingID;
	}

	public int getnCase() {
		return nCase;
	}

	public void setnCase(int nCase) {
		this.nCase = nCase;
	}

	public int getBoxVisible() {
		return boxVisible;
	}

	public void setBoxVisible(int boxVisible) {
		this.boxVisible = boxVisible;
	}

	
}
