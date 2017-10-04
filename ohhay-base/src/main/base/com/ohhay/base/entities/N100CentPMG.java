package com.ohhay.base.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Dec 27, 2016
 * user info stored in DB center for each area
 */
@Document(collection = "N100")
public class N100CentPMG implements Serializable{
	@Id
	private int id;
	@Field("NV102")
	private String nv102;//key
	@Field("NV106")
	private String nv106;//nick name
	@Field("NV107")
	private String nv107;//secret number (three digits)
	@Field("NL166")
	private Date nl166;
	@Field("NL168")
	private Date nl168;
	@Field("FO100")
	private int fo100;
	@Field("NV121")
	private String nv121;//URI mongoDB encrypted
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNv102() {
		return nv102;
	}
	public void setNv102(String nv102) {
		this.nv102 = nv102;
	}
	public String getNv106() {
		return nv106;
	}
	public void setNv106(String nv106) {
		this.nv106 = nv106;
	}
	public String getNv107() {
		return nv107;
	}
	public void setNv107(String nv107) {
		this.nv107 = nv107;
	}
	public Date getNl166() {
		return nl166;
	}
	public void setNl166(Date nl166) {
		this.nl166 = nl166;
	}
	public Date getNl168() {
		return nl168;
	}
	public void setNl168(Date nl168) {
		this.nl168 = nl168;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getNv121() {
		return nv121;
	}
	public void setNv121(String nv121) {
		this.nv121 = nv121;
	}
	@Override
	public String toString() {
		return "N100CentPMG [id=" + id + ", nv102=" + nv102 + ", nv106=" + nv106
				+ ", nv107=" + nv107 + ", nl166=" + nl166 + ", nl168=" + nl168
				+ ", fo100=" + fo100 + ", nv121=" + nv121 + "]";
	}
	
}
