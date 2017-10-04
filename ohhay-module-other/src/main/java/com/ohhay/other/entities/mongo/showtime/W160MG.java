package com.ohhay.other.entities.mongo.showtime;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

public class W160MG implements Serializable{
	@Field("WV161")
	private String wv161;//url
	@Field("WV162")
	private String wv162;//phut
	@Field("WV163")
	private String wv163;//gio
	@Field("WV164")
	private String wv164;//ngay
	@Field("WV165")
	private String wv165;//thang
	@Field("WV166")
	private String wv166;//nam
	public String getWv161() {
		return wv161;
	}
	public void setWv161(String wv161) {
		this.wv161 = wv161;
	}
	public String getWv162() {
		return wv162;
	}
	public void setWv162(String wv162) {
		this.wv162 = wv162;
	}
	public String getWv163() {
		return wv163;
	}
	public void setWv163(String wv163) {
		this.wv163 = wv163;
	}
	public String getWv164() {
		return wv164;
	}
	public void setWv164(String wv164) {
		this.wv164 = wv164;
	}
	public String getWv165() {
		return wv165;
	}
	public void setWv165(String wv165) {
		this.wv165 = wv165;
	}
	public String getWv166() {
		return wv166;
	}
	public void setWv166(String wv166) {
		this.wv166 = wv166;
	}
	
}	
