package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Apr 14, 2017
 * affiliate info
 */
public class A100PMG implements Serializable{
	public static final String AV105_NEW = "NEW";
	public static final String AV105_CONFIRMED = "CON";
	public static final String AV105_REJECTED = "REJ";
	@Field("FO100C")
	private int fo100c;//admin account confirm
	@Field("AV101")
	private String av101;//first name
	@Field("AV102")
	private String av102;//last name
	@Field("AV103")
	private String av103;//id number
	@Field("AV104")
	private String av104;//id image
	@Field("AV105")
	private String av105;//status "CON","REJ" or "NEW" 
	@Field("AV107")
	private String av107;//phone
	@Field("AV111")
	private String av111;//address
	@Field("AD110")
	private Date ad110;//birthday
	@Field("AD106")
	private Date ad106;//update date
	@Field("AD108")
	private Date ad108;//create date
	@Field("AD109")
	private Date ad109;//confirm date
	@Transient
	private String ad110Str;
	public A100PMG(int fo100c, String av101, String av102, String av103,
			String av104, String av105, String av107, Date ad110, Date ad106,
			Date ad108, Date ad109, String av111) {
		super();
		this.fo100c = fo100c;
		this.av101 = av101;
		this.av102 = av102;
		this.av103 = av103;
		this.av104 = av104;
		this.av105 = av105;
		this.av107 = av107;
		this.ad110 = ad110;
		this.ad106 = ad106;
		this.ad108 = ad108;
		this.ad109 = ad109;
		this.av111 = av111;
	}

	public int getFo100c() {
		return fo100c;
	}

	public void setFo100c(int fo100c) {
		this.fo100c = fo100c;
	}

	public String getAv101() {
		return av101;
	}

	public void setAv101(String av101) {
		this.av101 = av101;
	}

	public String getAv102() {
		return av102;
	}

	public void setAv102(String av102) {
		this.av102 = av102;
	}

	public String getAv103() {
		return av103;
	}

	public void setAv103(String av103) {
		this.av103 = av103;
	}

	public String getAv104() {
		return av104;
	}

	public void setAv104(String av104) {
		this.av104 = av104;
	}

	public String getAv105() {
		return av105;
	}

	public void setAv105(String av105) {
		this.av105 = av105;
	}

	public String getAv107() {
		return av107;
	}

	public void setAv107(String av107) {
		this.av107 = av107;
	}

	public Date getAd110() {
		return ad110;
	}

	public void setAd110(Date ad110) {
		this.ad110 = ad110;
	}

	public Date getAd106() {
		return ad106;
	}

	public void setAd106(Date ad106) {
		this.ad106 = ad106;
	}

	public Date getAd108() {
		return ad108;
	}

	public void setAd108(Date ad108) {
		this.ad108 = ad108;
	}

	public Date getAd109() {
		return ad109;
	}

	public void setAd109(Date ad109) {
		this.ad109 = ad109;
	}

	public String getAv111() {
		return av111;
	}

	public void setAv111(String av111) {
		this.av111 = av111;
	}
	
	

	public String getAd110Str() {
		return ad110Str;
	}

	public void setAd110Str(String ad110Str) {
		this.ad110Str = ad110Str;
	}

	@Override
	public String toString() {
		return "A100PMG [fo100c=" + fo100c + ", av101=" + av101 + ", av102="
				+ av102 + ", av103=" + av103 + ", av104=" + av104 + ", av105="
				+ av105 + ", av107=" + av107 + ", av111=" + av111 + ", ad110="
				+ ad110 + ", ad106=" + ad106 + ", ad108=" + ad108 + ", ad109="
				+ ad109 + "]";
	}
}	
