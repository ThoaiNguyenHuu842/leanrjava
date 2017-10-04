package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiNH
 * date create: 06/07/2015
 * user right 
 */
public class Q170 implements Serializable{
	@QbMapping(name = "FQ300", typeMapping = Types.INTEGER)
	private int fq300;
	@QbMapping(name = "FQ400", typeMapping = Types.INTEGER)
	private int fq400;
	@QbMapping(name = "QV301", typeMapping = Types.CHAR)
	private String qv301;
	@QbMapping(name = "QV302", typeMapping = Types.CHAR)
	private String qv302;
	@QbMapping(name = "FQ350", typeMapping = Types.INTEGER)
	private int fq350;
	@QbMapping(name = "QN171", typeMapping = Types.INTEGER)
	private int qn171;
	@QbMapping(name = "QV172", typeMapping = Types.CHAR)
	private String qv172;
	@QbMapping(name = "QV173", typeMapping = Types.CHAR)
	private String qv173;
	@QbMapping(name = "QV174", typeMapping = Types.CHAR)
	private String qv174;
	public int getFq300() {
		return fq300;
	}
	public void setFq300(int fq300) {
		this.fq300 = fq300;
	}
	public int getFq400() {
		return fq400;
	}
	public void setFq400(int fq400) {
		this.fq400 = fq400;
	}
	public String getQv301() {
		return qv301;
	}
	public void setQv301(String qv301) {
		this.qv301 = qv301;
	}
	public String getQv302() {
		return qv302;
	}
	public void setQv302(String qv302) {
		this.qv302 = qv302;
	}
	public int getFq350() {
		return fq350;
	}
	public void setFq350(int fq350) {
		this.fq350 = fq350;
	}
	public int getQn171() {
		return qn171;
	}
	public void setQn171(int qn171) {
		this.qn171 = qn171;
	}
	
	public String getQv172() {
		return qv172;
	}
	public void setQv172(String qv172) {
		this.qv172 = qv172;
	}
	public String getQv173() {
		return qv173;
	}
	public void setQv173(String qv173) {
		this.qv173 = qv173;
	}
	public String getQv174() {
		return qv174;
	}
	public void setQv174(String qv174) {
		this.qv174 = qv174;
	}
	
}
