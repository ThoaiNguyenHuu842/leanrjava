package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create Jun 30, 2017
 */
public class P300VConInfo implements Serializable {
	private int id;
	private int fo100;
	private String pv301;//title
	private String pv302;//key đi kèm giải mã khi pieper public
	private String pv305;//PIEPER text content
	private String pv314;//preview content
	private String pv304;
	private String vd303;
	private String vd304;
	private String vv307;//trang thai
	private String vv308;//doi tuong mua hang
	private String vv310;//sent status
	private int totalUsed;
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getPv301() {
		return pv301;
	}
	public void setPv301(String pv301) {
		this.pv301 = pv301;
	}
	public String getPv302() {
		return pv302;
	}
	public void setPv302(String pv302) {
		this.pv302 = pv302;
	}
	public String getPv305() {
		return pv305;
	}
	public void setPv305(String pv305) {
		this.pv305 = pv305;
	}
	public String getPv314() {
		return pv314;
	}
	public void setPv314(String pv314) {
		this.pv314 = pv314;
	}
	public String getVd303() {
		return vd303;
	}
	public void setVd303(String vd303) {
		this.vd303 = vd303;
	}
	public String getVd304() {
		return vd304;
	}
	public void setVd304(String vd304) {
		this.vd304 = vd304;
	}
	public int getTotalUsed() {
		return totalUsed;
	}
	public void setTotalUsed(int totalUsed) {
		this.totalUsed = totalUsed;
	}
	public String getPv304() {
		return pv304;
	}
	public void setPv304(String pv304) {
		this.pv304 = pv304;
	}
	public String getVv307() {
		return vv307;
	}
	public void setVv307(String vv307) {
		this.vv307 = vv307;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVv308() {
		return vv308;
	}
	public void setVv308(String vv308) {
		this.vv308 = vv308;
	}
	public String getVv310() {
		return vv310;
	}
	public void setVv310(String vv310) {
		this.vv310 = vv310;
	}
	
	
}
