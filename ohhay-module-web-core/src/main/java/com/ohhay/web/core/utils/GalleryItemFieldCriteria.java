package com.ohhay.web.core.utils;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create 10/11/2014
 * parameter edit gallery item
 */
public class GalleryItemFieldCriteria implements Serializable{
	private String trackingObjectName;
	private int trackingStatus;//1: acitve, 0: deactive
	private String languageCode;
	public static final int GROUP_TYPE_LINK_TEXT = 0;
	public static final int GROUP_TYPE_LINK_IMG = 1;
	private int testMode;
	private int landingID;
	private int extend;//luu lai la page webinaris, bsell, hay normal page
	private long editPosition;//luu lai posion khi edit
	private int fc400;//id landing page khi edit
	private int groupType;//0:link text, 1:link img
	private String editItem;
	private String content;
	private String imgBase64;
	private String pc900;
	private String iframeHeight;
	private String cv117;
	public GalleryItemFieldCriteria(GalleryItemFieldCriteria galleryItemField) {
		super();
		this.editPosition = galleryItemField.getEditPosition();
		this.fc400 = galleryItemField.getFc400();
		this.editItem = galleryItemField.editItem;
		this.content = galleryItemField.getContent();
		this.pc900 = galleryItemField.getPc900();
		this.testMode = galleryItemField.getTestMode();
		this.landingID = galleryItemField.getLandingID();
	}
	
	
	public int getLandingID() {
		return landingID;
	}


	public void setLandingID(int landingID) {
		this.landingID = landingID;
	}


	public String getIframeHeight() {
		return iframeHeight;
	}


	public void setIframeHeight(String iframeHeight) {
		this.iframeHeight = iframeHeight;
	}


	public int getTestMode() {
		return testMode;
	}


	public void setTestMode(int testMode) {
		this.testMode = testMode;
	}


	public GalleryItemFieldCriteria() {
		// TODO Auto-generated constructor stub
	}
	public String getEditItem() {
		return editItem;
	}
	public void setEditItem(String editItem) {
		this.editItem = editItem;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPc900() {
		return pc900;
	}
	public void setPc900(String pc900) {
		this.pc900 = pc900;
	}
	public long getEditPosition() {
		return editPosition;
	}
	public void setEditPosition(long editPosition) {
		this.editPosition = editPosition;
	}
	public int getFc400() {
		return fc400;
	}
	public void setFc400(int fc400) {
		this.fc400 = fc400;
	}
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "pc900:"+pc900+", content:"+content+", editItem:"+editItem+", editPosition:"+editPosition+","+fc400;
	}
	public int getExtend() {
		return extend;
	}
	public void setExtend(int extend) {
		this.extend = extend;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
	public static int getGroupTypeLinkText() {
		return GROUP_TYPE_LINK_TEXT;
	}
	public static int getGroupTypeLinkImg() {
		return GROUP_TYPE_LINK_IMG;
	}


	public String getTrackingObjectName() {
		return trackingObjectName;
	}


	public void setTrackingObjectName(String trackingObjectName) {
		this.trackingObjectName = trackingObjectName;
	}


	public int getTrackingStatus() {
		return trackingStatus;
	}


	public void setTrackingStatus(int trackingStatus) {
		this.trackingStatus = trackingStatus;
	}
	/**
	 * create: 07/07/2015
	 * @return
	 * 
	 */
	public int getWebid(){
		int webId = 0;
		String[] ids = this.getPc900().split("#");
		webId = Integer.parseInt(ids[0]);
		return webId;
	}


	public String getCv117() {
		return cv117;
	}


	public void setCv117(String cv117) {
		this.cv117 = cv117;
	}

	
}
