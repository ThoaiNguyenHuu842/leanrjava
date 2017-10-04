package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author TuNt
 * create date Mar 14, 2017
 * ohhay-core
 */
public class N000OR implements Serializable{
	public static final String NV002_DN = "D";
	public static final String NV002_CN = "C";
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "NV002", typeMapping = Types.CHAR)
	private String nv002;
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getNv002() {
		return nv002;
	}
	public void setNv002(String nv002) {
		this.nv002 = nv002;
	}
	@Override
	public String toString() {
		return "N000OR [fo100=" + fo100 + ", nv002=" + nv002 + "]";
	}
}
