package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;
import java.text.DateFormat;

import com.ohhay.base.dao.QbMapping;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * create Mar 1, 2016
 */
public class E150D implements Serializable{
	@QbMapping(name = "ev101", typeMapping = Types.CHAR)
	private String ev101;
	@QbMapping(name = "ev102", typeMapping = Types.CHAR)
	private String ev102;
	@QbMapping(name = "ev103", typeMapping = Types.CHAR)
	private String ev103;
	@QbMapping(name = "en105", typeMapping = Types.INTEGER)
	private int en105;
	@QbMapping(name = "en106", typeMapping = Types.INTEGER)
	private int en106;
	@QbMapping(name = "ed143", typeMapping = Types.DATE)
	private Date ed143;
	@QbMapping(name = "fo100c", typeMapping = Types.INTEGER)
	private int fo100c;
	@QbMapping(name = "fo100d", typeMapping = Types.INTEGER)
	private int fo100d;
	@QbMapping(name = "el147", typeMapping = Types.CHAR)
	private String el147;
	@QbMapping(name = "ov102", typeMapping = Types.CHAR)
	private String ov102;
	@QbMapping(name = "pe150", typeMapping = Types.INTEGER)
	private int pe150;
	@QbMapping(name = "fe100", typeMapping = Types.INTEGER)
	private int fe100;
	@QbMapping(name = "ev151", typeMapping = Types.CHAR)
	private String ev151;//web id
	@QbMapping(name = "ev155", typeMapping = Types.CHAR)
	private String ev155;//backup confirmed id
	@QbMapping(name = "STATU", typeMapping = Types.CHAR)
	private String statu;
	@QbMapping(name = "ED162", typeMapping = Types.DATE)
	private Date ed162;//ngay apply vao trang goc
	public M900MG getProfile(){
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		M900MG m900mg = templateService.findDocumentById(fo100d, fo100d, M900MG.class);
		return m900mg;
		
	}
	public String getEd162String() {
		String ed162String = "";
		DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (ed162 != null)
			ed162String = dateFormat.format(ed162);
		return ed162String;
	}
	public String getEv101() {
		return ev101;
	}
	public void setEv101(String ev101) {
		this.ev101 = ev101;
	}
	public String getEv102() {
		return ev102;
	}
	public void setEv102(String ev102) {
		this.ev102 = ev102;
	}
	public String getEv103() {
		return ev103;
	}
	public void setEv103(String ev103) {
		this.ev103 = ev103;
	}
	public int getEn105() {
		return en105;
	}
	public void setEn105(int en105) {
		this.en105 = en105;
	}
	public int getEn106() {
		return en106;
	}
	public void setEn106(int en106) {
		this.en106 = en106;
	}
	public Date getEd143() {
		return ed143;
	}
	public void setEd143(Date ed143) {
		this.ed143 = ed143;
	}
	public int getFo100c() {
		return fo100c;
	}
	public void setFo100c(int fo100c) {
		this.fo100c = fo100c;
	}
	public int getFo100d() {
		return fo100d;
	}
	public void setFo100d(int fo100d) {
		this.fo100d = fo100d;
	}
	public String getEl147() {
		return el147;
	}
	public void setEl147(String el147) {
		this.el147 = el147;
	}
	public String getOv102() {
		return ov102;
	}
	public void setOv102(String ov102) {
		this.ov102 = ov102;
	}
	public int getPe150() {
		return pe150;
	}
	public void setPe150(int pe150) {
		this.pe150 = pe150;
	}
	public int getFe100() {
		return fe100;
	}
	public void setFe100(int fe100) {
		this.fe100 = fe100;
	}
	public String getEv151() {
		return ev151;
	}
	public void setEv151(String ev151) {
		this.ev151 = ev151;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public Date getEd162() {
		return ed162;
	}
	public void setEd162(Date ed162) {
		this.ed162 = ed162;
	}
	public String getEv155() {
		return ev155;
	}
	public void setEv155(String ev155) {
		this.ev155 = ev155;
	}
	@Override
	public String toString() {
		return "E150D [ev101=" + ev101 + ", ev102=" + ev102 + ", ev103=" + ev103
				+ ", en105=" + en105 + ", en106=" + en106 + ", ed143=" + ed143
				+ ", fo100c=" + fo100c + ", fo100d=" + fo100d + ", el147="
				+ el147 + ", ov102=" + ov102 + ", pe150=" + pe150 + ", fe100="
				+ fe100 + ", ev151=" + ev151 + ", ev155=" + ev155 + ", statu="
				+ statu + ", ed162=" + ed162 + "]";
	}
	
}
