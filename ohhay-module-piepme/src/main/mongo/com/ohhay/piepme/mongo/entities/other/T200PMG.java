package com.ohhay.piepme.mongo.entities.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Feb 17, 2017
 * token info used to login in web piepme
 */
@Document(collection = QbMongoCollectionsName.T200)
public class T200PMG implements Serializable{
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("TV201")
	private String tv201;//token
	@Field("TL206")
	private Date tl206;//update date
	@Field("TL208")
	private Date tl208;//created date
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
	public Date getTl206() {
		return tl206;
	}
	public void setTl206(Date tl206) {
		this.tl206 = tl206;
	}
	public Date getTl208() {
		return tl208;
	}
	public void setTl208(Date tl208) {
		this.tl208 = tl208;
	}
	@Override
	public String toString() {
		return "T200PMG [id=" + id + ", fo100=" + fo100 + ", tv201=" + tv201
				+ ", tl206=" + tl206 + ", tl208=" + tl208 + "]";
	}
}
