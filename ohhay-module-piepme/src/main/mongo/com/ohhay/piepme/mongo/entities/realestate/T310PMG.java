package com.ohhay.piepme.mongo.entities.realestate;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Jun 21, 2017
 */
@Document(collection = QbMongoCollectionsName.T310)
public class T310PMG implements Serializable{
	@Id
	private int id;
	@Field("TV311")
	private String tv311;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTv311() {
		return tv311;
	}
	public void setTv311(String tv311) {
		this.tv311 = tv311;
	}
	@Override
	public String toString() {
		return "T310PMG [id=" + id + ", tv311=" + tv311 + "]";
	}
}
