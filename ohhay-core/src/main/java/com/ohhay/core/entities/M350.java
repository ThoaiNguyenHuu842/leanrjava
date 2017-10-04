package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

public class M350 implements Serializable{
	@QbMapping(name = "ov161", typeMapping = Types.CHAR)
	private int pm350;
	@QbMapping(name = "fo100", typeMapping = Types.INTEGER)
	private int mn351;
	private int mn352;
	private int mn353;
	private int mn354;
	private int mn355;
	private int mn356;
	private int mn357;
	private int mn358;
	private int mn359;
	private int mn360;
	private Date md361;
	private String mv362;
	@QbMapping(name = "mv363", typeMapping = Types.CHAR)
	private String mv363;
	private String mv364;
	private String mv365;
	private String mv366;
	@QbMapping(name = "mv367", typeMapping = Types.CHAR)
	private String mv367;
	private String mv368;
	private String mv369;
	@QbMapping(name = "mv373", typeMapping = Types.CHAR)
	private String mv373;//subject email
	@QbMapping(name = "mv375", typeMapping = Types.CHAR)
	private String mv375;//content email
	private int fo100;
	private int fc150;
	private int fb050;
	public int getPm350() {
		return pm350;
	}
	public void setPm350(int pm350) {
		this.pm350 = pm350;
	}
	public int getMn351() {
		return mn351;
	}
	public void setMn351(int mn351) {
		this.mn351 = mn351;
	}
	public int getMn352() {
		return mn352;
	}
	public void setMn352(int mn352) {
		this.mn352 = mn352;
	}
	public int getMn353() {
		return mn353;
	}
	public void setMn353(int mn353) {
		this.mn353 = mn353;
	}
	public int getMn354() {
		return mn354;
	}
	public void setMn354(int mn354) {
		this.mn354 = mn354;
	}
	public int getMn355() {
		return mn355;
	}
	public void setMn355(int mn355) {
		this.mn355 = mn355;
	}
	public int getMn356() {
		return mn356;
	}
	public void setMn356(int mn356) {
		this.mn356 = mn356;
	}
	public int getMn357() {
		return mn357;
	}
	public void setMn357(int mn357) {
		this.mn357 = mn357;
	}
	public int getMn358() {
		return mn358;
	}
	public void setMn358(int mn358) {
		this.mn358 = mn358;
	}
	public int getMn359() {
		return mn359;
	}
	public void setMn359(int mn359) {
		this.mn359 = mn359;
	}
	public int getMn360() {
		return mn360;
	}
	public void setMn360(int mn360) {
		this.mn360 = mn360;
	}
	public Date getMd361() {
		return md361;
	}
	public void setMd361(Date md361) {
		this.md361 = md361;
	}
	public String getMv362() {
		return mv362;
	}
	public void setMv362(String mv362) {
		this.mv362 = mv362;
	}
	public String getMv363() {
		return mv363;
	}
	public void setMv363(String mv363) {
		this.mv363 = mv363;
	}
	public String getMv364() {
		return mv364;
	}
	public void setMv364(String mv364) {
		this.mv364 = mv364;
	}
	public String getMv365() {
		return mv365;
	}
	public void setMv365(String mv365) {
		this.mv365 = mv365;
	}
	public String getMv366() {
		return mv366;
	}
	public void setMv366(String mv366) {
		this.mv366 = mv366;
	}
	public String getMv367() {
		return mv367;
	}
	public void setMv367(String mv367) {
		this.mv367 = mv367;
	}
	public String getMv368() {
		return mv368;
	}
	public void setMv368(String mv368) {
		this.mv368 = mv368;
	}
	public String getMv369() {
		return mv369;
	}
	public void setMv369(String mv369) {
		this.mv369 = mv369;
	}
	public String getMv373() {
		return mv373;
	}
	public void setMv373(String mv373) {
		this.mv373 = mv373;
	}
	public String getMv375() {
		return mv375;
	}
	public void setMv375(String mv375) {
		this.mv375 = mv375;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public int getFc150() {
		return fc150;
	}
	public void setFc150(int fc150) {
		this.fc150 = fc150;
	}
	public int getFb050() {
		return fb050;
	}
	public void setFb050(int fb050) {
		this.fb050 = fb050;
	}
	
}
