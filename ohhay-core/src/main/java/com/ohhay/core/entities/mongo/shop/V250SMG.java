package com.ohhay.core.entities.mongo.shop;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Jun 17, 2016
 * shop online 2016 product
 */
@Document(collection = QbMongoCollectionsName.V250)
public class V250SMG implements Serializable{
	@Id 
	private long id;
	@Field("FO100")
	private int fo100;
	@Field("VV251")
	private String vv251;
	@Field("OTAGS")
	private List<String> otags;
	@Field("VN270")
	private int vn270;
	@Field("VD279")
	private Date vd279;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getVv251() {
		return vv251;
	}
	public void setVv251(String vv251) {
		this.vv251 = vv251;
	}
	public List<String> getOtags() {
		return otags;
	}
	public void setOtags(List<String> otags) {
		this.otags = otags;
	}
	public int getVn270() {
		return vn270;
	}
	public void setVn270(int vn270) {
		this.vn270 = vn270;
	}
	public Date getVd279() {
		return vd279;
	}
	public void setVd279(Date vd279) {
		this.vd279 = vd279;
	}
	@Override
	public String toString() {
		return "V250S [id=" + id + ", fo100=" + fo100 + ", vv251=" + vv251
				+ ", otags=" + otags + ", vn270=" + vn270 + ", vd279=" + vd279
				+ "]";
	}
}
