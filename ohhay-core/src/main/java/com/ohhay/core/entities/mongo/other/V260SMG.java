package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

public class V260SMG implements Serializable{
	@Field("MV651")
	private String mv651;

	public String getMv651() {
		return mv651;
	}

	public void setMv651(String mv651) {
		this.mv651 = mv651;
	}
	
}
