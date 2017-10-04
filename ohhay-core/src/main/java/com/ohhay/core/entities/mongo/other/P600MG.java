package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiVt create date Apr 21, 2016 ohhay-core
 */
@Document(collection = QbMongoCollectionsName.P600)
public class P600MG implements Serializable {
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("PV501")
	private String pv601;// url
	@Field("PD506")
	private Date pd506;// last update
	@Field("PD508")
	private Date pd508;// date created
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

	public String getPv601() {
		return pv601;
	}

	public void setPv601(String pv601) {
		this.pv601 = pv601;
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

	public Date getDateDelete() {
		return dateDelete;
	}

	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}

	@Override
	public String toString() {
		return "P600MG [id=" + id + ", fo100=" + fo100 + ", pv601=" + pv601 + ", pd506=" + pd506 + ", pd508=" + pd508
				+ ", dateDelete=" + dateDelete + "]";
	}

}
