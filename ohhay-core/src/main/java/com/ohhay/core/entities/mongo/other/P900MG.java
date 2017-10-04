package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
/**
 * @author ThoaiNH
 * create 15/07/2015
 * store old picture to delete by cron-job
 */
@Document(collection = QbMongoCollectionsName.M150)
public class P900MG implements Serializable{
	@Field("FO100")
	private int fo100;
	@Field("PV901")
	private String pv901;
	@Field("PD902")
	private Date pd902;
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getPv901() {
		return pv901;
	}
	public void setPv901(String pv901) {
		this.pv901 = pv901;
	}
	public Date getPd902() {
		return pd902;
	}
	public void setPd902(Date pd902) {
		this.pd902 = pd902;
	}
	
	
}
