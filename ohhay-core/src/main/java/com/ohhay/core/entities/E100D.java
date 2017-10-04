package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
/**
 * @author ThoaiNH
 * create Mar 1, 2016
 */
public class E100D implements Serializable{
	@QbMapping(name = "ev101", typeMapping = Types.CHAR)
	private String ev101;
	@QbMapping(name = "ev102", typeMapping = Types.CHAR)
	private String ev102;
	@QbMapping(name = "ev103", typeMapping = Types.CHAR)
	private String ev103;
	@QbMapping(name = "en104", typeMapping = Types.INTEGER)
	private int en104;
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
	@QbMapping(name = "pe100", typeMapping = Types.INTEGER)
	private int pe100;
	@QbMapping(name = "ev151", typeMapping = Types.CHAR)
	private String ev151;
	public M900MG getProfile(){
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		M900MG m900mg = templateService.findDocumentById(fo100c, fo100c, M900MG.class);
		return m900mg;
		
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
	public int getEn104() {
		return en104;
	}
	public void setEn104(int en104) {
		this.en104 = en104;
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
	public int getPe100() {
		return pe100;
	}
	public void setPe100(int pe100) {
		this.pe100 = pe100;
	}
	public String getEv151() {
		return ev151;
	}
	public void setEv151(String ev151) {
		this.ev151 = ev151;
	}
	@Override
	public String toString() {
		return "E100D [ev101=" + ev101 + ", ev102=" + ev102 + ", ev103=" + ev103
				+ ", en104=" + en104 + ", en105=" + en105 + ", en106=" + en106
				+ ", ed143=" + ed143 + ", fo100c=" + fo100c + ", fo100d="
				+ fo100d + ", el147=" + el147 + ", ov102=" + ov102 + ", pe150="
				+ pe150 + ", pe100=" + pe100 + ", ev151=" + ev151 + "]";
	}
	
}
