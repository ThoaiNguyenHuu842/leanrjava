package com.ohhay.piepme.mongo.entities.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create May 24, 2017
 */
@Document(collection = QbMongoCollectionsName.R000)
public class R000PMG implements Serializable{
	@Field("METHOD")
	private String method;
	@Field("URL")
	private String url;
	@Field("PRAMS")
	private String prams;
	@Field("IP")
	private String ip;
	@Field("FO100")
	private int fo100;
	@Field("TS")
	private Date ts;
	@Field("SRC")
	private String src;
	@Field("MODEL")
	private String model;
	@Field("OS")
	private String os;
	@Field("VERSION")
	private int version;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPrams() {
		return prams;
	}
	public void setPrams(String prams) {
		this.prams = prams;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
