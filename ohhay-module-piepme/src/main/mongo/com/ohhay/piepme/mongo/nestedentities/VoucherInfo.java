package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create Jul 7, 2017
 */
public class VoucherInfo implements Serializable{
	private int fo100;
	private String urlAvarta;
	private String nv106;
	private int fp300V;
	private String voucherCode;
	private String dateCreate;
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getUrlAvarta() {
		return urlAvarta;
	}
	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}
	public String getNv106() {
		return nv106;
	}
	public void setNv106(String nv106) {
		this.nv106 = nv106;
	}
	public int getFp300V() {
		return fp300V;
	}
	public void setFp300V(int fp300v) {
		fp300V = fp300v;
	}
	public String getVoucherCode() {
		return voucherCode;
	}
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	
}
