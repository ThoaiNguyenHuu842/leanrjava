package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Dec 8, 2016
 */
public class Otag implements Serializable{
	@Field("KEY")
	private String key;
	@Field("KEY_STEM")
	private String keyStem;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKeyStem() {
		return keyStem;
	}
	public void setKeyStem(String keyStem) {
		this.keyStem = keyStem;
	}
	
}
