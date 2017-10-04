package com.ohhay.other.entities.mongo.domain.topic;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

public class U810MG implements Serializable{
	@Field("UV811")
	private String uv811;//domain
	@Field("UV812")
	private String uv812;//url path (/sdt hoac /sdt?webinaris)
	@Field("UN813")
	private int un813;//type web
	@Field("UN814")
	private int un814;//hien thi cac chuc nang cua ohhay hay khong (1:hien het, 0:tat het, 2: tat menu tren)
	public String getUv811() {
		return uv811;
	}
	public void setUv811(String uv811) {
		this.uv811 = uv811;
	}
	public String getUv812() {
		return uv812;
	}
	public void setUv812(String uv812) {
		this.uv812 = uv812;
	}
	public int getUn813() {
		return un813;
	}
	public void setUn813(int un813) {
		this.un813 = un813;
	}
	public int getUn814() {
		return un814;
	}
	public void setUn814(int un814) {
		this.un814 = un814;
	}	
	
}
