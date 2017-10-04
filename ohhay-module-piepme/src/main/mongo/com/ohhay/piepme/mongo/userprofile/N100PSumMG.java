package com.ohhay.piepme.mongo.userprofile;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Jun 9, 2016
 */
@Document(collection = QbMongoCollectionsName.N100)
public class N100PSumMG implements Serializable{
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("NV111")
	private String nv111;//device token
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
	public String getNv111() {
		return nv111;
	}
	public void setNv111(String nv111) {
		this.nv111 = nv111;
	}
	@Override
	public String toString() {
		return "N100PSumMG [id=" + id + ", fo100=" + fo100 + ", nv111=" + nv111
				+ "]";
	}
	
}
