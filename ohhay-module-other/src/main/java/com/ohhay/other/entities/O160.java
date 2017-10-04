package com.ohhay.other.entities;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

public class O160 implements Serializable{
	@QbMapping(name = "ov161", typeMapping = Types.CHAR)
	private String ov161;
	@QbMapping(name = "fo100", typeMapping = Types.INTEGER)
	private int fo100;
	public String getOv161() {
		return ov161;
	}
	public void setOv161(String ov161) {
		this.ov161 = ov161;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	
}
