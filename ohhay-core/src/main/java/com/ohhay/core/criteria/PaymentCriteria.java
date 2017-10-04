package com.ohhay.core.criteria;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ThoaiNH
 * create 12/04/2015
 * paymnet wall parameter
 */
public class PaymentCriteria implements Serializable{
	@NotBlank(message="{setting.payment.mesage_error}")
	private String widgetType;
	@NotBlank(message="{setting.payment.mesage_error}")
	private String skuid;
	@NotBlank(message="{setting.payment.message_error}")
	private String userAddress;
	private String userBirthDateString;
	@NotBlank(message="{setting.payment.message_error}")
	private String userCity;
	@NotBlank(message="{setting.payment.message_error}")
	private String userCountry;
	@NotBlank(message="{setting.payment.message_error}")
	private String userZip;
	@NotBlank(message="{setting.payment.message_error}")
	private String userFisrtName;
	@NotBlank(message="{setting.payment.message_error}")
	private String userLastName;
	@NotBlank(message="{setting.payment.message_error}")
	private String userState;
	@NotBlank(message="{setting.payment.message_error}")
	private String userSex;
	@NotBlank(message="{setting.payment.message_error}")
	private String userEmail;
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	public String getSkuid() {
		return skuid;
	}
	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	public String getUserZip() {
		return userZip;
	}
	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}
	public String getUserFisrtName() {
		return userFisrtName;
	}
	public void setUserFisrtName(String userFisrtName) {
		this.userFisrtName = userFisrtName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public void setUserBirthDateString(String userBirthDateString) {
		this.userBirthDateString = userBirthDateString;
	}
	public String getUserBirthDateString() {
		return userBirthDateString;
	}
	@Override
	public String toString() {
		return "PaymentCriteria [widgetType=" + widgetType + ", skuid=" + skuid
				+ ", userAddress=" + userAddress + ", userBirthDateString="
				+ userBirthDateString + ", userCity=" + userCity
				+ ", userCountry=" + userCountry + ", userZip=" + userZip
				+ ", userFisrtName=" + userFisrtName + ", userLastName="
				+ userLastName + ", userState=" + userState + ", userSex="
				+ userSex + ", userEmail=" + userEmail + "]";
	}
	
	
}
