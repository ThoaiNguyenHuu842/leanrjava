package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ohhay.base.dao.QbMapping;

public class V080OR implements Serializable{
	@QbMapping(name = "VN081", typeMapping = Types.INTEGER)
	private int vn081;
	@QbMapping(name = "VN082", typeMapping = Types.INTEGER)
	private int vn082;
	@QbMapping(name = "VN083", typeMapping = Types.INTEGER)
	private int vn083;
	@QbMapping(name = "VN084", typeMapping = Types.INTEGER)
	private int vn084;
	@QbMapping(name = "VV085", typeMapping = Types.CHAR)
	private String vv085;
	@QbMapping(name = "VD086", typeMapping = Types.DATE)
	private Date vd086;
	@QbMapping(name = "VN087", typeMapping = Types.INTEGER)
	private int vn087;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "VL097", typeMapping = Types.CHAR)
	private String vl097;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	@QbMapping(name = "VD086", typeMapping = Types.DATE, format = true, pattern = "dd-MM-yyyy HH:mm")
	private String vd086Str;
	public int getVn081() {
		return vn081;
	}
	public void setVn081(int vn081) {
		this.vn081 = vn081;
	}
	public int getVn082() {
		return vn082;
	}
	public void setVn082(int vn082) {
		this.vn082 = vn082;
	}
	public int getVn083() {
		return vn083;
	}
	public void setVn083(int vn083) {
		this.vn083 = vn083;
	}
	public int getVn084() {
		return vn084;
	}
	public void setVn084(int vn084) {
		this.vn084 = vn084;
	}
	public Date getVd086() {
		return vd086;
	}
	public void setVd086(Date vd086) {
		this.vd086 = vd086;
	}
	public int getVn087() {
		return vn087;
	}
	public void setVn087(int vn087) {
		this.vn087 = vn087;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	
	public String getVv085() {
		return vv085;
	}
	public void setVv085(String vv085) {
		this.vv085 = vv085;
	}
	public String getVl097() {
		return vl097;
	}
	public void setVl097(String vl097) {
		this.vl097 = vl097;
	}
	
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	public String getVd086Str() {
		return vd086Str;
	}
	public void setVd086Str(String vd086Str) {
		this.vd086Str = vd086Str;
	}
	@Override
	public String toString() {
		return "V080OR [vn081=" + vn081 + ", vn082=" + vn082 + ", vn083=" + vn083 + ", vn084=" + vn084 + ", vv085=" + vv085 + ", vd086=" + vd086 + ", vn087="
				+ vn087 + ", fo100=" + fo100 + ", vl097=" + vl097 + ", rowss=" + rowss + ", vd086Str=" + vd086Str + "]";
	}
}
