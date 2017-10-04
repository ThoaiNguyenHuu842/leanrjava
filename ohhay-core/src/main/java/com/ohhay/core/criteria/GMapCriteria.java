package com.ohhay.core.criteria;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create 22/11/2014
 * parameter pass to web info service
 */
public class GMapCriteria implements Serializable{
	private String imgBase64;
	private int webId;
	private int indexPart;
	private int extend; 
	private String la;
	private String lng;
	private String address;
	private String markerImg;
	private String contentAddress;
	public int getWebId() {
		return webId;
	}
	public void setWebId(int webId) {
		this.webId = webId;
	}
	public int getIndexPart() {
		return indexPart;
	}
	public void setIndexPart(int indexPart) {
		this.indexPart = indexPart;
	}
	public int getExtend() {
		return extend;
	}
	public void setExtend(int extend) {
		this.extend = extend;
	}
	public String getLa() {
		return la;
	}
	public void setLa(String la) {
		this.la = la;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
	public String getMarkerImg() {
		return markerImg;
	}
	public void setMarkerImg(String markerImg) {
		this.markerImg = markerImg;
	}
	public String getContentAddress() {
		return contentAddress;
	}
	public void setContentAddress(String contentAddress) {
		this.contentAddress = contentAddress;
	}
	
		
}
