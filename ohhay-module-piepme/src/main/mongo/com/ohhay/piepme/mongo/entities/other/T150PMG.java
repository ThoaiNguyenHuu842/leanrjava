package com.ohhay.piepme.mongo.entities.other;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;


/**
 * @author ThoaiVt
 * date 21/09/2016
 */
@Document(collection = QbMongoCollectionsName.T150)
public class T150PMG {
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("TV151")
	private String tv151;
	@Field("TN152")
	private int tn152;
	@Field("TL156")
	private Date tl146;//update date
	@Field("TL158")
	private Date tl148;//created date
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
	public String getTv151() {
		return tv151;
	}
	public void setTv151(String tv151) {
		this.tv151 = tv151;
	}
	public int getTn152() {
		return tn152;
	}
	public void setTn152(int tn152) {
		this.tn152 = tn152;
	}
	public Date getTl146() {
		return tl146;
	}
	public void setTl146(Date tl146) {
		this.tl146 = tl146;
	}
	public Date getTl148() {
		return tl148;
	}
	public void setTl148(Date tl148) {
		this.tl148 = tl148;
	}
	
	
}
