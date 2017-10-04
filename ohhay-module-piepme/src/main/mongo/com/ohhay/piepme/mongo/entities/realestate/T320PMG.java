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
public class T320PMG implements Serializable{
	@Id
	private int id;
	@Field("TV321")
	private String tv321;//label cho nguoi tao pieper
	@Field("TV322")
	private String tv322;//label chua nguoi tim pieper
	@Field("FT310S")
	private List<Integer> ft310s;
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
	public List<Integer> getFt310s() {
		return ft310s;
	}
	public void setFt310s(List<Integer> ft310s) {
		this.ft310s = ft310s;
	}
	public String getTv322() {
		return tv322;
	}
	public void setTv322(String tv322) {
		this.tv322 = tv322;
	}
	@Override
	public String toString() {
		return "T320PMG [id=" + id + ", tv321=" + tv321 + ", tv322=" + tv322
				+ ", ft310s=" + ft310s + "]";
	}
}
