package com.ohhay.piepme.mongo.entities.pieper;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * MONGODB COLLECTION - Commercial PIEPER
 * @author ThoaiNH
 * create Mar 13, 2017
 * 
 */
@Document(collection = QbMongoCollectionsName.P300B)
public class P300BRePMG extends P300BPMG{
	@Transient
	private int ft330;
	@Transient
	private int ft310;
	@Transient
	private int ft320;
	//tv311, tv321, tv333
	@Transient
	private String tv311;
	@Transient
	private String tv321;
	@Transient
	private String tv333;
	@Transient
	private String kv105;//pieper owner is DN or CA
	public int getFt330() {
		return ft330;
	}
	public void setFt330(int ft330) {
		this.ft330 = ft330;
	}
	public int getFt310() {
		return ft310;
	}
	public void setFt310(int ft310) {
		this.ft310 = ft310;
	}
	public int getFt320() {
		return ft320;
	}
	public void setFt320(int ft320) {
		this.ft320 = ft320;
	}
	public String getTv311() {
		return tv311;
	}
	public void setTv311(String tv311) {
		this.tv311 = tv311;
	}
	public String getTv321() {
		return tv321;
	}
	public void setTv321(String tv321) {
		this.tv321 = tv321;
	}
	public String getTv333() {
		return tv333;
	}
	public void setTv333(String tv333) {
		this.tv333 = tv333;
	}
	public String getKv105() {
		return kv105;
	}
	public void setKv105(String kv105) {
		this.kv105 = kv105;
	}
	
}
