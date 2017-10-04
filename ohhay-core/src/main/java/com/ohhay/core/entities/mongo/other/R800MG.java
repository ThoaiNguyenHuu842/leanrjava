package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThongQB
 * create 28/08/2015
 * Report entity
 */
@SuppressWarnings("serial")
@Document(collection = QbMongoCollectionsName.R800)
public class R800MG implements Serializable{
	@Id
	private int id;
	
	@Field("FM150")
	private int fm150;//topic id
    
	@Field("FO100")
	private int fo100;//userLogin
    
	@Field("FO100R")
	private int fo100r;//topic owner
    
	@Field("RV801")
	private String rv801;//content
    
	@Field("RD808")
	private String rd808;//date
	
	@Field("RD808_STRING")
	private String rd808string;//date
	
	@Transient
	private String hv101;
	
	@Transient
	private String hoten;
	
	@Transient
	private String phone;
	
	@Transient
	private String avatar;
	
	@Transient
	private String hv101share;
	
	@Transient
	private String mv151;
	
	@Transient
	private String hv101r;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getFm150() {
		return fm150;
	}
	public void setFm150(int fm150) {
		this.fm150 = fm150;
	}
	
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	
	public int getFo100r() {
		return fo100r;
	}
	public void setFo100r(int fo100r) {
		this.fo100r = fo100r;
	}
	
	public String getRv801() {
		return rv801;
	}
	public void setRv801(String rv801) {
		this.rv801 = rv801;
	}
	
	public String getRd808() {
		return rd808;
	}
	public void setRd808(String rd808) {
		this.rd808 = rd808;
	}
	
	public String getRd808string() {
		return rd808string;
	}
	public void setRd808string(String rd808string) {
		this.rd808string = rd808string;
	}
	
	public String getHv101() {
		return hv101;
	}
	public void setHv101(String hv101) {
		this.hv101 = hv101;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getHv101share() {
		return hv101share;
	}
	public void setHv101share(String hv101share) {
		this.hv101share = hv101share;
	}
	public String getMv151() {
		return mv151;
	}
	public void setMv151(String mv151) {
		this.mv151 = mv151;
	}
	public String getHv101r() {
		return hv101r;
	}
	public void setHv101r(String hv101r) {
		this.hv101r = hv101r;
	}
}
