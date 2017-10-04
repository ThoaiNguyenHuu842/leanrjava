package com.ohhay.piepme.mongo.entities.other;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.piepme.mongo.nestedentities.P160PMG;
import com.ohhay.piepme.mongo.nestedentities.P170PMG;

/**
 * MONGODB COLLECTION - OTP code management
 * @author ThoaiNH
 * create Mar 3, 2017
 */
@Document(collection = QbMongoCollectionsName.P150)
public class P150PMG implements Serializable{
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("PA151")
	private List<P160PMG> pa151;//list OTP code sent to friends
	@Field("PV152")
	private String pv152;//action type ("CHA": change device, "LOS": lose device)
	@Field("PN154")
	private int pn154;//number of  confirmation
	@Field("PA155")
	private List<P170PMG> pa155;//history confirmation
	@Field("PD156")
	private Date pd156;//date created
	@Field("PD157")
	private Date pd157;//date confirm success
	@Field("PD158")
	private Date pd158;//date update UUID
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
	public List<P160PMG> getPa151() {
		return pa151;
	}
	public void setPa151(List<P160PMG> pa151) {
		this.pa151 = pa151;
	}
	public String getPv152() {
		return pv152;
	}
	public void setPv152(String pv152) {
		this.pv152 = pv152;
	}
	public int getPn154() {
		return pn154;
	}
	public void setPn154(int pn154) {
		this.pn154 = pn154;
	}
	public Date getPd156() {
		return pd156;
	}
	public void setPd156(Date pd156) {
		this.pd156 = pd156;
	}
	public Date getPd157() {
		return pd157;
	}
	public void setPd157(Date pd157) {
		this.pd157 = pd157;
	}
	public List<P170PMG> getPa155() {
		return pa155;
	}
	public void setPa155(List<P170PMG> pa155) {
		this.pa155 = pa155;
	}
	public Date getPd158() {
		return pd158;
	}
	public void setPd158(Date pd158) {
		this.pd158 = pd158;
	}
	
}
