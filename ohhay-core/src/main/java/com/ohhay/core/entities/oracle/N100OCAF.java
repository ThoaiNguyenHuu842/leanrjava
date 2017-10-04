package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author TuNt
 * create date Jul 12, 2017
 * ohhay-core
 */
public class N100OCAF implements Serializable{
	@QbMapping(name = "PN100", typeMapping = Types.INTEGER)
	private int pn100;
	@QbMapping(name = "NV101", typeMapping = Types.CHAR)
	private String nv101;
	@QbMapping(name = "NV102", typeMapping = Types.CHAR)
	private String nv102;
	@QbMapping(name = "ND108", typeMapping = Types.DATE, format = true, pattern = "dd-MM-yyyy HH:mm")
	private String nd108;
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
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	public int getPn100() {
		return pn100;
	}
	public void setPn100(int pn100) {
		this.pn100 = pn100;
	}
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
	public String getNd108() {
		return nd108;
	}
	public void setNd108(String nd108) {
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
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	@Override
	public String toString() {
		return "N100OCAF [pn100=" + pn100 + ", nv101=" + nv101 + ", nv102=" + nv102 + ", nd108=" + nd108 + ", nv109=" + nv109 + ", nv126=" + nv126 + ", nv127="
				+ nv127 + ", nv128=" + nv128 + ", nv130=" + nv130 + ", nv131=" + nv131 + ", fo100=" + fo100 + ", rowss=" + rowss + "]";
	}
	

}
