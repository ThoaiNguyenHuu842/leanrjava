package com.ohhay.web.lego.entities.mongo.web;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Dec 3, 2016
 */
public class E160MG implements Serializable{
	@Field("FE200")
	private int fe200;//version site is rejected
	@Field("FE400")
	private long fe400;//id site is rejected
	@Field("EV161")
	private String ev161;//reason reject
	@Field("EL168")
	private Date el168; //date reject
	public int getFe200() {
		return fe200;
	}
	public void setFe200(int fe200) {
		this.fe200 = fe200;
	}
	public String getEv161() {
		return ev161;
	}
	public void setEv161(String ev161) {
		this.ev161 = ev161;
	}
	public Date getEl168() {
		return el168;
	}
	public void setEl168(Date el168) {
		this.el168 = el168;
	}
	public long getFe400() {
		return fe400;
	}
	public void setFe400(long fe400) {
		this.fe400 = fe400;
	}
	@Override
	public String toString() {
		return "E160MG [fe200=" + fe200 + ", fe400=" + fe400 + ", ev161="
				+ ev161 + ", el168=" + el168 + "]";
	}
}
