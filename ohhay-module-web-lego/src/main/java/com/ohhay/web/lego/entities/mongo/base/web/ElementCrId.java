package com.ohhay.web.lego.entities.mongo.base.web;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Nov 24, 2015
 * web element current ID
 */
public class ElementCrId implements Serializable{
	@Field ("C_FORMID")
	private long cFormId;//checkbox id
	@Field ("R_FORMID")
	private long rFormId;//radio id
	public long getcFormId() {
		return cFormId;
	}
	public void setcFormId(long cFormId) {
		this.cFormId = cFormId;
	}
	public long getrFormId() {
		return rFormId;
	}
	public void setrFormId(long rFormId) {
		this.rFormId = rFormId;
	}
	
}
