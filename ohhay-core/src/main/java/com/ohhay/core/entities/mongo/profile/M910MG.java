package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
/**
 * @author ThoaiNH
 * create: 16/12/2014
 * store friend relation between two user
 */
@Document(collection = QbMongoCollectionsName.M910)
public class M910MG implements Serializable{
	@Field("_id")
	private int id;
	@Field("FO100")
	private int fo100;//fo100 chu danh ba
	@Field("FO100C")
	private int fo100c;//fo100 cua sdt 
	@Field("MV911")
	private String mv911;//ten luu o danh ba
	@Field("MV907")
	private String mv907;//so dien thoai
	@Field("MN912")
	private int mn912;//shareable: 1-Y, 0-N
	@Field("MV913")
	private String mv913;//src: 
	@Transient
	private String hoten;
	@Transient
	private String phone;
	@Transient
	private String avatar;
	@Transient
	private String link;
	
	public String getMv911() {
		return mv911;
	}
	public void setMv911(String mv911) {
		this.mv911 = mv911;
	}
	
	public String getMv907() {
		return mv907;
	}
	public void setMv907(String mv907) {
		this.mv907 = mv907;
	}
	
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMn912() {
		return mn912;
	}
	public void setMn912(int mn912) {
		this.mn912 = mn912;
	}
	
	public int getFo100c() {
		return fo100c;
	}
	public void setFo100c(int fo100c) {
		this.fo100c = fo100c;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[mv907:"+mv907+", mv911"+mv911+"]";
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
