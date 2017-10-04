package com.ohhay.web.lego.entities.mongo.base.web;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Oct 31, 2015
 */
public class BgWebBase implements Serializable{
	@Field("TYPE")
	private String type;
	@Field("DATA")
	private String data;
	public BgWebBase() {
		// TODO Auto-generated constructor stub
	}
	public BgWebBase(BgWebBase bg) {
		super();
		if(bg != null){
			this.type = bg.getType();
			this.data = bg.getData();
		}
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
