package com.ohhay.core.criteria;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author ThoaiNH
 * create Dec 14, 2015
 * parameter to create EVO account
 */
public class AccountEVORegisCriteria implements Serializable{
	public static final String REGISTYPE_FB = "fb";
	public static final String REGISTYPE_GOO = "gg";
	public static final String REGISTYPE_EVO = "be";
	public static final String REGISTYPE_KUB = "kb";
	@NotBlank(message="{setting.warnning.fname_error}")
	private String fName;
	@NotBlank(message="{setting.warnning.lname_error}")
	private String lName;
	@Pattern(regexp=".*@.*", message="{setting.warnning.email_error}")
	private String email;
	@NotBlank(message="{setting.warnning.password_error}")
	private String passWord;
	@NotBlank(message="{setting.warnning.confirmpassword_error}")
	private String rePassWord;
	private String countryCode;
	private String languageCode;
	private String languageName;
	private String region;
	private String gender;
	private int fd000;
	private String jobName;
	private String regisTypel;//FB, GOO, KUB or EVO
	private String socialId;
	/*
	 * register with trial
	 */
	private long templateId;
	private String html;
	private String data;
	private String apiCompSelector;
	private String editToolSelector;
	private String mobileEditor;
	/*
	 * kub video
	 */
	private String kubvideo;
	/*
	 * digistore info 
	 * update 25/03/2016
	 */
	private String buyerAddressCountry;
	private Date orderDaytime; 
	private double transactionAmount;
	private String digiStoreId;
	private String productId; 
	private String even;
	private Date transactionDate; 
	private String transactionId;
	private String addressZipcode;
	private String paymentPlan;
	private Date nextPaymentDay;
	private String invoiceUrl;
	private String rebillingStopUrl;
	private String receiptUrl;
	private String renewUrl;
	private String requestRefundUrl;
	private String supportUrl;
	private String becomeAffiliateUrl;
	private String passwordForDigi;
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRePassWord() {
		return rePassWord;
	}
	public void setRePassWord(String rePassWord) {
		this.rePassWord = rePassWord;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getFd000() {
		return fd000;
	}
	public void setFd000(int fd000) {
		this.fd000 = fd000;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getRegisTypel() {
		return regisTypel;
	}
	public void setRegisTypel(String regisTypel) {
		this.regisTypel = regisTypel;
	}
	public String getSocialId() {
		return socialId;
	}
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getApiCompSelector() {
		return apiCompSelector;
	}
	public void setApiCompSelector(String apiCompSelector) {
		this.apiCompSelector = apiCompSelector;
	}
	public String getEditToolSelector() {
		return editToolSelector;
	}
	public void setEditToolSelector(String editToolSelector) {
		this.editToolSelector = editToolSelector;
	}
	public String getMobileEditor() {
		return mobileEditor;
	}
	public void setMobileEditor(String mobileEditor) {
		this.mobileEditor = mobileEditor;
	}
	public long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}
	public String getKubvideo() {
		return kubvideo;
	}
	public void setKubvideo(String kubvideo) {
		this.kubvideo = kubvideo;
	}
	public String getBuyerAddressCountry() {
		return buyerAddressCountry;
	}
	public void setBuyerAddressCountry(String buyerAddressCountry) {
		this.buyerAddressCountry = buyerAddressCountry;
	}
	public Date getOrderDaytime() {
		return orderDaytime;
	}
	public void setOrderDaytime(Date orderDaytime) {
		this.orderDaytime = orderDaytime;
	}
	
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getDigiStoreId() {
		return digiStoreId;
	}
	public void setDigiStoreId(String digiStoreId) {
		this.digiStoreId = digiStoreId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getEven() {
		return even;
	}
	public void setEven(String even) {
		this.even = even;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAddressZipcode() {
		return addressZipcode;
	}
	public void setAddressZipcode(String addressZipcode) {
		this.addressZipcode = addressZipcode;
	}
	public String getPaymentPlan() {
		return paymentPlan;
	}
	public void setPaymentPlan(String paymentPlan) {
		this.paymentPlan = paymentPlan;
	}
	public Date getNextPaymentDay() {
		return nextPaymentDay;
	}
	public void setNextPaymentDay(Date nextPaymentDay) {
		this.nextPaymentDay = nextPaymentDay;
	}
	public String getInvoiceUrl() {
		return invoiceUrl;
	}
	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}
	public String getRebillingStopUrl() {
		return rebillingStopUrl;
	}
	public void setRebillingStopUrl(String rebillingStopUrl) {
		this.rebillingStopUrl = rebillingStopUrl;
	}
	public String getReceiptUrl() {
		return receiptUrl;
	}
	public void setReceiptUrl(String receiptUrl) {
		this.receiptUrl = receiptUrl;
	}
	public String getRenewUrl() {
		return renewUrl;
	}
	public void setRenewUrl(String renewUrl) {
		this.renewUrl = renewUrl;
	}
	public String getRequestRefundUrl() {
		return requestRefundUrl;
	}
	public void setRequestRefundUrl(String requestRefundUrl) {
		this.requestRefundUrl = requestRefundUrl;
	}
	public String getSupportUrl() {
		return supportUrl;
	}
	public void setSupportUrl(String supportUrl) {
		this.supportUrl = supportUrl;
	}
	public String getBecomeAffiliateUrl() {
		return becomeAffiliateUrl;
	}
	public void setBecomeAffiliateUrl(String becomeAffiliateUrl) {
		this.becomeAffiliateUrl = becomeAffiliateUrl;
	}
	public String getPasswordForDigi() {
		return passwordForDigi;
	}
	public void setPasswordForDigi(String passwordForDigi) {
		this.passwordForDigi = passwordForDigi;
	}
	@Override
	public String toString() {
		return "AccountEVORegisCriteria [fName=" + fName + ", lName=" + lName
				+ ", email=" + email + ", passWord=" + passWord
				+ ", rePassWord=" + rePassWord + ", countryCode=" + countryCode
				+ ", languageCode=" + languageCode + ", languageName="
				+ languageName + ", region=" + region + ", gender=" + gender
				+ ", fd000=" + fd000 + ", jobName=" + jobName + ", regisTypel="
				+ regisTypel + ", socialId=" + socialId + ", templateId="
				+ templateId + ", html=" + html + ", data=" + data
				+ ", apiCompSelector=" + apiCompSelector + ", editToolSelector="
				+ editToolSelector + ", mobileEditor=" + mobileEditor
				+ ", kubvideo=" + kubvideo + ", buyerAddressCountry="
				+ buyerAddressCountry + ", orderDaytime=" + orderDaytime
				+ ", transactionAmount=" + transactionAmount + ", digiStoreId="
				+ digiStoreId + ", productId=" + productId + ", even=" + even
				+ ", transactionDate=" + transactionDate + ", transactionId="
				+ transactionId + ", addressZipcode=" + addressZipcode
				+ ", paymentPlan=" + paymentPlan + ", nextPaymentDay="
				+ nextPaymentDay + ", invoiceUrl=" + invoiceUrl
				+ ", rebillingStopUrl=" + rebillingStopUrl + ", receiptUrl="
				+ receiptUrl + ", renewUrl=" + renewUrl + ", requestRefundUrl="
				+ requestRefundUrl + ", supportUrl=" + supportUrl
				+ ", becomeAffiliateUrl=" + becomeAffiliateUrl
				+ ", passwordForDigi=" + passwordForDigi + "]";
	}
	
}
