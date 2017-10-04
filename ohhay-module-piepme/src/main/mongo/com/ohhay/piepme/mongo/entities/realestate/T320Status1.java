package com.ohhay.piepme.mongo.entities.realestate;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Jun 21, 2017
 */
@Document(collection = QbMongoCollectionsName.T320)
public class T320Status1 implements Serializable{
	@Id
	private int id;
	@Field("TV321")
	private String tv321;//label cho nguoi tao
	@Field("TV322")
	private String tv322;//label chua nguoi tim
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTv321() {
		return tv321;
	}
	public void setTv321(String tv321) {
		this.tv321 = tv321;
	}
	public String getTv322() {
		return tv322;
	}
	public void setTv322(String tv322) {
		this.tv322 = tv322;
	}
}
