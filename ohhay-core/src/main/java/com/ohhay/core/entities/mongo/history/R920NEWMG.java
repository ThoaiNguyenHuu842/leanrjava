package com.ohhay.core.entities.mongo.history;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.utils.DateHelper;

public class R920NEWMG implements Serializable{
	@Field("FO100")
	private int fo100;
	@Field("RL946")
	private Date rl946;//ngay cap nhat
	@Transient
	private String rl946String;
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
}
