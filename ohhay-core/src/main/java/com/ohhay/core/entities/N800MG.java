package com.ohhay.core.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

public class N800MG implements Serializable{
	
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("FO100N")
	private int fo100n;
	@Field("NV801")
	private String nv801;
	@Field("NV802")
	private int nn802;
	@Field("NV803")
	private String nv803;
	@Field("NV804")
	private String nv804;
	@Field("NV806")
	private Date nd806;
	@Transient
	private String hoTen;
	@Transient
	private String avarta;
	@Transient
	private String secondLastAction;
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
	public int getFo100n() {
		return fo100n;
	}
	public void setFo100n(int fo100n) {
		this.fo100n = fo100n;
	}
	public String getNv801() {
		return nv801;
	}
	public void setNv801(String nv801) {
		this.nv801 = nv801;
	}
	public int getNn802() {
		return nn802;
	}
	public void setNn802(int nn802) {
		this.nn802 = nn802;
	}
	public String getNv803() {
		return nv803;
	}
	public String getNv804() {
		return nv804;
	}
	public void setNv804(String nv804) {
		this.nv804 = nv804;
	}
	public void setNv803(String nv803) {
		this.nv803 = nv803;
	}
	public Date getNd806() {
		return nd806;
	}
	public void setNd806(Date nd806) {
		this.nd806 = nd806;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getAvarta() {
		return avarta;
	}
	public void setAvarta(String avarta) {
		this.avarta = avarta;
	}

	public String getSecondLastAction() {
		return secondLastAction;
	}
	public void setSecondLastAction(String secondLastAction) {
		this.secondLastAction = secondLastAction;
	}
	@Override
	public String toString() {
		return "N800MG [id=" + id + ", fo100=" + fo100 + ", fo100n=" + fo100n + ", nv801=" + nv801 + ", nn802=" + nn802
				+ ", nv803=" + nv803 + ", nv804=" + nv804 + ", nd806=" + nd806 + ", hoTen=" + hoTen + ", avarta="
				+ avarta + ", secondLastAction=" + secondLastAction + "]";
	}
}
