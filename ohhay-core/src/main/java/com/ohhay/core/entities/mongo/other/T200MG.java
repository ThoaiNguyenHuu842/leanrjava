package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Apr 19, 2017
 * access token for piepme
 */
@Document(collection = QbMongoCollectionsName.T200)
public class T200MG implements Serializable{
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("TV201")
	private String tv201;
	@Field("TD208")
	private Date td208;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getTv201() {
		return tv201;
	}
	public void setTv201(String tv201) {
		this.tv201 = tv201;
	}
	public Date getTd208() {
		return td208;
	}
	public void setTd208(Date td208) {
		this.td208 = td208;
	}
	
}
