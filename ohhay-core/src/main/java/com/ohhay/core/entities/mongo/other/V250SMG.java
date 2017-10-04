package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * product shop ohhay
 */
@Document(collection = QbMongoCollectionsName.V250S)
public class V250SMG implements Serializable{
	@Id
	private int id;
	@Field("VV256")
	private String vv256;
	@Field("VV258")
	private String vv258;
	@Field("IMAGES")
	private java.util.List<V260SMG> listImages;
	@Field("PO100")
	private int po100;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVv256() {
		return vv256;
	}
	public void setVv256(String vv256) {
		this.vv256 = vv256;
	}
	public String getVv258() {
		return vv258;
	}
	public void setVv258(String vv258) {
		this.vv258 = vv258;
	}
	public java.util.List<V260SMG> getListImages() {
		return listImages;
	}
	public void setListImages(java.util.List<V260SMG> listImages) {
		this.listImages = listImages;
	}
	public int getPo100() {
		return po100;
	}
	public void setPo100(int po100) {
		this.po100 = po100;
	}
	
}
