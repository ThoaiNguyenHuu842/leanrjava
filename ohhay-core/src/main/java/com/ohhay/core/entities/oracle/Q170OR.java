package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiNH
 * create Aug 30, 2017
 */
public class Q170OR implements Serializable{
	@QbMapping(name = "QV171", typeMapping = Types.CHAR)
	private String qv171;
	@QbMapping(name = "QV172", typeMapping = Types.CHAR)
	private String qv172;
	@QbMapping(name = "QV173", typeMapping = Types.CHAR)
	private String qv173;
	@QbMapping(name = "QD174", typeMapping = Types.DATE)
	private Date qd174;
	@QbMapping(name = "FQ400", typeMapping = Types.INTEGER)
	private int fq400;
	@QbMapping(name = "FO100U", typeMapping = Types.INTEGER)
	private int fo100u;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	public String getQv171() {
		return qv171;
	}
	public void setQv171(String qv171) {
		this.qv171 = qv171;
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
	public Date getQd174() {
		return qd174;
	}
	public void setQd174(Date qd174) {
		this.qd174 = qd174;
	}
	public int getFq400() {
		return fq400;
	}
	public void setFq400(int fq400) {
		this.fq400 = fq400;
	}
	public int getFo100u() {
		return fo100u;
	}
	public void setFo100u(int fo100u) {
		this.fo100u = fo100u;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	
}
