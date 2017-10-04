package com.ohhay.web.lego.entities.mongo.web;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Oct 27, 2015
 */
@Document(collection = QbMongoCollectionsName.E950)
public class E950MG implements Serializable{
	@Id
	protected long id;
	@Field("WEBID")
	protected long webId;
	@Field("FO100")
	protected int fo100;
	@Field("EV951")
	protected String ev951;//html
	@Field("EV952")
	protected String ev952;//site name
	@Field("EV953")
	protected String ev953;//mobile html
	@Field ("BONEVO_VERSION")
	private String bonevoVersion;//version of code
	public E950MG() {
		// TODO Auto-generated constructor stub
		this.bonevoVersion = ApplicationConstant.BONEVO_VERSION;
	}
	
	public E950MG(E950MG e) {
		super();
		this.id = e.getId();
		this.webId = e.getWebId();
		this.fo100 = e.getFo100();
		this.ev951 = e.getEv951();
		this.ev952 = e.getEv952();
		this.ev953 = e.getEv953();
		this.bonevoVersion = e.getBonevoVersion();
	}
	
	
	public String getBonevoVersion() {
		return bonevoVersion;
	}

	public void setBonevoVersion(String bonevoVersion) {
		this.bonevoVersion = bonevoVersion;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getWebId() {
		return webId;
	}
	public void setWebId(long webId) {
		this.webId = webId;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getEv951() {
		return ev951;
	}
	public void setEv951(String ev951) {
		this.ev951 = ev951;
	}
	public String getEv952() {
		return ev952;
	}
	public void setEv952(String ev952) {
		this.ev952 = ev952;
	}
	public String getEv953() {
		return ev953;
	}
	public void setEv953(String ev953) {
		this.ev953 = ev953;
	}
	
}
