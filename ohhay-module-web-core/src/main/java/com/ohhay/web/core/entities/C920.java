package com.ohhay.web.core.entities;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

public class C920 implements Serializable {
	@QbMapping(name = "pc920", typeMapping = Types.INTEGER)
	private int pc920;
	@QbMapping(name = "fc400", typeMapping = Types.INTEGER)
	private int fc400;
	@QbMapping(name = "cv921", typeMapping = Types.CHAR)
	private String cv921;
	@QbMapping(name = "cv922", typeMapping = Types.CHAR)
	private String cv922;
	@QbMapping(name = "cv923", typeMapping = Types.CHAR)
	private String cv923;
	@QbMapping(name = "cl937", typeMapping = Types.CHAR)
	private String cl937;

	@QbMapping(name = "cl939", typeMapping = Types.CHAR)
	private String cl939;
	@QbMapping(name = "fc820", typeMapping = Types.INTEGER)
	private int fc820;
	@QbMapping(name = "cv823", typeMapping = Types.CHAR)
	private String cv823;
	@QbMapping(name = "cv802", typeMapping = Types.CHAR)
	private String cv802;
	@QbMapping(name = "cn824", typeMapping = Types.INTEGER)
	private int cn824;

	public int getPc920() {
		return pc920;
	}

	public void setPc920(int pc920) {
		this.pc920 = pc920;
	}

	public int getFc400() {
		return fc400;
	}

	public void setFc400(int fc400) {
		this.fc400 = fc400;
	}

	public String getCv921() {
		return cv921;
	}

	public void setCv921(String cv921) {
		this.cv921 = cv921;
	}

	public String getCv922() {
		return cv922;
	}

	public void setCv922(String cv922) {
		this.cv922 = cv922;
	}

	public String getCv923() {
		return cv923;
	}

	public void setCv923(String cv923) {
		this.cv923 = cv923;
	}

	public String getCl937() {
		return cl937;
	}

	public void setCl937(String cl937) {
		this.cl937 = cl937;
	}

	public String getCl939() {
		return cl939;
	}

	public void setCl939(String cl939) {
		this.cl939 = cl939;
	}

	public int getFc820() {
		return fc820;
	}

	public void setFc820(int fc820) {
		this.fc820 = fc820;
	}

	public String getCv823() {
		return cv823;
	}

	public void setCv823(String cv823) {
		this.cv823 = cv823;
	}

	public int getCn824() {
		return cn824;
	}

	public void setCn824(int cn824) {
		this.cn824 = cn824;
	}

	public String getCv802() {
		return cv802;
	}

	public void setCv802(String cv802) {
		this.cv802 = cv802;
	}
	
}
