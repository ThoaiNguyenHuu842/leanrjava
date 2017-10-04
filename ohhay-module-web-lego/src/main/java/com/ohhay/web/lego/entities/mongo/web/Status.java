package com.ohhay.web.lego.entities.mongo.web;

import java.io.Serializable;

/**
 * @author TuNt
 * create date Nov 29, 2016
 * ohhay-module-web-lego
 */
public class Status implements Serializable{
	private String id;
	private int total;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Status [id=" + id + ", total=" + total + "]";
	} 
}
