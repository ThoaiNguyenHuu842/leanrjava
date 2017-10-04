package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Jun 27, 2016
 */
public class M970MG implements Serializable{
	@Field("MV971")
	private String mv971;//logo img url
	@Field("MV972")
	private String mv972;//logo style
	@Field("MV973")
	private String mv973;//title style
	public String getMv971() {
		return mv971;
	}
	public void setMv971(String mv971) {
		this.mv971 = mv971;
	}
	public String getMv972() {
		return mv972;
	}
	public void setMv972(String mv972) {
		this.mv972 = mv972;
	}
	public String getMv973() {
		return mv973;
	}
	public void setMv973(String mv973) {
		this.mv973 = mv973;
	}
	
}
