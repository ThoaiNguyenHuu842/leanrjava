package com.ohhay.web.core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

public class C900 implements Serializable{
	@QbMapping(name = "pc900", typeMapping = Types.INTEGER)
	private int pc900;
	@QbMapping(name = "fc850", typeMapping = Types.INTEGER)
	private int fc850;
	@QbMapping(name = "cv901", typeMapping = Types.CHAR)
	private String cv901;
	@QbMapping(name = "cv902", typeMapping = Types.CHAR)
	private String cv902;
	@QbMapping(name = "cv903", typeMapping = Types.CHAR)
	private String cv903;
	@QbMapping(name = "cv904", typeMapping = Types.CHAR)
	private String cv904;
	@QbMapping(name = "cv905", typeMapping = Types.CHAR)
	private String cv905;
	@QbMapping(name = "cl916", typeMapping = Types.DATE)
	private Date cl916;
	@QbMapping(name = "cl917", typeMapping = Types.CHAR)
	private String cl917;
	@QbMapping(name = "cl918", typeMapping = Types.DATE)
	private Date cl918;
	@QbMapping(name = "cl919", typeMapping = Types.CHAR)
	private String cl919;
	public int getPc900() {
		return pc900;
	}
	public void setPc900(int pc900) {
		this.pc900 = pc900;
	}
	public int getFc850() {
		return fc850;
	}
	public void setFc850(int fc850) {
		this.fc850 = fc850;
	}
	public String getCv901() {
		return cv901;
	}
	public void setCv901(String cv901) {
		this.cv901 = cv901;
	}
	public String getCv902() {
		return cv902;
	}
	public void setCv902(String cv902) {
		this.cv902 = cv902;
	}
	public String getCv903() {
		return cv903;
	}
	public void setCv903(String cv903) {
		this.cv903 = cv903;
	}
	public String getCv904() {
		return cv904;
	}
	public void setCv904(String cv904) {
		this.cv904 = cv904;
	}
	public String getCv905() {
		return cv905;
	}
	public void setCv905(String cv905) {
		this.cv905 = cv905;
	}
	public Date getCl916() {
		return cl916;
	}
	public void setCl916(Date cl916) {
		this.cl916 = cl916;
	}
	public String getCl917() {
		return cl917;
	}
	public void setCl917(String cl917) {
		this.cl917 = cl917;
	}
	public Date getCl918() {
		return cl918;
	}
	public void setCl918(Date cl918) {
		this.cl918 = cl918;
	}
	public String getCl919() {
		return cl919;
	}
	public void setCl919(String cl919) {
		this.cl919 = cl919;
	}
	
}
