package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ohhay.base.dao.QbMapping;

public class V000OR implements Serializable{
	@QbMapping(name = "VN001", typeMapping = Types.INTEGER)
	private int vn001;
	@QbMapping(name = "VN002", typeMapping = Types.INTEGER)
	private int vn002;
	@QbMapping(name = "VN003", typeMapping = Types.INTEGER)
	private int vn003;
	@QbMapping(name = "VV004", typeMapping = Types.CHAR)
	private String vv004;
	@QbMapping(name = "VV005", typeMapping = Types.CHAR)
	private String vv005;
	@QbMapping(name = "VV006", typeMapping = Types.CHAR)
	private String vv006;
	@QbMapping(name = "VD007", typeMapping = Types.DATE)
	private Date vd007;
	@QbMapping(name = "VL017", typeMapping = Types.CHAR)
	private String vl017;
	@QbMapping(name= "PV000", typeMapping = Types.INTEGER)
	private int pv000;
	@QbMapping(name = "VD007", typeMapping = Types.DATE, format = true, pattern = "dd-MM-yyyy HH:mm")
	private String vd007Str;
	public int getVn001() {
		return vn001;
	}
	public void setVn001(int vn001) {
		this.vn001 = vn001;
	}
	public int getVn002() {
		return vn002;
	}
	public void setVn002(int vn002) {
		this.vn002 = vn002;
	}
	public int getVn003() {
		return vn003;
	}
	public void setVn003(int vn003) {
		this.vn003 = vn003;
	}
	public String getVv004() {
		return vv004;
	}
	public void setVv004(String vv004) {
		this.vv004 = vv004;
	}
	public String getVv005() {
		return vv005;
	}
	public void setVv005(String vv005) {
		this.vv005 = vv005;
	}
	public String getVv006() {
		return vv006;
	}
	public void setVv006(String vv006) {
		this.vv006 = vv006;
	}
	public java.sql.Date getVd007() {
		return vd007;
	}
	public void setVd007(java.sql.Date vd007) {
		this.vd007 = vd007;
	}
	public String getVl017() {
		return vl017;
	}
	public void setVl017(String vl017) {
		this.vl017 = vl017;
	}
	public int getPv000() {
		return pv000;
	}
	public void setPv000(int pv000) {
		this.pv000 = pv000;
	}
	public String getVd007Str() {
		return vd007Str;
	}
	public void setVd007Str(String vd007Str) {
		this.vd007Str = vd007Str;
	}
	@Override
	public String toString() {
		return "V000OR [vn001=" + vn001 + ", vn002=" + vn002 + ", vn003=" + vn003 + ", vv004=" + vv004 + ", vv005=" + vv005 + ", vv006=" + vv006 + ", vd007="
				+ vd007 + ", vl017=" + vl017 + ", pv000=" + pv000 + ", vd007Str=" + vd007Str + "]";
	}
	
}
