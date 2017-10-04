package com.ohhay.web.core.entities.mongo.webbase;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create: 12/09/2014
 * element info
 */
public class C900MG implements Serializable {
	//for edit attribute
	@Transient
	private String trackingObjectName;
	@Transient
	private int trackingStatus;//1: acitve, 0: deactive
	@Transient
	private String languageCode;
	@Transient
	private String imgBase64;
	@Transient
	private int testMode;//web test mode
	@Transient
	private int extend;
	@Transient
	private long editPosition;//luu lai posion khi edit
	@Transient
	private int fc400;//id childrend page (neu fc400>0)
	@Transient
	private int webId;//id: c400, id:w400 or id:l400
	@Transient
	private int indexBox;
	@Transient
	private int indexEleme;
	@Transient
	private int indexProperty;//index to edit c100
	@Transient
	private String superID;
	@Transient
	private String iframeHeight;
	@Transient
	private int landingID;
	@Transient
	private String cv117;
	//mongon field
	@Field("ID")
	private String pc900;
	@Field("CV901")
	private String cv901;
	@Field("CV902")
	private String cv902;
	@Field("CV904")
	private String cv904;
	@Field("CV905")
	private String cv905;
	@Field("CV906")
	private String cv906;
	@Field("VISIBLE")
	private int visible;//an: -1, hien: 0
	@Field("FC850")
	private int fc850;
	public C900MG(C900MG c900mg) {
		this.pc900 = c900mg.getPc900();
		this.cv901 = c900mg.getCv901();
		this.cv902 = c900mg.getCv902();
		this.cv904 = c900mg.getCv904();
		this.cv905 = c900mg.getCv905();
		this.cv906 = c900mg.getCv906();
		this.fc850 = c900mg.getFc850();
		this.extend = c900mg.getExtend();
		this.editPosition = c900mg.getEditPosition();
		this.fc400 = c900mg.getFc400();
		this.languageCode = c900mg.getLanguageCode();
		this.visible = c900mg.getVisible();
		this.testMode = c900mg.getTestMode();
		this.landingID = c900mg.getLandingID();
	}
	public C900MG(String pc900,String cv901, String cv902, String cv904, String cv905, String cv906, int fc850){
		super();
		this.pc900 = pc900;
		this.cv901 = cv901;
		this.cv902 = cv902;
		this.cv904 = cv904;
		this.cv905 = cv905;
		this.cv906 = cv906;
		this.fc850 = fc850;
	}
	public C900MG() {

	}
	
	
	public int getTestMode() {
		return testMode;
	}
	public void setTestMode(int testMode) {
		this.testMode = testMode;
	}
	public String getCv901() {
		return cv901;
	}

	public void setCv901(String cv901) {
		this.cv901 = cv901;
	}

	public String getCv902() {
		return cv902;
	}

	public void setCv902(String cv902) {
		this.cv902 = cv902;
	}

	public String getCv905() {
		return cv905;
	}

	public void setCv905(String cv905) {
		this.cv905 = cv905;
	}

	public int getFc850() {
		return fc850;
	}

	public void setFc850(int fc850) {
		this.fc850 = fc850;
	}

	public String getPc900() {
		return pc900;
	}

	public void setPc900(String pc900) {
		this.pc900 = pc900;
	}
	public String getCv904() {
		return cv904;
	}

	public void setCv904(String cv904) {
		this.cv904 = cv904;
	}

	public long getEditPosition() {
		return editPosition;
	}

	public void setEditPosition(long editPosition) {
		this.editPosition = editPosition;
	}

	public String getCv906() {
		return cv906;
	}

	public void setCv906(String cv906) {
		this.cv906 = cv906;
	}
	public int getFc400() {
		return fc400;
	}

	public void setFc400(int fc400) {
		this.fc400 = fc400;
	}

	public int getWebId() {
		if(webId != 0)
			return webId;
		else{
			int webId = 0;
			String[] ids = this.getPc900().split("#");
			webId = Integer.parseInt(ids[0]);
			return webId;	
		}
	}

	public void setWebId(int webId) {
		this.webId = webId;
	}

	public int getIndexBox() {
		return indexBox;
	}

	public void setIndexBox(int indexBox) {
		this.indexBox = indexBox;
	}

	public int getIndexEleme() {
		return indexEleme;
	}

	public void setIndexEleme(int indexEleme) {
		this.indexEleme = indexEleme;
	}

	public int getExtend() {
		return extend;
	}

	public void setExtend(int extend) {
		this.extend = extend;
	}
	/*
	 * VERY IMPORTANT
	 */
	public String getSuperID() {
		superID = webId+"#"+indexBox+"#"+indexEleme+"#"+indexProperty;
		return superID;
	}

	public void setSuperID(String superID) {
		this.superID = superID;
	}

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public int getIndexProperty() {
		return indexProperty;
	}

	public void setIndexProperty(int indexProperty) {
		this.indexProperty = indexProperty;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getIframeHeight() {
		return iframeHeight;
	}
	public void setIframeHeight(String iframeHeight) {
		this.iframeHeight = iframeHeight;
	}
	public int getLandingID() {
		return landingID;
	}
	public void setLandingID(int landingID) {
		this.landingID = landingID;
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
	public String getCv117() {
		return cv117;
	}
	public void setCv117(String cv117) {
		this.cv117 = cv117;
	}
	@Override
	public String toString() {
		return "C900MG [trackingObjectName=" + trackingObjectName
				+ ", trackingStatus=" + trackingStatus + ", languageCode="
				+ languageCode + ", imgBase64=" + imgBase64 + ", testMode="
				+ testMode + ", extend=" + extend + ", editPosition="
				+ editPosition + ", fc400=" + fc400 + ", webId=" + webId
				+ ", indexBox=" + indexBox + ", indexEleme=" + indexEleme
				+ ", indexProperty=" + indexProperty + ", superID=" + superID
				+ ", iframeHeight=" + iframeHeight + ", landingID=" + landingID
				+ ", pc900=" + pc900 + ", cv901=" + cv901 + ", cv902=" + cv902
				+ ", cv904=" + cv904 + ", cv905=" + cv905 + ", cv906=" + cv906
				+ ", visible=" + visible + ", fc850=" + fc850 + "]";
	}
	
}
