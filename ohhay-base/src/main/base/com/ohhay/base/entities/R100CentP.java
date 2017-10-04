package com.ohhay.base.entities;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiVt created 22/02/2017 report piepme MYSQL
 */
public class R100CentP implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@QbMapping(name = "SEN", typeMapping = Types.CHAR)
	private String sen;// receive
	@QbMapping(name = "VIE", typeMapping = Types.CHAR)
	private String vie;// view
	@QbMapping(name = "LIK", typeMapping = Types.CHAR)
	private String lik;// like
	@QbMapping(name = "FOL", typeMapping = Types.CHAR)
	private String fol;// follow
	@QbMapping(name = "YY", typeMapping = Types.INTEGER)
	private int yy;
	@QbMapping(name = "XX", typeMapping = Types.CHAR)
	private String xx;
	@QbMapping(name = "PIEPS", typeMapping = Types.INTEGER)
	private int pieps;
	@QbMapping(name = "RV101", typeMapping = Types.CHAR)
	private String rv101;
	@QbMapping(name = "ANZAHL", typeMapping = Types.INTEGER)
	private int anzahl;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "OV110", typeMapping = Types.CHAR)
	private String ov110;
	
	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public int getFo100() {
		return fo100;
	}

	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}

	public String getOv110() {
		return ov110;
	}

	public void setOv110(String ov110) {
		this.ov110 = ov110;
	}

	public String getSen() {
		return sen;
	}

	public void setSen(String sen) {
		this.sen = sen;
	}

	public String getVie() {
		return vie;
	}

	public void setVie(String vie) {
		this.vie = vie;
	}

	public String getLik() {
		return lik;
	}

	public void setLik(String lik) {
		this.lik = lik;
	}

	public String getFol() {
		return fol;
	}

	public void setFol(String fol) {
		this.fol = fol;
	}

	public int getYy() {
		return yy;
	}

	public void setYy(int yy) {
		this.yy = yy;
	}

	public int getPieps() {
		return pieps;
	}

	public void setPieps(int pieps) {
		this.pieps = pieps;
	}	

	public String getXx() {
		return xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}
	
	public String getRv101() {
		return rv101;
	}

	public void setRv101(String rv101) {
		this.rv101 = rv101;
	}

	@Override
	public String toString() {
		return "R100CentP [sen=" + sen + ", vie=" + vie + ", lik=" + lik + ", fol=" + fol + ", yy=" + yy + ", xx=" + xx
				+ ", pieps=" + pieps + ", rv101=" + rv101 + ", anzahl=" + anzahl + ", fo100=" + fo100 + ", ov110="
				+ ov110 + "]";
	}

	

}
