package com.ohhay.web.core.entities;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * danh sach du an webinaris
 */
@Document(collection="V250")
public class V250 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pv250;
	private String vv251;
	private String vv252;
	private String vv502;// Ten San Pham
	private String vv501; // Ma san pham
	public String getVv502() {
		return vv502;
	}
	public void setVv502(String vv502) {
		this.vv502 = vv502;
	}
	public String getVv501() {
		return vv501;
	}
	public void setVv501(String vv501) {
		this.vv501 = vv501;
	}
	public String getVn254() {
		return vn254;
	}
	public void setVn254(String vn254) {
		this.vn254 = vn254;
	}
	private String vn254;// Gia San Pham	
	private String urlimg;
	public String getVv251() {
		return vv251;
	}
	public void setVv251(String vv251) {
		this.vv251 = vv251;
	}
	public String getVv252() {
		return vv252;
	}
	public void setVv252(String vv252) {
		this.vv252 = vv252;
	}
	public String getUrlimg() {
		return urlimg;
	}
	public void setUrlimg(String urlimg) {
		this.urlimg = urlimg;
	}
	public String getPv250() {
		return pv250;
	}
	public void setPv250(String pv250) {
		this.pv250 = pv250;
	}

}
