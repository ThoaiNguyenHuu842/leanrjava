package com.ohhay.web.lego.entities.mongo.topic;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Nov 19, 2015
 */
@Document(collection = QbMongoCollectionsName.ET950)
public class ET950MG implements Serializable{
	@Id
	protected long id;
	@Field("WEBID")
	protected long webId;
	@Field("FO100")
	protected int fo100;
	@Field("EV951")
	protected String ev951;
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
	
}
