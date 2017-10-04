package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;


/**
 * @author ThoaiNH
 * create Apr 21, 2017
 * bank account info
 */
public class N200OR implements Serializable{
	@QbMapping(name = "NV201", typeMapping = Types.CHAR)
	private String nv201;
	@QbMapping(name = "ND202", typeMapping = Types.DATE)
	private Date nd202;
	@QbMapping(name = "NV204", typeMapping = Types.CHAR)
	private String nv204;
	@QbMapping(name = "NV207", typeMapping = Types.CHAR)
	private String nv207;
	@QbMapping(name = "NV208", typeMapping = Types.CHAR)
	private String nv208;
	@QbMapping(name = "NV209", typeMapping = Types.CHAR)
	private String nv209;
	@QbMapping(name = "NV210", typeMapping = Types.CHAR)
	private String nv210;
	@QbMapping(name = "NV211", typeMapping = Types.CHAR)
	private String nv211;
	@QbMapping(name = "NV212", typeMapping = Types.CHAR)
	private String nv212;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "ND217", typeMapping = Types.DATE)
	private Date nd217;
	public String getNv201() {
		return nv201;
	}
	public void setNv201(String nv201) {
		this.nv201 = nv201;
	}
	public Date getNd202() {
		return nd202;
	}
	public void setNd202(Date nd202) {
		this.nd202 = nd202;
	}
	public String getNv204() {
		return nv204;
	}
	public void setNv204(String nv204) {
		this.nv204 = nv204;
	}
	public String getNv207() {
		return nv207;
	}
	public void setNv207(String nv207) {
		this.nv207 = nv207;
	}
	public String getNv208() {
		return nv208;
	}
	public void setNv208(String nv208) {
		this.nv208 = nv208;
	}
	public String getNv209() {
		return nv209;
	}
	public void setNv209(String nv209) {
		this.nv209 = nv209;
	}
	public String getNv210() {
		return nv210;
	}
	public void setNv210(String nv210) {
		this.nv210 = nv210;
	}
	public String getNv211() {
		return nv211;
	}
	public void setNv211(String nv211) {
		this.nv211 = nv211;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public Date getNd217() {
		return nd217;
	}
	public void setNd217(Date nd217) {
		this.nd217 = nd217;
	}
	public String getNv212() {
		return nv212;
	}
	public void setNv212(String nv212) {
		this.nv212 = nv212;
	}
	
}
