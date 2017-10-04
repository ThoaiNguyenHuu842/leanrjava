package com.ohhay.piepme.mongo.entities.other;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.piepme.mongo.nestedentities.M930Key;

/**
 * @author ThoaiNH
 * create Jan 16, 2017
 * piepme user history
 */
@Document(collection = QbMongoCollectionsName.M930)
public class M930PMG implements Serializable{
	@Id
	private int id;
	@Field("KEYS")
	private List<M930Key> keys;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<M930Key> getKeys() {
		return keys;
	}
	public void setKeys(List<M930Key> keys) {
		this.keys = keys;
	}
	@Override
	public String toString() {
		return "M930PMG [id=" + id + ", keys=" + keys + "]";
	}
	
}
