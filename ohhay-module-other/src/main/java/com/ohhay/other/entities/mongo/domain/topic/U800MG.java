package com.ohhay.other.entities.mongo.domain.topic;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
/*
 * danh sach domain cua user
 */
@Document(collection = QbMongoCollectionsName.U800)
public class U800MG implements Serializable{
	/**
	 * 
	 */
	@Id
	private long id;//po100	
	private static final long serialVersionUID = 1L;
	@Field("FO100")
	private int fo100;
	@Field("UV801")
	private String uv801;//domain name
	@Field("UV802")
	private String uv802;//verification code
	@Field("UN803")
	private int un803;//verified status: 1 is Verified, other is Waiting
	@Transient
	private String nv100;
	@Transient 
	private String mv903;
		
	public String getNv100() {
		return nv100;
	}
	public void setNv100(String nv100) {
		this.nv100 = nv100;
	}
	public String getMv903() {
		return mv903;
	}
	public void setMv903(String mv903) {
		this.mv903 = mv903;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getUv801() {
		return uv801;
	}
	public void setUv801(String uv801) {
		this.uv801 = uv801;
	}
	public String getUv802() {
		return uv802;
	}
	public void setUv802(String uv802) {
		this.uv802 = uv802;
	}
	public int getUn803() {
		return un803;
	}
	public void setUn803(int un803) {
		this.un803 = un803;
	}
	public long getId() {
		return id;
	}
	public void setId(long newId) {
		this.id = newId;
	}
	
	public M900MG getUser(){
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
		return m900mg;
	}
	
	@Override
	public String toString() {
		return "U800MG [id=" + id + ", fo100=" + fo100 + ", uv801=" + uv801 + "]";
	}
	
}
