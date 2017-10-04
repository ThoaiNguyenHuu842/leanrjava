package com.ohhay.core.entities.mongo.shop;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create 10:40 am 06/04/2015
 * * shop info:
 * - menu title
 * - menu icon
 * - list video
 */
@Document(collection = QbMongoCollectionsName.J910)
public class J910MG implements Serializable{
	@Id //FO100
	private int id;
	@Field("JV911")
	private String jv911; //menu title
	@Field("JV915")
	private String jv915; //menu icon font awesome
	@Field("JN916")
	private int jn916;//0: chua xác nhan orel, 1: da xac nhan
	@Field("L948")
	private java.util.Date vl948;//date update
	@Field("FK100_OREL")
	private int fk100;
	
	
	@Field("FN100_OREL")
	private int fn100;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJv911() {
		return jv911;
	}
	public void setJv911(String jv911) {
		this.jv911 = jv911;
	}
	/*
	 * get font of icon menu
	 */
	public String getJv915() {
		String []ss = jv915.split(" ");
		if(ss.length > 1)
			return ss[0];
		return jv915;
	}
	public void setJv915(String jv915) {
		this.jv915 = jv915;
	}
	public java.util.Date getVl948() {
		return vl948;
	}
	public void setVl948(java.util.Date vl948) {
		this.vl948 = vl948;
	}
	public int getFk100() {
		return fk100;
	}
	public void setFk100(int fk100) {
		this.fk100 = fk100;
	}
	public int getJn916() {
		return jn916;
	}
	public void setJn916(int jn916) {
		this.jn916 = jn916;
	}
	public int getFn100() {
		return fn100;
	}
	public void setFn100(int fn100) {
		this.fn100 = fn100;
	}
	
	
}
