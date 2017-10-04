package com.ohhay.core.criteria;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create 12/09/2014
 * parameter pass do create domain service
 */
public class DomainCriteria implements Serializable{
	private int pos;//position = -1: insert, >=0 update 
	private String domainName;
	private int type;//1: home page, 2: webinaris page, 3: bsell
	private int fe400;
	private String verificationCode;
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getFe400() {
		return fe400;
	}
	public void setFe400(int fe400) {
		this.fe400 = fe400;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
}
