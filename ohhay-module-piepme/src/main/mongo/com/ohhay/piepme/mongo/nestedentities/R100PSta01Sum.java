package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create Feb 24, 2017
 */
public class R100PSta01Sum implements Serializable{
	private int views;
	private int reaches;
	private int likes;
	private int follows;
	private int piep;
	private String piepDate;
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getReaches() {
		return reaches;
	}
	public void setReaches(int reaches) {
		this.reaches = reaches;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getFollows() {
		return follows;
	}
	public void setFollows(int follows) {
		this.follows = follows;
	}
	public int getPiep() {
		return piep;
	}
	public void setPiep(int piep) {
		this.piep = piep;
	}
	public String getPiepDate() {
		return piepDate;
	}
	public void setPiepDate(String piepDate) {
		this.piepDate = piepDate;
	}
	@Override
	public String toString() {
		return "R100PSta01Sum [views=" + views + ", reaches=" + reaches
				+ ", likes=" + likes + ", follows=" + follows + ", piep=" + piep
				+ ", piepDate=" + piepDate + "]";
	}
	
}
