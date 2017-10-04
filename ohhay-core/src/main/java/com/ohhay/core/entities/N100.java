package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.mysql.jdbc.Blob;
import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiNH
 * date create 25/07/2015
 */
public class N100 implements Serializable{
	@QbMapping(name = "nv116", typeMapping = Types.CHAR)
	private String nv116;
	@QbMapping(name = "nv117", typeMapping = Types.CHAR)
	private String nv117;
	@QbMapping(name = "nv118", typeMapping = Types.CHAR)
	private String nv118;
	@QbMapping(name = "nv119", typeMapping = Types.CHAR)
	private  String nv119;
	@QbMapping(name = "nv120", typeMapping = Types.CHAR)
	private String nv120;
	public String getNv116() {
		return nv116;
	}
	public void setNv116(String nv116) {
		this.nv116 = nv116;
	}
	public String getNv117() {
		return nv117;
	}
	public void setNv117(String nv117) {
		this.nv117 = nv117;
	}
	public String getNv118() {
		return nv118;
	}
	public void setNv118(String nv118) {
		this.nv118 = nv118;
	}
	public String getNv120() {
		return nv120;
	}
	public void setNv120(String nv120) {
		this.nv120 = nv120;
	}
	
	public String getNv119() {
		return nv119;
	}
	public void setNv119(String nv119) {
		this.nv119 = nv119;
	}
	@Override
	public String toString() {
		return "N100 [nv116=" + nv116 + ", nv117=" + nv117 + ", nv118=" + nv118
				+ ", nv119=" + nv119 + ", nv120=" + nv120 + "]";
	}
	
	
}
