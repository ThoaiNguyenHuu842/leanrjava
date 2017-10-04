package com.ohhay.core.criteria;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create 15/05/2015
 * vote parameter
 */
public class R100VoteCriteria implements Serializable{
	private String esFo100;
	private String rv101;
	
	public String getEsFo100() {
		return esFo100;
	}
	public void setEsFo100(String esFo100) {
		this.esFo100 = esFo100;
	}
	public String getRv101() {
		return rv101;
	}
	public void setRv101(String rv101) {
		this.rv101 = rv101;
	}
}
