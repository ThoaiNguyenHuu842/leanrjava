package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ohhay.base.dao.QbMapping;

public class V130OR implements Serializable{
	@QbMapping(name = "VN131", typeMapping = Types.INTEGER)
	private int vn131;
	@QbMapping(name = "VV132", typeMapping = Types.CHAR)
	private String vv132;
	@QbMapping(name = "VN133", typeMapping = Types.INTEGER)
	private int vn133;
	@QbMapping(name = "VN134", typeMapping = Types.INTEGER)
	private int vn134;
	@QbMapping(name = "VN135", typeMapping = Types.INTEGER)
	private int vn135;
	@QbMapping(name = "VD136", typeMapping = Types.DATE)
	private Date vd136;
	@QbMapping(name = "FP100", typeMapping = Types.INTEGER)
	private int fp100;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "VL147", typeMapping = Types.CHAR)
	private String vl147;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	@QbMapping(name = "VD136", typeMapping = Types.DATE, format = true, pattern = "dd-MM-yyyy HH:mm")
	private String vd136Str;
	
	public int getVn131() {
		return vn131;
	}
	public void setVn131(int vn131) {
		this.vn131 = vn131;
	}
	public String getVv132() {
		return vv132;
	}
	public void setVv132(String vv132) {
		this.vv132 = vv132;
	}
	public int getVn133() {
		return vn133;
	}
	public void setVn133(int vn133) {
		this.vn133 = vn133;
	}
	public int getVn134() {
		return vn134;
	}
	public void setVn134(int vn134) {
		this.vn134 = vn134;
	}
	public int getVn135() {
		return vn135;
	}
	public void setVn135(int vn135) {
		this.vn135 = vn135;
	}
	public Date getVd136() {
		return vd136;
	}
	public void setVd136(Date vd136) {
		this.vd136 = vd136;
	}
	public int getFp100() {
		return fp100;
	}
	public void setFp100(int fp100) {
		this.fp100 = fp100;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getVl147() {
		return vl147;
	}
	public void setVl147(String vl147) {
		this.vl147 = vl147;
	}
	
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	
	public String getVd136Str() {
		return vd136Str;
	}
	public void setVd136Str(String vd136Str) {
		this.vd136Str = vd136Str;
	}
	@Override
	public String toString() {
		return "V130OR [vn131=" + vn131 + ", vv132=" + vv132 + ", vn133=" + vn133 + ", vn134=" + vn134 + ", vn135=" + vn135 + ", vd136=" + vd136 + ", fp100="
				+ fp100 + ", fo100=" + fo100 + ", vl147=" + vl147 + "]";
	}
	
}
