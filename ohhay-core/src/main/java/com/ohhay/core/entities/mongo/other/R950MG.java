package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;

/**
 * @author TuNt
 * create date Mar 15, 2017
 * ohhay-core
 */
public class R950MG implements Serializable{
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
		return "R950MG [id=" + id + ", total=" + total + "]";
	}
	
	
}
