package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Apr 11, 2016
 * BONEVO free image
 */
@Document(collection = QbMongoCollectionsName.P500)
public class P500MG implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("PV501")
	private String pv501;//url
	@Field("PV502")
	private String pv502;//url thumbnail
	@Field("PD506")
	private Date pd506;//last update
	@Field("PD508")
	private Date pd508;//date created
	@Field("DATE_DELETE")
	private Date dateDelete;
	@Transient
	private int rowss;
	
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPv501() {
		return pv501;
	}
	public void setPv501(String pv501) {
		this.pv501 = pv501;
	}
	public String getPv502() {
		return pv502;
	}
	public void setPv502(String pv502) {
		this.pv502 = pv502;
	}
	public Date getPd506() {
		return pd506;
	}
	public void setPd506(Date pd506) {
		this.pd506 = pd506;
	}
	public Date getPd508() {
		return pd508;
	}
	public void setPd508(Date pd508) {
		this.pd508 = pd508;
	}

	public int getFo100() {
		return fo100;
	}
	public Date getDateDelete() {
		return dateDelete;
	}
	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	@Override
	public String toString() {
		return "P500MG [id=" + id + ", fo100=" + fo100 + ", pv501=" + pv501 + ", pv502=" + pv502 + ", pd506=" + pd506
				+ ", pd508=" + pd508 + ", dateDelete=" + dateDelete + "]";
	}
	
}
