package com.ohhay.web.lego.entities.mongo.web;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Nov 17, 2016
 * designer backup id
 */
@Document(collection = QbMongoCollectionsName.E200)
public class E200MG implements Serializable{
	@Id
	private int id;
	@Field("FO100")
	private int fo100;//designer id
	@Field("FE150")
	private int fe150;
	@Field("FE400")
	private long fe400;//web id
	@Field("EV201")
	private String ev201;//status: NEW, SUB
	@Field("EV202")
	private String ev202;//note
	@Field("EL206")
	private Date el206;//update date
	@Field("EL208")
	private Date el208;//created date
	@Field("EL209")
	private Date el209;//submitted date
	@Transient
	private String el208String;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public int getFe150() {
		return fe150;
	}
	public void setFe150(int fe150) {
		this.fe150 = fe150;
	}
	public long getFe400() {
		return fe400;
	}
	public void setFe400(long fe400) {
		this.fe400 = fe400;
	}
	public String getEv201() {
		return ev201;
	}
	public void setEv201(String ev201) {
		this.ev201 = ev201;
	}
	public String getEv202() {
		return ev202;
	}
	public void setEv202(String ev202) {
		this.ev202 = ev202;
	}
	public Date getEl206() {
		return el206;
	}
	public void setEl206(Date el206) {
		this.el206 = el206;
	}
	public Date getEl208() {
		return el208;
	}
	public void setEl208(Date el208) {
		this.el208 = el208;
	}
	public Date getEl209() {
		return el209;
	}
	public void setEl209(Date el209) {
		this.el209 = el209;
	}
	public String getEl208String() {
		return el208String;
	}
	public void setEl208String(String el208String) {
		this.el208String = el208String;
	}
	@Override
	public String toString() {
		return "E200MG [id=" + id + ", fo100=" + fo100 + ", fe150=" + fe150 + ", fe400=" + fe400 + ", ev201=" + ev201
				+ ", ev202=" + ev202 + ", el206=" + el206 + ", el208=" + el208 + ", el209=" + el209 + ", el208String="
				+ el208String + "]";
	}
}
