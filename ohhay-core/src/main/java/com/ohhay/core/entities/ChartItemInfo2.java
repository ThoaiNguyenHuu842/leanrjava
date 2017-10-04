package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;


/**
 * @author ThoaiNH
 * crete 10/09/2014
 * chart info mapper
 */
public class ChartItemInfo2 implements Serializable{
	@QbMapping(name = "XX", typeMapping = Types.CHAR)
	private String xx;
	@QbMapping(name = "YY", typeMapping = Types.INTEGER)
	private int yy;
	public String getXx() {
		return xx;
	}
	public void setXx(String xx) {
		this.xx = xx;
	}
	public int getYy() {
		return yy;
	}
	public void setYy(int yy) {
		this.yy = yy;
	}
	@Override
	public String toString() {
		return "ChartItemInfo2 [xx=" + xx + ", yy=" + yy + "]";
	}
	
}
