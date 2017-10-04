package com.ohhay.core.entities.mongo.history;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.utils.DateHelper;

public class R920MG implements Serializable{
	@Field("FO100")
	private int fo100;
	@Transient
	private String esFo100;
	@Field("RL946")
	private Date rl946;//ngay cap nhat
	@Transient
	private String rl946String;
	@Field("RV921")
	private String rv921;//url bookmarked
	@Field("RV922")
	private String rv922;//name bookmark
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public Date getRl946() {
		return rl946;
	}
	public void setRl946(Date rl946) {
		this.rl946 = rl946;
	}
	public String getRv921() {
		return rv921;
	}
	public void setRv921(String rv921) {
		this.rv921 = rv921;
	}
	public String getRv922() {
		return rv922;
	}
	public void setRv922(String rv922) {
		this.rv922 = rv922;
	}
	public String getRl946String() {
		String sDate = DateHelper.formatDateLong(rl946);
		if(sDate != null)
			return sDate;
		else
			return "";
	}
	public String getRl946TimeLine(){
		return DateHelper.convertDateToTimeLine(rl946);
	}
	public void setRl946String(String rl946String) {
		this.rl946String = rl946String;
	}
	public String getEsFo100() {
		return esFo100;
	}
	public void setEsFo100(String esFo100) {
		this.esFo100 = esFo100;
	}
	
}
