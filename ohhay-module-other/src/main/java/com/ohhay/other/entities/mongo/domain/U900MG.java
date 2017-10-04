package com.ohhay.other.entities.mongo.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
/*
 * danh sach domain cua user
 */
@Document(collection = QbMongoCollectionsName.U900)
public class U900MG implements Serializable{
	@Id
	private int id;//po100
	@Field("UA901")
	private List<U910MG> listU910mgs;//danh sach domain
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<U910MG> getListU910mgs() {
		return listU910mgs;
	}
	public void setListU910mgs(List<U910MG> listU910mgs) {
		this.listU910mgs = listU910mgs;
	}
	
}
