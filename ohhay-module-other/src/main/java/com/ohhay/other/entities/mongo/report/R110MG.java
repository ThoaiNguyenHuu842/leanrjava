package com.ohhay.other.entities.mongo.report;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class R110MG {
	@Field("YEAR")
	private int year;
	@Field("MONTH")
	private int month;
	@Field("DAY")
	private int day;
	@Field("REPORT")
	private List<R120MG> listR120mgs;

	public R110MG() {
		// TODO Auto-generated constructor stub
	}

	public R110MG(int year, int month, int day, List<R120MG> listR120mgs) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.listR120mgs = listR120mgs;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public List<R120MG> getListR120mgs() {
		return listR120mgs;
	}

	public void setListR120mgs(List<R120MG> listR120mgs) {
		this.listR120mgs = listR120mgs;
	}

}
