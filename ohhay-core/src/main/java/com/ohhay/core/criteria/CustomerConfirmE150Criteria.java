package com.ohhay.core.criteria;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create Mar 5, 2016
 * parameter customer confirm E150
 */
public class CustomerConfirmE150Criteria implements Serializable{
	private int fo100Des;
	private int pe150;
	private int fe400C;
	public int getFo100Des() {
		return fo100Des;
	}
	public void setFo100Des(int fo100Des) {
		this.fo100Des = fo100Des;
	}
	public int getPe150() {
		return pe150;
	}
	public void setPe150(int pe150) {
		this.pe150 = pe150;
	}
	public int getFe400C() {
		return fe400C;
	}
	public void setFe400C(int fe400c) {
		fe400C = fe400c;
	}
	@Override
	public String toString() {
		return "CustomerConfirmE150Criteria [fo100Des=" + fo100Des + ", pe150="
				+ pe150 + ", fe400C=" + fe400C + "]";
	}
	
}
