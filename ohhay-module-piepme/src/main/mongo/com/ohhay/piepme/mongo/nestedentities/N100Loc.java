package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create May 12, 2017
 */
public class N100Loc implements Serializable{
	private int id;
	private int fo100;
	private double distance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public N100Loc(int id, int fo100, double distance) {
		super();
		this.id = id;
		this.fo100 = fo100;
		this.distance = distance;
	}
}
