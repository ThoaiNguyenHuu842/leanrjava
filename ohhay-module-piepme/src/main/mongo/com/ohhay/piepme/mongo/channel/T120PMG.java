package com.ohhay.piepme.mongo.channel;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Jul 28, 2017
 */
@Document(collection = QbMongoCollectionsName.T120)
public class T120PMG implements Serializable{
	@Id
	private int id;
	@Field("FT100")
	private int ft100;
	@Field("FT110")
	private int ft110;
	@Field("FO100")
	private int fo100;
	@Field("TV129")
	private String tv129;
	@Field("TL128")
	private Date tl128;
	@Field("TL122")
	private Date tl122;//ngay xac nhan
	@Field("TL123")
	private Date tl123;//ngay tu choi
	public T120PMG(){}
	public T120PMG(int id, int ft100, int ft110, int fo100, String tv129) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.ft100 = ft100;
		this.fo100 = fo100;
		this.tv129 = tv129;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFt100() {
		return ft100;
	}
	public void setFt100(int ft100) {
		this.ft100 = ft100;
	}
	public int getFt110() {
		return ft110;
	}
	public void setFt110(int ft110) {
		this.ft110 = ft110;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getTv129() {
		return tv129;
	}
	public void setTv129(String tv129) {
		this.tv129 = tv129;
	}
	public Date getTl128() {
		return tl128;
	}
	public void setTl128(Date tl128) {
		this.tl128 = tl128;
	}
	public Date getTl122() {
		return tl122;
	}
	public void setTl122(Date tl122) {
		this.tl122 = tl122;
	}
	public Date getTl123() {
		return tl123;
	}
	public void setTl123(Date tl123) {
		this.tl123 = tl123;
	}
	
}
