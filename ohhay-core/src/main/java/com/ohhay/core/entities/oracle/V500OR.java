package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiNH
 * date create : 27/03/2015 
 */
public class V500OR implements Serializable{
	@QbMapping(name = "PV500", typeMapping = Types.INTEGER)
	private int pv500;
	@QbMapping(name = "VV501", typeMapping = Types.CHAR)
	private String vv501;
	@QbMapping(name = "VV502", typeMapping = Types.CHAR)
	private String vv502;
	@QbMapping(name = "VV503", typeMapping = Types.CHAR)
	private String vv503;
	@QbMapping(name = "VV504", typeMapping = Types.CHAR)
	private String vv504;
	@QbMapping(name = "VN505", typeMapping = Types.DOUBLE)
	private double vn505;
	@QbMapping(name = "VV508", typeMapping = Types.CHAR)
	private String vv508;
	@QbMapping(name = "VV509", typeMapping = Types.CHAR)
	private String vv509;
	@QbMapping(name = "VN510", typeMapping = Types.INTEGER)
	private int vn510;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	public int getPv500() {
		return pv500;
	}
	public void setPv500(int pv500) {
		this.pv500 = pv500;
	}
	public String getVv501() {
		return vv501;
	}
	public void setVv501(String vv501) {
		this.vv501 = vv501;
	}
	public String getVv502() {
		return vv502;
	}
	public void setVv502(String vv502) {
		this.vv502 = vv502;
	}
	public String getVv503() {
		return vv503;
	}
	public void setVv503(String vv503) {
		this.vv503 = vv503;
	}
	public String getVv504() {
		return vv504;
	}
	public void setVv504(String vv504) {
		this.vv504 = vv504;
	}

	public double getVn505() {
		return vn505;
	}
	public void setVn505(double vn505) {
		this.vn505 = vn505;
	}
	public String getVv508() {
		return vv508;
	}
	public void setVv508(String vv508) {
		this.vv508 = vv508;
	}
	public String getVv509() {
		return vv509;
	}
	public void setVv509(String vv509) {
		this.vv509 = vv509;
	}
	public int getVn510() {
		return vn510;
	}
	public void setVn510(int vn510) {
		this.vn510 = vn510;
	}
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	@Override
	public String toString() {
		return "V500OR [pv500=" + pv500 + ", vv501=" + vv501 + ", vv502=" + vv502 + ", vv503=" + vv503 + ", vv504=" + vv504 + ", vn505=" + vn505 + ", vv508="
				+ vv508 + ", vv509=" + vv509 + ", vn510=" + vn510 + ", rowss=" + rowss + "]";
	}
}
