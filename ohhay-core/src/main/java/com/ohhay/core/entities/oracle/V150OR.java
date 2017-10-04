package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ohhay.base.dao.QbMapping;

public class V150OR implements Serializable{
	@QbMapping(name = "VN151", typeMapping = Types.INTEGER)
	private int vn151;
	@QbMapping(name = "VN152", typeMapping = Types.INTEGER)
	private int vn152;
	@QbMapping(name = "VV153", typeMapping = Types.CHAR)
	private String vv153;
	@QbMapping(name = "VN154", typeMapping = Types.INTEGER)
	private int vn154;
	@QbMapping(name = "VN155", typeMapping = Types.INTEGER)
	private int vn155;
	@QbMapping(name = "VN156", typeMapping = Types.INTEGER)
	private int vn156;
	@QbMapping(name = "VN157", typeMapping = Types.INTEGER)
	private int vn157;
	@QbMapping(name = "VN158", typeMapping = Types.INTEGER)
	private int vn158;
	@QbMapping(name = "VD159", typeMapping = Types.DATE)
	private Date vd159;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "VL167", typeMapping = Types.CHAR)
	private String vl167;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	@QbMapping(name = "VD159", typeMapping = Types.DATE, format = true, pattern = "dd-MM-yyyy HH:mm")
	private String vd159Str;
	public int getVn151() {
		return vn151;
	}
	public void setVn151(int vn151) {
		this.vn151 = vn151;
	}
	public int getVn152() {
		return vn152;
	}
	public void setVn152(int vn152) {
		this.vn152 = vn152;
	}
	public String getVv153() {
		return vv153;
	}
	public void setVv153(String vv153) {
		this.vv153 = vv153;
	}
	public int getVn154() {
		return vn154;
	}
	public void setVn154(int vn154) {
		this.vn154 = vn154;
	}
	public int getVn155() {
		return vn155;
	}
	public void setVn155(int vn155) {
		this.vn155 = vn155;
	}
	public int getVn156() {
		return vn156;
	}
	public void setVn156(int vn156) {
		this.vn156 = vn156;
	}
	public int getVn157() {
		return vn157;
	}
	public void setVn157(int vn157) {
		this.vn157 = vn157;
	}
	public int getVn158() {
		return vn158;
	}
	public void setVn158(int vn158) {
		this.vn158 = vn158;
	}
	public Date getVd159() {
		return vd159;
	}
	public void setVd159(Date vd159) {
		this.vd159 = vd159;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getVl167() {
		return vl167;
	}
	public void setVl167(String vl167) {
		this.vl167 = vl167;
	}
	
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	public void setVd159Str(String vd159Str) {
		this.vd159Str = vd159Str;
	}
	public String getVd159Str() {
		return vd159Str;
	}
	@Override
	public String toString() {
		return "V150OR [vn151=" + vn151 + ", vn152=" + vn152 + ", vv153=" + vv153 + ", vn154=" + vn154 + ", vn155=" + vn155 + ", vn156=" + vn156 + ", vn157="
				+ vn157 + ", vn158=" + vn158 + ", vd159=" + vd159 + ", fo100=" + fo100 + ", vl167=" + vl167 + ", rowss=" + rowss + ", vd159Str=" + vd159Str
				+ "]";
	}
	
}
