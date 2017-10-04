package com.ohhay.other.entities;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

@SuppressWarnings("serial")
public class ItemTabN750 implements Serializable {
	@QbMapping(name = "VAL", typeMapping = Types.INTEGER)
	private int val; // id
	@QbMapping(name = "LABEL", typeMapping = Types.CHAR)
	private String label;// phone number code
	@QbMapping(name = "LABEL1", typeMapping = Types.CHAR)
	private String label1;// ten quoc gia
	@QbMapping(name = "LABEL2", typeMapping = Types.CHAR)
	private String label2;// ma quoc gia
	@QbMapping(name = "LABEL3", typeMapping = Types.CHAR)
	private String label3;// ten ngon ngu
	@QbMapping(name = "LABEL4", typeMapping = Types.CHAR)
	private String label4;// ma ngon ngu
	@QbMapping(name = "LABEL5", typeMapping = Types.CHAR)
	private String label5;// chau luc
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel1() {
		return label1;
	}
	public void setLabel1(String label1) {
		this.label1 = label1;
	}
	public String getLabel2() {
		return label2;
	}
	public void setLabel2(String label2) {
		this.label2 = label2;
	}
	public String getLabel3() {
		return label3;
	}
	public void setLabel3(String label3) {
		this.label3 = label3;
	}
	public String getLabel4() {
		return label4;
	}
	public void setLabel4(String label4) {
		this.label4 = label4;
	}
	public String getLabel5() {
		return label5;
	}
	public void setLabel5(String label5) {
		this.label5 = label5;
	}
	
	
}
