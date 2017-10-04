package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create May 8, 2017
 */
public class G100SummaryInfo implements Serializable{
	private int id;
	private String gv101;
	private String gv102;
	public G100SummaryInfo(int id, String gv101, String gv102) {
		super();
		this.id = id;
		this.gv101 = gv101;
		this.gv102 = gv102;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGv101() {
		return gv101;
	}
	public void setGv101(String gv101) {
		this.gv101 = gv101;
	}
	public String getGv102() {
		return gv102;
	}
	public void setGv102(String gv102) {
		this.gv102 = gv102;
	}
}
