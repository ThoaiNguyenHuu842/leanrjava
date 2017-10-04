package com.ohhay.core.entities.mongo.other;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

@Document(collection = QbMongoCollectionsName.O150)
public class O150MG {
	@Id
	private int id;
	@Field("OV151")
	private String ov151;
	@Field("FO100")
	private int fo100;
	@Field("OL158")
	private Date ol158;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOv151() {
		return ov151;
	}
	public void setOv151(String ov151) {
		this.ov151 = ov151;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public Date getOl158() {
		return ol158;
	}
	public void setOl158(Date ol158) {
		this.ol158 = ol158;
	}
	@Override
	public String toString() {
		return "O150MG [id=" + id + ", ov151=" + ov151 + ", fo100=" + fo100
				+ ", ol158=" + ol158 + "]";
	}
	
}
