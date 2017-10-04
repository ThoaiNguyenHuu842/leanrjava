package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create Jun 16, 2017
 */
public class K100SummaryInfo implements Serializable{
	private int id;
	private int fo100;
	private String kv101;
	private String nv106;
	private String urlAvarta;
	private int followingStt;
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
	public String getKv101() {
		return kv101;
	}
	public void setKv101(String kv101) {
		this.kv101 = kv101;
	}
	public String getUrlAvarta() {
		return urlAvarta;
	}
	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}
	public int getFollowingStt() {
		return followingStt;
	}
	public void setFollowingStt(int followingStt) {
		this.followingStt = followingStt;
	}
	public String getNv106() {
		return nv106;
	}
	public void setNv106(String nv106) {
		this.nv106 = nv106;
	}
	@Override
	public String toString() {
		return "K100SummaryInfo [id=" + id + ", fo100=" + fo100 + ", kv101="
				+ kv101 + ", nv106=" + nv106 + ", urlAvarta=" + urlAvarta
				+ ", followingStt=" + followingStt + "]";
	}
	
}
