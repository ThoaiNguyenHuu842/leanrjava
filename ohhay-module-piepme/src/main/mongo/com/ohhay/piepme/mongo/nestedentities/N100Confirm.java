package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Apr 11, 2017
 */
public class N100Confirm implements Serializable{
	@Field("_id")
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("STT")
	private String stt;
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
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	@Override
	public String toString() {
		return "N100Confirm [id=" + id + ", fo100=" + fo100 + ", stt=" + stt
				+ "]";
	}
	
}
