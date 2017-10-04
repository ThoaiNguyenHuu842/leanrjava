package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

public class KeyWord implements Serializable{
	@Field("KEYWORD")
	private String keyword;
	
	public KeyWord(String keyword) {
		super();
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
