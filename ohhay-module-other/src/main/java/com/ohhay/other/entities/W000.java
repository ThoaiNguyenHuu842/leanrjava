package com.ohhay.other.entities;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

public class W000 implements Serializable{
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "WV001", typeMapping = Types.CHAR)
	private String wv001;
	@QbMapping(name = "WV002", typeMapping = Types.CHAR)
	private String wv002;
	@QbMapping(name = "WV003", typeMapping = Types.CHAR)
	private String wv003;
	@QbMapping(name = "WV004", typeMapping = Types.CHAR)
	private String wv004;
	@QbMapping(name = "WV005", typeMapping = Types.CHAR)
	private String wv005;
	@QbMapping(name = "WV006", typeMapping = Types.CHAR)
	private String wv006;
	@QbMapping(name = "WV007", typeMapping = Types.CHAR)
	private String wv007;
	public String getWv001() {
		return wv001;
	}
	public void setWv001(String wv001) {
		this.wv001 = wv001;
	}
	public String getWv002() {
		return wv002;
	}
	public void setWv002(String wv002) {
		this.wv002 = wv002;
	}
	public String getWv003() {
		return wv003;
	}
	public void setWv003(String wv003) {
		this.wv003 = wv003;
	}
	public String getWv004() {
		return wv004;
	}
	public void setWv004(String wv004) {
		this.wv004 = wv004;
	}
	public String getWv005() {
		return wv005;
	}
	public void setWv005(String wv005) {
		this.wv005 = wv005;
	}
	public String getWv006() {
		return wv006;
	}
	public void setWv006(String wv006) {
		this.wv006 = wv006;
	}
	public String getWv007() {
		return wv007;
	}
	public void setWv007(String wv007) {
		this.wv007 = wv007;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	
}
