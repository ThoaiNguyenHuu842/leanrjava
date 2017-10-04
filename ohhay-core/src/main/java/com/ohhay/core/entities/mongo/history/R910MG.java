package com.ohhay.core.entities.mongo.history;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

public class R910MG implements Serializable{
	@Field("FO100")
	private int fo100;
	@Field("RL946")
	private Date rl946;//ngay cap nhat
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
	
	
}
