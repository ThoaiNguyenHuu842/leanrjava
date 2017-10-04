package com.ohhay.other.entities;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

public class O200 implements Serializable{
	@QbMapping(name = "ov201", typeMapping = Types.CHAR)
	private String ov201;
	@QbMapping(name = "ov202", typeMapping = Types.CHAR)
	private String ov202;
	@QbMapping(name = "ov205", typeMapping = Types.CHAR)
	private String ov205;
	@QbMapping(name = "po200", typeMapping = Types.INTEGER)
	private int po200;
	@QbMapping(name = "fo100", typeMapping = Types.INTEGER)
	private int fo100;
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getOv201() {
		return ov201;
	}
	public void setOv201(String ov201) {
		this.ov201 = ov201;
	}
	public String getOv202() {
		return ov202;
	}
	public void setOv202(String ov202) {
		this.ov202 = ov202;
	}
	public int getPo200() {
		return po200;
	}
	public void setPo200(int po200) {
		this.po200 = po200;
	}
	public String getOv205() {
		return ov205;
	}
	public void setOv205(String ov205) {
		this.ov205 = ov205;
	}
	
	public int getSourceDomainRedirect() {
		if (this.ov205.equals("B")) {
			return 1;
		} else if (this.ov205.equals("T")){
			return 2;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "O200 [ov201=" + ov201 + ", ov202=" + ov202 + ", po200=" + po200 + ", fo100=" + fo100 + "]";
	}
	
	
}
