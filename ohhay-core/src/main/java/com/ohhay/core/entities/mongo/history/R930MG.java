package com.ohhay.core.entities.mongo.history;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

public class R930MG implements Serializable{
	@Field("FO100S")
	private int fo100s;
	@Field("FO100F")
	private int fo100f;
	@Field("RL946")
	private Date rl946;//ngay cap nhat
	public int getFo100s() {
		return fo100s;
	}
	public void setFo100s(int fo100s) {
		this.fo100s = fo100s;
	}
	public int getFo100f() {
		return fo100f;
	}
	public void setFo100f(int fo100f) {
		this.fo100f = fo100f;
	}
	public Date getRl946() {
		return rl946;
	}
	public void setRl946(Date rl946) {
		this.rl946 = rl946;
	}
	
}
