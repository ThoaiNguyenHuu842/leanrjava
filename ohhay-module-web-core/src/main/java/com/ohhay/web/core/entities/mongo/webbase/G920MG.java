package com.ohhay.web.core.entities.mongo.webbase;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * google map info embed in C920
 */
public class G920MG implements Serializable{
	@Field("GV921")
	private String gv921;//dia chi full
	@Field("GV922")
	private String gv922;//marker image
	@Field("GN923")
	private double gn923;//la: vi do
	@Field("GN924")
	private double gn924;//log: kinh do
	@Field("GV925")
	private String gv925;//Mark address
	
	public String getGv921() {
		return gv921;
	}
	public void setGv921(String gv921) {
		this.gv921 = gv921;
	}
	public String getGv922() {
		return gv922;
	}
	public void setGv922(String gv922) {
		this.gv922 = gv922;
	}
	public double getGn923() {
		return gn923;
	}
	public void setGn923(double gn923) {
		this.gn923 = gn923;
	}
	public double getGn924() {
		return gn924;
	}
	public void setGn924(double gn924) {
		this.gn924 = gn924;
	}
	public String getGv925() {
		return gv925;
	}
	public void setGv925(String gv925) {
		this.gv925 = gv925;
	}
	
	
}
