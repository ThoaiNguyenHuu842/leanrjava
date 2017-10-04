package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Transient;

/**
 * @author ThoaiNH
 * create Feb 24, 2017
 */
public class R100PSta01 implements Serializable{
	private int reaches;
	private int reaches05km;
	private int reaches10km;
	private int reaches30km;
	private int reaches100km;
	private List<R100PSta01Sum> piepsSta; //statistic info of each piep-time
	private R100PSta01Sum sta; //statistic info
	public int getReaches() {
		return reaches;
	}
	public void setReaches(int reaches) {
		this.reaches = reaches;
	}
	public int getReaches05km() {
		return reaches05km;
	}
	public void setReaches05km(int reaches05km) {
		this.reaches05km = reaches05km;
	}
	public int getReaches10km() {
		return reaches10km;
	}
	public void setReaches10km(int reaches10km) {
		this.reaches10km = reaches10km;
	}
	public int getReaches30km() {
		return reaches30km;
	}
	public void setReaches30km(int reaches30km) {
		this.reaches30km = reaches30km;
	}
	public int getReaches100km() {
		return reaches100km;
	}
	public void setReaches100km(int reaches100km) {
		this.reaches100km = reaches100km;
	}
	public List<R100PSta01Sum> getPiepsSta() {
		return piepsSta;
	}
	public void setPiepsSta(List<R100PSta01Sum> piepsSta) {
		this.piepsSta = piepsSta;
	}
	public R100PSta01Sum getSta() {
		return sta;
	}
	public void setSta(R100PSta01Sum sta) {
		this.sta = sta;
	}
	@Override
	public String toString() {
		return "R100PSta01 [reaches=" + reaches + ", reaches05km=" + reaches05km
				+ ", reaches10km=" + reaches10km + ", reaches30km="
				+ reaches30km + ", reaches100km=" + reaches100km + ", piepsSta="
				+ piepsSta + ", sta=" + sta + "]";
	}
	
}
