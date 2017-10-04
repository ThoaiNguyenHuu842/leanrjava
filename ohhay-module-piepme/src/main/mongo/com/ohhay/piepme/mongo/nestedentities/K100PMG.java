package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Jul 11, 2016
 * business info
 */
public class K100PMG implements Serializable{
	public static final String KV105_NEW = "NEW";
	public static final String KV105_CONFIRMED = "CON";
	public static final String KV105_REJECTED = "REJ";
	@Field("FO100C")
	private int fo100c;//admin account confirm
	@Field("KV101")
	private String kv101;//company name
	@Field("KV102")
	private String kv102;//company address
	@Field("KV103")
	private String kv103;//tax number
	@Field("KV105")
	private String kv105;//status "CON","REJ" or "NEW" 
	@Field("KV106")
	private String kv106;//loi gioi thieu
	@Field("KV107")
	private String kv107;//phone
	@Field("KV110")
	private String kv110;//affiliate id
	@Field("KV111")
	private String kv111;//device token for business account
	@Field("KV112")
	private String kv112;//ly do reject moi nhat
	@Field("KV113")
	private String kv113;//uuid luu tam torng qua trinh upgrade DN
	@Field("KD106")
	private Date kd106;//update date
	@Field("KD108")
	private Date kd108;//create date
	@Field("KD109")
	private Date kd109;//confirm date
	@Field("LICENSE_IMGS")
	private List<String> licenseImgs;//license images
	@Field("KV101_STEM")
	private String kv101Stem;
	public K100PMG(int fo100c, String kv101, String kv102, String kv103,
			String kv105, String kv106, String kv107, Date kd106, Date kd108,
			Date kd109, List<String> licenseImgs, String kv110, String kv101Stem) {
		super();
		this.fo100c = fo100c;
		this.kv101 = kv101;
		this.kv102 = kv102;
		this.kv103 = kv103;
		this.kv105 = kv105;
		this.kv106 = kv106;
		this.kv107 = kv107;
		this.kd106 = kd106;
		this.kd108 = kd108;
		this.kd109 = kd109;
		this.licenseImgs = licenseImgs;
		this.kv110 = kv110;
		this.kv101Stem = kv101Stem;
	}
	public int getFo100c() {
		return fo100c;
	}
	public void setFo100c(int fo100c) {
		this.fo100c = fo100c;
	}
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
	public String getKv105() {
		return kv105;
	}
	public void setKv105(String kv105) {
		this.kv105 = kv105;
	}
	public Date getKd106() {
		return kd106;
	}
	public void setKd106(Date kd106) {
		this.kd106 = kd106;
	}
	public Date getKd108() {
		return kd108;
	}
	public void setKd108(Date kd108) {
		this.kd108 = kd108;
	}
	public Date getKd109() {
		return kd109;
	}
	public void setKd109(Date kd109) {
		this.kd109 = kd109;
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
	public List<String> getLicenseImgs() {
		return licenseImgs;
	}
	public void setLicenseImgs(List<String> licenseImgs) {
		this.licenseImgs = licenseImgs;
	}
	public String getKv110() {
		return kv110;
	}
	public void setKv110(String kv110) {
		this.kv110 = kv110;
	}
	public String getKv111() {
		return kv111;
	}
	public void setKv111(String kv111) {
		this.kv111 = kv111;
	}
	public String getKv101Stem() {
		return kv101Stem;
	}
	public void setKv101Stem(String kv101Stem) {
		this.kv101Stem = kv101Stem;
	}
	public String getKv112() {
		return kv112;
	}
	public void setKv112(String kv112) {
		this.kv112 = kv112;
	}
	public String getKv113() {
		return kv113;
	}
	public void setKv113(String kv113) {
		this.kv113 = kv113;
	}
	@Override
	public String toString() {
		return "K100PMG [fo100c=" + fo100c + ", kv101=" + kv101 + ", kv102=" + kv102 + ", kv103=" + kv103 + ", kv105=" + kv105 + ", kv106=" + kv106 + ", kv107="
				+ kv107 + ", kv110=" + kv110 + ", kv111=" + kv111 + ", kd106=" + kd106 + ", kd108=" + kd108 + ", kd109=" + kd109 + ", licenseImgs="
				+ licenseImgs + ", kv101Stem=" + kv101Stem + "]";
	}
}	
