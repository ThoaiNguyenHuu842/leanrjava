package com.ohhay.core.entities;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create 22/04/2015
 * thong tin viewer oohhay
 */
public class OhhayViewer implements Serializable{
	private String ip;//ip viweer
	private String countryCode;//ma quoc gia
	private String countryName;// ten quoc gia
	private String languageCode;// ma ngon ngu
	private String languageName;// ten ngon ngu
	private String countryCallingCode;//ma dien thoai
	private String regionCode;//ma chau luc
	private String browser;//trinh duyet
	private String os;//he dieu hanh
	private String device;//thiet bi
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getCountryCallingCode() {
		return countryCallingCode;
	}
	public void setCountryCallingCode(String countryCallingCode) {
		this.countryCallingCode = countryCallingCode;
	}
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getOhhayLangCode()
	{
		if(countryCode != null){
			switch (countryCode.trim().toLowerCase()) {
			case "vn":
				return "vi";
			case "de" :
			case "at" :
			case "ch":
				return "de";
			default:
				break;
			}
		}
		return "en";
	}
	@Override
	public String toString() {
		return "OhhayViewer [ip=" + ip + ", countryCode=" + countryCode
				+ ", countryName=" + countryName + ", languageCode="
				+ languageCode + ", languageName=" + languageName
				+ ", countryCallingCode=" + countryCallingCode + ", regionCode="
				+ regionCode + ", browser=" + browser + ", os=" + os
				+ ", device=" + device + "]";
	}
	
}
