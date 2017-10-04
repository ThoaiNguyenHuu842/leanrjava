package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;
/*
 * ngon ngu
 */
public class LanguageMG implements Serializable{
	@Transient
	private String type;
	@Field("CODE")
	private String code;
	@Field("TEXT")
	private String text;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
