package com.ohhay.base.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author TuNt
 * create date May 26, 2017
 * ohhay-base
 */
public class R100CentP2017Piep implements Serializable {
	@QbMapping(name = "RV101", typeMapping = Types.CHAR)
	private String rv101;
	@QbMapping(name = "RD102", typeMapping = Types.DATE)
	private Date rd102;
	@QbMapping(name = "RN103", typeMapping = Types.INTEGER)
	private int rn103;
	@QbMapping(name = "RN104", typeMapping = Types.INTEGER)
	private int rn104;
	@QbMapping(name = "FP100", typeMapping = Types.INTEGER)
	private int fp100;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	public String getRv101() {
		return rv101;
	}
	public void setRv101(String rv101) {
		this.rv101 = rv101;
	}
	public Date getRd102() {
		return rd102;
	}
	public void setRd102(Date rd102) {
		this.rd102 = rd102;
	}
	public int getRn103() {
		return rn103;
	}
	public void setRn103(int rn103) {
		this.rn103 = rn103;
	}
	public int getRn104() {
		return rn104;
	}
	public void setRn104(int rn104) {
		this.rn104 = rn104;
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
	@Override
	public String toString() {
		return "R100CentP2017Piep [rv101=" + rv101 + ", rd102=" + rd102 + ", rn103=" + rn103 + ", rn104=" + rn104 + ", fp100=" + fp100 + ", fo100=" + fo100
				+ "]";
	}
	
}
