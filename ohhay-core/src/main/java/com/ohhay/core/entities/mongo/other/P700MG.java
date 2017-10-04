package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;


/**
 * @author ThoaiNH
 * create Apr 23, 2016
 * bonevo free video
 */
@Document(collection = QbMongoCollectionsName.P700)
public class P700MG implements Serializable {
	@Id
	private int id;
	@Field("FO100")
	private int fo100;//uploader
	@Field("PV701")
	private String pv701;// url video
	@Field("PV702")
	private String pv702;// url thumbnail
	@Field("PD708")
	private Date pd708;// date created
	@Field("DATE_DELETE")
	private Date dateDelete;
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
	public String getPv701() {
		return pv701;
	}
	public void setPv701(String pv701) {
		this.pv701 = pv701;
	}
	public String getPv702() {
		return pv702;
	}
	public void setPv702(String pv702) {
		this.pv702 = pv702;
	}
	public Date getPd708() {
		return pd708;
	}
	public void setPd708(Date pd708) {
		this.pd708 = pd708;
	}
	
	public Date getDateDelete() {
		return dateDelete;
	}
	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}
	@Override
	public String toString() {
		return "P700MG [id=" + id + ", fo100=" + fo100 + ", pv701=" + pv701 + ", pv702=" + pv702 + ", pd708=" + pd708
				+ ", dateDelete=" + dateDelete + "]";
	}
	
}
