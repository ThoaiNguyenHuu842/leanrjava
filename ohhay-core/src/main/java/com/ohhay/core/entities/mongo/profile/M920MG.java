package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;
/**
 * @author ThoaiNH
 * create 12/08/2014
 * item user 's job
 */
public class M920MG implements Serializable{
	@Field("VAL")
	private int val;
	@Field("LABEL")
	private String label;
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
	
	
}
