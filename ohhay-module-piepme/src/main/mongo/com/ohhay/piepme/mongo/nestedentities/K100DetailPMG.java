package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create Jul 11, 2016
 */
public class K100DetailPMG implements Serializable{
	private String kv101;//company name
	private String kv102;//company address
	private String kv103;//tax number
	private String kv106;//loi gioi thieu
	private String kv107;//phone
	private int totalPiep;
	private int totalFollower;
	private int totalLike;
	private int followingStt; 
	public String getKv101() {
		return kv101;
	}
	public void setKv101(String kv101) {
		this.kv101 = kv101;
	}
	public String getKv102() {
		return kv102;
	}
	public void setKv102(String kv102) {
		this.kv102 = kv102;
	}
	public String getKv103() {
		return kv103;
	}
	public void setKv103(String kv103) {
		this.kv103 = kv103;
	}
	public String getKv106() {
		return kv106;
	}
	public void setKv106(String kv106) {
		this.kv106 = kv106;
	}
	public String getKv107() {
		return kv107;
	}
	public void setKv107(String kv107) {
		this.kv107 = kv107;
	}
	public int getTotalPiep() {
		return totalPiep;
	}
	public void setTotalPiep(int totalPiep) {
		this.totalPiep = totalPiep;
	}
	public int getTotalFollower() {
		return totalFollower;
	}
	public void setTotalFollower(int totalFollower) {
		this.totalFollower = totalFollower;
	}
	public int getTotalLike() {
		return totalLike;
	}
	public void setTotalLike(int totalLike) {
		this.totalLike = totalLike;
	}
	public int getFollowingStt() {
		return followingStt;
	}
	public void setFollowingStt(int followingStt) {
		this.followingStt = followingStt;
	}
	@Override
	public String toString() {
		return "K100DetailPMG [kv101=" + kv101 + ", kv102=" + kv102 + ", kv103="
				+ kv103 + ", kv106=" + kv106 + ", kv107=" + kv107
				+ ", totalPiep=" + totalPiep + ", totalFollower="
				+ totalFollower + ", totalLike=" + totalLike + ", followingStt="
				+ followingStt + "]";
	}
	
}
