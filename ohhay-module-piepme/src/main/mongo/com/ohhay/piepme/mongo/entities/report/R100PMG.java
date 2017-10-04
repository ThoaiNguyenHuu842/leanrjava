package com.ohhay.piepme.mongo.entities.report;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * MONGODB COLLECTION - tracking info
 * @author ThoaiNH
 * create Feb 23, 2017
 */
@Document(collection = QbMongoCollectionsName.R100)
public class R100PMG implements Serializable{
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("FO100T")
	private int fo100t;//owner of pieper
	@Field("FP300")
	private int fp300;
	@Field("RN101")
	private int rn101;//repiep time
	@Field("RV102")
	private String rv102;//SEN or VIE
	@Field("RD106")
	private Date rd106;
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
	public int getFp300() {
		return fp300;
	}
	public void setFp300(int fp300) {
		this.fp300 = fp300;
	}
	public int getRn101() {
		return rn101;
	}
	public void setRn101(int rn101) {
		this.rn101 = rn101;
	}
	public Date getRd106() {
		return rd106;
	}
	public void setRd106(Date rd106) {
		this.rd106 = rd106;
	}
	public String getRv102() {
		return rv102;
	}
	public void setRv102(String rv102) {
		this.rv102 = rv102;
	}
	@Override
	public String toString() {
		return "R100PMG [id=" + id + ", fo100=" + fo100 + ", fo100t=" + fo100t + ", fp300=" + fp300 + ", rn101=" + rn101
				+ ", rv102=" + rv102 + ", rd106=" + rd106 + "]";
	}
	
}
