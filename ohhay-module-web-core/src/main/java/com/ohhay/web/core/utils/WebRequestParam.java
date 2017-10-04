package com.ohhay.web.core.utils;

/**
 * @author ThoaiNH
 * create 11/12/2014
 * web request param
 */
public class WebRequestParam {
	private int nCase;
	private String phoneRequest;
	private int parentID;
	private int webChildID;
	private String extendPageParam;
	private String languageCode;
	private String editMode;
	private int testMode;
	private int landingPageID;
	private int fb050Shanti;
	public WebRequestParam(int nCase, String phoneRequest, int parentID,
			int webChildID, String extendPageParam, String languageCode,
			String editMode, int testMode, int landingPageID, int fb050Shanti) {
		super();
		this.nCase = nCase;
		this.phoneRequest = phoneRequest;
		this.parentID = parentID;
		this.webChildID = webChildID;
		this.extendPageParam = extendPageParam;
		this.languageCode = languageCode;
		this.editMode = editMode;
		this.testMode = testMode;
		this.landingPageID = landingPageID;
		this.fb050Shanti = fb050Shanti;
	}
	
	public int getFb050Shanti() {
		return fb050Shanti;
	}

	public void setFb050Shanti(int fb050Shanti) {
		this.fb050Shanti = fb050Shanti;
	}

	public int getnCase() {
		return nCase;
	}

	public void setnCase(int nCase) {
		this.nCase = nCase;
	}

	public String getPhoneRequest() {
		return phoneRequest;
	}

	public void setPhoneRequest(String phoneRequest) {
		this.phoneRequest = phoneRequest;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public int getWebChildID() {
		return webChildID;
	}

	public void setWebChildID(int webChildID) {
		this.webChildID = webChildID;
	}

	public String getExtendPageParam() {
		return extendPageParam;
	}

	public void setExtendPageParam(String extendPageParam) {
		this.extendPageParam = extendPageParam;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getEditMode() {
		return editMode;
	}

	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}

	public int getTestMode() {
		return testMode;
	}

	public void setTestMode(int testMode) {
		this.testMode = testMode;
	}

	public int getLandingPageID() {
		return landingPageID;
	}

	public void setLandingPageID(int landingPageID) {
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "*****WEB REQUEST PARAM: nCase:"+nCase+", phone:"+phoneRequest+", parentID:"+parentID+", webChildId:"+webChildID+
				", extend:"+extendPageParam+", languageCode:"+languageCode+", editMode"+editMode +", testMode:"+testMode+", landingID:"+landingPageID;
	}
}
