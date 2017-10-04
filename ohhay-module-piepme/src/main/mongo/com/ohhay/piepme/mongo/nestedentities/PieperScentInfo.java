package com.ohhay.piepme.mongo.nestedentities;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Dec 23, 2016
 */
public class PieperScentInfo {
	@Field("FO100")
	private int fo100;
	@Field("DATE_TIME")
	private Date date;
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
