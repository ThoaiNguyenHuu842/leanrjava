package com.ohhay.web.core.load.util;

/**
 * @author ThoaiNH
 * data temp to store web element data
 * update: 30/06/2015
 * them cv115 luu postion css cua gallerty
 */
public class PropertyValue {
	private int index;
	private String value;
	private String cv113;//tracking code
	private int cn114;//tracking status
	private String cv115;//position css
	private String cv116;//store more info of text element EX: email form send mail
	private String cv117;//store more info of image element
	public PropertyValue(int index, String value, String cv113, int cn114, String cv115, String cv116, String cv117) {
		super();
		this.index = index;
		this.value = value;
		this.cv113 = cv113;
		this.cn114 = cn114;
		this.cv115 = cv115;
		this.cv116 = cv116;
		this.cv117 = cv117;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCv113() {
		return cv113;
	}
	public void setCv113(String cv113) {
		this.cv113 = cv113;
	}
	public int getCn114() {
		return cn114;
	}
	public void setCn114(int cn114) {
		this.cn114 = cn114;
	}
	public String getCv115() {
		return cv115;
	}
	public void setCv115(String cv115) {
		this.cv115 = cv115;
	}
	public String getCv116() {
		return cv116;
	}
	public void setCv116(String cv116) {
		this.cv116 = cv116;
	}
	public String getCv117() {
		return cv117;
	}
	public void setCv117(String cv117) {
		this.cv117 = cv117;
	}
	
}
