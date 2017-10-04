package com.ohhay.other.entities.mongo.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

public class U910MG implements Serializable{
	@Field("UV911")
	private String uv911;//domain
	@Field("UV912")
	private String uv912;//url path (/sdt hoac /sdt?webinaris)
	@Field("UN913")
	private int un913;//type web
	@Field("UN914")
	private int un914;//hien thi cac chuc nang cua ohhay hay khong (1:hien het, 0:tat het, 2: tat menu tren)
	public String getUv911() {
		return uv911;
	}
	public void setUv911(String uv911) {
		this.uv911 = uv911;
	}
	public String getUv912() {
		return uv912;
	}
	public void setUv912(String uv912) {
		this.uv912 = uv912;
	}
	public int getUn913() {
		return un913;
	}
	public void setUn913(int un913) {
		this.un913 = un913;
	}
	public int getUn914() {
		return un914;
	}
	public void setUn914(int un914) {
		this.un914 = un914;
	}	
	
}
