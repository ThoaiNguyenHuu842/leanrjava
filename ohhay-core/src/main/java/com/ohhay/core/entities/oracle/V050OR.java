package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ohhay.base.dao.QbMapping;

public class V050OR implements Serializable{
	@QbMapping(name = "VN051", typeMapping = Types.INTEGER)
	private int vn051;
	@QbMapping(name = "VN052", typeMapping = Types.INTEGER)
	private int vn052;
	@QbMapping(name = "VN053", typeMapping = Types.INTEGER)
	private int vn053;
	@QbMapping(name = "VV054", typeMapping = Types.CHAR)
	private String vv054;
	@QbMapping(name = "VV055", typeMapping = Types.CHAR)
	private String vv055;
	@QbMapping(name = "VV056", typeMapping = Types.CHAR)
	private String vv056;
	@QbMapping(name = "VD057", typeMapping = Types.DATE)
	private Date vd057;
	@QbMapping(name = "FV000", typeMapping = Types.INTEGER)
	private int fv000;
	@QbMapping(name = "VL077", typeMapping = Types.CHAR)
	private String vl077;
	@QbMapping(name= "PV050", typeMapping = Types.INTEGER)
	private int pv050;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	@QbMapping(name = "VD057", typeMapping = Types.DATE, format = true, pattern = "dd-MM-yyyy HH:mm")
	private String vd057Str;
	public int getVn051() {
		return vn051;
	}
	public void setVn051(int vn051) {
		this.vn051 = vn051;
	}
	public int getVn052() {
		return vn052;
	}
	public void setVn052(int vn052) {
		this.vn052 = vn052;
	}
	public int getVn053() {
		return vn053;
	}
	public void setVn053(int vn053) {
		this.vn053 = vn053;
	}
	public String getVv054() {
		return vv054;
	}
	public void setVv054(String vv054) {
		this.vv054 = vv054;
	}
	public String getVv055() {
		return vv055;
	}
	public void setVv055(String vv055) {
		this.vv055 = vv055;
	}
	public String getVv056() {
		return vv056;
	}
	public void setVv056(String vv056) {
		this.vv056 = vv056;
	}
	public Date getVd057() {
		return vd057;
	}
	public void setVd057(Date vd057) {
		this.vd057 = vd057;
	}
	public int getFv000() {
		return fv000;
	}
	public void setFv000(int fv000) {
		this.fv000 = fv000;
	}
	public String getVl077() {
		return vl077;
	}
	public void setVl077(String vl077) {
		this.vl077 = vl077;
	}
	public int getPv050() {
		return pv050;
	}
	public void setPv050(int pv050) {
		this.pv050 = pv050;
	}
	
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	public String getVd057Str() {
		return vd057Str;
	}
	public void setVd057Str(String vd057Str) {
		this.vd057Str = vd057Str;
	}
	@Override
	public String toString() {
		return "V050OR [vn051=" + vn051 + ", vn052=" + vn052 + ", vn053=" + vn053 + ", vv054=" + vv054 + ", vv055=" + vv055 + ", vv056=" + vv056 + ", vd057="
				+ vd057 + ", fv000=" + fv000 + ", vl077=" + vl077 + ", pv050=" + pv050 + ", rowss=" + rowss + ", vd057Str=" + vd057Str + "]";
	}
	
}
