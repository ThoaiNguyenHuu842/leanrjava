package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Jan 16, 2017
 */
public class M930Key implements Serializable{
	@Field("KEY")
	private String key;
	@Field("STT")
	private int stt;
	@Field("DATE_VIEW")
	private Date dateView;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public Date getDateView() {
		return dateView;
	}
	public void setDateView(Date dateView) {
		this.dateView = dateView;
	}
	@Override
	public String toString() {
		return "M930Key [key=" + key + ", stt=" + stt + ", dateView=" + dateView
				+ "]";
	}
	
}
