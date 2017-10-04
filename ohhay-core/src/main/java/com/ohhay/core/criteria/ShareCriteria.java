package com.ohhay.core.criteria;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create 09/04/2015
 * share parameter
 */
public class ShareCriteria implements Serializable{
	private int fo100;
	private int fo100s;
	private int fo100f;
	private int totalShare;
	private String currentLink;
	
	public int getTotalShare() {
		return totalShare;
	}
	public void setTotalShare(int totalShare) {
		this.totalShare = totalShare;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public int getFo100s() {
		return fo100s;
	}
	public void setFo100s(int fo100s) {
		this.fo100s = fo100s;
	}
	public int getFo100f() {
		return fo100f;
	}
	public void setFo100f(int fo100f) {
		this.fo100f = fo100f;
	}
	public String getCurrentLink() {
		return currentLink;
	}
	public void setCurrentLink(String currentLink) {
		this.currentLink = currentLink;
	}
	
}
