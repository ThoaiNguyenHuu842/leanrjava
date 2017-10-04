package com.ohhay.piepme.mongo.entities.other;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

/**
 * @author ThoaiVt
 * @date 07-07-2017
 */
public class R100VMG implements Serializable {
	private int fo100;
	private String urlAvarta;
	private String nv106;
	private int totalUsed;
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
	public int getTotalUsed() {
		return totalUsed;
	}
	public void setTotalUsed(int totalUsed) {
		this.totalUsed = totalUsed;
	}

}
