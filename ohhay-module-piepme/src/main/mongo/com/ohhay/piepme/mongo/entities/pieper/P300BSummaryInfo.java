package com.ohhay.piepme.mongo.entities.pieper;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create Jun 8, 2017
 */
public class P300BSummaryInfo implements Serializable{
	private int totalNearestPieper;
	private int totalHotPieper;
	public int getTotalNearestPieper() {
		return totalNearestPieper;
	}
	public void setTotalNearestPieper(int totalNearestPieper) {
		this.totalNearestPieper = totalNearestPieper;
	}
	public int getTotalHotPieper() {
		return totalHotPieper;
	}
	public void setTotalHotPieper(int totalHotPieper) {
		this.totalHotPieper = totalHotPieper;
	}
	
}	
