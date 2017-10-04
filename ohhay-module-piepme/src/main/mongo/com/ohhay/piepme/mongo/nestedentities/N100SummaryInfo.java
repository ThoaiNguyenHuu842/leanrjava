package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;
import java.util.List;

/**
 * @author ThoaiNH
 * create May 8, 2017
 */
public class N100SummaryInfo implements Serializable{
    private int id;
	private int fo100;
	private String urlAvarta;
	private String nv106;
	private List<Integer> fq400s;
	public N100SummaryInfo(int id, int fo100, String urlAvarta, String nv106) {
		super();
		this.id = id;
		this.fo100 = fo100;
		this.urlAvarta = urlAvarta;
		this.nv106 = nv106;
	}
	public N100SummaryInfo() {
		super();
	}
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
	public String getUrlAvarta() {
		return urlAvarta;
	}
	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}
	public String getNv106() {
		return nv106;
	}
	public void setNv106(String nv106) {
		this.nv106 = nv106;
	}
	public List<Integer> getFq400s() {
		return fq400s;
	}
	public void setFq400s(List<Integer> fq400s) {
		this.fq400s = fq400s;
	}
	@Override
	public String toString() {
		return "N100SummaryInfo [id=" + id + ", fo100=" + fo100 + ", urlAvarta=" + urlAvarta + ", nv106=" + nv106 + "]";
	}
}
