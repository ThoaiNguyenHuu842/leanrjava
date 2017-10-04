package com.ohhay.core.criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ThoaiNH
 * create Dec 14, 2015
 * parameter to create EVO account
 */
public class AccountPiepmeRegisCriteria implements Serializable{
	private String email;
	private String nickName;
	private String uuid;
	private String securityNumber;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String key) {
		this.uuid = key;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSecurityNumber() {
		return securityNumber;
	}
	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
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
	
}
