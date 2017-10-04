package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

import com.ohhay.core.constant.OhhayDefaultData;

/**
 * @author ThoaiNH
 * create Nov 11, 2016
 * comment of pieper
 */
public class PieperComment implements Serializable{
	private int id;
	private int fo100;
	private int pieperId;//id of pieper
	private String mv903;
	private String mv905;
	private String mv908;
	private String nv101;
	private String nv106;
	private String nv100;
	private String region;
	private String urlAvarta;//avarta of owner
	private String pd378String;//created time
	private String comment;
	
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
	public int getPieperId() {
		return pieperId;
	}
	public void setPieperId(int pieperId) {
		this.pieperId = pieperId;
	}
	public String getMv903() {
		return mv903;
	}
	public void setMv903(String mv903) {
		this.mv903 = mv903;
	}
	public String getMv905() {
		return mv905;
	}
	public void setMv905(String mv905) {
		this.mv905 = mv905;
	}
	public String getMv908() {
		return mv908;
	}
	public void setMv908(String mv908) {
		this.mv908 = mv908;
	}
	public String getNv101() {
		return nv101;
	}
	public void setNv101(String nv101) {
		this.nv101 = nv101;
	}
	public String getNv106() {
		return nv106;
	}
	public void setNv106(String nv106) {
		this.nv106 = nv106;
	}
	public String getNv100() {
		return nv100;
	}
	public void setNv100(String nv100) {
		this.nv100 = nv100;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPd378String() {
		return pd378String;
	}
	public void setPd378String(String pd378String) {
		this.pd378String = pd378String;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}
	public String getImgLink()
	{
		if(region != null)
			return "https://"+region.toLowerCase()+"-oohhay.s3.amazonaws.com/"+fo100+"/";
		else
			return "https://oohhay.s3.amazonaws.com/"+fo100+"/";
	}
	public String getUrlAvarta() {
		String urlAvarta = null;
		if (mv908 != null && mv908.length() > 0)
			urlAvarta = getImgLink() + mv908;
		else
		{
			if(mv905.toLowerCase().equals("m"))
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_M;
			else if(mv905.toLowerCase().equals("f"))
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_F;
			else
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_N;
		}
		return urlAvarta;
	}
	@Override
	public String toString() {
		return "PieperComment [id=" + id + ", fo100=" + fo100 + ", pieperId="
				+ pieperId + ", mv903=" + mv903 + ", mv905=" + mv905
				+ ", mv908=" + mv908 + ", nv101=" + nv101 + ", nv106=" + nv106
				+ ", nv100=" + nv100 + ", region=" + region + ", urlAvarta="
				+ urlAvarta + ", pd378String=" + pd378String + ", comment="
				+ comment + "]";
	}
	
}
