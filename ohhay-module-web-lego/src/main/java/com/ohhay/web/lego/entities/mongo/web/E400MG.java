package com.ohhay.web.lego.entities.mongo.web;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.web.lego.entities.mongo.base.web.OhhayLegoWebBase;

/**
 * @author ThoaiNH
 * create Oct 13, 2015
 */
@Document(collection = QbMongoCollectionsName.E400)
public class E400MG extends OhhayLegoWebBase implements Serializable{
	public E400MG() {
		// TODO Auto-generated constructor stub
	}
	public E400MG(E400MG e){
		super(e);
	}
	
	@Field("FA950S")
	List<Integer> fa950s;
	
	@Transient
	private int fm150;
	@Transient
	private int totalUser;
	@Transient
	private int roleAdmin;
	@Transient
	private int sizeRowss;
	@Transient
	private String nv100;
	@Transient
	private int totalSize;
	@Transient
	private int totalDomain;
	@Transient
	private int totalSection;
	@Transient
	private M900MG m900d; //save designer info
	@Transient
	private M900MG m900l; //save designer info last
	@Transient
	private String el446String;
	@Transient
	private String el448String;
	@Transient
	private String ev152;
	@Transient
	private String ev152l;
	@Transient
	private int fe150;
	@Transient
	private int fe150l;
	@Transient
	private int fe400d;
	@Transient
	private int fe400l;
	@Transient 
	private int totalDesignerHistory;
	
	public M900MG getM900l() {
		return m900l;
	}
	public void setM900l(M900MG m900l) {
		this.m900l = m900l;
	}
	public M900MG getM900d() {
		return m900d;
	}
	public void setM900d(M900MG m900d) {
		this.m900d = m900d;
	}
	public int getFm150() {
		return fm150;
	}

	public void setFm150(int fm150) {
		this.fm150 = fm150;
	}
	public int getTotalUser() {
		return totalUser;
	}
	public void setTotalUser(int totalUser) {
		this.totalUser = totalUser;
	}
	public int getRoleAdmin() {
		return roleAdmin;
	}
	public void setRoleAdmin(int roleAdmin) {
		this.roleAdmin = roleAdmin;
	}
	public int getSizeRowss() {
		return sizeRowss;
	}
	public void setSizeRowss(int sizeRowss) {
		this.sizeRowss = sizeRowss;
	}
	public String getNv100() {
		return nv100;
	}
	public void setNv100(String nv100) {
		this.nv100 = nv100;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalDomain() {
		return totalDomain;
	}
	public void setTotalDomain(int totalDomain) {
		this.totalDomain = totalDomain;
	}
	public int getTotalSection() {
		return totalSection;
	}
	public void setTotalSection(int totalSection) {
		this.totalSection = totalSection;
	}
	public String getEl446String() {
		return el446String;
	}
	public void setEl446String(String el446String) {
		this.el446String = el446String;
	}
	public String getEl448String() {
		return el448String;
	}
	public void setEl448String(String el448String) {
		this.el448String = el448String;
	}
	public String getEv152() {
		return ev152;
	}
	public void setEv152(String ev152) {
		this.ev152 = ev152;
	}
	public int getFe150() {
		return fe150;
	}
	public void setFe150(int fe150) {
		this.fe150 = fe150;
	}
	public int getFe400d() {
		return fe400d;
	}
	public void setFe400d(int fe400d) {
		this.fe400d = fe400d;
	}
	public int getTotalDesignerHistory() {
		return totalDesignerHistory;
	}
	public void setTotalDesignerHistory(int totalDesignerHistory) {
		this.totalDesignerHistory = totalDesignerHistory;
	}
	public String getEv152l() {
		return ev152l;
	}
	public void setEv152l(String ev152l) {
		this.ev152l = ev152l;
	}
	public int getFe150l() {
		return fe150l;
	}
	public void setFe150l(int fe150l) {
		this.fe150l = fe150l;
	}
	public int getFe400l() {
		return fe400l;
	}
	public void setFe400l(int fe400l) {
		this.fe400l = fe400l;
	}
	public List<Integer> getFa950s() {
		return fa950s;
	}
	public void setFa950s(List<Integer> fa950s) {
		this.fa950s = fa950s;
	}
	
	
}
