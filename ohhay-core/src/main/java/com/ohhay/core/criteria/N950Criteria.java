package com.ohhay.core.criteria;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * @author ThoaiNH
 * SEO parameter
 */
public class N950Criteria implements Serializable {
	private int webId;
	private int extend;
	private String imgBase64;
	private String address;
//	@Email(message="{setting.n950.email}")
	private String email;
	private String phone;
	private String route;
	private String stnumbers;
	private String sublocality;
	private String locality;
	private String postalcode;
	private String country;
	private String menuIcon;
	private String menuTitle;
	private String webTitle;
	private String webDescript;
	private String webImage;
	//@Pattern(regexp="[a-zA-Z0-9-.]+" , message ="Dữ liệu nhập không hợp lệ")
	private String friendlyUrl;	
	@Range(min = -90, max = 90, message="{setting.n950.la}")
	private double la;
	@Range(min = -180, max = 180, message="{setting.n950.lng}")
	private double lng;
	public int getWebId() {
		return webId;
	}

	public void setWebId(int webId) {
		this.webId = webId;
	}

	public int getExtend() {
		return extend;
	}

	public void setExtend(int extend) {
		this.extend = extend;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getStnumbers() {
		return stnumbers;
	}

	public void setStnumbers(String stnumbers) {
		this.stnumbers = stnumbers;
	}

	public String getSublocality() {
		return sublocality;
	}

	public void setSublocality(String sublocality) {
		this.sublocality = sublocality;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public double getLa() {
		return la;
	}

	public void setLa(double la) {
		this.la = la;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
	
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getWebTitle() {
		return webTitle;
	}

	public void setWebTitle(String webTitle) {
		this.webTitle = webTitle;
	}

	public String getWebDescript() {
		return webDescript;
	}

	public void setWebDescript(String webDescript) {
		this.webDescript = webDescript;
	}

	public String getWebImage() {
		return webImage;
	}

	public void setWebImage(String webImage) {
		this.webImage = webImage;
	}

	public String getFriendlyUrl() {
		return friendlyUrl;
	}

	public void setFriendlyUrl(String friendlyUrl) {
		this.friendlyUrl = friendlyUrl;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "webId:" + webId + ",extend:" + extend + ",address:" + address
				+ ",email:" + email + ",phone:" + phone + ",route:" + route
				+ ",stnumbers:" + stnumbers + ",sublocality:" + sublocality
				+ ",locality:" + locality + ",postalcode:" + postalcode
				+ ",country:" + country+",la:"+la+",lng:"+lng
				+ ", menuIcon:"+menuIcon+",menuTitle:"+menuTitle;
	}
}
