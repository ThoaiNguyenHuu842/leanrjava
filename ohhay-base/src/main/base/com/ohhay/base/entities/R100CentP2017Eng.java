package com.ohhay.base.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author TuNt
 * create date May 22, 2017
 * ohhay-base
 */
public class R100CentP2017Eng implements Serializable{
	@QbMapping(name = "AVERA", typeMapping = Types.DECIMAL)
	private BigDecimal avera;
	@QbMapping(name = "XX", typeMapping = Types.DATE)
	private Date xx;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	
	public BigDecimal getAvera() {
		return avera;
	}
	public void setAvera(BigDecimal avera) {
		this.avera = avera;
	}
	public Date getXx() {
		return xx;
	}
	public void setXx(Date xx) {
		this.xx = xx;
	}
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	@Override
	public String toString() {
		return "R100CentP2017Eng [avera=" + avera + ", xx=" + xx + "]";
	}
	
}
