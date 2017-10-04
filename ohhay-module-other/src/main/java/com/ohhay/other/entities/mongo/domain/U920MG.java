package com.ohhay.other.entities.mongo.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Dec 30, 2015
 */
@Document(collection = QbMongoCollectionsName.U920)
public class U920MG implements Serializable{

	@Id
	private long id;
	@Field("FO100")
	private int fo100;
	@Field("UV921")
	private String uv921;//domain name
	@Field("UV922")
	private String uv922;//verification code
	@Field("UN923")
	private int un923;//verified status: 1 is Verified, other is Waiting
	@Field("UD928")
	private Date ud928;
	@Field("FE400")
	private int fe400;
	@Transient
	private String nv100;
	@Transient 
	private String mv903;
	@Transient
	private String hoten;
	@Transient
	private int rowss;
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	public String getNv100() {
		return nv100;
	}
	public void setNv100(String nv100) {
		this.nv100 = nv100;
	}
	public String getMv903() {
		return mv903;
	}
	public void setMv903(String mv903) {
		this.mv903 = mv903;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getUv921() {
		return uv921;
	}
	public void setUv921(String uv921) {
		this.uv921 = uv921;
	}
	public int getFe400() {
		return fe400;
	}
	public void setFe400(int fe400) {
		this.fe400 = fe400;
	}
	public String getUv922() {
		return uv922;
	}
	public void setUv922(String uv922) {
		this.uv922 = uv922;
	}
	public int getUn923() {
		return un923;
	}
	public void setUn923(int un923) {
		this.un923 = un923;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public Date getUd928() {
		return ud928;
	}
	public void setUd928(Date ud928) {
		this.ud928 = ud928;
	}
	@Override
	public String toString() {
		return "U920MG [id=" + id + ", fo100=" + fo100 + ", uv921=" + uv921
				+ ", uv922=" + uv922 + ", un923=" + un923 + ", ud928=" + ud928
				+ ", fe400=" + fe400 + ", nv100=" + nv100 + ", mv903=" + mv903
				+ ", hoten=" + hoten + ", rowss=" + rowss + "]";
	}
	
}
