package com.ohhay.web.core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

public class C400 implements Serializable{
	@QbMapping(name = "pc400", typeMapping = Types.INTEGER)
	private int pc400;
	@QbMapping(name = "fo100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "fc800", typeMapping = Types.INTEGER)
	private int fc800;
	@QbMapping(name = "cv802", typeMapping = Types.CHAR)
	private String cv802;//link template
	@QbMapping(name = "cv401", typeMapping = Types.CHAR)
	private String cv401;
	@QbMapping(name = "cv402", typeMapping = Types.CHAR)
	private String cv402;
	@QbMapping(name = "cv403", typeMapping = Types.CHAR)
	private String cv403;
	@QbMapping(name = "cv404", typeMapping = Types.CHAR)
	private String cv404;
	@QbMapping(name = "cv405", typeMapping = Types.CHAR)
	private String cv405;
	@QbMapping(name = "cl426", typeMapping = Types.DATE)
	private Date cl426;
	@QbMapping(name = "cl427", typeMapping = Types.CHAR)
	private String cl427;
	@QbMapping(name = "cl428", typeMapping = Types.DATE)
	private Date cl428;
	@QbMapping(name = "cl429", typeMapping = Types.CHAR)
	private String cl429;
	public int getPc400() {
		return pc400;
	}
	public void setPc400(int pc400) {
		this.pc400 = pc400;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public int getFc800() {
		return fc800;
	}
	public void setFc800(int fc800) {
		this.fc800 = fc800;
	}
	public String getCv802() {
		return cv802;
	}
	public void setCv802(String cv802) {
		this.cv802 = cv802;
	}
	public String getCv401() {
		return cv401;
	}
	public void setCv401(String cv401) {
		this.cv401 = cv401;
	}
	public String getCv402() {
		return cv402;
	}
	public void setCv402(String cv402) {
		this.cv402 = cv402;
	}
	public String getCv403() {
		return cv403;
	}
	public void setCv403(String cv403) {
		this.cv403 = cv403;
	}
	public String getCv404() {
		return cv404;
	}
	public void setCv404(String cv404) {
		this.cv404 = cv404;
	}
	public String getCv405() {
		return cv405;
	}
	public void setCv405(String cv405) {
		this.cv405 = cv405;
	}
	public Date getCl426() {
		return cl426;
	}
	public void setCl426(Date cl426) {
		this.cl426 = cl426;
	}
	public String getCl427() {
		return cl427;
	}
	public void setCl427(String cl427) {
		this.cl427 = cl427;
	}
	public Date getCl428() {
		return cl428;
	}
	public void setCl428(Date cl428) {
		this.cl428 = cl428;
	}
	public String getCl429() {
		return cl429;
	}
	public void setCl429(String cl429) {
		this.cl429 = cl429;
	}
	
}
