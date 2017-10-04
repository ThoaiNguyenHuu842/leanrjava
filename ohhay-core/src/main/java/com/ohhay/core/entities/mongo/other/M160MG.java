package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create 24/04/2015
 * ROLE TOPIC
 *
 */
public class M160MG implements Serializable{
	@Field("MN201")
	private int mn201;
	@Field("MN202")
	private String mn202;
	public int getMn201() {
		return mn201;
	}
	public void setMn201(int mn201) {
		this.mn201 = mn201;
	}
	public String getMn202() {
		return mn202;
	}
	public void setMn202(String mn202) {
		this.mn202 = mn202;
	}
	
}
