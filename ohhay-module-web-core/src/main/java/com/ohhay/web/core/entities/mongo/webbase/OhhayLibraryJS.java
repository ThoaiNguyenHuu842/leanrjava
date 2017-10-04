package com.ohhay.web.core.entities.mongo.webbase;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

public class OhhayLibraryJS implements Serializable{
	@Field("JS_ID")
	int id;
	public OhhayLibraryJS(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id;
	}
}
