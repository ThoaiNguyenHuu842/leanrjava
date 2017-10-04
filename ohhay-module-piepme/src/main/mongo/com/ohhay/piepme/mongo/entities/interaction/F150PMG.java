package com.ohhay.piepme.mongo.entities.interaction;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * MONGODB COLLECTION - flowing
 * @author ThoaiNH
 * create Apr 13, 2017
 */
@Document(collection=QbMongoCollectionsName.F150)
public class F150PMG implements Serializable {
	@Id
	private int id;
	@Field("FO100")
	private int fo100;//nguoi theo doi
	@Field("FO100T")
	private int fo100t;//nguoi duoc theo doi
	@Field("PD156")
	private Date pd156;
	@Transient
	private String nv101;
	@Transient
	private String nv102;

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
	public int getFo100t() {
		return fo100t;
	}
	public void setFo100t(int fo100t) {
		this.fo100t = fo100t;
	}
	public Date getPd156() {
		return pd156;
	}
	public void setPd156(Date pd156) {
		this.pd156 = pd156;
	}
	public String getNv101() {
		return nv101;
	}
	public void setNv101(String nv101) {
		this.nv101 = nv101;
	}
	public String getNv102() {
		return nv102;
	}
	public void setNv102(String nv102) {
		this.nv102 = nv102;
	}
	@Override
	public String toString() {
		return "F150PMG [id=" + id + ", fo100=" + fo100 + ", fo100t=" + fo100t + ", pd156=" + pd156 + ", nv101=" + nv101
				+ ", nv102=" + nv102 + "]";
	}
}
