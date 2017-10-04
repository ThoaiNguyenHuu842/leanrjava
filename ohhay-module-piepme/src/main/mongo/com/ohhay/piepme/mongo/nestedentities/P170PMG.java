package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Mar 6, 2017
 */
public class P170PMG implements Serializable{
	@Field("OTP_CODE1")
	private String otpCode1;
	@Field("OTP_CODE2")
	private String otpCode2;
	@Field("CONFIRMATION_DATE")
	private Date confirmationDate;
	public String getOtpCode1() {
		return otpCode1;
	}
	public void setOtpCode1(String otpCode1) {
		this.otpCode1 = otpCode1;
	}
	public String getOtpCode2() {
		return otpCode2;
	}
	public void setOtpCode2(String otpCode2) {
		this.otpCode2 = otpCode2;
	}
	public Date getConfirmationDate() {
		return confirmationDate;
	}
	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	@Override
	public String toString() {
		return "P170PMG [otpCode1=" + otpCode1 + ", otpCode2=" + otpCode2
				+ ", confirmationDate=" + confirmationDate + "]";
	}
	
}
