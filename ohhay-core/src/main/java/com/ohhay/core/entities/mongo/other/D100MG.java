package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Aug 18, 2016
 * BONEVO - user's documents file
 */
@Document(collection = QbMongoCollectionsName.D100)
public class D100MG implements Serializable{
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("DV101")
	private String dv101;//file name 
	@Field("DN102")
	private long dn102;//file size
	@Field("DV103")
	private String dv103;//file url
	@Field("DD106")
	private Date dd106;//date updated
	@Field("DD108")
	private Date dd108;//date created
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDv103() {
		return dv103;
	}
	public void setDv103(String dv103) {
		this.dv103 = dv103;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getDv101() {
		return dv101;
	}
	public void setDv101(String dv101) {
		this.dv101 = dv101;
	}
	public long getDn102() {
		return dn102;
	}
	public void setDn102(long dn102) {
		this.dn102 = dn102;
	}
	public Date getDd106() {
		return dd106;
	}
	public void setDd106(Date dd106) {
		this.dd106 = dd106;
	}
	public Date getDd108() {
		return dd108;
	}
	public void setDd108(Date dd108) {
		this.dd108 = dd108;
	}
	@Override
	public String toString() {
		return "D100MG [fo100=" + fo100 + ", dv101=" + dv101 + ", dn102="
				+ dn102 + ", dd106=" + dd106 + ", dd108=" + dd108 + "]";
	}
	
}
