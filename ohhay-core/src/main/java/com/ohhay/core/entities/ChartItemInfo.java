package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiNH
 * crete 10/09/2014
 * chart info mapper
 */
public class ChartItemInfo implements Serializable{
	@QbMapping(name = "XX", typeMapping = Types.DATE)
	private Date xx;
	@QbMapping(name = "XM", typeMapping = Types.CHAR)
	private String xm;
	@QbMapping(name = "YY", typeMapping = Types.INTEGER)
	private int yy;
	public Date getXx() {
		return xx;
	}
	public void setXx(Date xx) {
		this.xx = xx;
	}
	public int getYy() {
		return yy;
	}
	public void setYy(int yy) {
		this.yy = yy;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	
}
