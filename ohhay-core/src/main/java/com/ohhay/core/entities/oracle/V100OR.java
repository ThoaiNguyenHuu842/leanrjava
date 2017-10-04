package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

public class V100OR implements Serializable{
	@QbMapping(name = "VN101", typeMapping = Types.INTEGER)
	private int vn101;
	@QbMapping(name = "VV102", typeMapping = Types.CHAR)
	private String vv102;
	@QbMapping(name = "VN103", typeMapping = Types.INTEGER)
	private int vn103;
	@QbMapping(name = "VN104", typeMapping = Types.INTEGER)
	private int vn104;
	@QbMapping(name = "VN105", typeMapping = Types.INTEGER)
	private int vn105;
	@QbMapping(name = "VN106", typeMapping = Types.INTEGER)
	private int vn106;
	@QbMapping(name = "VN107", typeMapping = Types.INTEGER)
	private int vn107;
	@QbMapping(name = "VN108", typeMapping = Types.INTEGER)
	private int vn108;
	@QbMapping(name = "VN109", typeMapping = Types.INTEGER)
	private int vn109;
	@QbMapping(name = "VN110", typeMapping = Types.INTEGER)
	private int vn110;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "VL127", typeMapping = Types.CHAR)
	private String vl127;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	public int getVn101() {
		return vn101;
	}
	public void setVn101(int vn101) {
		this.vn101 = vn101;
	}
	public int getVn103() {
		return vn103;
	}
	public void setVn103(int vn103) {
		this.vn103 = vn103;
	}
	public int getVn104() {
		return vn104;
	}
	public void setVn104(int vn104) {
		this.vn104 = vn104;
	}
	public int getVn105() {
		return vn105;
	}
	public void setVn105(int vn105) {
		this.vn105 = vn105;
	}
	public int getVn106() {
		return vn106;
	}
	public void setVn106(int vn106) {
		this.vn106 = vn106;
	}
	public int getVn107() {
		return vn107;
	}
	public void setVn107(int vn107) {
		this.vn107 = vn107;
	}
	public int getVn108() {
		return vn108;
	}
	public void setVn108(int vn108) {
		this.vn108 = vn108;
	}
	public int getVn109() {
		return vn109;
	}
	public void setVn109(int vn109) {
		this.vn109 = vn109;
	}
	public int getVn110() {
		return vn110;
	}
	public void setVn110(int vn110) {
		this.vn110 = vn110;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getVv102() {
		return vv102;
	}
	public void setVv102(String vv102) {
		this.vv102 = vv102;
	}
	public String getVl127() {
		return vl127;
	}
	public void setVl127(String vl127) {
		this.vl127 = vl127;
	}
	
	@Override
	public String toString() {
		return "V100OR [vn101=" + vn101 + ", vv102=" + vv102 + ", vn103=" + vn103 + ", vn104=" + vn104 + ", vn105=" + vn105 + ", vn106=" + vn106 + ", vn107="
				+ vn107 + ", vn108=" + vn108 + ", vn109=" + vn109 + ", vn110=" + vn110 + ", fo100=" + fo100 + ", vl127=" + vl127 + "]";
	}
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
}
