package com.ohhay.other.entities.mongo.report;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

@Document(collection = QbMongoCollectionsName.R100)
public class R100MG implements Serializable{
	@Id
	private int id;//po100
	@Field("RA101")
	private List<R110MG> listR110mgs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<R110MG> getListR110mgs() {
		return listR110mgs;
	}
	public void setListR110mgs(List<R110MG> listR110mgs) {
		this.listR110mgs = listR110mgs;
	}
	
}
