package com.ohhay.other.entities.mongo.report;

import org.springframework.data.mongodb.core.mapping.Field;

public class R120MG {
	@Field("COLNO")
	private int colno;//report type
	@Field("TOTAL")
	private int total;//total
	public R120MG() {
		// TODO Auto-generated constructor stub
	}
	public R120MG(int colno, int total) {
		super();
		this.colno = colno;
		this.total = total;
	}
	public int getColno() {
		return colno;
	}
	public void setColno(int colno) {
		this.colno = colno;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
