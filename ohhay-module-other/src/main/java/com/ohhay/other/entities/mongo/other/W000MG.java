package com.ohhay.other.entities.mongo.other;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.other.entities.W000;
/*
 * Danh sach url hien thi trang home
 */
@Document(collection = QbMongoCollectionsName.W000)
public class W000MG implements Serializable{
	public W000MG(W000 w000) {
		// TODO Auto-generated constructor stub
		this.id = w000.getFo100();
		this.wv001 = w000.getWv001();
		this.wv002 = w000.getWv002();
		this.wv003 = w000.getWv003();
		this.wv004 = w000.getWv004();
		this.wv005 = w000.getWv005();
		this.wv006 = w000.getWv006();
	}
	public W000MG() {
		// TODO Auto-generated constructor stub
	}
	@Id
	private long id;//po100
	@Field("wv001")
	private String wv001;//title
	@Field("wv002")
	private String wv002;//sdt
	@Field("wv003")
	private String wv003;//mail
	@Field("wv004")
	private String wv004;//quoc gia
	@Field("wv005")
	private String wv005;//quan huyen
	@Field("wv006")
	private String wv006;//ghi chu
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getWv001() {
		return wv001;
	}
	public void setWv001(String wv001) {
		this.wv001 = wv001;
	}
	public String getWv002() {
		return wv002;
	}
	public void setWv002(String wv002) {
		this.wv002 = wv002;
	}
	public String getWv003() {
		return wv003;
	}
	public void setWv003(String wv003) {
		this.wv003 = wv003;
	}
	public String getWv004() {
		return wv004;
	}
	public void setWv004(String wv004) {
		this.wv004 = wv004;
	}
	public String getWv005() {
		return wv005;
	}
	public void setWv005(String wv005) {
		this.wv005 = wv005;
	}
	public String getWv006() {
		return wv006;
	}
	public void setWv006(String wv006) {
		this.wv006 = wv006;
	}
	
}
