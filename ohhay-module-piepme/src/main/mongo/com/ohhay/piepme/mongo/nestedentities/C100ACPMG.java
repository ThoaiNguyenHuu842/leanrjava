package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

/**
 * @author TuNt
 * create date Jan 9, 2017
 * entity map recent activities
 */
public class C100ACPMG implements Serializable{
	private int id;
	private int fo100f;
	private String type;
	private String nickName;
	private String nv101;
	private String urlAvarta;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFo100f() {
		return fo100f;
	}
	public void setFo100f(int fo100f) {
		this.fo100f = fo100f;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNv101() {
		return nv101;
	}
	public void setNv101(String nv101) {
		this.nv101 = nv101;
	}
	
	public String getUrlAvarta() {
		return urlAvarta;
	}
	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}

	@Override
	public String toString() {
		return "C100ACPMG [id=" + id + ", fo100f=" + fo100f + ", type=" + type + ", nickName=" + nickName + ", nv101="
				+ nv101 + ", urlAvarta=" + urlAvarta + "]";
	}
}
