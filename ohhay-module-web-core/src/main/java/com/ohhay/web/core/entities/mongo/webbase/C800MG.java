package com.ohhay.web.core.entities.mongo.webbase;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

@Document(collection = QbMongoCollectionsName.C800)
public class C800MG{
	@Id
	public int id;// fc800
	@Field("PART")
	public List<C920MG> listC920mg;
	@Field("CV802")
	public String cv802;
	@Field("CL946")
	public Date cl946;
	@Field("CL948")
	public Date cl948;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<C920MG> getListC920mg() {
		return listC920mg;
	}
	public void setListC920mg(List<C920MG> listC920mg) {
		this.listC920mg = listC920mg;
	}
	public String getCv802() {
		return cv802;
	}
	public void setCv802(String cv802) {
		this.cv802 = cv802;
	}
	public Date getCl946() {
		return cl946;
	}
	public void setCl946(Date cl946) {
		this.cl946 = cl946;
	}
	public Date getCl948() {
		return cl948;
	}
	public void setCl948(Date cl948) {
		this.cl948 = cl948;
	}
	
}
