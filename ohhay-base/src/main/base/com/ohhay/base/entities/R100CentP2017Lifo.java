package com.ohhay.base.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author TuNt
 * create date May 22, 2017
 * ohhay-base
 */
public class R100CentP2017Lifo implements Serializable {
	@QbMapping(name = "YY", typeMapping = Types.BIGINT)
	private BigDecimal yy;
	@QbMapping(name = "HH", typeMapping = Types.INTEGER)
	private int hh;
	@QbMapping(name = "DD", typeMapping = Types.INTEGER)
	private int dd;
	@QbMapping(name = "MM", typeMapping = Types.INTEGER)
	private int mm;
	@QbMapping(name = "YYYY", typeMapping = Types.INTEGER)
	private int yyyy;
	@QbMapping(name = "RV101", typeMapping = Types.CHAR)
	private String rv101;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	public BigDecimal getYy() {
		return yy;
	}
	public void setYy(BigDecimal yy) {
		this.yy = yy;
	}
	public int getHh() {
		return hh;
	}
	public void setHh(int hh) {
		this.hh = hh;
	}
	public int getDd() {
		return dd;
	}
	public void setDd(int dd) {
		this.dd = dd;
	}
	public int getMm() {
		return mm;
	}
	public void setMm(int mm) {
		this.mm = mm;
	}
	public int getYyyy() {
		return yyyy;
	}
	public void setYyyy(int yyyy) {
		this.yyyy = yyyy;
	}
	public String getRv101() {
		return rv101;
	}
	public void setRv101(String rv101) {
		this.rv101 = rv101;
	}
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	@Override
	public String toString() {
		return "R100CentP2017Revi [yy=" + yy + ", hh=" + hh + ", dd=" + dd + ", mm=" + mm + ", yyyy=" + yyyy + ", rv101=" + rv101 + ", rowss=" + rowss + "]";
	}
}
