package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Mar 3, 2017
 */
public class P160PMG implements Serializable{
	@Field("FO100")
	private int fo100;
	@Field("OTP_CODE")
	private String otpCode;
	@Field("STT")
	private String stt;
	public P160PMG(int fo100, String otpCode) {
		super();
		this.fo100 = fo100;
		this.otpCode = otpCode;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getOtpCode() {
		return otpCode;
	}
	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	
	
}
