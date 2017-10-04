package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiNH
 * date create: 08/07/2015
 */
public class N100AddOR implements Serializable{
	@QbMapping(name = "NV101", typeMapping = Types.CHAR)
	private String nv101;
	@QbMapping(name = "NV102", typeMapping = Types.CHAR)
	private String nv102;
	@QbMapping(name = "ND108", typeMapping = Types.DATE)
	private Date nd108;
	@QbMapping(name = "NV109", typeMapping = Types.CHAR)
	private String nv109;
	@QbMapping(name = "NV126", typeMapping = Types.CHAR)
	private String nv126;
	@QbMapping(name = "NV127", typeMapping = Types.CHAR)
	private String nv127;
	@QbMapping(name = "NV128", typeMapping = Types.CHAR)
	private String nv128;
	@QbMapping(name = "NV130", typeMapping = Types.CHAR)
	private String nv130;
	@QbMapping(name = "NV131", typeMapping = Types.CHAR)
	private String nv131;
	@QbMapping(name = "DN", typeMapping = Types.CHAR)
	private String dn;
	@QbMapping(name = "THIEU", typeMapping = Types.INTEGER)
	private int thieu;
	private String nd108Str;
	public String getNv101() {
		return nv101;
	}
	public void setNv101(String nv101) {
		this.nv101 = nv101;
	}
	public String getNv102() {
		return nv102;
	}
	public void setNv102(String nv102) {
		this.nv102 = nv102;
	}
	public Date getNd108() {
		return nd108;
	}
	public void setNd108(Date nd108) {
		this.nd108 = nd108;
	}
	public String getNv109() {
		return nv109;
	}
	public void setNv109(String nv109) {
		this.nv109 = nv109;
	}
	public String getNv126() {
		return nv126;
	}
	public void setNv126(String nv126) {
		this.nv126 = nv126;
	}
	public String getNv127() {
		return nv127;
	}
	public void setNv127(String nv127) {
		this.nv127 = nv127;
	}
	public String getNv128() {
		return nv128;
	}
	public void setNv128(String nv128) {
		this.nv128 = nv128;
	}
	public String getNv130() {
		return nv130;
	}
	public void setNv130(String nv130) {
		this.nv130 = nv130;
	}
	public String getNv131() {
		return nv131;
	}
	public void setNv131(String nv131) {
		this.nv131 = nv131;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public int getThieu() {
		return thieu;
	}
	public void setThieu(int thieu) {
		this.thieu = thieu;
	}
	public String getNd108Str() {
		return nd108Str;
	}
	public void setNd108Str(String nd108Str) {
		this.nd108Str = nd108Str;
	}
	
}
