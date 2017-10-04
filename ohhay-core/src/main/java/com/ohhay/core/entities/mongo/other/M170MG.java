package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create 15/07/2015
 * image of topic
 */
public class M170MG  implements Serializable{
	@Field("_id")
	private int id;
	@Field("IMG")
	private String img;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
